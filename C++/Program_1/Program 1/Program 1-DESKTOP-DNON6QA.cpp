// Program 1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

bool isLeapYear(int year);
bool isSpecial(int month);
string monthName(int month);

// Chapter 5. Exercise 5.31.
int main()
{
	int year;
	int day;
	cout << "This program would display calendar table for the year." << endl;
	cout << "Please enter year and a day seperated by space:";
	cin >> year;
	cin >> day;
	while (day > 5 || day < 1) {
		cout << "Invalid day. Please input day between 1 and 5:";
		cin >> day;
	}
	int month = 1;
	int days = 31;
	string aux[40];
	int weekDay = 1;
	int count = 1;
	cout << endl;
	//A lopp that goes from 1 to 12
	while ( month != 13 ) {
		//Creates a Header.
		cout << setw(18) << monthName(month) << year << endl;
		cout <<  " -----------------------------------------" << endl;
		cout << setw(6) <<"Sun" << setw(6) << "Mon" << setw(6) << "Tue" << setw(6) << "Wed" << setw(6) << "Thu" << setw(6) << "Fri" << setw(6) << "Sat" << endl;
		//Builds up entire month days, outputing it.
		for (int x = 0; x < days + day; x++) {
			if (x < day) {
				aux[x] = " ";
				if (weekDay == 7) {
					cout << setw(6) << aux[x] << endl;
					weekDay = 1;				
				}else {
					cout << setw(6) << aux[x];
					weekDay++;
				}
			}else {
				if (weekDay == 7) {
					aux[x] = to_string(count++);
					cout << setw(6) << aux[x] << endl;
					weekDay = 1;				
				}else {
					aux[x] = to_string(count++);
					cout << setw(6) << aux[x];
					weekDay++;
				}
				
			}
		}
		//Updates the parameters and increment month.
		day = weekDay - 1;
		weekDay = 1;
		count = 1;
		month++;
		//check for the number of days for the next month.
		if (isLeapYear(year) && month == 2) {
			days = 29;
		}else if (month == 2) {
			days = 28;
		}else if (isSpecial(month)){
			days = 30;
		}else {
			days = 31;
		}
		cout << endl;
	}

	//pause character.
	char pause;
	cout << "Enter pause character to end the program:";
	cin >> pause;
    return 0;
}

// checks if the month is special or not. Returns boolean
bool isSpecial(int month) {
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
		return true;
	}else {
		return false;
	}
}

//checks if Year is a Leap Year. Return boolean
bool isLeapYear(int year) {
	if (year % 4 == 0) {
		if (year % 100 != 0) {
			return true;
		}else if (year % 400 == 0) {
			return true;
		}else {
			return false;
		}
	}else {
		return false;
	}

}

//return the month String(name).
string monthName(int month) {
	if (month == 1) {
		return "January ";
	}else if (month == 2) {
		return "February ";
	}else if (month == 3){
		return "March ";
	}else if (month == 4) {
		return "April ";
	}else if (month == 5) {
		return "May ";
	}else if (month == 6) {	
		return "June ";
	}else if (month == 7) {
		return "July ";
	}else if (month == 8) {
		return "August ";
	}else if (month == 9) {
		return "September ";
	}else if (month == 10) {
		return "October ";
	}else if (month == 11) {
		return "November ";
	}else {
		return "December ";
	}
}

