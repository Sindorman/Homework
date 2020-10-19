#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 455
Homework #3

Description: HW3 TCP client.
'''

from socket import *
import sys

def main(host, port, filename):
    serverName = 'localhost'
    serverPort = 12000
    clientSocket = socket(AF_INET, SOCK_STREAM)
    clientSocket.connect((host, port))
    clientSocket.send("GET /{} HTTP/1.1 \n Host: {}:{}".format(filename, host_name, port).encode())
    response = clientSocket.recv(1024).decode()

    print("Server responded with:\n{}".format(response))
    content = ""
    if response == 'HTTP/1.1 200 OK\r\n':
        while response != '\r\n':
            response = clientSocket.recv(1024).decode()
            content = content + response

    print(content)
    clientSocket.close()

    return

if __name__ == "__main__":
    port = 6789
    if (len(sys.argv) < 4):
        print("Error, please provide host_name, port and filename arguments")
        exit(0)

    host_name = sys.argv[1]

    try:
        port = int(sys.argv[2])
    except ValueError:
        print("Error: {} is not a valid port number".format(sys.argv[2]))
        print("Please provide valid port number as an integer that is greater than 1200")
        exit(1)

    filename = sys.argv[3]

    main(host_name, port, filename)


