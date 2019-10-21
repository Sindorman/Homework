#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 355
Homework #3

Description: HW3.
             
'''

from functools import reduce
import operator

class iterFile:
    def __init__(self, name):
        try:
            self.stream = open(name, 'r')
            self.line_list = []
            self.index = 0
        except FileNotFoundError:
            print("Error, file {name} was not found!")
            exit()

    def __iter__(self):
        return self

    def __next__(self) -> str:
        line = None

        if self.line_list == [] or self.index >= len(self.line_list):
            line = self.stream.readline()
            self.line_list = []

            if line == "":
                self.stream.close()
                raise StopIteration

            for word in line.split():
                self.line_list.append(word)

            if self.line_list == []:
                return self.__next__()

            self.index = 1
            return self.line_list[0]

        ret = self.line_list[self.index]
        self.index += 1
        return ret

def sprintLog(users:dict) -> dict:
    '''
    Function that takes in dictionary of Sprint Log per user and coverts it into dictionary of tasks that users worked on.
    \nparameters: dictionary of users as dict.
    \nreturns: dictionary of tasks as dict.
    '''
    result_dict = {}
    for user in users:
        for task in users[user]:
            task_exists = result_dict.get(task)
            if task_exists is None:
                result_dict[task] = {user: users[user][task]}
            else:
                task_exists[user] = users[user][task]
                result_dict[task] = task_exists
    return result_dict

def addSprints(log1:dict, log2:dict) -> dict:
    '''
    Function that takes in dictionaries of Sprint Logs per task and merges them together.
    \nparameters: two dictionaries of users as dict.
    \nreturns: dictionary of tasks as dict.
    '''

    result_dict = {}

    for task in log1:
        task_exists = result_dict.get(task)
        if task_exists:
            for user in log1[task]:
                user_exists = result_dict[task].get(user)
                if user_exists:
                    result_dict[task][user] = log1[task][user] + user_exists
                else:
                    result_dict[task][user] = log1[task][user]
        else:
            result_dict[task] = log1[task]

    for task in log2:
        task_exists = result_dict.get(task)
        if task_exists:
            for user in log2[task]:
                user_exists = result_dict[task].get(user)
                if user_exists:
                    result_dict[task][user] = log2[task][user] + user_exists
                else:
                    result_dict[task][user] = log2[task][user]
        else:
            result_dict[task] = log2[task]

    return result_dict

def addNLogs(main_log:dict) -> dict:
    '''
    Function that takes in dictionary of Sprints Log per user and combines them.
    \nparameters: dictionary of sprints as dict.
    \nreturns: dictionary of tasks as dict.
    '''
    return reduce(addSprints, map(sprintLog, main_log))


def lookupVal(L:list, k):
    '''
    Function that takes in list of dictionaries and a key.
    \nparameters: list of dictionaries as list and key as anything.
    \nreturns: element of found, otherwise None.
    '''
    found = None
    for x in range(len(L) - 1, -1, -1):
        found = L[x].get(k)
        if found is not None:
            break
    return found

def lookupVal2(L:list, k):
    '''
    Function that takes in list of tuples and a key.
    \nparameters: list of tuples as list and key as anything.
    \nreturns: element of found, otherwise None.
    '''
    looking_index = len(L) - 1
    found = None
    while looking_index >= 0:
        found = L[looking_index][1].get(k)
        if found is not None or looking_index == 0:
            break
        looking_index = L[looking_index][0]

    return found

def unzip(L:list) -> list:
    '''
    Function that takes in list of tuples and returns tuple of lists.
    \nparameters: list of tuples as list.
    \nreturns: tuple of lists.
    '''
    if len(L) == 0:
        return []

    first = list(map(lambda x: x[0], L))
    second = list(map(lambda x: x[1], L))
    third = list(map(lambda x: x[2], L))
    return (first, second, third)

def numPaths(m:int, n:int, blocks:list) -> int:
    '''
    Function that takes in two integers which are length and width of grid and list of blocked cells and returns number of possible paths.
    \nparameters: two integers that are length and width and a list of blocked cells that are tuples.
    \nreturns: number of paths as an integer.
    '''
    
    left = 0
    down = 0
    if m == 1 and n == 1:
        return 1

    if not (m-1, n) in blocks and m-1 > 0 and n > 0:
        down = numPaths(m-1, n, blocks)
    if not (m, n-1) in blocks and m > 0 and n-1 > 0:
        left = numPaths(m, n-1, blocks)
    return down + left

def wordHistogram(words:iterFile) -> list:
    dict_seen = {}
    for word in words:
        seen_word = dict_seen.get(word)
        if seen_word is None:
            dict_seen[word] = 1
        else:
            dict_seen[word] = seen_word + 1
    return list(reversed(sorted(dict_seen.items(), key=operator.itemgetter(1))))


def main():
    print(wordHistogram(iterFile("testfile.txt")))

if __name__ == "__main__":
    main()