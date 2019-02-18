#!/usr/bin/python3

import pandas as pd
import math
import matplotlib
import sys

class GeneSample:
    def __init__(self, list_of_genes):
        self.genes = list_of_genes

    def __str__(self):
        return str(self.genes)
    
    def __len__(self):
        return len(self.genes)

    def dist(self, other):

        if len(self.genes) != len(other):
            return None

        index = 0
        dist = 0
        while index < len(other):
            dist += (other[index] - self.genes[index])**2
            index += 1
        return math.sqrt(dist)

class Cluster:
    def __init__(self):
        self.samples = list()

def main():
    if len(sys.argv) < 2:
        print("Error! Please run script providing name of the files containing data!")
        exit(1)
    
if __name__ == "__main__":
    main()