#include "stdafx.h"
#include "Rectangle2D.h"


Rectangle2D::Rectangle2D()
{
	x = 0;
	y = 0;
	width = 1;
	height = 1;
}

Rectangle2D::Rectangle2D(double x, double y, double width, double height)
{
	this->x = x;
	this->y = y;
	this->width = width;
	this->height = height;
}

void Rectangle2D::setX(double x) {
	this->x = x;
}

void Rectangle2D::setY(double y) {
	this->y = y;
}

void Rectangle2D::setWidth(double width) {
	this->width = width;
}

void Rectangle2D::setHeight(double height) {
	this->height = height;
}

double Rectangle2D::getX() const {
	return x;
}

double Rectangle2D::getY() const {
	return y;
}

double Rectangle2D::getWidth() const {
	return width;
}

double Rectangle2D::getHeight() const {
	return height;
}

double Rectangle2D::getPerimeter() const {
	return 2 * width + 2 * height;
}

double Rectangle2D::getArea() const {
	return width * height;
}

bool Rectangle2D::contains(double x, double y) const {
	double width = this->width;
	double height = this->height;
	return ((x >= this->x - width / 2 && x <= this->x + width / 2) && (y >= this->y - height / 2 && y <= this->y + height / 2));
}

bool Rectangle2D::contains(const Rectangle2D &r) const {
	return (r.getX() - r.getWidth() / 2 >= this->x - this->width / 2 && r.getX() + r.getWidth() / 2 <= this->x + this->width / 2) && (r.getY() - r.getHeight() / 2 >= this->y - this->height / 2 && r.getY() + r.getHeight() / 2 <= this->y + this->height / 2);
}

bool Rectangle2D::overlaps(const Rectangle2D &r) const {
	if (contains(r.getX() - r.getWidth() / 2, r.getY() + r.getHeight() / 2)) {
		return true;
	}
	else if (contains(r.getX() + r.getWidth() / 2, r.getY() + r.getHeight() / 2)) {
		return true;
	}	else if (contains(r.getX() - r.getWidth() / 2, r.getY() - r.getHeight() / 2)) {		return true;	}	else if (contains(r.getX() + r.getWidth() / 2, r.getY() - r.getHeight() / 2)) {		return true;	}	else {		return false;	}
}

Rectangle2D getRectangle(const double a[][SIZE], int x) {
	double minX = a[0][0];
	double maxX = a[4][0];
	double minY = a[0][1];
	double maxY = a[4][1];
	for (int i = 0; i < x; i++) {
		if (a[i][0] < minX) {
			minX = a[i][0];
		}
		if (a[i][0] > maxX) {
			maxX = a[i][0];
		}
		if (a[i][1] < minY) {
			minY = a[i][1];
		}
		if (a[i][1] > maxY) {
			maxY = a[i][1];
		}
	}
	Rectangle2D aux((maxX + minX) / 2, (maxY + minY) / 2, maxX - minX, maxY - minY);
	return aux;
}

Rectangle2D::~Rectangle2D()
{	
	/*
	delete x;
	delete y;
	delete height;
	delete width;
	*/
}
