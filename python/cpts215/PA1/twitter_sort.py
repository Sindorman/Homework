#!/usr/bin/python3
from scanner import *
from datetime import datetime

class record:

    def __init__(self,string):
        self.name = 0
        self.message = 0
        self.datetime_object = 0
    
    def get_name(self):
        return self.name

    def get_message(self):
        return self.message

    def get_datetime(self):
        return self.datetime_object

    def is_more_recent(self, record):
        return self.datetime_object < record.get_datetime()


def read_records(filename):
    if filename is None or filename is "":
        print("Error! Please provide a file name!")
        exit(1)
    s = Scanner(filename)

def is_more_recent(record1, record2):
    return record1.is_more_recent(record2)

def write_records(records_list, filename):
    if filename is None or filename is "":
        print("Error! Please provide a file name!")
        exit(1)
    with open(filename, "w") as f:
        for rec in records_list:
            f.write("{}\n".format(rec))

if __name__ == "__main__":
    read_records("")