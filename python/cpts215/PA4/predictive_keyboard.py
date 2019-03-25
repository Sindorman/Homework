#!/usr/bin/python3
import re
import sys

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
        self.word_to_count = {}
        self.prefix_to_entry = {}
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
        if self.word_to_count.get(cleanString ) is None:
            self.word_to_count[cleanString ] = 1
        else:
            self.word_to_count[cleanString ] = self.word_to_count.get(cleanString ) + 1

    def build(self):
        for w in self.word_to_count:
            prefix = ""
            dictE = DictEntry(w, self.word_to_count.get(w) / self.total)
            for c in w:
                prefix += c
                if self.prefix_to_entry.get(prefix) is None:
                    self.prefix_to_entry[prefix] = dictE
                elif self.prefix_to_entry.get(prefix).get_prob() < dictE.get_prob():
                    self.prefix_to_entry[prefix] = dictE

    def get_word_count(self, word):
        if self.word_to_count.get(word) is None:
            return 0
        else:
            return self.word_to_count.get(word)

    def get_best(self, prefix):
        if self.prefix_to_entry.get(prefix) is None:
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
    print(test.word_to_count)
    test.build()
    print(test.get_best("tha"))

if __name__ == "__main__":
    main()