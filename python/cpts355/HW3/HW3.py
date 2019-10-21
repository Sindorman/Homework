#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 355
Homework #3

Description: TODO This is program that runs A-priori algorithm on given data and outputs 5 pairs and triples with most confidence.
             
'''

from functools import reduce

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

def main():
    pass

if __name__ == "__main__":
    main()