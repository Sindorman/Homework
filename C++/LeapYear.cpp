// ConsoleApplication1.cpp : Defines the entry point for the console application.
// Asks for an integer and check if it is a leap year.

#include "stdafx.h"
#include <iostream>
using namespace std;

int main()
{
	int year;
	bool LeapYear = false;
	while (!LeapYear) {
		cout << "Please write a year" << endl;
		cin >> year;
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			LeapYear = true;
			cout << "Yeas, it is a LeapYear" << endl;
		}
		else {
			cout << "No, it is not a leap year" << endl;
		}
	}
	cin >> year;
	
}

