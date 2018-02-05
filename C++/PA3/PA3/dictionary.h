

#ifndef __DICT_H
#define __DICT_H

#include "hashtable.h"
#include <fstream>
#include "word.h"
#include <string>
#include <iostream>
#include "rapidjson-master/include/rapidjson/document.h"

using namespace rapidjson;
class Dictionary
{

private:
	Hashtable<string, Word> _dict;  // Primary dictionary store

//TODO
	
	void parseline(string line) {
		
		switch (hashIt(line))
		{
		case 1: 
			help();
			break;
		case 2:
			string *s[2];
			getCont(line, s);
			_dict.insert(*s[0], Word(*s[0], *s[1]));
			break;
		case 3:
			getCont(line, s);
			_dict.remove(*s[0]);
			break;
		case 4: 
			std::cout << (_dict.find(line.substr(6, line.length())) != nullptr) ? (_dict.find(line.substr(6, line.length()))->to_string()) : ("unknown word");
			break;
		case 5: 	
			//string* input;
			//Json(line.substr(5, line.length()), input);
			//Document document;
			//document.Parse(json);
			break;
		case 7: 
			_dict.size();
			break;
		case 8: 
			_dict.clear();
			break;
		case 9:
			_dict.print();
			break;
		case 10:
			_dict.print(std::stoi(line.substr(4, line.length())));
			break;
		case 11:
			cout << _dict.getRandom().to_string();
		case 12: 
			std::exit(EXIT_FAILURE);
			break;
		}
	}

	void Json(string s, string *o)
	{
		ifstream json(s, std::ifstream::binary);
		while (!json.eof())
		{
			std::getline(json, *o);
		}
	}
	int hashIt(string line)
	{
		if (line == "help") return 1;
		if (line.substr(0, 2) == "add") return 2;
		if (line.substr(0, 5) == "remove") return 3;
		if (line.substr(0, 5) == "define") return 4;
		if (line.substr(0, 3) == "load") return 5;
		if (line.substr(0, 5) == "unload") return 6;
		if (line == "size") return 7;
		if (line == "clear") return 8;
		if (line == "print") return 9;
		if (line.substr(0, 4) == "print") return 10;
		if (line == "random") return 11;
		if (line == "quit") return 12;
	}
	void getCont(string s, string *f[])
	{
		for (int i = 0; i != 1; i++)
		{
			if (s[0] != '"')
			{
				for (int x = 0; x < s.length(); x++)
				{
					if (s[x] == ' ')
					{
						*f[i] = s.substr(0, x - 1);
						break;
					}
				}
			}
			else {
				for (int x = 1; x < s.length(); x++)
				{
					if (s[x] == '"')
					{
						*f[i] = s.substr(1, x - 1);
						break;
					}
				}
			}
		}
	}
	public:
		Dictionary()	// Default constructor
		 { }

		 // Help fucntion when calling help command. Sorry for the big std::cout statement. I formated as much as I could.
		 void help()
		 {
			cout << "help -> print out command help" <<
			"/nadd /“word/” /“definition/” -> Add (or update!) a word and it’s definition. Must handle quotes" <<
			"/nremove /“word/” -> Remove a given word. Must handle quotes" <<
			"/ndefine /“word/” -> Define a word by printing out it’s definition or /“unknown word/”" <<
			"/nload /“filename/” -> Load in a JSON file of dictionary words" <<
			"/nunload /“filename/” -> Remove words from a given JSON file of dictionary words" <<
			"/nsize -> Print out current number of words in the dictionary" <<
			"/nclear -> Remove ALL words from the dictionary" <<
			"/nprint [#words] -> Print out all words, unless user gives a maximum number" <<
			"/nrandom -> Print out a single word chosen randomly from the dictionary" <<
			"/nquit -> Quit the user interface and shut down";
		 }
	/**
	 *  Run the main dictionary user interface
	 */
	void run_ui() {
		// print out header
		cout << "+------------------------------------------+" << endl;
		cout << "|-- Welcome to the best dictionary evar! --|" << endl;
		cout << "+------------------------------------------+" << endl;

		string instr;
		cout << " Enter command (^D or EOF quits): ";

		// read in user input until eof
		while (getline(cin, instr)) {
			cout << "Entered: " << instr << endl;
			parseline(instr);
			// call function based on line contents
			// print results

			cout << "Enter command: ";
		}
		cout << endl;
	}

};



#endif
