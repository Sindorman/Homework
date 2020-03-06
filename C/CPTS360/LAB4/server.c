// This is the echo SERVER server.c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <netdb.h>

#define MAX 256
#define SERVER_HOST "localhost"
#define SERVER_IP "127.0.0.1"
#define SERVER_PORT 1234


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
    // printf("here");
    char *beginning = concat("/bin/", command);
    char *passing[] = {beginning, NULL};
    int pipefd[2];
    pipe(pipefd);
    int status;
    if ( fork() == 0 )
    {
        close(pipefd[0]);


        dup2(pipefd[1], 1); // send stdout to the pipe
        dup2(pipefd[1], 2); // send stderr to the pipe
        close(pipefd[1]);
        execv(passing[0], passing); // child: call execv with the path and the args
    }
    else
    {
        char buffer[1024];
        close(pipefd[1]); // close the write end of the pipe in the parent


        while (read(pipefd[0], buffer, sizeof(buffer)) != 0)
        {
        }
        return (char*) buffer;
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
        if(strcmp(command, "pwd") == 0)
        {
            return curdir;
        }
        else
        {
            return execute(command, (char*)args_copy);
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
            char *result = process_command((char*) line);


            // process the line from clinet
            strcat(line, " ECHO");
            // send the echo line to client 
            n = write(csock, result, MAX);


            printf("server: wrote n=%d bytes; ECHO=[%s]\n", n, line);
            printf("server: ready for next request\n");
            }
        }
}
