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
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None
    
    def __lt__(self, other):
        return self.data < other.data

class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None
        self.size = 0
    
    def get_node(self, index):
        current = self.head
        for i in range(index):
            current = current.next
            if current == self.head:
                return None
        return current
    
    def append(self, new_node):
        if self.head is None:
            self.head = new_node
            new_node.next = new_node
            new_node.prev = new_node
            self.size += 1
        else:
            end = self.get_node(self.size - 1)
            end.next = new_node
            new_node.prev = end
            new_node.next = self.head
            self.size += 1
    
    def add(self, new_node):
        self.append(new_node)
        self.head = new_node
        self.size += 1
    
    def remove(self, node):
        if self.head.next == self.head:
            self.head = None
        else:
            node.prev.next = node.next
            node.next.prev = node.prev
            if self.head == node:
                self.head = node.next
            self.size -= 1
    
    def pop(self):
        end = self.get_node(self.size - 1)
        end.prev.next = self.head
        end.prev = None
        end.next = None
        self.size -= 1
        return end
    
    def print(self):
        if self.head is None:
            return
        current = self.head 
        while True:
            print(current.data, end = ' ')
            current = current.next
            if current == self.head:
                break
    
    def __len__(self):
        return self.size


def main():
    '''
    Main funtion definition
    '''
    '''
    if len(sys.argv) < 3:
        print("Error! Please run script providing name of the files containing data and number of clusters!")
        exit(1)
    try:
        k = int(sys.argv[2])
    except ValueError:
        print("Error! That is not an number!")
        exit(1)
    '''
    cir_list = CircularDoublyLinkedList()
    for i in range(1, 5001):
        new_node = Node(i)
        cir_list.append(new_node)
    cir_list.print()

    for i in range(2500):
        cir_list.pop()
    cir_list.print()

    
if __name__ == "__main__":
    main()