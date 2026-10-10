#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>

/*
3
.*..***.***.
.*....*...*.
.*..***.***.
.*..*.....*.
.*..***.***.
*/

using namespace std;

int getDigitFromPattern(const string& pattern)
{
	// Define a vector of vectors of strings to represent the patterns
	unordered_map<string, int> patternMap = {
		{ ".*..*..*..*..*.", 1 },
		{ "***..*****..***", 2 },
		{ "***..****..****", 3 }
	};
	string key;
	for (const auto& row : pattern)
	{
		key += row;
	}
	return patternMap.at(key);
}

string buildNumberFromPatterns(const vector<string>& input, const int n)
{
	string result = "";
	for (int col = 0; col < 4*n; col += 4)
	{
		string pattern = "";
		for (int row = 0; row < 5; row++)
		{
			pattern += input[row].substr(col, 3);
		}
		int digit = getDigitFromPattern(pattern);
		result += to_string(digit);
	}
	return result;
}

int main()
{
	int n;
	cin >> n;
	vector<string> input(5);
	for (int i = 0; i < 5; i++)
	{
		cin >> input[i];
	}

	cout << buildNumberFromPatterns(input, n) << endl;

    return 0;
}