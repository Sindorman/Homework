#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 355
Homework #3

Description: TODO This is program that runs A-priori algorithm on given data and outputs 5 pairs and triples with most confidence.
             
'''

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

def main():
    pass


if __name__ == "__main__":
    main()