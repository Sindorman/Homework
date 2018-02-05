// Kickstart_Google_2017.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>
#include <vector>

//std::string convert(std::string a);

void top(int arr[])
{
	std::vector<std::vector<int>> aux(2);
	aux[0] = std::vector<int>(50);
	aux[1] = std::vector<int>(50);
	for (int x = 0; x < sizeof(arr) / sizeof(int); x++)
	{
		aux[0][x] = arr[x];
	}
}

int main()
{
	/*
	int  num;
	std::cin >> num;
	std::string aux[100];
	for (int x = 0; x < num; x++)
	{
		std::cin >> aux[x];
		aux[x] = convert(aux[x]);
	}
	for (int x = 0; x < num; x++)
	{
		std::cout << "Case #" << x + 1 << ": " << ((aux[x].length() == 3) ? ("AMBIGUOUS") : (aux[x])) << std::endl;
	}
	return 0;
	*/
	int test[] = { 3,5,2 };
	top(test);
}

/*std::string convert(std::string a)
{
	std::string temp = a;
	for (int x = 0; x < a.length(); x++)
	{
		if (x == 0 || x == a.length() - 1) {
			temp[(x == 0) ? (x + 1) : (x - 1)] = a[x];
		} else {
			temp[x] = char(((int(a[x - 1]) + int(a[x + 1]) - 130) % 26) + 65);
		}
	}
	return temp;
}
*/
