// program4.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Sorting.h"
using namespace std;
void main()
{
	int list1[] = { 3, 5, 1, 0, 2, 8, 3 };
	printArray(list1, 7);
	sort(list1, 7);
	printArray(list1, 7);
	cout << endl;

	double list2[] = { 3.5, 0.5, 1.4, 0.4, 2.5, 1.4 };
	printArray(list2, 6);
	sort(list2, 6);
	printArray(list2, 6);
	cout << endl;

	string list3[] = { "Denver", "Apple", "apply", "Dallas", "app" };
	printArray(list3, 5);
	sort(list3, 5);
	printArray(list3, 5);
	cout << endl;

	UCASEstring list4[] = { "Denver", "Apple", "apply", "Dallas", "app" };
	printArray(list4, 5);
	sort(list4, 5);
	printArray(list4, 5);
	system("pause");
}

