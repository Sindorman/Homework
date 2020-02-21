#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
#include <fcntl.h>

char input[16][16];

char *ARGV[16];

char line[128];

char *command;

int myargc = 0;

int main(int argc, char *argv[], char *envp[]){

    char line_copy[128];
    char *path = getenv("PATH");
    printf("1. show HOME directory: HOME = %s\n", getenv("HOME"));
    printf("2. show PATH directory: PATH = %s\n", path);
    printf("3. decompose PATH into dir strings:\n");
    for( int i = 0; i < strlen(path); i++)
    {
        if(path[i] == ':')
        {
            printf(" ");
            continue;
        }
            
        printf("%c", path[i]);
    }
    printf("\n: *********** mbsh processing loop **********\n");

    while (1){

        printf("mbsh: ");
        fflush(stdout);

        fgets(line, 128, stdin);

        line[strlen(line) - 1] = 0;

        strcpy(line_copy, line);

        char *next = strtok(line_copy, " ");

        int i = 0;

        while (next != 0)
        {

            strcpy(input[i], next);

            next = strtok(NULL, " ");

            i++;
            myargc += 1;
        }

        execute_cmd();
        
        cleanup();
    }

    return 0;
}

void create_argv(char *destination[])
{
    int i = 0;

    while (input[i][0] != 0)
    {

        destination[i] = input[i];
        i++;
    }

    destination[i] = NULL;
}

void cleanup()
{
    for (int i = 0; i < 16; i++)
    {

        memset(input[i], 0, strlen(input[i]));
        
    }

    myargc = 0;
}

int check_redirect(char *argv[])
{

    int i = 0;
    char temp[16];

    while (argv[i])
    {
        strncpy(temp, argv[i], 16);

        if (strcmp(temp, "<") == 0)
        {

            strncpy(temp, argv[i + 1], 16);
            close(0);
            open(temp, O_RDONLY);

            return 1;
        }
        else if (strcmp(temp, ">") == 0)
        {

            strncpy(temp, argv[i + 1], 16);
            close(1);
            open(temp, O_WRONLY | O_CREAT, 0644);
            return 1;
        }
        else if (strcmp(temp, ">>") == 0)
        {

            strncpy(temp, argv[i + 1], 16);
            close(1);
            open(temp, O_WRONLY | O_CREAT | O_APPEND, 0644);
            return 1;
        }
        i++;
    }
    return -1;
}


int check_pipe_or_split(char *head[], char *tail[])
{
    int i = 0;
    while (ARGV[i])
    {
        const char *ptr = strchr(ARGV[i], ';');
        if (strcmp(ARGV[i], "|") == 0)
        {
            int j = 0;
            while (ARGV[j])
            {
                if (j < i)
                {
                    head[j] = ARGV[j];
                }
                else if (j > i)
                {
                    tail[j - 3] = ARGV[j];
                }
                j++;
            }
            return 1;
        } else if(ptr)
        {
            int index = ptr - ARGV[i];
            memcpy(ARGV[i], &ARGV[i][0], index - 1);
            ARGV[i][strlen(ARGV[i]) - 1] = '\0';
        }
        i++;
    }
    return 0;
}

int execute(char *myargv[]){
    char commandPath[16] = "/bin/";
    
    strcat(commandPath, myargv[0]);

    if (check_redirect(myargv) != -1)
    {
        myargv[myargc - 2] = NULL;
    }

    int success = execve(commandPath, myargv, NULL);

    if (success == -1)
    {
        printf("%s\n", strerror(errno));
    }
}

int fork_child()
{

    int pid, status;

    pid = fork();
    printf("parent %s PROC %d forks a child process %d\n", getlogin(), getpid(), pid);

    if (pid > 0)
    {            
        printf("parent %c PROC %d waits\n", geteuid(), getpid());
        pid = wait(&status);
        printf("child PROC %d died, exit status = %04x\n", pid, status);
    }
    else if (pid == 0)
    {
        
        char *head[16], *tail[16];

        if (check_pipe_or_split(head, tail) == 1)
        {
            // Actually pipe
            int pd[2], _pid;

            pipe(pd);

            _pid = fork();
            if (_pid)
            {
                close(pd[0]);

                close(1);
                dup(pd[1]);
                close(pd[1]);
                execute(head);
            }
            else
            {
                close(pd[1]);

                close(0);
                dup(pd[0]);
                close(pd[0]);
                execute(tail);
            }

        }else{

            execute(ARGV);
        }

        exit(0);

    }
    else
    { 
        printf("Error: Unable to create process\n");
    }
}

void execute_cmd(void){
    create_argv(ARGV);

    if (strcmp(input[0], "exit") == 0)
    {
        exit(1);
    }
    else if (strcmp(input[0], "cd") == 0)
    {
        char *home;
        int success = -1;

        if (strcmp(input[1], "\0") == 0)
        {

            home = getenv("HOME");
            success = chdir(home);

        } else {
            success = chdir(input[1]);
        }
        if (success == 0)
        {
            printf("cd to %s successful\n", (strcmp(input[1], "\0") == 0) ? "$HOME" : input[1]);
        }
        else
        {

            printf("cd to %s failed\n", input[1]);
        }

        char cwd[256];
        getcwd(cwd, sizeof(cwd));
        printf("cwd is: %s\n", cwd);
    }
    else
    {
        fork_child();
    }
}