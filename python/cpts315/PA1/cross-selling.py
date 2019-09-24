import sys
import timeit

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
    items = {}
    for line in read:
        for item in line.split(' '):
            if items.get(item) is None:
                items[item] = 1
            else:
                items[item] += 1

    stop = timeit.default_timer()
    print("elapsed (s): %.6f" %(stop - start))
    print(items)

if __name__ == "__main__":
    main()