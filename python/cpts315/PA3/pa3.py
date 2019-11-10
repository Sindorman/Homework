#!/usr/bin/python3
'''
Programmer: Mykhailo Bykhovtsev
Class: CptS 315
Programming Project #3

Description: TODO
             
'''


import numpy as np
import re


iterations = 20
learning_rate = 1

def bPerceptron(tstdata, tstlabel,datafile, labelsfile):

    f = open("output.txt", "a")

    f.write("===Binary Perceptron===\n")
    print("Running Binary Perceptron:")

    stopwords = set()

    stpwrds = open("stoplist.txt", "r")
    for l in stpwrds:
        t = l.strip('\n')
        stopwords.add(t)

    features = 0

    f_vocab = set()
    data = open(tstdata, "r")
    for l in data:
        for word in l.split():
            if word not in stopwords:
                if word not in f_vocab:
                    f_vocab.add(word)
                    features += 1

    W = np.zeros(features, np.int)

    f_vocab = sorted(f_vocab)
    f_data = list()
    f_label = list()
    for l_data, l_label in zip(open(str(tstdata), "r"), open(str(tstlabel), "r")):
        t = list()
        for word in f_vocab:
            if word in l_data.split():
                t.append(1)
            else:
                t.append(0)

        f_data.append(t)
        f_label.append(l_label.strip('\n'))

    N, d = np.shape(f_data)
    train_l = N
    train_m = list()

    for k in range(iterations):
        i_mistake = 0
        for j in range(len(f_data)):
            x = f_data[j]
            yHat_t = np.dot(x, W) 
            correct_label = int(f_label[j])

            if yHat_t > 0:
                yHat_t = 1
            elif yHat_t == 0:
                yHat_t = 0
            else:
                yHat_t = 0

            if int(yHat_t) != correct_label:
                i_mistake += 1
                W = W + learning_rate * x
        train_m.append(i_mistake)
    
 
    f_data = list()
    f_label = list()
    for l_data, l_label in zip(open(str(datafile), "r"), open(str(labelsfile), "r")):
        t = list()
        for word in f_vocab:
            if word in l_data.split():
                t.append(1)
            else:
                t.append(0)
        
        f_data.append(t)
        f_label.append(l_label.strip('\n'))

    N,d = np.shape(f_data)
    test_l = N
    test_m = list()

    for k in range(iterations):
        i_mistake = 0
        for j in range(len(f_data)):
            x = f_data[j]
            yHat_t = np.dot(x, W) 
            correct_label = int(f_label[j])

            if yHat_t > 0:
                yHat_t = 1
            elif yHat_t == 0:
                yHat_t = 0
            else:
                yHat_t = 0

            if int(yHat_t) != correct_label:
                i_mistake += 1
                W = W + learning_rate * x
        test_m.append(i_mistake)
    
    Perceptron_output(f, test_m, train_m, train_l, test_l)

def mPerceptron(trndata, tstdata):

    f = open("output.txt", "a")
    f.write("===Running Multi Class Perceptron===\n")
    print("Multi Class Perceptron:")
    features = 128

   
    train_m = list()
    weights = dict()

    for c in char_in_range('a', 'z'):
        weights[c]= (np.zeros(features, np.int) )

    data_file= open(trndata)
    data = []
    for l in data_file:
        t_line = l.strip().split("\t")
        if t_line[0] != '':
            binary = []
            for char in t_line[1][2:]:
                binary.append(int(char))
        
            label = t_line[2]
            datapacket = (binary, label)
            data.append(datapacket)


    for k in range(features):
        i_mistake = 0
        for item in data:
            data_packet = item[0]
            data_label = item[1]

            temp_label = sum_of_weights(data_packet,weights)


            if  temp_label != data_label:
                
                weights[data_label] = weights[data_label] + (learning_rate * data_packet)
                weights[temp_label] = weights[temp_label] - (learning_rate * data_packet)
                i_mistake += 1
        train_m.append(i_mistake)
    train_l = len(data)


    test_m = list()
    data_file= open(tstdata)

    data = []
    for l in data_file:
        t_line = l.strip().split("\t")
        if t_line[0] != '':
            binary = []
            for char in t_line[1][2:]:
                binary.append(int(char))
        
            label = t_line[2]
            datapacket = (binary, label)
            data.append(datapacket)

    for k in range(iterations):
        i_mistake = 0
        for item in data:
            data_packet = item[0]
            data_label = item[1]

            temp_label = sum_of_weights(data_packet,weights)


            if  temp_label!= data_label:
                
                weights[data_label] = weights[data_label] + (learning_rate * data_packet)
                weights[temp_label] = weights[temp_label] - (learning_rate * data_packet)
                i_mistake += 1

        test_m.append(i_mistake)
    test_l= len(data)

    Perceptron_output(f, test_m, train_m, train_l, test_l)

def sum_of_weights(dp, w):

    ret = '\n'
    t_YHat_t = 0
    for c in char_in_range('a', 'z'):
        t_weight = w[c]
        yHat_t = np.dot(dp, t_weight) 
        if yHat_t > t_YHat_t:
            ret = c

    if  ret == '\n':
        ret = 'a'
    return ret

def Perceptron_output(f, test_m, train_m, train_l, test_l):
    i = 1
    for test, train in zip(test_m, train_m):
        train_a = (float(train_l)-float(train))/float(train_l)
        train_a = train_a * 100.00
        test_a = (float(test_l)-float(test))/float(test_l)
        test_a = test_a * 100.00
        message = "iteration %d: Training Mistakes:%s | Training Accuracy:%f || Testing Mistakes:%s | Testing Accuracy:%f\n" %(i, train, train_a, test, test_a)
        print(message)
        f.write(message)
        i += 1
    f.close()

def char_in_range(c1, c2):
    for c in range(ord(c1), ord(c2)+1):
        yield chr(c)

def main():
    bPerceptron("traindata.txt",  "trainlabels.txt", "testdata.txt", "testlabels.txt")
    mPerceptron("ocr_train.txt", "ocr_test.txt")

if __name__ == "__main__":
    main()