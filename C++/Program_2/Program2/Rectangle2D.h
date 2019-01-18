#pragma once
class Rectangle2D
{
public:
	Rectangle2D();
	Rectangle2D(double x, double y, double width, double height);
	~Rectangle2D();
	void setX(double x);
	void setY(double y);
	void setWidth(double width);
	void setHeight(double height);
	double getX() const;
	double getY() const;
	double getWidth() const;
	double getHeight() const;
	double getArea() const;
	double getPerimeter() const;
	bool contains(double x, double y) const;
	bool contains(const Rectangle2D &r) const;
	bool overlaps(const Rectangle2D &r) const;

private:
	double x;
	double y;
	double width;
	double height;
};

const int SIZE = 2;
Rectangle2D getRectangle(const double a[][SIZE], int x);
