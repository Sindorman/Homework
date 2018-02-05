#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

const int number = 5;
int sum = 0, rem;
int reverse_int(int num) {
	if (num) {
		rem = num % 10;
		sum = sum * 10 + rem;
		reverse_int(num / 10);
	}
	else
		return sum;
	return sum;
}
template<typename T>
T max(const T arr[], int size);
int *double_capacity(const int *list, int size);
string swapCase(const string & s);
void reverse(int ar[], int n);
bool isConsecutiveFour(const int  values[], int  size);
void locateLargest(const double  arr[][4], int  location[]);
int add();

class  GeometricObject {
public:
	GeometricObject();
	GeometricObject(const string & color, bool filled);
	string  getColor() const;
	void setColor(const string & color);
	bool isFilled() const;
	void setFilled(bool filled);
	string  toString() const;

private:
	string  color;
	bool filled;
};

class Triangle : public GeometricObject {
public:

	Triangle() {
		side1 = 1.0;
		side2 = 1.0;
		side3 = 1.0;
	}

	Triangle(double one, double two, double three) {
		side1 = one;
		side2 = two;
		side3 = three;
	}
	double getSideOne() const { return side1; }
	double getSideTwo() const { return side2; }
	double getSideThree() const { return side3; }

	double getPerimeter() const {
		return side1 + side2 + side3;
	}

	double getArea() const {
		double p = (side1 + side2 + side3) / 2;
		return sqrt(p * (p - side1) * (p - side2) * (p - side3));
	}

private:
	double side1;
	double side2;
	double side3;
};
GeometricObject::GeometricObject() {
	color = "white";
	filled = false;
}

GeometricObject::GeometricObject(const string & color, bool filled) {
	this->color = color;
	this->filled = filled;
}

string  GeometricObject::getColor() const {
	return color;
}

void GeometricObject::setColor(const string & color) {
	this->color = color;
}

bool GeometricObject::isFilled() const {
	return filled;
}

void GeometricObject::setFilled(bool filled) {
	this->filled = filled;
}

string  GeometricObject::toString() const {
	return "Geometric­Object ";
}

class Student {
public:

	string first;

	string last;

	float gpa;

	Student() {
		first = "";
		last = "";
		gpa = 0.0;
	}
	Student(string first, string last, float gpa) {
		this->first = first;
		this->last = last;
		this->gpa = gpa;
	}

	void print() {
		cout << first << " " << last << " " << gpa << endl;
	}

	void sort(Student one[]) {
		Student min;
		for (int c = 0; c < (number - 1); c++)
		{
			for (int d = 0; d < number - c - 1; d++)
			{
				if (one[d].gpa > one[d + 1].gpa) /* For decreasing order use < */
				{
					min = one[d];
					one[d] = one[d + 1];
					one[d + 1] = min;
				}
			}
		}
	}
};

class  Circle {
private:
	double  radius;
	static  int  numberOfObjects;

public:
	Circle() : radius(1) {
		numberOfObjects++;
	}

	Circle(double  r) : radius(r) {
		numberOfObjects++;
	}

	static  int  getNumberOfObjects() {
		return numberOfObjects;
	}
	double  getArea() const {
		return radius*radius*3.14159;
	}
	double  getRadius() const {
		return radius;
	}

	void setRadius(double  r) {
		radius = (r >= 0) ? r : 0;
	}

	double compareTo(const Circle& c) const {
		return this->radius - c.getRadius();
	}
	const Circle& operator=(const Circle& c) {
		radius = c.getRadius();
		this->numberOfObjects = c.getNumberOfObjects();
		return *this;
	}

};

bool operator<(const Circle& c1, const Circle& c2) {
	return c1.compareTo(c2) < 0;
}
bool operator<=(const Circle& c1, const Circle& c2) {
	return c1.compareTo(c2) <= 0;
}
bool operator>(const Circle& c1, const Circle& c2) {
	return c1.compareTo(c2) > 0;
}
bool operator>=(const Circle& c1, const Circle& c2) {
	return c1.compareTo(c2) >= 0;
}
bool operator==(const Circle& c1, const Circle& c2) {
	return c1.compareTo(c2) == 0;
}
bool operator!=(const Circle& c1, const Circle& c2) {
	return c1.compareTo(c2) != 0;
}


int Circle::numberOfObjects = 0;


int main() {
	/*
	//chapter 2. Exercise 2.6
	int temp;
	cout << "Enter an integer between 0 and 1000:";
	cin >> temp;
	if ((double)temp / 100 > 1) {
		int swap = temp / 100 + (temp / 10) % 10 + temp % 10;
		cout << " The sum of the digits is: " << swap << endl;
	}else if ((double)temp / 10 > 1) {
		int swap = temp / 10 + temp % 10;
		cout << " The sum of the digits is: " << swap << endl;
	}else {
		cout << " The sum of the digits is: " << temp << endl;
	}
	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;

	*/


	//chapter 3. Exercise 3.4
	/*
	float temp;
	cout << "Enter the temperature: ";
	cin >> temp;
	if (temp < 30.0) {
		cout << "Too cold!" << endl;
	}
	else if (temp > 100.0) {
		cout << "Too hot!" << endl;
	}
	else {
		cout << "Just right!" << endl;
	}
	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;

	*/

	//chapter 3. Exercise 3.16
	/*
	int sideOne;
	int sideTwo;
	int sideThree;
	cout << "Enter the three edges of a triangle: ";
	cin >> sideOne;
	cin >> sideTwo;
	cin >> sideThree;
	if (sideOne + sideTwo > sideThree) {
		cout << "The perimeter of the triangle is " << sideOne + sideTwo + sideThree << endl;
	}
	else {
		cout << "Invalid input" << endl;
	}
	//pause is not for the submition
	
	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	/*
	//chapter 4. Exercise 4.8
	int input;
	char output;
	cout << "Enter and ASCII code: ";
	cin >> input;
	if (input <= 0 || input >= 127) {
		cout << "Wrong ASCII code" << endl;
	}
	else {
		output = input;
		cout << " The character is " << output << endl;
	}
	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	//chapter 4. Exercise 4.19
	/*
	string first;
	string second;
	string third;
	cout << "Enter the first city:";
	getline(cin, first);
	cout << " Enter the second city:";
	getline(cin, second);
	cout << " Enter the third city:";
	getline(cin, third);
	string min;
	if (first > second) {
		min = second;
		if (min > third) {
			min = third;
			if (second < first) {
				cout << " The three cities in alphabetical order are: " << min << ", " << second << ", " << first << "." << endl;
			}else {
				cout << " The three cities in alphabetical order are: " << min << ", " << first << ", " << second << "." << endl;
			}
		}else {
			if (third < first) {
				cout << " The three cities in alphabetical order are: " << min << ", " << third << ", " << first << "." << endl;
			}else {
				cout << " The three cities in alphabetical order are: " << min << ", " << first << ", " << third << "." << endl;
			}
		}
	}
	else if (second > first) {
		min = first;
		if (min > third) {
			min = third;
			if (first < second) {
				cout << " The three cities in alphabetical order are: " << min << ", " << first << ", " << second << "." << endl;
			}else {
				cout << " The three cities in alphabetical order are: " << min << ", " << second << ", " << first << "." << endl;
			}
		}else {
			if (third < second) {
				cout << " The three cities in alphabetical order are: " << min << ", " << third << ", " << second << "." << endl;
			}else {
				cout << " The three cities in alphabetical order are: " << min << ", " << second << ", " << third << "." << endl;
			}
		}
	}
	else {
		if (second > third) {
			cout << " The three cities in alphabetical order are: " << third << ", " << second << ", " << first << "." << endl;
		}
		else {
			cout << " The three cities in alphabetical order are: " << first << ", " << second << ", " << third << "." << endl;
		}
	}
	//pause is not for the submition
	
	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

    //chapter 5
	/*
	int input;
	int sum = 0;
	std::cin >> input;
	if (input % 2 == 0 && input > 0) {
		sum += input;
	}
	while (input > 0) {
		std::cin >> input;
		if (input % 2 == 0 && input > 0) {
			sum += input;
		}
	}
	std::cout << sum;
	
	
	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/
	
	//chapter 5. Exercise 5.48

    /*
	string input;
	int count;
	cout << "Enter a string or return to end program:";
	getline(cin, input);
	while (input.size() != 0) {
		count = 0;
		for (int x = 0; x < input.size(); x++) {
			char temp = input.at(x);
			if (temp >= 65 && temp <= 90) {
				count++;
			}
		}
		cout << "The number of uppercase letters is " << count << endl;
		cout << "Enter a string or return to end program:";
		getline(cin, input);
	}

	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	/*
	//chapter 6. Programming exercise
	string input;
	cout << "Enter a string or hit enter to end program: ";
	getline(cin, input);
	while (input.length() != 0) {
		string output = swapCase(input);
		cout << "The new string is: " << output << endl;
		cout << "Enter a string or hit enter to end program: ";
		getline(cin, input);
	}

	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;

	*/

	//chapter 7.
    /*
	int n = 8;
	bool wasReadIn[50];
	int input;	
	int wasReadIn2[50];
	for (int h = 0; h < 50; h++) {
		wasReadIn2[h] = 0;
	}
	for (int x = 0; x <= 8; x++) {
		cin >> input;
		wasReadIn2[input]++;
	}
	for (int i = 0; i < 49; i++) {
		if (wasReadIn2[i] != 0) {
			for (int y = 0; y < wasReadIn2[i]; y++) {
				cout << i << " ";
			}
		}
	}
	if (wasReadIn2[49] != 0) {
		for (int y = 0; y < wasReadIn2[49] - 1; y++) {
			cout << 49 << " ";
		}
		cout << 49; 
	}
	//pause is not for the submition

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	/*
	int aux[] = { 5, 1, 3, 7, 4, 6, 2};
	reverse(aux, 7);
	for (int x = 0; x < 7; x++) {
		cout << aux[x] << " ";
	}
	

	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	//chapter 7. Programming assigment.
	/*
	int n;
	int values[80];
	cout << "Enter the number of values:";
	cin >> n;
	for (int x = 1; x < n + 1; x++) {
		cout << "Enter the " << x << "th of " << n << " values:";
		cin >> values[x];
	}
	if (isConsecutiveFour(values, n)) {
		cout << "The list has consecutive fours" << endl;
	}else {
		cout << "The list has no consecutive fours" << endl;
	}

	//pause is not for submition
	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	//chapter 8. Programming Excersice;
	/*
	double arr[3][4];
	int location[2];
	for (int x = 0; x < 3; x++) {
		cout << "Enter row " << x << ":";
		for (int y = 0; y < 4; y++) {
			cin >> arr[x][y];
		}
	}
	locateLargest(arr, location);
	cout << " The location of the largest element is at" << "(" << location[0] << ", " << location[1] << ")" << endl;

	//pause is not for submition
	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
	*/

	//int list[5] = { 0, 5, 10, 15, 20 };
	//int j;

	//for (j = 1; j <= 5; j++)
	//cout << list[j] << " ";
	//cout << endl;

	//char name[8] = "William";
	//cout << name;

	//char str[15];
	//str[15] = "Blue Sky";
	//cout << str;

	//char charArray[51];
	//char discard;
	//cin.get(charArray, 51);
	//cin.get(discard);
	//cout << discard;

	//int list[5] = { 0, 5, 10, 15, 20 };
	//int j;

	//for (j = 0; j < 5; j++)
	//	cout << list[j] << " ";
	//cout << endl;

	//char str[] = "Hello there";

	//chapter 10. Excersice
	/*
	Student aux[number];
	string first;
	string last;
	float gpa;
	for (int x = 0; x < number; x++) {
		cin >> first;
		cin >> last;
		cin >> gpa;
		aux[x] = Student(first, last, gpa);
	}
	aux[0].sort(aux);
	for (int x = 0; x < number; x++) {
		aux[x].print();
	}
	*/

	//chapter 11. Excercise 11.3	
	/*
	int input;
	int size;
	cout << "Enter the number of elements for the array:";
	cin >> size;	
	int *a = new int[size];
	cout << "Enter " << size << " int values:";
	for (int x = 0; x < size; x++) {
		cin >> input;
		a[x] = input;
	}
	a = double_capacity(a, size);
	cout << "Enter " << size << " more int values:";
	for (int x = size; x < 2*size; x++) {
		cin >> input;
		a[x] = input;
	}
	cout << "The elements of the array are:";
	for (int x = 0; x < 2 * size - 1; x++) {
		cout << a[x] << " ";
	}
	cout << a[2 * size - 1] << endl;
	*/

	//chapter 12. Excercise 12.1
	/*
	int arr[5];
	cout << "Enter 5 integer values: ";
	for (int x = 0; x < 5; x++) {
		cin >> arr[x];
	}
	cout << "The maximum value is: " << max(arr, 5) << endl;
	double arr2[5];
	cout << "Enter 5 double values: ";
	for (int x = 0; x < 5; x++) {
		cin >> arr2[x];
	}
	cout << "The maximum value is: " << max(arr2, 5) << endl;
	string arr3[5];
	cout << "Enter 5 string values: ";
	for (int x = 0; x < 5; x++) {
		cin >> arr3[x];
	}
	cout << "The maximum value is: " << max(arr3, 5) << endl;
	*/

	//chapter 13. Exercise 13.6
	/*
	fstream i("input.txt", ios::out);
	for (int x = 75; x < 80; x++) {
		i << char(x) << char(x) << " ";
	}
	i << "XX" << " ";
	for (int x = 81; x < 83; x++) {
		i << char(x) << char(x) << " ";
	}
	i << "ZZ";
	i.close();
	string one, two;
	string aux[100];
	i.open("input.txt", ios::in);
	int x = 0;
	while (!i.eof()) {
		i >> aux[x];
		x++;
	}
	bool done = false;
	for (int i = 0; i < x - 2; i++) {
		if (aux[i] > aux[i + 1] && done == false) {
			cout << aux[i] << " " << aux[i + 1] << endl;
			done = true;
		}
	}
	*/

	//chapter 14.

	/*
	Circle circles[3];
	double input;
	cout << "Enter the radius of the first circle: ";
	cin >> input;
	circles[0].setRadius(input);
	cout << "Enter the radius of the second circle: ";
	cin >> input;
	circles[2].setRadius(input);
	cout << "Enter the radius of the third circle: ";
	cin >> input;
	circles[1].setRadius(input);
	Circle temp;
	for (int x = 0; x < 2; x++) {
		if (circles[x] > circles[x + 1]) {
			temp = circles[x];
			circles[x] = circles[x + 1];
			circles[x + 1] = temp;
		}
		if (circles[0] > circles[2]) {
			temp = circles[0];
			circles[0] = circles[2];
			circles[2] = temp;
		}
	}
	cout << circles[0].getRadius() << ", " << circles[1].getRadius() << ", " << circles[2].getRadius();
	
	*/

	//chapter 15.
	/*
	double one, two, three;
	cout << "Enter the three sides of a triangle: ";
	cin >> one;
	cin >> two;
	cin >> three;
	Triangle tr(one, two, three);
	string col;
	cout << "Enter the color of the triangle: ";
	cin >> col;
	tr.setColor(col);
	char f;
	cout << "Enter 'y' if the triangle is filled, 'n' if it isn't: ";
	cin >> f;
	if (f == 'y') {
		tr.setFilled(true);
	}else {
		tr.setFilled(false);
	}
	cout << "The triange perimeter is: " << tr.getPerimeter() << endl;
	cout << "The triangle area is: " << tr.getArea() << endl;
	cout << "The triangle color is: " << tr.getColor() << endl;
	if (tr.isFilled() == true) {
		cout << "The triangle is filled " << endl;
	}
	else {
		cout << "The triangle is not filled" << endl;
	}

	*/

	//chapter 17
	//cout << add();
	int input;
	cout << "Enter an integer: ";
	cin >> input;
	cout << reverse_int(input) << endl;
	//pause is not for submition
	cout << "Enter pause character: ";
	char pause;
	cin >> pause;
	return 0;
}

void locateLargest(const double  arr[][4], int  location[]) {
	double max = arr[0][0];
	location[0] = 0;
	location[1] = 0;
	for (int x = 0; x < 3; x++) {
		for (int y = 0; y < 4; y++) {
			if (arr[x][y] > max) {
				max = arr[x][y];
				location[0] = x;
				location[1] = y;
			}
		}
	}
}

bool isConsecutiveFour(const int  values[], int  size) {
	int temp[80];
	for (int i = 0; i < size; i++) {
		temp[i] = 0;
	}

	for (int i = 0; i < size; i++) {
		temp[values[i]]++;
		if (temp[values[i]] == 4) {
			return true;
		}
	}
	for (int i = 0; i < 80; i++) {
		if (temp[i] == 4) {
			return true;
		}
	}
	return false;
}

string swapCase(const string & s) {
	string aux = s;
	for (int x = 0; x < aux.length(); x++) {
		char temp = aux.at(x);
			if (islower(temp)) {
				temp = toupper(temp);
				aux[x] = temp;
			}
			else {
				temp = tolower(temp);
				aux[x] = temp;
			}
	}
	return aux;
}

void reverse(int ar[], int n) {
	for (int i = 0; i < n / 2; i++) {
		int temp = ar[i];
		ar[i] = ar[n - i - 1];
		ar[n - i - 1] = temp;
	}
}

int *double_capacity(const int *list, int size) {
	int *temp = new int[2 * size];
	for (int x = 0; x < size; x++) {
		temp[x] = list[x];
	}	
	return temp;
}

template<typename T>
T max(const T arr[], int size) {
	T max = arr[0];
	for (int x = 0; x < size; x++) {
		if (arr[x] > max) {
			max = arr[x];
		}
	}
	return max;
}

void fscopy(fstream& input, fstream& output) {
	if (input.good() && output.good()) {
		string temp;
		while (getline(input, temp)) {
			output << temp << endl;
		}
		input.close();
		output.close();
	}
}

int add() {
	int input = 0;
	cin >> input;
	if (input != 0) {
		input += add();
		return input;
	}
	else {
		return 0;
	}
}