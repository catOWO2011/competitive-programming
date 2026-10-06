#include <iostream>
#include <vector>
#include <sstream>
#include <string>

using namespace std;

/*
1

3 1 2
32.0 54.7 -2
*/

int main()
{
    int T;
	cin >> T;
	cin.ignore();

	for (int t = 0; t < T; ++t)
	{
		string line;
		while (getline(cin, line) && line.empty());

		istringstream iss(line);
		vector<int> indexArray;
		int value;
		while (iss >> value)
			indexArray.push_back(value);

		getline(cin, line);
		
		iss.str(line);
		iss.clear();
		vector<string> values(indexArray.size());
		for (int i = 0; i < indexArray.size(); ++i)
		{
			iss >> values[indexArray[i] - 1];
		}

		if (t > 0) {
			cout << endl;
		}

		for (int i = 0; i < values.size(); ++i)
		{
			cout << values[i] << endl;
		}
	}
    return 0;
}