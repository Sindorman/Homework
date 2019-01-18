/*
CS212 Spring 2014 Program2
testing code for Liang text Exercises 11_9 and 11_11
with additions by W.P. Iverson
*/
#include "stdafx.h"
#include "Rectangle2D.h"
#include <iostream>
#include <cmath>
using namespace std;

int main()
{
	// Basic testing for Exercise #9
	cout << "11_09 testing:" << endl;
	Rectangle2D r1(2, 2, 5.5, 4.9);
	cout << "Area: " << r1.getArea() << endl;
	cout << "Perimeter: " << r1.getPerimeter() << endl;
	cout << r1.contains(3, 3) << endl;
	Rectangle2D r2(4, 5, 10.5, 3.2);
	cout << r1.contains(r2) << endl;

	// build the third rectangle one item at a time
	Rectangle2D r3;
	r3.setX(4);
	r3.setY(5);
	r3.setHeight(2.3);
	r3.setWidth(5.4);
	cout << r1.overlaps(r3) << endl;

	// And Exercise#11
	cout << "11_11 testing:" << endl;
	double points[5][2] = { { 1, 2.5 },{ 3, 4 },{ 5, 6 },{ 7, 8 },{ 9, 10 } };
	// non-member function as per text spec:
	Rectangle2D boundingRectangle = getRectangle(points, 5);

	cout << "The bounding rectangle's center (" << boundingRectangle.getX() << ", " <<
		boundingRectangle.getY() << "), width " << boundingRectangle.getWidth() <<
		", height " << boundingRectangle.getHeight() << endl;

	// below should work without modifications to above requirements
	// i.e this is more like practice for the next exam:
	cout << "Iverson testing (old exam#1):" << endl;
	double* p1 = points[0];
	double* p2 = points[2];
	*p2 = 99;
	cout << *(p1 + 1) << endl;		// 1.
	cout << (*p1 + 1) << endl;		// 2.
	cout << p1 << endl;				// 3.
	p1++;
	p2++;
	cout << *p1 << endl;			// 4.
	cout << *p2 << endl;			// 5.
	p1 = points[3] + 2;
	p2 = p2 + 2;
	cout << *p1 << endl;			// 6.
	cout << *p2 << endl;			// 7.

									// Explore dynamic memory
	Rectangle2D* r4;
	r4 = new Rectangle2D(2, 2, 5.5, 4.9);
	cout << (*r4).getArea() << endl;
	delete r4;
	cout << (*r4).getArea() << endl;

	system("pause");
	return 0;
}