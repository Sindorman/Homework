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
import random

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
            end = self.head.prev
            end.next = new_node
            new_node.prev = end
            new_node.next = self.head
            self.head.prev = new_node
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
        if self.size is 0:
            return None
        end = self.head.prev
        end.prev.next = self.head
        self.head.prev = end.prev
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

    def bubble_sort(self):
        temp_one = self.head
        temp_two = temp_one.next
        index = 0
        while index < self.size:
            swapped = False
            while temp_two is not temp_one:
                if temp_two.data > temp_two.next.data:
                    temp_data = temp_two.next.data
                    temp_two.next.data = temp_two.data
                    temp_two.data = temp_data
                    swapped = True
                temp_two = temp_two.next
            if swapped is False:
                return
            index += 1
            temp_one = temp_one.next
            temp_two = temp_one.next


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
    
    cir_list = CircularDoublyLinkedList()
    for i in range(1, 5001):
        new_node = Node(i)
        cir_list.append(new_node)
    cir_list.print()

    for i in range(2500):
        cir_list.pop()
    cir_list.print()
    '''
    index = 1
    cir_list2 = CircularDoublyLinkedList()
    while index is not 301:
        new_node = Node(random.randint(1, 301))
        cir_list2.append(new_node)
        index += 1
    cir_list2.print()
    cir_list2.bubble_sort()
    print('\n')
    cir_list2.print()
    
if __name__ == "__main__":
    main()