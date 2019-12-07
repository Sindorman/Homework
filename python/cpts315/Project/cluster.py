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
import sys

def main():
    '''
    Main funtion definition
    '''
    if len(sys.argv) < 2:
        print("Error! Please run script providing name of the files containing data!")
        exit(1)

    # Preprocessing
    names = ['Name', 'Team', 'Position', 'Height(inches)', 'Weight(pounds)', 'Age']
    iris = pd.read_csv(sys.argv[1], delimiter = ',', names=names)
    X = iris.iloc[:, 3:].values
    Y = iris.iloc[:, 0].values

    # Train Test Split
    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.20)
    # Feature Scaling
    scaler = StandardScaler()
    scaler.fit(X_train)

    X_train = scaler.transform(X_train)
    X_test = scaler.transform(X_test)

    # Training and Predictions
    classifier = KNeighborsClassifier(n_neighbors=5)
    classifier.fit(X_train, y_train)
    

    y_pred = classifier.predict(X_test)

    print("===============================================")
    print("\nHello, this program will tell you which Major League Baseball player you are close to.\n")
    inp = ""
    while inp != "exit" or inp != "Exit":
        inp = input("Input your Height(inches), Weight(pounds) and age in this format \"70, 165, 18\" (without quotes). Or type exit to exit program.\n")
        s = inp.split(',')
        if len(s) < 3:
            print("Please type in all information")
            continue
        t_test = scaler.transform([[s[0], s[1], s[2]]])
        t_pred = classifier.predict(t_test)
        res = iris.loc[iris['Name'] == t_pred[0]]
        print("\nYou are close to this/these player(s):\n")
        print(res)
        print("\n")
        print("===============================================")
    
if __name__ == "__main__":
    main()