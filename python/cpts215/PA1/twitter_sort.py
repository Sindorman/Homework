#!/usr/bin/python3

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019
Micro Assigment #1
1/25/19

Description: This program scanns two files that contain twitter feeds, merges and sorts them together in timely order.
             Also outputs number of tweets that went over 140 characters limit, number of short tweets and average characters per tweet, and top 5 hashtags.
'''

from scanner import *
import re
from datetime import datetime

class record:
    '''
    A class representing a twitter records. 
    record has user name message, date-time attributes.
    '''

    def __init__(self, string):
        '''
        Takes string and processes it splitting into name, message and date-time. Gets rid of @ in the username
        Parameter string: string that contains the whole line of a file
        '''
        string = string.split('\"')
        self.name = str(string[0])[1:(len(string[0])-1)]
        self.message = string[1][0:]
        self.datetime_object = datetime.strptime(str(string[2][0:(len(string[2])-1)]), ' %Y %m %d %H:%M:%S')

    def get_name(self):
        '''
        Method to publicly access name
        Returns: name attribute of a class as string
        '''
        return self.name

    def get_message(self):
        '''
        Method to publicly access message
        Returns: message attribute of a class as string
        '''
        return self.message

    def get_datetime(self):
        '''
        Method to publicly access date-time
        Returns: date-time attribute of a class as datetime object
        '''
        return self.datetime_object

    def is_more_recent(self, record):
        '''
        Compares given record to current record and returns boolean if current more recent
        Parameters: record as datetime object
        Returns: boolean if current record is more recent then given
        '''
        return self.datetime_object < record.get_datetime()

    def __gt__(self, other):
        '''
        Greater that override used by sorted() to sort records class
        Parameters: record as datetime object
        Returns: boolean if current record is more recent then given
        '''
        return self.is_more_recent(other)
    
    def __str__(self):
        '''
        Returns record as a string. Used mostly for printing
        Returns: string that represents a record
        '''
        return str("{} \"{}\" {}".format(self.name, self.message, str(self.datetime_object)))


def read_records(filename):
    '''
    Reads file line by line, creates list of records and returns it.
    Parameters: name of the file as string
    Returns: list of record objects
    '''
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
    '''
    Method that compares two records
    Parameters: two record objects
    Returns: boolean if first record is more recent then the another
    '''
    return record1.is_more_recent(record2)

def write_records(records_list, filename):
    '''
    Method which given list of records writes them into a file which name is given as second parameter
    Parameters: list of records and name of the file as string
    '''
    if filename is None or filename is "":
        print("Error! Please provide a file name!")
        exit(1)

    with open(filename, "w") as f:
        for rec in records_list:
            f.write("{}\n".format(rec))

def merge_and_sort_tweets(records_list1, records_list2):
    '''
    Merges one list into another and sorts them by date-time object
    Parameters: two lists containing record objects
    Returns: list of merge and sorted record objects
    '''
    records_list1.extend(records_list2)
    return sorted(records_list1)

def count_tweets_hashtags(records_list):
    '''
    Method which reads list of records and outputs info such as number of tweets over 140 character limit, number of short tweets, average characters per tweet,
    top 5 hashtags appeared in the messages.
    Parameters: list of record objects
    '''
    count_total = 0
    count_short = 0
    count_over = 0
    hashtag_dict = {}
    for r in records_list:
        hashtags = re.findall(r"#(\w+)", r.get_message())
        for h in hashtags:
            if hashtag_dict.get(h) is None:
                hashtag_dict[h] = 1
            else:
                hashtag_dict[h] += 1
        count_total += len(r.get_message())
        if(len(r.get_message()) > 140):
            count_over += 1
        elif(len(r.get_message()) < 50):
            count_short += 1
    print("Number of tweets over 140 character limit: {}\nNumber of short tweets: {}\nAverage characters per tweet: {}".format(count_over, count_short, round(count_total/len(records_list))))
    print("\nTop hastags:")
    index = 1
    for w in sorted(hashtag_dict, key=hashtag_dict.get, reverse=True):
        if index == 6:
            break
        print("{}. #{} appeared: {} times".format(index, w, hashtag_dict[w]))
        index += 1

def main():
    '''
    Main function that deals with arguments and execution.
    '''
    if len(sys.argv) < 4:
        print("Error! Please run script providing name of the two files to be merged and name of the output file as arguments!")
        exit(1)
    records1 = read_records(sys.argv[1])
    records2 = read_records(sys.argv[2])
    sorted_rec = merge_and_sort_tweets(records1, records2)

    count_tweets_hashtags(sorted_rec)
    write_records(sorted_rec, sys.argv[3])

if __name__ == "__main__":
    main()