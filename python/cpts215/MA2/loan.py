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

def calc_loan(name, cost, n, r):
    monthly_pay = monthly_payment(cost, n , r)
    print("Monthly payment: {}".format(monthly_pay))

    table = list()
    total_interest = 0
    balance = cost
    while balance > 0:
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
    plot_graph(name, table, n)

def plot_graph(name, table, n):
    balance_x = np.array(list(range(0, n + 1)))
    balance_y = list()

    labels = ["Principle Paid", "Interest Paid"]

    principle_paid = list()
    interest_paid = list()
    principle = 0
    interest = 0
    
    index = 0
    while index < len(table):
        if index % 12 == 0:
            balance_y.append(table[index][0])
            principle_paid.append(int(principle))
            interest_paid.append(int(interest))
        principle += table[index][1]
        interest += table[index][2]
        index += 1
    balance_y.append(0)
    principle_paid.append(int(principle))
    interest_paid.append(int(interest))

    formatted_balance = list()
    for b in balance_y:
        formatted_balance.append("${:,}".format(round(b)))
    
    formatted_balance = np.array(formatted_balance)
    
    balance_y = np.array(balance_y)
    principle_paid = np.array(principle_paid)
    interest_paid = np.array(interest_paid)
    #plt.rc('font', family='serif', size=13)
    plt.stackplot(balance_x, principle_paid, interest_paid, colors=['g', "#FFA500"], labels=labels)
    plt.title(name + " Loan")
    balance_line = plt.plot(balance_x, balance_y, label="Balance Remaining")
    plt.setp(balance_line, color='k', linewidth=2.0)
    plt.legend(loc='upper center')
    plt.autoscale(enable=True, axis=u'both', tight=False)
    plt.ylabel('Balance', fontsize=15)
    plt.xlabel('Years', fontsize=15)
    plt.axis([0, n, 0, table[0][0] + table[0][0] * 0.2])
    plt.yticks(balance_y, formatted_balance)
    plt.grid()
    plt.show()


def main():
    name = input("What is the loan for? ")    
    try:        
        total = round(float(input("Please enter the principal amount for the loan: ")), 2)
        rate = float(input("Please enter the yearly interest rate (as a percent) for the loan: "))
        years = int(input("Please enter the number of years for the loan: "))
    except ValueError:
        print("Error! That is not an number!")
        exit(1)
    calc_loan(name, total, years, rate)
    #print("Balance: {}".format(balance(total, years, rate)))

if __name__ == "__main__":
    main()
