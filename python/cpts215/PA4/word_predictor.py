#!/usr/bin/python3
import re
import sys
from map_hash import Map
import timeit

class DictEntry:
    def __init__(self, word, prob):
        self.word = word
        self.prob = prob

    def get_word(self):
        return self.word

    def get_prob(self):
        return self.prob
    
    def __str__(self):
        return self.word
    
    def __eq__(self, other):
        if type(other) is int:
            return False
        return self.word == other.word

class WordPredictor:
    def __init__(self, size=20011):
        self.word_to_count = Map(size)
        self.prefix_to_entry = Map(size)
        self.total = 0


    def train(self, training_file):
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
        cleanString = (re.sub(r'\W+','', word )).lower()
        if self.word_to_count.get(cleanString ) == -1:
            self.word_to_count.put(cleanString, 1)
        else:
            value = self.word_to_count.get(cleanString )
            self.word_to_count.put(cleanString, value + 1)
        self.total += 1

    def build(self):
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
        if self.word_to_count.get(word) == -1:
            return 0
        else:
            return self.word_to_count.get(word)

    def get_best(self, prefix):
        if self.prefix_to_entry.get(prefix) == -1:
            return DictEntry("null", 0)
        else:
            return self.prefix_to_entry.get(prefix)
    
    def get_best_n(self, prefix):
        if self.prefix_to_entry.get(prefix) == -1:
            return [DictEntry("null", 0)]
        else:
            return self.prefix_to_entry.get(prefix)

    def get_training_count(self):
        return self.total

def main():
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