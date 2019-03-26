#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019 03/24
Programming Project #4
03/24/19

Description: This program contains DictEntry and WordPredictor classes that are use for predictive keyboard.
             This program also has interaction that providie training and building predictive program and interfacing with it. Require N top matches to be inputted as argument.
'''
import re
import sys
from map_hash import Map
import timeit

class DictEntry:
    '''
    A class representing a Dictionary entry. 
    Store word as string and probability as float.
    '''
    def __init__(self, word, prob):
        '''
        Creates new entry with given word and probability.
        Parameter list: word as string and probability as float.
        '''
        self.word = word
        self.prob = prob

    def get_word(self):
        '''
        used to get word.
        returns: word as string
        '''
        return self.word

    def get_prob(self):
        '''
        used to get probability.
        returns: probability as float
        '''
        return self.prob
    
    def __str__(self):
        '''
        used to get output as string.
        returns: word as string.
        '''
        return self.word
    
    def __eq__(self, other):
        '''
        Class for comparing one entry to another
        Parameter list: dictionary entry.
        '''
        if type(other) is int:
            return False
        return self.word == other.word

class WordPredictor:
    '''
    A class representing a Word Predictor. 
    Store words as associative Map and prefixes as associative Map.
    '''
    def __init__(self, size=20011):
        '''
        Creates new Word processor.
        Parameter list: size of maps to create.
        '''
        self.word_to_count = Map(size)
        self.prefix_to_entry = Map(size)
        self.total = 0


    def train(self, training_file):
        '''
        Trains on words from a file. Stores all words into word map and how many times given word was seen. Returns error if file was not found.
        Parameter list: file name as a string.
        '''
        try:
            f = open(training_file, "r")
            for line in f:
                for word in line.split():
                    self.train_word(word)
            f.close()

        except FileNotFoundError:
            print("Could not open training file: {}".format(training_file))
            return

    def train_word(self, word):
        '''
        Trains on a single word. Used mostly by train()
        Parameter list: word as string.
        '''
        cleanString = (re.sub(r'\W+','', word )).lower()
        if self.word_to_count.get(cleanString ) == -1:
            self.word_to_count.put(cleanString, 1)
        else:
            value = self.word_to_count.get(cleanString )
            self.word_to_count.put(cleanString, value + 1)
        self.total += 1

    def build(self):
        '''
        Iterates all words and build their predicitions and prefexis storing in prefix Map. Basic version.
        '''
        for t in self.word_to_count.slots:
            if t is None:
                continue
            for key in t:
                if key is None:
                    continue
                prefix = ""
                dictE = DictEntry(key.key, key.value / self.total)
                for c in key.key:
                    prefix += c
                    if self.prefix_to_entry.get(prefix) == -1:
                        self.prefix_to_entry.put(prefix, dictE)
                    elif self.prefix_to_entry.get(prefix).get_prob() < dictE.get_prob():
                        self.prefix_to_entry.put(prefix, dictE)
    
    def build_n(self):
        '''
        Iterates all words and build their predicitions and prefexis storing in prefix Map. Advanced version. Stores all word posibilities for a prefix in descending order to get top N.
        '''
        for t in self.word_to_count.slots:
            if t is None:
                continue
            for key in t:
                if key is None:
                    continue
                prefix = ""
                dictE = DictEntry(key.key, key.value / self.total)
                for c in key.key:
                    prefix += c
                    if self.prefix_to_entry.get(prefix) == -1:
                        self.prefix_to_entry.put(prefix, [dictE])
                    else:
                        item = self.prefix_to_entry.get(prefix)
                        if item[0].get_prob() < dictE.get_prob():
                            item.insert(0, dictE)
                        elif item.count(dictE) > 0 and item[item.index()].get_prob() < dictE.get_prob():
                            item[item.index()] = dictE
                        else:
                            item.append(dictE)
                        self.prefix_to_entry.put(prefix, item)

    def get_word_count(self, word):
        '''
        Method of getting number to times we saw a single word.
        Parameter list: word as string.
        returns: value of how many times we saw a word as int.
        '''
        if self.word_to_count.get(word) == -1:
            return 0
        else:
            return self.word_to_count.get(word)

    def get_best(self, prefix):
        '''
        Method of getting best single match for a given prefix.
        Parameter list: prefix as a string.
        returns: word as a string.
        '''
        if self.prefix_to_entry.get(prefix) == -1:
            return DictEntry("null", 0)
        else:
            return self.prefix_to_entry.get(prefix)
    
    def get_best_n(self, prefix):
        '''
        Method of getting all matches for a given prefix.
        Parameter list: prefix as a string.
        returns: list of Dict entries in descending order.
        '''
        if self.prefix_to_entry.get(prefix) == -1:
            return [DictEntry("null", 0)]
        else:
            return self.prefix_to_entry.get(prefix)

    def get_training_count(self):
        '''
        Method of accessing total number of words we have.
        returns: words count as int.
        '''
        return self.total

def main():
    '''
    Definition of main function. Has interface to interact with program.
    '''
    if len(sys.argv) < 2:
        print("Error! Please run giving N top matches for prefix!")
        exit(1)
    test = WordPredictor(794327)
    
    start = timeit.default_timer()
    inp = input("Train on mobydick.txt or wiki.txt? Type in whole file name with .txt\n")
    print("Training on: {}".format(inp))
    test.train(inp)
    #test.train_word("the")
    print("Total words: {}".format(test.get_training_count()))
    #print(test.word_to_count.print())
    test.build_n()
    stop = timeit.default_timer()
    elapsed = (stop - start)
    print("elapsed (s): %.6f" %(elapsed))
    inp = ""
    print("Please type in prefix got best match. Or type \"exit\" to exit the program.")
    while inp != "exit":
        inp = input("Prefix: ")
        if inp == "exit":
            break
        print("Best matches are: ")
        index = 0
        for t in test.get_best_n(inp.lower()):
            print("{}: {}\n".format(index, t.get_word()))
            index += 1
            if index == int(sys.argv[1]):
                break

if __name__ == "__main__":
    main()