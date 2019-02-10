#!/usr/bin/python3
import matplotlib
import math

def monthly_payment(cost, n, r):
    r /= 1200 # converting from precent to decimal and per month
    return round((r * cost) / (1 - (1 + r)**(-n*12)), 2)

def balance(cost, n , r):
    n_m = n * 12
    r_m = r / 1200
    return round((cost * (1 + r_m)**n_m) - monthly_payment(cost, n, r)*(((1 + r_m)**n_m - 1)/r_m), 2)

def calc_loan(cost, n, r):
    monthly_pay = monthly_payment(cost, n , r)
    print("Monthly payment: {}".format(monthly_payment))

    table = list()
    total_interest = 0
    while cost >= 0:
        interest = round(cost * (r / 1200), 2)
        total_interest += interest
        principle = round(monthly_pay - interest, 2)
        table.append([cost, principle, interest, monthly_pay])
        cost = round(cost - principle, 2)

    print("Total loan amount: {}".format(table[0][0] + total_interest))
    print("Total interest paid: {}".format(total_interest))

    print("{:<7} {:>10s}   {:>9s}   {:>8s}   {:>9s}".format("Month", "Balance", "Principal", "Interest", "Payment"))
    index = 1
    for t in table:
        print ("{:<7}${:>10.2f}   ${:>8.2f}   ${:>7.2f}   ${:>8.2f}".format(index, t[0], t[1], t[2], t[3]))
        index += 1
    print("{:<7}${:>10.2f}   ${:>8.2f}   ${:>7.2f}   ${:>8.2f}".format(index, 0.00, 0.00, 0.00, 0.00))


def main():
    input("What is the loan for? ")    
    try:        
        total = round(float(input("Please enter the principal amount for the loan: ")), 2)
        rate = float(input("Please enter the yearly interest rate (as a percent) for the loan: "))
        years = int(input("Please enter the number of years for the loan: "))
    except ValueError:
        print("Error! That is not an number!")
        exit(1)
    calc_loan(total, years, rate)
    print("Balance: {}".format(balance(total, years, rate)))

if __name__ == "__main__":
    main()
