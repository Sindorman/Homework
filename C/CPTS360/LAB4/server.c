// This is the echo SERVER server.c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netdb.h>

#define MAX 256
#define SERVER_HOST "localhost"
#define SERVER_IP "127.0.0.1"
#define SERVER_PORT 1234

#define die(e) do { fprintf(stderr, "%s\n", e); exit(EXIT_FAILURE); } while (0);

// Define variables:
struct sockaddr_in server_addr, client_addr;
char *curdir;
int mysock, csock;
int r, len, n;


// Server initialization code:
int server_init()
{
    printf("==================== server init ======================\n"); 
    // get DOT name and IP address of this host
    printf("1 : create a TCP STREAM socket\n");
    mysock = socket(AF_INET, SOCK_STREAM, 0);
    printf("2 : fill server_addr with host IP and PORT# info\n");
    // initialize the server_addr structure
    server_addr.sin_family = AF_INET; // for TCP/IP
    server_addr.sin_addr.s_addr = htonl(INADDR_ANY); // THIS HOST IP address 
    server_addr.sin_port = htons(SERVER_PORT); // port number

    printf("3 : bind socket to server address\n");
    // bind syscall: bind the socket to server_addr info
    r = bind(mysock,(struct sockaddr *)&server_addr, sizeof(server_addr));
    if (r < 0){
        printf("bind failed\n");
        exit(3);
    }
    printf("host = %s port = %d\n", SERVER_HOST, SERVER_PORT);
    printf("4 : server is listening ....\n");
    listen(mysock, 5); // listen queue length = 5
    printf("===================== init done =======================\n");
}


char* concat(const char *s1, const char *s2)
{
    char *result = malloc(strlen(s1) + strlen(s2) + 1);
    strcpy(result, s1);
    strcat(result, s2);
    return result;
}


char* execute(char *command, char *args)
{
    args[0] = concat("/bin/", command);
    char *beginning = concat("/bin/", command);
    char *buffer = malloc(MAX);
    pid_t pid;
    int pipefd[2];
    int status;
    if (pipe(pipefd)==-1)
        die("pipe");
    
    if ((pid = fork()) == -1)
        die("fork");

    if ( pid == 0 )
    {

        dup2(pipefd[1], STDOUT_FILENO); // send stdout to the pipe
        close(pipefd[0]);
        close(pipefd[1]);
        execv(beginning, args); // child: call execv with the path and the args
        die("execv");
    }
    else
    {
        close(pipefd[1]); // close the write end of the pipe in the parent
        int nbytes = read(pipefd[0], buffer, MAX);

        wait(NULL);
        return buffer;
    }
}


char* process_command(char *args)
{
    char args_copy[MAX];
    strcpy(args_copy, args);
    char *token = strtok(args_copy, " ");
    char *commands[MAX + 1];
    int counter = 0;
    while(token != NULL)
    {
        commands[counter] = token;
        token = strtok(NULL, " ");
        counter++;
    }
    char *command = commands[0];
    commands[counter + 1] = NULL;
    counter = 1;
    while(command != NULL || counter != 256)
    {
        if(strcmp(command, "pwd") == 0)
        {
            return curdir;
        }
        else
        {
            return execute(command, (char*)commands);
        }
        command = commands[counter + 1];
        counter++;
    }
}


int main(int argc, char *argv[])
{
    char line[MAX];
    server_init();
    curdir = getcwd(NULL, 0);
    printf("server: chroot to %s\n", curdir);
    chroot(curdir);
    while(1){ // Try to accept a client request
        printf("server: accepting new connection ....\n"); 


        // Try to accept a client connection as descriptor csock
        len = sizeof(client_addr);
        csock = accept(mysock, (struct sockaddr *)&client_addr, &len);
        if (csock < 0){
            printf("server: accept error\n");
            exit(1);
        }
        printf("server: accepted a client connection from\n");
        printf("-----------------------------------------------\n");
        printf("Client: IP=%s port=%d\n", "127.0.0.1",
        ntohs(client_addr.sin_port));
        printf("-----------------------------------------------\n");


        // Processing loop: csock <-- data --> client
        while(1)
        {
            n = read(csock, line, MAX);
            if (n==0)
            {
                printf("server: client died, server loops\n");
                close(csock);
                break;
            }
            // show the line string
            printf("server: read n=%d bytes; line=[%s]\n", n, line);
            char args_copy[MAX];
            strcpy(args_copy, line);
            char *token = strtok(args_copy, " ");
            char *commands[MAX + 1];
            int counter = 0;
            char *result;
            while(token != NULL)
            {
                commands[counter] = token;
                token = strtok(NULL, " ");
                counter++;
            }

            if (strcmp(commands[0], "put") == 0)
            {
                FILE *fp;
                n = read(csock, line, MAX);
                printf("server: read n=%d bytes; line=[%s]\n", n, line);
                fp = fopen(commands[1], "w");
                fprintf(fp, "%s", line);
                n = write(csock, commands[0], MAX);
                fclose(fp);


                printf("server: wrote n=%d bytes; ECHO=[%s]\n", n, result);
                printf("server: ready for next request\n");
                continue;
            }
            else if (strcmp(commands[0], "get") == 0)
            {
                char buffer[MAX];
                FILE *f;
                f=fopen(commands[1], "r");
                fscanf(f, "%s", buffer);
                n = write(csock, buffer, MAX);
                printf("server: wrote n=%d bytes; line=(%s)\n", n, buffer);
                fclose(f);
            }
            else
            {
                result = process_command((char*) line);
            }

            // process the line from clinet
            strcat(line, " ECHO");
            // send the echo line to client 
            n = write(csock, result, MAX);


            printf("server: wrote n=%d bytes; ECHO=[%s]\n", n, result);
            free(result);
            printf("server: ready for next request\n");
            }
        }
}
