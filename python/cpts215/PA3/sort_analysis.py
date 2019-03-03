#!/usr/bin/python3

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019
Programming Project #3
2/17/19

Description: TODO change this. This program scanns CSV file of data and does k-means clustering on it, with plotting it. Couldn't finish on time.
'''

import pandas as pd
import math
import matplotlib.pyplot as plt
import sys

class Node:
    def __init__(self):
        self.data = None
        self.prev = None
        self.next = None

class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None


def main():
    '''
    Main funtion definition
    '''
    if len(sys.argv) < 3:
        print("Error! Please run script providing name of the files containing data and number of clusters!")
        exit(1)
    try:
        k = int(sys.argv[2])
    except ValueError:
        print("Error! That is not an number!")
        exit(1)

    
if __name__ == "__main__":
    main()