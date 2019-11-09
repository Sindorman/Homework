#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 315
Programming Project #3

Description: TODO
             
'''

import timeit

vocab = []

start = timeit.default_timer()
# read out vocab
with open("traindata.txt", 'r') as f:
    for l in f:
        for w in l.split():
            if w.strip('\n') not in vocab:
                vocab.append(w.strip('\n'))

# clean our vocab
with open("stoplist.txt", 'r') as f:
    for l in f:
        l_n = l.strip('\n')
        if vocab.count(l_n):
            vocab.remove(l_n)

vocab = sorted(vocab)

stop = timeit.default_timer()
print("Preparing data took (s): %.6f" %(stop - start))
print("========================================\n")

print(vocab)

