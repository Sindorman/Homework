#!/usr/bin/python3

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019
Programming Project #2
2/17/19

Description: This program scanns CSV file of data and does k-means clustering on it, with plotting it. Couldn't finish on time.
'''

import pandas as pd
import math
import matplotlib.pyplot as plt
import random
import sys

class GeneSample:
    '''
    A class representing a sample. 
    Sample has list of data.
    '''

    def __init__(self, list_of_genes):
        '''
        Takes list of data points and stores it.
        Parameter list: list that contains data points.
        '''
        self.genes = list_of_genes

    def __str__(self):
        '''
        Returns string representation of data list.
        Returns: string representation of data points.
        '''
        return str(self.genes)
    
    def __len__(self):
        '''
        Returns length of data list.
        Returns: length of data list as an integer.
        '''
        return len(self.genes)

    def __eq__(self, other):
        '''
        Used to compare two data points.
        Parameter list: list that contains data points.
        Returns: boolean if they are equal.
        '''
        return self.genes == other

    def dist(self, other):
        '''
        Computes Euclidean distance between two lists.
        Parameter list: list that contains data points.
        Returns: distance as float.
        '''
        if other is None:
            return None

        if len(self.genes) != len(other):
            return None

        index = 0
        dist = 0
        while index < len(other):
            dist += (other[index] - self.genes[index])**2
            index += 1
        return math.sqrt(dist)

class Cluster:
    '''
    A class representing a cluster of samples. 
    Cluster has list of samples and centroid.
    '''
    def __init__(self):
        '''
        Initializes Cluster.
        '''
        self.samples = list()
        self.centroid = list()

    def append(self, sample):
        '''
        Appending to the cluster
        Parameter list: sample object that contains data points.
        '''
        self.samples.append(sample)

    def pop(self):
        '''
        Popping samples
        Returns: sample object.
        '''
        return self.samples.pop()
    
    def empty(self):
        '''
        Empties sample list
        '''
        self.samples = list()

    def compute_centroid(self):
        '''
        Computes centroid of the cluster.
        '''
        if len(self.samples) is 0:
            return None
        self.centroid = list()
        x = 0
        while x < len(self.samples[0]):
            value = 0
            for s in self.samples:
                value += s.genes[x]
            self.centroid.append(round(value / len(self.samples), 2))
            x += 1

    def get_centroid(self):
        '''
        Returns centroid of the cluster
        Returns: centroid object as a list.
        '''
        return self.centroid

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
    iris = pd.read_csv(sys.argv[1], delimiter = ',', index_col = 0, header=None)
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
    temp_samples = list()

    # Filling in the rest of samples
    while len(samples) is not 0:
        for cl in clusters:
            cl = random.choice(clusters)
            element = random.choice(samples)
            samples.remove(element)
            temp_samples.append(element)
            cl.append(element)

    samples.extend(temp_samples)
    del temp_samples

    old_clusters = list()
    done = False
    iteration = 0
    # Whole process of clustering
    while done is False:
        #if iteration is 3:
        #    exit(0)
        print("Iteration: {}".format(iteration))
        old_clusters = clusters.copy()
        for cl in clusters:
            print("length of cluster: {}".format(len(cl.samples)))
            cl.compute_centroid()
            cl.empty()

        index = 0
        distance = math.inf

        # comparing to centroids and moving to the right cluster
        for s in samples:
            for cl in clusters:
                d = s.dist(cl.get_centroid())
                print("d: {}".format(d))
                if d is None:
                    continue
                if d < distance:
                    distance = d
                    index = clusters.index(cl)
            clusters[index].append(s)
        
        for cl in clusters:
            print("length of cluster: {}".format(len(cl.samples)))

        # Checking if old clusters samples moved or not
        index = 0
        while index < len(old_clusters):
            for s in old_clusters[index].samples:
                if s in clusters[index].samples:
                    done = True
                else:
                    done = False
                    iteration += 1
                    continue
            index += 1
        iteration += 1
    #print(iris)
    plt.imshow(iris, cmap='seismic', interpolation='nearest')
    plt.show()
 
    
if __name__ == "__main__":
    main()