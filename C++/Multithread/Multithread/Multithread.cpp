// Multithread.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<thread>

char* String;
const int StringLength = 10;

void* UpdateString(void* Character) {

	char NewChar = (char) *((char*)Character);

	int i;

	while (1)
	{
		for (i = 0; i < StringLength; i++)
		{
			String[i] = NewChar;
		}
	}
	return NULL;
}
int main()
{
	String = (char*)malloc(sizeof(char) * (StringLength + 1));
	memset(String, 0, StringLength + 1);
	char A = 'A';
	char B = 'B';
	std::thread thread_id1(UpdateString, A), thread_id2(UpdateString, B);
	while (1)
	{
		printf("%s\n", String);
	}
	return 0;
}

