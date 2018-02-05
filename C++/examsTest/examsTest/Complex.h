#include <sstream>
#include <cmath>
#include <string>
#include <iostream>
using namespace std;
// Liang Complex Number example
// W.P. Iverson, CS212, Spring 2014
// header file:
class Complex
{
public:
  Complex();
  Complex(double, double);
  Complex(double);
  double getA() const;
  double getB() const;
  Complex add(const Complex&) const;
  Complex subtract(const Complex&) const;
  Complex multiply(const Complex&) const;
  Complex divide(const Complex&) const;
  double abs() const;
  string toString() const;

  Complex &operator+=(Complex &secondComplex);
  Complex &operator-= (Complex &secondComplex);
  Complex &operator*=(Complex &secondComplex);
  Complex &operator/=(Complex &secondComplex);

  double &operator[](const int & index);

  Complex &operator++(); // Prefix ++
  Complex &operator--(); // Prefix ++

  Complex operator++(int dummy); // Postfix ++
  Complex operator--(int dummy); // Postfix --

  Complex operator+(); // Unary +  
  Complex operator-(); // Unary - 
  Complex operator = (Complex);

  friend ostream &operator<<(ostream &stream, const Complex &complex);
  //friend Complex& operator<<(Complex&, string); // not on this example
  friend Complex& operator<<(Complex&, const int);
  friend istream &operator>>(istream &stream, Complex &complex);

private:
  double a;
  double b;
};

// testing the assignment operator
Complex Complex::operator = (Complex second)
{
	cout << "calling my complex = l-value is " << this->a << endl;
	a = second.a;
	b = second.b;
	return second;
}

// non-member functions:
Complex operator+(const Complex&, const Complex&);
Complex operator-(const Complex&, const Complex&);
Complex operator*(const Complex&, const Complex&);
Complex operator/(const Complex&, const Complex&);









// implementations below:
Complex& operator<<(Complex& left, const int right)
{
	left.a = right;
	left.b = 0;
	return left;
}

Complex operator+(const Complex &c1, const Complex &c2) 
{
  return c1.add(c2);
}

Complex operator-(const Complex &c1, const Complex &c2)  
{
  return c1.subtract(c2);
}

Complex operator*(const Complex &c1, const Complex &c2)  
{
  return c1.multiply(c2);
}

Complex operator/(const Complex &c1, const Complex &c2) 
{
  return c1.divide(c2);
}

Complex::Complex()
{
  a = 0;
  b = 0;
}

Complex::Complex(double a, double b)
{
  this->a = a;
  this->b = b;
}

Complex::Complex(double a)
{
  this->a = a;
  this->b = 0;
}

double Complex::getA() const 
{
  return a;
}

double Complex::getB() const 
{
  return b;
}

Complex Complex::add(const Complex &secondComplex) const 
{
  double newA = a + secondComplex.getA();
  double newB = b + secondComplex.getB();
  return Complex(newA, newB);
}

Complex Complex::subtract(const Complex &secondComplex) const 
{
  double newA = a - secondComplex.getA();
  double newB = b - secondComplex.getB();
  return Complex(newA, newB);
}

Complex Complex::multiply(const Complex &secondComplex) const 
{
  double newA = a * secondComplex.getA() - b * secondComplex.getB();
  double newB = b * secondComplex.getA() + a * secondComplex.getB();
  return Complex(newA, newB);
}

Complex Complex::divide(const Complex &secondComplex) const 
{
  double newA = (a * secondComplex.getA() + b * secondComplex.getB()) / (pow(secondComplex.getA(), 2.0) + pow(secondComplex.getB(), 2.0));
  double newB = (b * secondComplex.getA() - a * secondComplex.getB()) / (pow(secondComplex.getA(), 2.0) + pow(secondComplex.getB(), 2.0));
  return Complex(newA, newB);
}

double Complex::abs() const
{
  return sqrt(a * a + b * b);
}

string Complex::toString() const 
{
  stringstream ss;
  ss << a;
  if (b != 0)
    ss << " + " << b << "i";
  return ss.str();
}

Complex &Complex::operator+=(Complex &secondComplex)
{
  *this = this->add(secondComplex);
  return *this;
}

Complex &Complex::operator-=(Complex &secondComplex)
{
  *this = this->subtract(secondComplex);
  return *this;
}

Complex& Complex::operator*=(Complex &secondComplex)
{
  *this = this->multiply(secondComplex);
  return *this;
}

Complex &Complex::operator/=(Complex &secondComplex)
{
  *this = this->divide(secondComplex);
  return *this;
}

double &Complex::operator[](const int &index)
{
  if (index == 0)
    return a;
  else if (index == 1)
    return b;
  else
  {
    cout << "subscript error" << endl;
    exit(0);
  }
}

Complex &Complex::operator++() // Prefix ++
{
  a += 1;
  return *this;
}

Complex &Complex::operator--() // Prefix --
{
  a -= 1;
  return *this;
}

Complex Complex::operator++(int dummy) // Postfix ++
{
  Complex temp(a, b);
  a += 1;
  return temp;
}

Complex Complex::operator--(int dummy) // Postfix --
{
  Complex temp(a, b);
  a -= 1;
  return temp;
}

Complex Complex::operator+() // Unary +
{
  return *this;
}

Complex Complex::operator-() // Unary -
{
  a *= -1;
  return *this;
}

ostream &operator<<(ostream &str, const Complex &complex)
{
  if (complex.b == 0)
    str << complex.a;
  else
    str << complex.a << " + " << complex.b << "i";
  return str;
}


istream &operator>>(istream & str, Complex &complex)
{
  cout << "Enter a and b for (a + bi): ";
  str >> complex.a;
  str >> complex.b;
  return str;
}
