// examsTest.cpp : Defines the entry point for the console application.
//
#include "stdafx.h"
#include "Complex.h"
#include<iostream>
using namespace std;
// Testing how Iverson makes exams....
// modified by W.P. Iverson, May 18, 2014
// for CS212 class

class A {
public:
	//A() { cout << "makeA" << endl; data = 5; }
	~A() { cout << "destroyA" << endl;  }
	void fun() { cout << data * 3 << endl; }
	virtual void noFun() const { cout << "AnoFun " << endl; }
private: 
	int data;
};

class B : public A {
public:
	B() { cout << "makeB" << endl; data = 9; }
	~B() { cout << "destroyB" << endl; }
	void fun() { cout << data * 2 << endl; }
	virtual void noFun() const { cout << "BnoFun" << endl; }
private:
	int data;
};

void f(A one) { cout << "f-works" << endl; }
void g(B &two) { cout << "g-works" << endl; }
void h() { B three; cout << "h-works" << endl; }
int main()
{
	/*
	Complex one(2, 3);
	int test = 21;

	cout << one << endl; // 1.
	cout << test << endl; // 2.

	cout << sizeof(one) << endl; // 3.
	cout << sizeof(test) << endl; // 4.

	cout << test++ << endl; // 5.
	cout << ++test << endl; // 6.

	cout << one++ << endl; // 7.
	cout << ++one << endl; // 8.

	one = one << 2; // 9. write these // 10. ?
	cout << one << endl; // 11.

	test = test >> 2; // 12. write these?
	cout << test << endl; // 13.

	test = test << 2;
	cout << test << endl; // 14.

	cout << (test | 2) << endl; // 15.
	cout << (test & 4) << endl; // 16.

	int * pTest = &test;
	Complex * pOne = &one;

	cout << pTest << endl;		// 17. hex
	cout << ++pTest << endl;	// 18.

	cout << pOne << endl;	// 19. hex
	cout << ++pOne << endl; // 20.
	*/

	A *a = new A();
	B *b = new B();
	b->fun();
	a = b;
	a->fun();
	b->fun();
	a->noFun();
	b->noFun();
	f(*a);
	g(*b);
	h();
	// Read two intergers

	cout << "Enter two integers: ";

	int number1, number2;

	cin >> number1 >> number2;
	try
	{
		if (number2 == 0)
			throw number1;		
		cout << number1 << " / " << number2 << " is "

			<< (number1 / number2) << endl;
		cout << "C" << endl;
	}
	catch (int e)
	{
		cout << "A" << endl;
	}
	cout << "B" << endl;
	system("pause");

	return 0;
}

