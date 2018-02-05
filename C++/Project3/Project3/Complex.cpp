#include "stdafx.h"
#include "Complex.h"
// Testing program to follow Exercise in Liang text
// modified by W.P. Iverson, May 7, 2014
// for CS212 class
int main()
{
	Complex number1(3.5, 5.5);
	//cout << "Enter the first complex number: ";
	//cin >> number1;

	Complex number2(-3.5, 1);
	//cout << "Enter the second complex number: ";
	//cin >> number2;

	cout << "(" << number1 << ")" << " + " << "(" << number2 << ") = " << (number1 + number2) << endl;
	cout << "(" << number1 << ")" << " - " << "(" << number2 << ") = " << (number1 - number2) << endl;
	cout << "(" << number1 << ")" << " * " << "(" << number2 << ") = " << (number1 * number2) << endl;
	cout << "(" << number1 << ")" << " / " << "(" << number2 << ") = " << (number1 / number2) << endl;
	cout << "|" << number1 << "|" << " = " << number1.abs() << endl;

	// overloaded []
	number1[0] = 3.4;
	cout << number1++ << endl;
	cout << ++number2 << endl;
	cout << (3 + number2) << endl;
	cout << (number2 += number1) << endl;
	cout << (number2 *= number1) << endl;

	system("pause");

	return 0;
}