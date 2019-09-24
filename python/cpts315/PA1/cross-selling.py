#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 315
Programming Project #1

Description: This is program that runs A-priori algorithm on given data and outputs 5 pairs and triples with most confidence.
             
'''

import sys
import timeit
from apyori import apriori

def main():

    # Handling opening the file and reading it.
    if len(sys.argv) < 2:
        print("Error! Please run script providing name of the file that contains browsing history!")
        exit(1)
    try:
        read = open(sys.argv[1], "r")
    except FileNotFoundError:
        print("Error, was not able to find file: {}".format(sys.argv[1]))
        exit(1)

    start = timeit.default_timer()
    start_local = timeit.default_timer()
    max_len = 0
    main_list = []
    num_items = 0
    for line in read:
        row = line.split(' ')
        if len(row) > max_len:
            max_len = len(row)

        local_list = []
        for item in row:
            if item == "\n" or item == "":
                continue
            local_list.append(item)
            num_items += 1

        main_list.append(local_list)
    
    print("Max Length: {}".format(max_len))
    print("Number of items: {}".format(num_items))

    for x in range(0, len(main_list)):
        main_list[x] = main_list[x] + [""] * (max_len - len(main_list[x]))

    stop = timeit.default_timer()
    print("Prepping data took (s): %.6f" %(stop - start_local))

    start_local = timeit.default_timer()
    rules_2 = apriori(main_list, min_support=100/num_items, min_confidence=0.4, min_lift=3, min_length=2)
    results_2 = list(rules_2)
    stop = timeit.default_timer()
    print("Using A-priori for pairs took (s): %.6f" %(stop - start_local))

    start_local = timeit.default_timer()
    rules_3 = apriori(main_list, min_support=100/num_items, min_confidence=0.4, min_lift=3, min_length=3)
    results_3 = list(rules_3)
    stop = timeit.default_timer()
    print("Using A-priori for triples took (s): %.6f" %(stop - start_local))

    start_local = timeit.default_timer()
    sorted_data2 = {}
    sorted_data3 = {}
    for item in results_2:

        pair = item[0]
        items = [x for x in pair]
        sorted_data2["{} {}".format(items[0], items[1])] = item[2][0][2]
    sorted_data2 = sorted(sorted_data2.items(), key = lambda x : x[1], reverse=True)

    for item in results_3:

        pair = item[0]
        items = [x for x in pair]
        sorted_data3["{} {}".format(items[0], items[1])] = item[2][0][2]
    
    sorted_data3 = sorted(sorted_data3.items(), key = lambda x : x[1], reverse=True)
    
    stop = timeit.default_timer()
    print("Sorting results from A-priori took (s): %.6f" %(stop - start_local))

    start_local = timeit.default_timer()
    output = open("output.txt", 'w')
    output.write("OUTPUT A \n")
    for x in range(0, 5):
        output.write(str(sorted_data2[x][0]) + " ")
        output.write(str(sorted_data2[x][1]) + "\n")
    
    output.write("OUTPUT B \n")
    for x in range(0, 5):
        output.write(str(sorted_data3[x][0]) + " ")
        output.write(str(sorted_data3[x][1]) + "\n")
    stop = timeit.default_timer()
    print("Writing output into file took (s): %.6f" %(stop - start_local))

    stop = timeit.default_timer()
    print("Whole thing took (s): %.6f" %(stop - start))

if __name__ == "__main__":
    main()