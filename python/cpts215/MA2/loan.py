#!/usr/bin/python3
import matplotlib.pyplot as plt
import math
import numpy as np

def monthly_payment(cost, n, r):
    r /= 1200 # converting from precent to decimal and per month
    return round((r * cost) / (1 - (1 + r)**(-n*12)), 2)

def balance(cost, n , r):
    n_m = n * 12
    r_m = r / 1200
    return round((cost * (1 + r_m)**n_m) - monthly_payment(cost, n, r)*(((1 + r_m)**n_m - 1)/r_m), 2)

def calc_loan(cost, n, r):
    monthly_pay = monthly_payment(cost, n , r)
    print("Monthly payment: {}".format(monthly_pay))

    table = list()
    total_interest = 0
    balance = cost
    while balance >= 0:
        interest = round(balance * (r / 1200), 2)
        total_interest += interest
        principle = round(monthly_pay - interest, 2)
        table.append([balance, principle, interest, monthly_pay])
        balance = round(balance - principle, 2)

    print("Total loan amount: {}".format(table[0][0] + total_interest))
    print("Total interest paid: {}".format(total_interest))

    print("{:<7} {:>10s}   {:>9s}   {:>8s}   {:>9s}".format("Month", "Balance", "Principal", "Interest", "Payment"))
    index = 1
    for t in table:
        print ("{:<7}${:>10.2f}   ${:>8.2f}   ${:>7.2f}   ${:>8.2f}".format(index, t[0], t[1], t[2], t[3]))
        index += 1
    print("{:<7}${:>10.2f}   ${:>8.2f}   ${:>7.2f}   ${:>8.2f}".format(index, 0.00, 0.00, 0.00, 0.00))
    plot_graph(cost, table, n)

def plot_graph(balance, table, n):
    balance_x = np.array(list(range(0, n*12)))
    balance_y = list()

    labels = ["Balance Remaining", "Principle Paid", "Interest Paid"]

    principle_paid = list()
    principle = table[0][1]
    interest = table[0][2]
    interest_paid = list()

    for t in table:
        balance_y.append(int(t[0]))
        principle_paid.append(int(principle))
        interest_paid.append(int(interest))
        principle += t[1]
        interest += t[2]
    
    balance_y = np.array(balance_y)
    principle_paid = np.array(principle_paid)
    interest_paid = np.array(interest_paid)
    #plt.rc('font', family='serif', size=13)
    plt.stackplot(balance_x, balance_y, principle_paid, interest_paid,  labels=labels)
    plt.legend(loc='upper left')
    plt.autoscale(enable=True, axis=u'both', tight=False)
    plt.xlabel('Balance', fontsize=15)
    plt.ylabel('Years', fontsize=15)
    plt.show()


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
    #print("Balance: {}".format(balance(total, years, rate)))

if __name__ == "__main__":
    main()
