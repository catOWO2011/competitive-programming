#include <iostream>
#include <vector>
#include <string>

using namespace std;

/*
4
XXXX                XXXXX
XXX               XXXXXXX
XXXXX                XXXX
XX                 XXXXXX
2
XXXXXXXXXXXXXXXXXXXXXXXXX
XXXXXXXXXXXXXXXXXXXXXXXXX
1
XXXXXXXXX              XX
0
*/

int main()
{
    int N;
    while ((cin >> N) && N)
    {
		cin.ignore();
		// Define a vector of strings to store the input lines
		vector<string> lines(N);
		for (int i = 0; i < N; ++i)
		{
			getline(cin, lines[i]);
		}

		int minimumSpaces = 25;
		vector<int> spacesCount(N, 0);
		for (int i = 0; i < N; ++i)
		{
			int spaces = 0;
			for (char c : lines[i])
			{
				if (c == ' ')
				{
					spaces++;
				}
			}
			if (spaces < minimumSpaces)
			{
				minimumSpaces = spaces;
			}
			spacesCount[i] = spaces;
		}

		int spacesToRemove = (minimumSpaces / 2) * 2 + (minimumSpaces % 2 != 0 ? 1 : 0);
		int remainderSpaces = 0;
		for (int i = 0; i < N; ++i)
		{
			if (spacesCount[i] > spacesToRemove)
			{
				remainderSpaces += spacesCount[i] - spacesToRemove;
			}
		}

		cout << remainderSpaces << endl;
    }
    return 0;
}