#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 455
Homework #3

Description: HW3.
'''

#import socket module
import sys
from socket import *
from requests import get

def main(port : int):
    # get external IP
    ip = get('https://api.ipify.org').text

    serverSocket = socket(AF_INET, SOCK_STREAM)
    #Prepare a sever socket
    serverSocket.bind(('', port))
    serverSocket.listen(1)

    while True:

        #Establish the connection
        print('Ready to serve at address http://{}:{} ...'.format(ip, port))

        # accept connections from outside
        (connectionSocket, addr) = serverSocket.accept()

        try:
            message = connectionSocket.recv(1024).decode()
            filename = message.split()[1]
            f = open(filename[1:])
            outputdata = f.read()
            
            #Send one HTTP header line into socket
            connectionSocket.send('HTTP/1.1 200 OK\r\n'.encode())
            #Send the content of the requested file to the client
            for i in range(0, len(outputdata)):
                connectionSocket.send(outputdata[i].encode())

            connectionSocket.send(("\r\n").encode())
            connectionSocket.close()
        except IOError:
            print("file {} was not found!".format(filename[1:]))
            connectionSocket.send('HTTP/1.1 404 NOT FOUND\r\n'.encode())
            connectionSocket.close()
            break

if __name__ == "__main__":
    port = 6789
    if (len(sys.argv) >= 2):
        try:
            port = int(sys.argv[1])
        except ValueError:
            print("Error: {} is not a valid port number".format(sys.argv[1]))
            print("Please provide valid port number as an integer that is greater than 1200")
            exit(1)
    main(port)