#pragma once
#include<string>
#include<vector>
using namespace std;

template<typename T>
std::vector<T>& operator<<(std::vector<T> &v1, char in[])
{
	std::string temp = "";
	int i = 0;

	while (in[i] != 0)
	{
		temp += in[i++];
	}

	v1.push_back(temp);
	return v1;
}

inline std::ostream& operator<<(std::ostream& out, std::string& str)
{
	int i = 0;
	while (str[i] != 0)
	{
		out.put(str[i++]);
	}
	return out;
}

template<typename T>
vector<T> intersection(const vector<T>& v1, const vector<T>& v2) {	
	vector<T> aux;
	for (int x = 0; x < v1.size(); x++) {
		for (int y = 0; y < v2.size(); y++) {
			if (v1[x] == v2[y]) aux.push_back(v1[x]);
		}
	}
	return aux;
}
