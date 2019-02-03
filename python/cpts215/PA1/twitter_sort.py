#!/usr/bin/python3
from scanner import *
from datetime import datetime

class record:

    def __init__(self, string):
        string = string.split('\"')
        self.name = str(string[0])[1:(len(string[0])-1)]
        self.message = string[1][0:(len(string[1]))]
        self.datetime_object = datetime.strptime(str(string[2][0:(len(string[2])-1)]), ' %Y %m %d %H:%M:%S')
        self.char_length = len(self.message)

    def get_name(self):
        return self.name

    def get_message(self):
        return self.message

    def get_char_length(self):
        return self.char_length

    def get_datetime(self):
        return self.datetime_object

    def is_more_recent(self, record):
        return self.datetime_object < record.get_datetime()

    def __gt__(self, other):
        return self.is_more_recent(other)
    
    def __str__(self):
        return str("{} \"{}\" {}".format(self.name, self.message, str(self.datetime_object)))


def read_records(filename):
    if filename is None or filename is "":
        print("Error! Please provide a file name!")
        exit(1)

    s = Scanner(filename)
    line = s.readline()
    record_list = list()

    while line != "":
        rec = record(line)
        record_list.append(rec)
        line = s.readline()

    return record_list

def is_more_recent(record1, record2):
    return record1.is_more_recent(record2)

def write_records(records_list, filename):
    if filename is None or filename is "":
        print("Error! Please provide a file name!")
        exit(1)

    with open(filename, "w") as f:
        for rec in records_list:
            f.write("{}\n".format(rec))

def merge_and_sort_tweets(records_list1, records_list2):
    records_list1.extend(records_list2)
    return sorted(records_list1)

def count_tweets_hashtags(records_list):
    count_total = 0
    count_short = 0
    count_over = 0
    for r in records_list:
        count_total += r.get_char_length()
        if(r.get_char_length() > 140):
            count_over += 1
        elif(r.get_char_length() < 50):
            count_short += 1
    print("Number of tweets over 140 character limit: {}\nNumber of short tweets: {}\nAverage characters per tweet: {}".format(count_over, count_short, round(count_total/len(records_list))))

def main():
    if len(sys.argv) < 4:
        print("Error! Please run script providing name of the two files to be merged and name of the output file as arguments!")
        exit(1)

    records1 = read_records(sys.argv[1])
    records2 = read_records(sys.argv[2])
    sorted_rec = merge_and_sort_tweets(records1, records2)

    for r in sorted_rec:
        print(r)

    count_tweets_hashtags(sorted_rec)
    write_records(sorted_rec, sys.argv[3])

if __name__ == "__main__":
    main()