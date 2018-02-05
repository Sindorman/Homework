#pragma once
#include<string>
#include<iostream>
using std::string;
class UCASEstring: public std::string
{
public:
	UCASEstring(string a):std::string(a) {
		s = a;
		this->s_size = a.size();
	}
	~UCASEstring() {
	
	}
	UCASEstring(const UCASEstring& u):std::string(u) {
		s = u.getString();
		this->s_size = u.size();
	}
	char& operator[](int index) {
		if (index < 0 || index > s.size()) {
			throw std::out_of_range("index is out of range");
		}
		return s[index];
	}	

	int compareTo(UCASEstring& u) {
		if (u.size() != s.size()) {
			return s.size() - u.size();
		}
		else {
			for (int x = 0; x < s.size(); x++) {
				if (s[x] != u[x]) {
					return toupper(s[x]) - toupper(u[x]);
				}
			}
		}
		return 0;
	}	
	string getString() const{
		return s;
	}
	int size() const {
		return this->s_size;
	}
private:
	string s;
	int s_size;
};

bool operator<(UCASEstring& u, UCASEstring& u2) {
	return u.compareTo(u2) < 0;
}

bool operator<=(UCASEstring& u, UCASEstring& u2) {
	return u.compareTo(u2) <= 0;
}

bool operator>(UCASEstring& u, UCASEstring& u2) {
	return u.compareTo(u2) > 0;
}

bool operator>=(UCASEstring& u, UCASEstring& u2) {
	return u.compareTo(u2) >= 0;
}

bool operator==(UCASEstring& u, UCASEstring& u2) {
	return u.compareTo(u2) == 0;
}

bool operator!=(UCASEstring& u, UCASEstring& u2) {
	return u.compareTo(u2) != 0;
}

template<typename T>
void sort(T list[], int listSize) {
	for (int i = 0; i < listSize; i++) {
		T currentMin = list[i];
		int currentMinIndex = i;

		for (int j = i + 1; j < listSize; j++) {
			if (currentMin > list[j]) {
				currentMin = list[j];
				currentMinIndex = j;
			}
		}
		if (currentMinIndex != i) {
			list[currentMinIndex] = list[i];
			list[i] = currentMin;
		}
	}
}
template<typename T>
void printArray(T a[], int size) {
	for (int x = 0; x < size - 1; x++) {
		cout << a[x] << " ";
	}
	cout << a[size - 1] << endl;
}