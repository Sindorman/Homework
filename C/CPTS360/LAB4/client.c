// The echo client client.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netdb.h>


#define MAX 256
#define SERVER_HOST "localhost"
#define SERVER_IP "127.0.0.1"
#define SERVER_PORT 1235

// Define variables
struct sockaddr_in server_addr; 
int sock, r;
char *curdir;


// clinet initialization code
int client_init()
{
    printf("======= clinet init ==========\n");
    printf("1 : create a TCP socket\n");
    sock = socket(AF_INET, SOCK_STREAM, 0);
    if (sock<0){
        printf("socket call failed\n");
        exit(1);
    }


    printf("2 : fill server_addr with server's IP and PORT#\n");
    server_addr.sin_family = AF_INET;
    //server_addr.sin_addr.s_addr = htonl((127<<24) + 1);
    server_addr.sin_addr.s_addr = htons(INADDR_ANY);
    server_addr.sin_port = htons(SERVER_PORT);


    // Connect to server
    printf("3 : connecting to server ....\n");
    r = connect(sock,(struct sockaddr *)&server_addr, sizeof(server_addr));
    if (r < 0){
        printf("connect failed\n");
        exit(3);
    }
    printf("4 : connected OK to\n"); 
    printf("---------------------------------------------------------\n");
    printf("hostname=%s PORT=%d\n", SERVER_HOST, SERVER_PORT);
    printf("---------------------------------------------------------\n");
    printf("========= init done ==========\n");
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
    char strip[MAX];
    memcpy(strip, &command[1], MAX - 1);
    strip[MAX - 1] = '\0';
    char *beginning = concat("/bin/", strip);
    char *passing[] = {beginning, NULL};
    int status;
    if ( fork() == 0 )
    {
        execv(passing[0], passing); // child: call execv with the path and the args
    }
    else
    {
        wait(& status);
    }
}


char* process_command(char *args)
{
    char args_copy[MAX];
    strcpy(args_copy, args);
    char *token = strtok(args_copy, " ");
    char *commands[256];
    int counter = 0;
    while(token != NULL)
    {
        commands[counter] = token;
        token = strtok(NULL, " ");
        counter++;
    }
    char *command = commands[0];
    counter = 1;
    while(command != NULL || counter != 256)
    {
        if(strcmp(command, "lpwd") == 0)
        {
            return curdir;
        }
        else
        {
            execute(command, (char*) args_copy);
        }
        command = commands[counter + 1];
        counter++;
    }
}


int main(int argc, char *argv[ ])
{
    int n;
    char line[MAX], ans[MAX];
    curdir = getcwd(NULL, 0);
    printf("client: chroot to %s\n", curdir);
    chroot(curdir);


    client_init();
    printf("---------------------------------------------------------\n");
    printf("Available commands: get put ls cd pwd mkdir rmdir rm \nlcat lls lcd lpwd lmkdir lrmdir lrm\n");
    printf("---------------------------------------------------------\n");


    printf("******** processing loop *********\n");
    while (1)
    {
        printf("input a line : ");
        bzero(line, MAX); // zero out line[ ]
        fgets(line, MAX, stdin); // get a line (end with \n) from stdin
        line[strlen(line)-1] = 0; // kill \n at end
        if (line[0]==0) // exit if NULL line
            exit(0);


        // Send ENTIRE line to server
        if(line[0] == 'l' && line[1] != 's')
        {
            char *result = process_command((char*) line);
            //if(result != NULL)
                //printf("Executing local command: %s\n", result);
            continue;
        }
        else
        {
            n = write(sock, line, MAX);
            printf("client: wrote n=%d bytes; line=(%s)\n", n, line);


            // Read a line from sock and show it
            n = read(sock, ans, MAX);
            printf("client: read n=%d bytes; echo=(%s)\n", n, ans);
        }
    }
}
