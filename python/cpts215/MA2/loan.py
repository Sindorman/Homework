#!/usr/bin/python3
import matplotlib
import math

def monthly_payment(cost, n, r):
    r /= 1200 # converting from precent to decimal and per month
    return(r*cost)/(1-(1+r)**-n)

def balance(cost, n , r):
    return (cost * (1 + r)**n) - monthly_payment(cost, n, r)*(((1 + r)**n - 1)/r)

def main():
    input("What is the loan for? ")
    total = input("Please enter the principal amount for the loan: ")
    rate = input("Please enter the yearly interest rate (as a percent) for the loan: ")
    try:
        years = input("Please enter the number of years for the loan: ")
    except ValueError:
        print("Error! That is not an integer!")

if __name__ == "__main__":
    main()
