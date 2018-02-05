#include<string>
#include<iostream>
#include<math.h>
using namespace std;
#pragma once
class Complex
{
public:
	Complex(double a, double b) {
		this->a = a;
		this->b = b;
	}
	Complex(double a) {
		this->a = a;
		this->b = 0;
	}
	Complex() {
		a = 0;
		b = 0;
	}
	Complex add(const Complex& c) const{
		return Complex(c.getA() + a, c.getB() + b);
	}
	Complex subtract(const Complex& c) const{
		return Complex(a - c.getA(), b - c.getB());
	}
	Complex multiply(const Complex& c) const{
		return Complex(a * c.getA() - c.getB() * b, a * c.getA() + c.getB() * b);				
	}
	Complex divide(const Complex& c) const{
		return Complex((a * c.getA() + c.getB() * b) / ((c.getA()*c.getA() + c.getB()*c.getB())), (b * c.getA() - a * c.getB()) / ((c.getA()*c.getA() + c.getB()*c.getB())));		
	}
	string toString() {
		if (b == 0) {
			return std::to_string(a);
		}
		return std::to_string(a) + "+ " + std::to_string(b) + " i";		
	}
	double abs() {
		return sqrt(a * a + b * b);
	}
	double& operator[](int index) {
		if (index == 0) {
			return a;
		}
		else if (index == 1) {
			return b;
		}
		else {
			throw new std::out_of_range("Index out of range");
		}
	}
	Complex& operator++() {
		a++;
		b++;
		return *this;
	}
	Complex operator++(int dummy) {
		Complex temp(a, b);
		a++;
		b++;
		return temp;
	}
	Complex& operator--() {
		a--;
		b--;
		return *this;
	}
	Complex operator--(int dummy) {
		Complex temp(a, b);
		a--;
		b--;
		return temp;
	}
	Complex& operator+=(const Complex& c) {
		*this = add(c);
		return *this;
	}
	Complex& operator-=(const Complex& c) {
		*this = subtract(c);
		return *this;
	}
	Complex& operator*=(const Complex& c) {
		*this = multiply(c);
		return *this;
	}
	Complex& operator/=(const Complex& c) {
		*this = divide(c);
		return *this;
	}
	
	double getA() const{ return a; }
	double getB() const{ return b; }
	~Complex();

private:
	double a;
	double b;
};

Complex operator+(const Complex& c1, const Complex& c2) {
	return c1.add(c2);
}
Complex operator-(const Complex& c1, const Complex& c2) {
	return c1.subtract(c2);
}
Complex operator*(const Complex& c1, const Complex& c2) {
	return c1.multiply(c2);
}
Complex operator/(const Complex& c1, const Complex& c2) {
	return c1.divide(c2);
}
ostream& operator<<(ostream& out, const Complex& c) {
	if (c.getB() == 0) {
		out << c.getA();
	}
	else {
		out << c.getA() << " + " << c.getB() << " i";
	}
	return out;
}


