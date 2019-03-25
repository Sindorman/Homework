#!/usr/bin/python3
import re
import sys
from map_hash import Map

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

class WordPredictor:
    def __init__(self):
        self.word_to_count = Map()
        self.prefix_to_entry = Map()
        self.total = 0


    def train(self, training_file):
        try:
            f = open(training_file, "r")
            for line in f:
                for word in line.split():
                    self.train_word(word)
                    self.total += 1
            f.close()

        except FileNotFoundError:
            print("Could not open training file: {}".format(training_file))
            exit(1)

    def train_word(self, word):
        cleanString = (re.sub(r'\W+','', word )).lower()
        if self.word_to_count.get(cleanString ) == -1:
            self.word_to_count.put(cleanString, 1)
        else:
            value = self.word_to_count.get(cleanString )
            print("word: {}, value: {}".format(cleanString, value))
            self.word_to_count.remove(cleanString)
            self.word_to_count.put(cleanString, value + 1)
            value = self.word_to_count.get(cleanString )
            print("new word: {}, value: {}".format(cleanString, value))

    def build(self):
        for t in self.word_to_count.slots:
            if t is None:
                continue
            for key in t:
                if key is None:
                    continue
                prefix = ""
                dictE = DictEntry(key, self.word_to_count.get(key) / self.total)
                #print(key)
                for c in key:
                    prefix += c
                    if self.prefix_to_entry.get(prefix) == -1:
                        self.prefix_to_entry.put(prefix, dictE)
                    elif self.prefix_to_entry.get(prefix).get_prob() < dictE.get_prob():
                        self.prefix_to_entry.put(prefix, dictE)

    def get_word_count(self, word):
        if self.word_to_count.get(word) == -1:
            return 0
        else:
            return self.word_to_count.get(word)

    def get_best(self, prefix):
        if self.prefix_to_entry.get(prefix) == -1:
            return 0
        else:
            return self.prefix_to_entry.get(prefix).get_word()

    def get_total(self):
        return self.total

def main():
    if len(sys.argv) < 2:
        print("Error! Please run script providing name of the training file!")
        exit(1)
    test = WordPredictor()
    test.train(sys.argv[1])
    print("Total words: {}".format(test.get_total()))
    #print(test.word_to_count.print())
    #test.build()
    #print(test.get_best("t"))
    print(test.word_to_count.get("and"))

if __name__ == "__main__":
    main()