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
import time

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
        if index >= self.size:
            return None
        for i in range(index):
            if current.next is None:
                return current
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

    def as_list(self):
        if self.head is None:
            return list()
        current = self.head
        l = list()
        while True:
            l.append(current.data)
            current = current.next
            if current == self.head:
                break
        return l

    def __len__(self):
        return self.size

    def create_list(self, number):
        index = 1
        while index != number + 1:
            new_node = Node(index)
            self.append(new_node)
            index += 1

    def create_list_random(self, number):
        index = 0
        while index != number:
            new_node = Node(random.randint(1, number))
            self.append(new_node)
            index += 1
    
    def create_list_descending(self, number):
        while number > 0:
            new_node = Node(number)
            self.append(new_node)
            number -= 1

    def bubble_sort(self):
        index = 0
        data = [0, 0, 0 ,0]
        while index < self.size:
            data[1] = data[1] + 1
            swapped = False
            temp_one = self.head
            data[3] = data[3] + 1
            while temp_one.next is not self.head:
                data[1] = data[1] + 1
                if temp_one.data > temp_one.next.data:
                    temp_data = temp_one.next.data
                    temp_one.next.data = temp_one.data
                    temp_one.data = temp_data
                    swapped = True
                    data[2] = data[2] + 3
                temp_one = temp_one.next
                data[3] = data[3] + 1
                data[0] = data[0] + 1
            if swapped is False:
                return data
            data[0] = data[0] + 1
            index += 1
            data[3] = data[3] + 1
        return data
    
    def selection_sort(self):
        index = 0
        minimal = None
        temp_one = self.head
        data = [0, 0, 0 ,0]
        while index < self.size:
            data[1] = data[1] + 1
            minimal = temp_one
            temp_two = temp_one.next
            data[3] = data[3] + 2
            while temp_two is not self.head:
                data[1] = data[1] + 1
                if temp_two.data < minimal.data:
                    minimal = temp_two
                temp_two = temp_two.next
                data[3] = data[3] + 1
                data[0] = data[0] + 1
            temp_data = minimal.data
            minimal.data = temp_one.data
            temp_one.data = temp_data
            data[2] = data[2] + 3
            index += 1
            data[3] = data[3] + 1
            temp_one = temp_one.next
            data[3] = data[3] + 1
        return data
        
    def merge_sort(self):

        # Get merged list(as singlular) and connect head to the tail.
        # comparison, loop control comp, assign, loop control assign
        data = [0, 0, 0, 0]
        self.head = self.merge_sort_helper(self.head, data)
        tail = self.get_node(self.size - 1)
        tail.next = self.head
        self.head.prev = tail
        return data

    def merge_sort_helper(self, start, data):
        if start == None or start.next == None:
            return start
        self.head.prev.next = None
        left, right = self.split_list(start, data)

        left  = self.merge_sort_helper(left, data)
        right = self.merge_sort_helper(right, data)

        return self.merge_lists(left, right, data)

    def split_list(self, node, data):
        if node == None or node.next == None:
            left = node
            right = None

            return left, right

        else:
            middle = node
            front = node.next

            while front != None:
                data[1] = data[1] + 1
                front = front.next
                data[3] = data[3] + 1

                if front != None:
                    front = front.next
                    middle = middle.next
                    data[2] = data[2] + 1
                data[0] = data[0] + 1
        data[0] = data[0] + 1

        left = node
        right = middle.next
        middle.next = None
        data[2] = data[2] + 1

        return left, right

    def merge_lists(self, left, right, data):
        temp_head = Node(None)
        current = temp_head
        data[2] = data[2] + 1

        while left and right:
            if left.data < right.data:
                current.next = left
                left.prev = current
                left = left.next
                data[2] = data[2] + 3

            else:
                current.next = right
                right.prev = current
                right = right.next
                data[2] = data[2] + 3
            data[0] = data[0] + 1
            data[1] = data[1] + 1

            current = current.next
            data[3] = data[3] + 1

        if left == None:
            current.next = right
            data[2] = data[2] + 1

        elif right == None:
            current.next = left
            data[2] = data[2] + 1

        return temp_head.next

import time

def time_fn( fn):
    start = time.clock()
    results = fn()
    end = time.clock()
    total = 0
    for t in results:
        total += t
    results.append(total)
    return [results, end-start]

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
    factor = 1
    conf = [500 * factor, 1000 * factor, 5000 * factor, 10000 * factor]
    selection_list = []
    bubble_list = []
    merge_list = []
    # Sorted
    for c in conf:
        cir_list = CircularDoublyLinkedList()
        cir_list.create_list(c)
        temp_d = time_fn(cir_list.bubble_sort)
        temp_d[0].insert(0, "Sorted N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        bubble_list.append(temp_d[0])

        cir_list = CircularDoublyLinkedList()
        cir_list.create_list(c)
        temp_d = time_fn(cir_list.selection_sort)
        temp_d[0].insert(0, "Sorted N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        selection_list.append(temp_d[0])

        cir_list = CircularDoublyLinkedList()
        cir_list.create_list(c)
        temp_d = time_fn(cir_list.merge_sort)
        temp_d[0].insert(0, "Sorted N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        merge_list.append(temp_d[0])
    
    # Descending
    for c in conf:
        cir_list = CircularDoublyLinkedList()
        cir_list.create_list_descending(c)
        temp_d = time_fn(cir_list.bubble_sort)
        temp_d[0].insert(0, "Descending Sorted N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        bubble_list.append(temp_d[0])

        cir_list = CircularDoublyLinkedList()
        cir_list.create_list_descending(c)
        temp_d = time_fn(cir_list.selection_sort)
        temp_d[0].insert(0, "Descending Sorted N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        selection_list.append(temp_d[0])

        cir_list = CircularDoublyLinkedList()
        cir_list.create_list_descending(c)
        temp_d = time_fn(cir_list.merge_sort)
        temp_d[0].insert(0, "Descending Sorted N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        merge_list.append(temp_d[0])
    # Random
    for c in conf:
        cir_list = CircularDoublyLinkedList()
        cir_list.create_list_random(c)
        temp_d = time_fn(cir_list.bubble_sort)
        temp_d[0].insert(0, "Random N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        bubble_list.append(temp_d[0])

        cir_list = CircularDoublyLinkedList()
        cir_list.create_list_random(c)
        temp_d = time_fn(cir_list.selection_sort)
        temp_d[0].insert(0, "Random N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        selection_list.append(temp_d[0])

        cir_list = CircularDoublyLinkedList()
        cir_list.create_list_random(c)
        temp_d = time_fn(cir_list.merge_sort)
        temp_d[0].insert(0, "Random N={}".format(c))
        temp_d[0].insert(1, temp_d[1])
        merge_list.append(temp_d[0])

        data_selected = pd.DataFrame(selection_list, columns=["List configuration", "Seconds", "# Data", "# Loop", "# Data assignments", "# Loop assignments", "Total"])
        data_merge = pd.DataFrame(merge_list, columns=["List configuration", "Seconds", "# Data", "# Loop", "# Data assignments", "# Loop assignments", "Total"])
        data_bubble = pd.DataFrame(bubble_list, columns=["List configuration", "Seconds", "# Data", "# Loop", "# Data assignments", "# Loop assignments", "Total"])

        data_bubble.to_csv("bubble_sort.csv", index=False)
        data_merge.to_csv("merge_sort.csv", index=False)
        data_selected.to_csv("selection_sort.csv", index=False)

        
        #print(data)

    
if __name__ == "__main__":
    main()