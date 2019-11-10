#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 355
Homework #4

Description: HW4.
             
'''

import re

# WRITE YOUR NAME and YOUR COLLABORATORS HERE

#------------------------- 10% -------------------------------------
# The operand stack: define the operand stack and its operations
opstack = []  #assuming top of the stack is the end of the list

# Now define the helper functions to push and pop values on the opstack 
# (i.e, add/remove elements to/from the end of the Python list)
# Remember that there is a Postscript operator called "pop" so we choose 
# different names for these functions.
# Recall that `pass` in python is a no-op: replace it with your code.

def opPop():
    if not len(opstack):
        raise Exception("Stack is empty")
    return opstack.pop()
    # opPop should return the popped value.
    # The pop() function should call opPop to pop the top value from the opstack, but it will ignore the popped value.

def opPush(value):
    opstack.append(value)

#-------------------------- 20% -------------------------------------
# The dictionary stack: define the dictionary stack and its operations
dictstack = []  #assuming top of the stack is the end of the list

# now define functions to push and pop dictionaries on the dictstack, to 
# define name, and to lookup a name

def dictPop():
    if not len(dictstack):
        return None
    return dictstack.pop()

def dictPush(d):
    dictstack.append(d)
    #dictPush pushes the dictionary ‘d’ to the dictstack. 
    #Note that, your interpreter will call dictPush only when Postscript 
    #“begin” operator is called. “begin” should pop the empty dictionary from 
    #the opstack and push it onto the dictstack by calling dictPush.

def define(name, value):
    dictstack.append({name: value})
    #add name:value pair to the top dictionary in the dictionary stack. 
    #Keep the '/' in the name constant. 
    #Your psDef function should pop the name and value from operand stack and 
    #call the “define” function.

def lookup(name):
    for d in range(len(dictstack) - 1, -1, -1):
        for n in dictstack[d]:
            if n[1:] == name:
                return dictstack[d][n]
    print("definition is not in the stack!")
    return None
    # return the value associated with name
    # What is your design decision about what to do when there is no definition for “name”? If “name” is not defined, your program should not break, but should give an appropriate error message.


#--------------------------- 10% -------------------------------------
# Arithmetic and comparison operators: add, sub, mul, eq, lt, gt
# Make sure to check the operand stack has the correct number of parameters 
# and types of the parameters are correct.
def add():
    opPush(opPop() + opPop())

def sub():
    second = opPop()
    first = opPop()
    opPush(first - second)

def mul():
    opPush(opPop() * opPop())

def eq():
    opPush(opPop() == opPop())

def lt():
    opPush(opPop() > opPop())

def gt():
    opPush(opPop() < opPop())

#--------------------------- 25% -------------------------------------
# Array operators: define the string operators length, get, put, aload, astore
def length():
    ar = opPop()
    opPush(ar)
    opPush(ar[0])


def get():
    value = opPop()
    ar = opPop()
    opPush(ar)
    opPush(value)
    opPush(ar[1][value])

def put():
    put_what = opPop()
    put_where = opPop()
    put_there = opPop()
    put_there[1][put_where] = put_what
    opPush(put_there)

def aload():
    ar = opstack[len(opstack) - 1]
    opPop()
    for l in ar[1]:
        opPush(l)
    opPush(ar)

def astore():
    ar = opstack[len(opstack) - 1]
    opPop()
    for l in range(len(ar[1]) - 1, -1, -1):
        ar[1][l] = opPop()
    opPush(ar)
#--------------------------- 15% -------------------------------------
# Define the stack manipulation and print operators: dup, copy, pop, clear, exch, roll, stack
def dup():
    d = opPop()
    opPush(d)
    opPush(d)

def copy():
    copy_count = opPop()
    temp = []
    for c in range(0, copy_count):
        temp.append(opPop())
    
    for c in range(0, 2):
        for e in range(len(temp) - 1, -1, -1):
            opPush(temp[e])

def count():
    opPush(len(opstack))

def pop():
    return opPop()

def clear():
    opstack.clear()

def exch():
    e1 = opPop()
    e2 = opPop()
    opPush(e1)
    opPush(e2)

def stack():
    for c in range(len(opstack) - 1, -1, -1):
        print(c)

#--------------------------- 20% -------------------------------------
# Define the dictionary manipulation operators: psDict, begin, end, psDef
# name the function for the def operator psDef because def is reserved in Python. Similarly, call the function for dict operator as psDict.
# Note: The psDef operator will pop the value and name from the opstack and call your own "define" operator (pass those values as parameters).
# Note that psDef()won't have any parameters.

def psDict():
    opPop()
    opPush({})

def begin():
    dictPush(opPop())

def end():
    dictPop()

def psDef():
    value = opPop()
    name = opPop()
    define(name, value)

def tokenize(s):
    return re.findall("/?[a-zA-Z][a-zA-Z0-9_]*|[\[][a-zA-Z-?0-9_\s!][a-zA-Z-?0-9_\s!]*[\]]|[-]?[0-9]+|[}{]+|%.*|[^ \t\n]", s)


# COMPLETE THIS FUNCTION
# The it argument is an iterator.
# The sequence of return characters should represent a list of properly nested
# tokens, where the tokens between '{' and '}' is included as a sublist. If the
# parenteses in the input iterator is not properly nested, returns False.
def groupMatch(it):
    res = []
    for c in it:
        if c == '}':
            return res
        elif c=='{':
            # Note how we use a recursive call to group the tokens inside the
            # inner matching parenthesis.
            # Once the recursive call returns the code-array for the inner 
            # parenthesis, it will be appended to the list we are constructing 
            # as a whole.
            res.append(groupMatch(it))
        else:
            check = check_input(c)
            if check is None:
                check = c
            res.append(check)
    return False

# COMPLETE THIS FUNCTION
# Function to parse a list of tokens and arrange the tokens between { and } braces 
# as code-arrays.
# Properly nested parentheses are arranged into a list of properly nested lists.
def parse(L):
    res = []
    it = iter(L)
    for c in it:
        if c=='}':  #non matching closing parenthesis; return false since there is 
                    # a syntax error in the Postscript code.
            return False
        elif c=='{':
            res.append(groupMatch(it))
        else:
            check = check_input(c)
            if check is None:
                check = c
            res.append(check)
    return res

# COMPLETE THIS FUNCTION 
# Write auxiliary functions if you need them. This will probably be the largest function of the whole project, 
# but it will have a very regular and obvious structure if you've followed the plan of the assignment.
def interpretSPS(code): # code is a code array
    pass


def interpreter(s): # s is a string
    interpretSPS(parse(tokenize(s)))

def check_input(s):
    ret = None
    # Sort of hacky, but we only have few types to looks for
    if len(s) > 1 and (s[0] == "[" and s[-1] == "]"):
        n = re.findall("/?[a-zA-Z][a-zA-Z0-9_]*|[\[][a-zA-Z-?0-9_\s!][a-zA-Z-?0-9_\s!]*[\]]|[-]?[0-9]+|[}{]+|%.*|[^ \t\n]", s[1:-1])
        l = []
        for f in n:
            c = check_input(f)
            if c is None:
                continue
            l.append(c)
        return (len(l), l)

    if s == "True":
        return True
    elif s == "False":
        return False
    try:
        ret = int(s)
    except:
        try:
            ret = float(s)
        except:
            pass
    return ret

def main():
    pass

if __name__ == "__main__":
    main()