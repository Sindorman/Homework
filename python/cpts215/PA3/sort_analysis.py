#!/usr/bin/python3

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019
Programming Project #3
2/17/19

Description: This program Contains Double Circular Linked List class implementation.
             It also contains selection sort, bubble sort, merge sort for it. It generates 3 types of lists with configures amount of data,
             runs different sorting algorithms and compares their metrics, outputting to CSV file and graphing.
'''

import pandas as pd
import math
import matplotlib.pyplot as plt
import sys
import random
import time
import numpy as np

class Node:
    '''
    A class representing a node. 
    Node has data, and two pointers.
    '''
    def __init__(self, data):
        '''
        Creates new node with given data and None pointers.
        Parameter list: data point.
        '''
        self.data = data
        self.prev = None
        self.next = None
    
    def __lt__(self, other):
        '''
        Method to compare current data point to another.
        Parameter list: another data point.
        '''
        return self.data < other.data

class CircularDoublyLinkedList:
    '''
    A class representing Double Circualr Linked List. 
    Class has pointer which represents head and size of the current list.
    '''
    def __init__(self):
        '''
        Creates new empty list.
        '''
        self.head = None
        self.size = 0
    
    def get_node(self, index):
        '''
        Method of getting a node at given point.
        Parameter list: index as an integer.
        returns: Node at the given index
        '''
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
        '''
        Method to append to the end of the list.
        Parameter list: New node.
        '''
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
        '''
        Method to add to the front of the list.
        Parameter list: New node.
        '''
        self.append(new_node)
        self.head = new_node
        self.size += 1
    
    def remove(self, node):
        '''
        Method to remove specific node from the list.
        Parameter list: specific node.
        '''
        if self.head.next == self.head:
            self.head = None
        else:
            node.prev.next = node.next
            node.next.prev = node.prev
            if self.head == node:
                self.head = node.next
            self.size -= 1

    def pop(self):
        '''
        Method to pop the last element from the list. Removes the node
        returns: node at the end of the list
        '''
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
        '''
        Method to print the List
        '''
        if self.head is None:
            return
        current = self.head 
        while True:
            print(current.data, end = ' ')
            current = current.next
            if current == self.head:
                break

    def as_list(self):
        '''
        Method to get current Double list as regular Python list
        return: python list object
        '''
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
        '''
        Method of getting length of the list
        return: lenght of the list as an integer
        '''
        return self.size

    def create_list(self, number):
        '''
        Method of populating list of sorted elements
        Parameter list: number of elements to populate, as an integer.
        '''
        index = 1
        while index != number + 1:
            new_node = Node(index)
            self.append(new_node)
            index += 1

    def create_list_random(self, number):
        '''
        Method of populating list of random elements. Elements ranges from 1 to the given number.
        Parameter list: number of elements to populate, as an integer.
        '''
        index = 0
        while index != number:
            new_node = Node(random.randint(1, number))
            self.append(new_node)
            index += 1
    
    def create_list_descending(self, number):
        '''
        Method of populating list of descending sorted elements
        Parameter list: number of elements to populate, as an integer.
        '''
        while number > 0:
            new_node = Node(number)
            self.append(new_node)
            number -= 1

    def bubble_sort(self):
        '''
        Bubble sort algorithm. Sorts current list using early exit bubble sort.
        returns: list of metrics data
        '''
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
        '''
        Selection sort algorithm. Sorts current list using selection sort.
        returns: list of metrics data
        '''
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
        '''
        Merge sort algorithm. Sorts current list using merge sort.
        returns: list of metrics data
        '''

        # Get merged list(as singlular) and connect head to the tail.
        # comparison, loop control comp, assign, loop control assign
        data = [0, 0, 0, 0]
        self.head = self.merge_sort_helper(self.head, data)
        tail = self.get_node(self.size - 1)
        tail.next = self.head
        self.head.prev = tail
        return data

    def merge_sort_helper(self, start, data):
        '''
        Merge sort helper, for recursion.
        returns: pointer to the merged list
        '''
        if start == None or start.next == None:
            return start
        self.head.prev.next = None
        left, right = self.split_list(start, data)

        left  = self.merge_sort_helper(left, data)
        right = self.merge_sort_helper(right, data)

        return self.merge_lists(left, right, data)

    def split_list(self, node, data):
        '''
        Merge sort split list helper function.
        Parameter list: node at which to start the split. list of metrics
        returns: left and right split lists
        '''
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
        '''
        Merge sort helper function. Merges two lists.
        Parameters list: left pointer, right pointer, list of metrics
        returns: merged list starting from left pointer to the right pointer.
        '''
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

def time_fn( fn):
    '''
    Helpfer function to time specific function and recieve returns from the function.
    Parameters list: function pointer.
    returns: list containing returns of the function and time it took to run.
    '''
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
    Definition of main function. Calls all data, gets it. Dumps data to the CSV and graphs it.
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

    data_bubble.to_csv("bubble_sort_results.csv", index=False)
    data_merge.to_csv("merge_sort_results.csv", index=False)
    data_selected.to_csv("selection_sort_results.csv", index=False)

    make_graph("Sorted", "Total_Operations", data_selected["Total"].tolist()[0:4], data_merge["Total"].tolist()[0:4], data_bubble["Total"].tolist()[0:4])
    make_graph("Descending", "Total_Operations", data_selected["Total"].tolist()[4:8], data_merge["Total"].tolist()[4:8], data_bubble["Total"].tolist()[4:8])
    make_graph("Random", "Total_Operations", data_selected["Total"].tolist()[8:], data_merge["Total"].tolist()[8:], data_bubble["Total"].tolist()[8:])
    make_graph("Sorted", "Seconds", data_selected["Seconds"].tolist()[0:4], data_merge["Seconds"].tolist()[0:4], data_bubble["Seconds"].tolist()[0:4])
    make_graph("Descending", "Seconds", data_selected["Seconds"].tolist()[4:8], data_merge["Seconds"].tolist()[4:8], data_bubble["Seconds"].tolist()[4:8])
    make_graph("Random", "Seconds", data_selected["Seconds"].tolist()[8:], data_merge["Seconds"].tolist()[8:], data_bubble["Seconds"].tolist()[8:])

def make_graph(name, metrics, data_s, data_m, data_b):
    '''
    Function that graphs data and saves the graph as PNG.
    Parameters list: name of the graph as string. Name of the metrics as string. Data for selection sort. Data for merge sort. Data for bubble sort.
    '''

    s1 = pd.Series(data_s, index=[500, 1000, 5000, 10000], name="selection sort")
    s2 = pd.Series(data_m, index=[500, 1000, 5000, 10000], name="merge sort")
    s3 = pd.Series(data_b, index=[500, 1000, 5000, 10000], name="bubble_sort")
    sers = [s1, s2, s3]

    x_locs = np.arange(1, 5)
    x_labels = [500, 1000, 5000, 10000]
    f, ax = plt.subplots()
    ax.set_title(name)
    ax.set_ylabel(metrics)
    ax.set_xlabel("List size N")
    ax.set_xticks(x_locs)
    ax.set_xticklabels(x_labels)
    for ser in sers:
        plt.plot(x_locs, ser, label=ser.name)
    plt.legend(loc=0)
    plt.savefig(r"{}.png".format(name + "_" + metrics))
    plt.show()

if __name__ == "__main__":
    main()