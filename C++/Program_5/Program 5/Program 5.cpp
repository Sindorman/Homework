#include "stdafx.h"
#include "BST.h"
using namespace std;
// CS212, Spring 2014, Program #5
void main()
{
	// Create a binary search tree for strings
	BST<string> tree;

	// Add elements to the tree
	tree.insert("America");
	tree.insert("Canada");
	tree.insert("Russia");
	tree.insert("France");

	// Create an Iterator
	Iterator<string> iterator = tree.begin();

	tree.insert("Texas"); // modify the tree

						  // Traverse a binary tree using iterators
	while (iterator != tree.end())
	{
		cout << *iterator << " ";
		iterator++;
	}
	// Texas is missing, FIX IT!!!!
	system("pause");
}