// Test.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
using namespace std;


void main()
{
	int array[5] = { 6,1,7,2,9 };
	int i = 13, k = 42;

	cout << i / k << endl;		// 1.
	cout << array[2] << endl;		// 2.

	int* p1 = array;
	int* p2 = &i;
	*p2 = 31;

	cout << i << endl;			// 3.
	cout << k << endl;			// 4.

	cout << *p1 << endl;			// 5.
	cout << *p2 << endl;			// 6.

	p1 = array + 2;
	p2 = p1 + 2;

	cout << *p1 << endl;			// 7.
	cout << *p2 << endl;			// 8.

									// reset i and k for clarity
	i = 13;		k = 42;

	i = k + 2;
	k = *array + 2;

	cout << i << endl;			// 9.
	cout << k << endl;			// 10.
	system("PAUSE");
}

