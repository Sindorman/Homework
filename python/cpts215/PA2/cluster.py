#!/usr/bin/python3

import pandas as pd
import math
import matplotlib.pyplot as plt
import random
import sys

class GeneSample:
    def __init__(self, list_of_genes):
        self.genes = list_of_genes

    def __str__(self):
        return str(self.genes)
    
    def __len__(self):
        return len(self.genes)

    def __eq__(self, other):
        return self.genes == other

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
        self.centroid = list()

    def append(self, sample):
        self.samples.append(sample)

    def pop(self):
        return self.samples.pop()

    def compute_centroid(self):
        self.centroid = list()
        x = 0
        while x < len(self.samples[0]):
            value = 0
            for s in self.samples:
                value += s.genes[x]
            self.centroid.append(round(value / len(self.samples), 5))
            x += 1

    def get_centroid(self):
        return self.centroid

def main():
    if len(sys.argv) < 3:
        print("Error! Please run script providing name of the files containing data and number of clusters!")
        exit(1)
    
    iris = pd.read_csv(sys.argv[1], delimiter = ',', index_col = 0, header=None)
    
    i = 0
    samples = list()
    for index, row in iris.iterrows():
        #print("Row {}, content: {}".format(index, row))
        samples.append(GeneSample(row.tolist()))

    clusters = list()
    while i < sys.argv[2]:
        cl = Cluster()
        element = random.choice(samples)
        samples.remove(element)
        cl.append(element)
        clusters.append()

        

    #print(iris)

    
    #plt.imshow(iris, cmap='hot', interpolation='nearest')
    #plt.show()
 
    
if __name__ == "__main__":
    main()