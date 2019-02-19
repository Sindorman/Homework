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
    
    def empty(self):
        self.samples = list()

    def compute_centroid(self):
        if len(self.samples) is 0:
            return
        self.centroid = list()
        x = 0
        while x < len(self.samples[0]):
            value = 0
            for s in self.samples:
                value += s.genes[x]
            self.centroid.append(round(value / len(self.samples), 2))
            x += 1

    def get_centroid(self):
        return self.centroid

def main():
    if len(sys.argv) < 3:
        print("Error! Please run script providing name of the files containing data and number of clusters!")
        exit(1)
    try:
        k = int(sys.argv[2])
    except ValueError:
        print("Error! That is not an number!")
        exit(1)
    iris = pd.read_csv(sys.argv[1], delimiter = ',', index_col = 0, header=None)
    #plt.imshow(iris, cmap='seismic', interpolation='nearest')
    #plt.show()
    i = 0
    samples = list()
    for index, row in iris.iterrows():
        #print("Row {}, content: {}".format(index, row))
        samples.append(GeneSample(row.tolist()))

    clusters = list()
    temp_samples = list()

    # Initializing empty clusters
    while i < k:
        cl = Cluster()
        element = random.choice(samples)
        samples.remove(element)
        cl.append(element)
        clusters.append(cl)
        temp_samples.append(element)
        i += 1
    samples.extend(temp_samples)
    del temp_samples

    # Filling in the rest of samples
    while len(samples) is not 0:
        for cl in clusters:
            cl = random.choice(clusters)
            element = random.choice(samples)
            samples.remove(element)
            cl.append(element)

    old_clusters = list()
    done = False

    # Whole process of clustering
    while done is False:
        old_clusters = clusters.copy()
        for cl in clusters:
            cl.compute_centroid()
            cl.empty()

        index = 0
        distance = math.inf

        # comparing to centroids and moving to the right cluster
        for s in samples:
            for cl in clusters:
                d = s.dist(cl.get_centroid())
                if d < distance:
                    distance = d
                    index = clusters.index(cl)
            clusters[index].append(s)

        # Checking if old clusters samples moved or not
        index = 0
        while index < len(old_clusters):
            for s in old_clusters[index].samples:
                if s in clusters[index].samples:
                    done = True
                else:
                    done = False
            index += 1
    #print(iris)
 
    
if __name__ == "__main__":
    main()