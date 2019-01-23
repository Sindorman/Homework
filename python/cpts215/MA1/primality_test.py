#!/usr/bin/python3

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 215, Spring 2019
Micro Assigment #1
1/17/19

Description: This program tells user if the inputted number is a prime number, and shows the sum of all primes from 2 to the number given.
'''

import math
import sympy

def is_prime(number):
    '''
    Checks if the passed parameter number is a prime number
    Parameter radius: interger number to check
    Returns: boolean
    '''

    # 2 is the lowest prime number
    if number == 2:
        return True

    int_until = math.floor(math.sqrt(number)) # loop until this value
    current_prime = 2 # starting with the lowest prime number

    # From starting prime 2 until int_until we check if number is dividible by any other prime number
    while current_prime < int_until:
        if number % current_prime == 0:
            return False
        current_prime = sympy.nextprime(current_prime)

    return True

def sum_primes(number):
    '''
    Gives a sum of all prime numbers from 2 to the given parameter number
    Parameter radius: interger number to loop until
    Returns: integer sum
    '''

    prime = 2 # starting with the lowest prime number
    sum_of_primes = 0 # sum of our primes

    while prime <= number:
        sum_of_primes += prime
        prime = sympy.nextprime(prime)

    return sum_of_primes


if __name__ == "__main__":

    num = 0

    # Filter input to the integers only
    try:
        num = int(input("Please enter an integer >= 2: "))
    except ValueError:
        print("Error! That is not an integer!")

    # Check if our interger is greater or equal to 2
    if num < 2:
        print("Error! integer must be >= 2!")
        exit(1)

    # Call our function and print the result
    print("{} is {}prime".format(num, ("not " if  not is_prime(num) else "")))

    # TODO check with professor if this is a different integer prompted number
    print("sum of primes from 2 to {} is {}".format(num, sum_primes(num)))