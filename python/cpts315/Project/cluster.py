#!/usr/bin/python3

'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 315
Programming Project #1

Description: This program scanns CSV file of data and does KNN.
'''

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report, confusion_matrix
import timeit
import sys

class KNN():

    def __init__(self):
        self.classifier = ""

    def process_and_train(self, file_name):
        start = timeit.default_timer()
        # Preprocessing
        self.names = ['Name', 'Team', 'Position', 'Height(inches)', 'Weight(pounds)', 'Age']
        self.iris = pd.read_csv(file_name, delimiter = ',', names=self.names)
        X = self.iris.iloc[:, 3:].values
        self.Y = self.iris.iloc[:, 0].values

        # Train Test Split
        #X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0)
        # Feature Scaling
        self.scaler = StandardScaler()
        self.scaler.fit(X)

        self.X_train = self.scaler.transform(X)
        #y_train = scaler.transform(Y)
        #X_test = scaler.transform(X_test)

        # Training and Predictions
        self.classifier = KNeighborsClassifier(n_neighbors=5)
        self.classifier.fit(self.X_train, self.Y)
        stop = timeit.default_timer()
        print("Reading files, processing and training took (s): %.6f" %(stop - start))
        print("========================================\n")

def main():
    '''
    Main funtion definition
    '''
    if len(sys.argv) < 2:
        print("Error! Please run script providing name of the files containing data!")
        exit(1)

    classifier = KNN()
    classifier.process_and_train(sys.argv[1])

    print("===============================================")
    print("\nHello, this program will tell you which Major League Baseball player you are close to.\n")
    inp = ""
    while inp != "exit" or inp != "Exit":
        inp = input("Input your Height(inches), Weight(pounds) and age in this format \"70, 165, 18\" (without quotes). Or type exit to exit program.\n")
        s = inp.split(',')
        if len(s) < 3:
            print("Please type in all information")
            if inp != "exit" or inp != "Exit":
                exit()
            continue
        t_test = classifier.scaler.transform([[s[0], s[1], s[2]]])
        t_pred = classifier.classifier.predict(t_test)
        res = classifier.iris.loc[classifier.iris['Name'] == t_pred[0]]
        print("\nYou are close to this/these player(s):\n")
        print(res)
        print("\n")
        print("===============================================")


if __name__ == "__main__":
    main()