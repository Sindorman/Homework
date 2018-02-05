/**
 *  Basic hash table implementation
 *   Aaron S. Crandall - 2017 <acrandal@gmail.com>
 *
 */

#ifndef __HASH_H
#define __HASH_H

#include <list>
#include <vector>
#include <string>
#include <iostream>
#include <algorithm>

using namespace std;
/*
	private:
		void rehash();
		int hash_function(KEYTYPE key);

	public:
		bool insert(KEYTYPE key, VALTYPE val);
		bool contains(KEYTYPE key);
		int remove(KEYTYPE key);
		VALTYPE* find(KEYTYPE key);
		int size();            // Elements currently in table
		bool empty();          // Is the hash empty?
		float load_factor();   // Return current load factor
		void clear();          // Empty out the table
		int bucket_count();    // Total number of buckets in table
*/

template <typename KEYTYPE, typename VALTYPE>
class Hashtable
{
	private:
		vector<std::list<VALTYPE>> _hash;
		//vector< type > _hash;

		/**
		 *  Rehash the table into a larger table when the load factor is too large
		 */
		 //TODO

		void rehash() {
			_hash.resize(NextPrime(loadSize * 2));
			for (int x = 0; x < loadSize; x++)
			{

					for (auto y = _hash[x].begin(); y != _hash[x].end(); y++)
					{
						insert(y->myword, *y);
					}
			}
			loadSize = NextPrime(loadSize * 2);
		}

		/**
		 *  Function that takes the key (a string or int) and returns the hash key
		 *   This function needs to be implemented for several types it could be used with!
		 */
		int hash_function(int key) {
			return key % loadSize;
		}

		unsigned int hash_function(string key) {
			unsigned int hashVal = 0;

			for (char ch : key)
				hashVal = 37 * hashVal + ch;

			return hashVal % loadSize;
		}

		// Basic isPrime function.

		bool IsPrime(int number)
		{

		    if (number == 2 || number == 3)
		        return true;

		    if (number % 2 == 0 || number % 3 == 0)
		        return false;

		    int divisor = 6;
		    while (divisor * divisor - 2 * divisor + 1 <= number)
		    {

		        if (number % (divisor - 1) == 0)
		            return false;

		        if (number % (divisor + 1) == 0)
		            return false;

		        divisor += 6;

		    }

		    return true;

		}

		//Basic next prime number function, for internal use only for getting prime size.

		int NextPrime(int a)
		{

		    while (!IsPrime(++a))
		    { }
		    return a;

		}
		int loadSize; // current size of the table. Starts at 101
		int realSize; // total number of the elements in the table
		int elementSize; // number of the bucket at the hashtable, aka linked lists

	public:
		/**
		 *  Basic constructor
		 */
		Hashtable() : loadSize(101), realSize(0), elementSize(0) {
			_hash.resize(101);
		};

		// constructor with custom size. Used for rehashing mostly.
		Hashtable(int size)
		{
			loadSize = NextPrime(size);
		}

		/**
		 *  Add an element to the hash table
		 */
		 //TODO

		bool insert(KEYTYPE key, VALTYPE val) {
			int t = hash_function(key);
			if (contains(key))
			{
				for (auto x = _hash[t].begin(); x != _hash[t].end(); x++)
				{
					if (x->definition == val.definition)
					{
					 x->definition = val.definition;
					 realSize++;
					 if (load_factor() > 1) rehash();
					 return true;;
				 	}
				}
			}else {
				_hash[t].push_back(val);
				realSize++;
				if (load_factor() > 1) rehash();
				return true;
			}
			// Currently unimplemented
			return false;
		}

		/**
		 *  Return whether a given key is present in the hash table
		 */

		 //TODO

		bool contains(KEYTYPE key) {
			if (_hash.size() == 0) return false;
			int x = hash_function(key);
				for (auto y =  _hash[x].begin(); y != _hash[x].end(); y++)
				{
					if (_hash[x].empty() == false && y->myword == key) return true;
				}
			return false;
		}


		/**
		 *  Completely remove key from hash table
		 *   Returns number of elements removed
		 */
		 //TODO

		int remove(KEYTYPE key) {
			int x = hash_function(key);
				for (auto y =  _hash[x].begin(); y != _hash[x].end(); y++)
				{
					if (toupper(y->myword) == toupper(key))
					{
						y = _hash[x].erase(y);
						realSize--;
						return 1;
					}
				}
			return 0;
		}

		/**
		 *  Searches the hash and returns a pointer
		 *   Pointer to Word if found, or nullptr if nothing matches
		 */
		 //TODO

		VALTYPE* find(KEYTYPE key) {
			int x = hash_function(key);
				for (auto y =  _hash[x].begin(); y != _hash[x].end(); y++)
				{
					if (y->myword == key) return &(*y);
				}
			return nullptr;
		}

		/**
		 *  Return current number of elements in hash table
		 */
		int size() {
			return realSize;
		}

		/**
		 *  Return true if hash table is empty, false otherwise
		 */
		bool empty() {
			return realSize == 0;
		}

		/**
		 *  Calculates the current load factor for the hash
		 */
		float load_factor() {
			//return _hash.load_factor();
			return (float)realSize / (float)loadSize;
		}

		/**
		 *  Returns current number of buckets (elements in vector)
		 */
		int bucket_count() {
			return loadSize;
		}

		/**
		 *  Deletes all elements in the hash
		 */
		 //TODO

		void clear() {
			for (int x = 0; x < loadSize; x++)
			{
				_hash[x].clear();
			}
		}

		string toupper(string &i)
		{
			std::transform(i.begin(), i.end(), i.begin(), ::toupper);
			return i;
		}
		void print()
		{
			for (int x = 0; x < loadSize; x++)
			{
				if (_hash[x].empty() == false)
				{
					for (auto y = _hash[x].begin(); y != _hash[x].end(); y++)
					{
						cout << y->myword + " : " + y->definition;
					}
				}
				cout << endl;
			}
		}
		void print(int i)
		{
			for (int x = 0; x < loadSize || i != 0; x++)
			{
				if (_hash[x].empty() == false)
				{
					for (auto y = _hash[x].begin(); y != _hash[x].end(); y++)
					{
						cout << y->myword + " : " + y->definition;
						i--;
					}
				}
				cout << endl;
			}
		}
		VALTYPE getRandom()
		{
			do {
				int r = rand() % loadSize + 1;
				if (_hash[r].empty() == false) {
					auto t = _hash[r].end();
					return *t;
				}
			} while (true);
		}
};


#endif
