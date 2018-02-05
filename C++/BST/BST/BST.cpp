// BST.cpp : Defines the entry point for the console application.
//

// stdafx.cpp : source file that includes just the standard includes
// BST.pch will be the pre-compiled header
// stdafx.obj will contain the pre-compiled type information

#include "stdafx.h"
/*
* Binary Search Tree implementation - heavily lifted from https://gist.github.com/mgechev/5911348
*
* Simple integer keys and basic operations
* Now also doing testing & height calculations
*
* Aaron Crandall - 2016 - Added / updated:
*  * Inorder, Preorder, Postorder printouts
*  * Stubbed in level order printout
*  * Also doing height calculations
*
*/

#include <fstream>
#include <iostream>
#include <queue>
#include <cstring>
#include "BST.h"
#include "TestData.h"
using namespace std;

/*
*  Interface to run all tests if --test is passed on command line
*/
void runTests() {
	cout << " [x] Running tests. " << endl;
	TestData *testing = new TestData();			// Create object with testing data and interfaces

	BST<int> *bst_test = new BST<int>;

	/* Should make a tree like this:
	10
	5          14
	1      7           17
	19
	18

	*/
	int testval = testing->get_next_minitest();
	while (testval >= 0) {
		bst_test->add(testval);
		testval = testing->get_next_minitest();
	}
	cout << " [x] Should print out in pre order: 10 5 1 7 14 17 19 18 " << endl;
	cout << " [x] Resulting print out test:      ";
	bst_test->printPreOrder();
	cout << endl;

	cout << " [x] Should print a tree height of  : 5" << endl;
	cout << " [x] Currently calculating height of: ";
	cout << bst_test->height();
	cout << endl;
}


/*
* Generate the CSV file for the project output here
*/
void genCSV() {
	cout << " [x] Generating CSV output file. " << endl;
	//cout << " [!!!] UNIMPLEMENTED - Need to generate the CSV file based on the tree height growth." << endl;

	/*  Sample of how to use the TestData structure for getting the test data sets
	int sorted = testing->get_next_sorted();
	while(sorted >= 0){
	bst_sorted->add( sorted );
	sorted = testing->get_next_sorted();
	}
	cout << "Sorted height: " << bst_sorted->height() << endl;
	*/
	ofstream csv;
	TestData *testing = new TestData();
	csv.open("OutputData-BST.csv");
	csv << "N,log_2(N),Sorted,Balanced,Scrambled #0,Scrambled #1,Scrambled #2,Scrambled #3,Scrambled #4";
	BST<int> *bst_sorted = new BST<int>;
	BST<int> *bst_balanced = new BST<int>;
	BST<int> *bst_scrambled1 = new BST<int>;
	BST<int> *bst_scrambled2 = new BST<int>;
	BST<int> *bst_scrambled3 = new BST<int>;
	BST<int> *bst_scrambled4 = new BST<int>;
	BST<int> *bst_scrambled5 = new BST<int>;
	int sorted = testing->get_next_sorted();
	int balanced = testing->get_next_balanced();
	int scrambled[5];
	while (sorted >= 0) {
		bst_sorted->add(sorted);
		csv << bst_sorted->getSize() << "," << log2(bst_sorted->getSize()) << ",";
		sorted = testing->get_next_sorted();
		csv << bst_sorted->height() << ",";
		bst_balanced->add(balanced);
		csv << bst_balanced->height() << ",";
		balanced = testing->get_next_balanced();
		scrambled[0] = testing->get_next_scrambled(0);
		bst_scrambled1->add(scrambled[0]);
		csv << bst_scrambled1->height() << ",";
		scrambled[1] = testing->get_next_scrambled(1);
		bst_scrambled2->add(scrambled[1]);
		csv << bst_scrambled2->height() << ",";
		scrambled[2] = testing->get_next_scrambled(2);
		bst_scrambled3->add(scrambled[2]);
		csv << bst_scrambled3->height() << ",";
		scrambled[3] = testing->get_next_scrambled(3);
		bst_scrambled4->add(scrambled[3]);
		csv << bst_scrambled4->height() << ",";
		scrambled[4] = testing->get_next_scrambled(4);
		bst_scrambled5->add(scrambled[4]);
		csv << bst_scrambled5->height() << "\n";
	}
	csv.close();


	// make a file: OutputData-BST.csv
	// make 7 trees (sorted, balanced, scrambled[0..4])
	// fill trees with data from TestData
	//  -- as you fill, get the heights and output to CSV file: log_2 N, height sorted, height balanced, height scrambled[0..4]
	//  -- fill until the get_next_* functions return -1
}


/*
*   Main function for execution
*/
/*
int main(int argc, char* argv[]) {
	if (argc > 1 && !strcmp(argv[1], "--test"))
	{
		cout << " [x] Program in test mode, doing simple tests. " << endl;
		runTests();  // testing should just do a simple tree insert set and search
	}
	else
	{
		cout << " [x] Program built and running in full CSV generation mode. " << endl;
		genCSV();
	}
	return 0;
}
*/



int main()
{
	//genCSV();
	int arr [13] = { 100, 150, 50, 125, 152, 3, 80, 1, 20, 52, 90, 83, 99};
	BST<int> *temp = new BST<int>(arr, 13);
	cout << "height: " << temp->height();
	temp->printPostOrder();
	temp->printInOrder();
	temp->printPreOrder();
	system("pause");
    return 0;
}

