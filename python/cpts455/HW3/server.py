#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 455
Homework #3

Description: HW3 TCP server.
'''

#import socket module
import sys
import threading
from socket import *

def handle_client(connectionSocket, address):
    while True:
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

            f.close()
            connectionSocket.send(("\r\n").encode())
            connectionSocket.close()

        except IOError:
            print("File {} was not found!".format(filename[1:]))
            try:
                connectionSocket.send('HTTP/1.1 404 NOT FOUND\r\n'.encode())
                connectionSocket.close()
            except:
                print("socket connection closed.")

            return


def main(port : int):
    # get external IP

    serverSocket = socket(AF_INET, SOCK_STREAM)
    #Prepare a sever socket
    serverSocket.bind(('', port))
    serverSocket.listen(5)

    while True:

        #Establish the connection
        print('Ready to serve at address http://localhost:{} ...'.format(port))

        # accept connections from outside
        (connectionSocket, addr) = serverSocket.accept()
        threading._start_new_thread(handle_client, (connectionSocket, addr))

    serverSocket.close()

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