#include <iostream>
#include <vector>

using namespace std;

/*
10
A ? ? D ? ? ? H ? ?
4
3
8
6
10
*/

int main()
{
	int n;
	cin >> n;
	vector<string> names(n);
	for (int i = 0; i < n; i++)
	{
		cin >> names[i];
	}

	int q;
	cin >> q;
	vector<int> queries(q);
	for (int i = 0; i < q; i++)
	{
		cin >> queries[i];
	}

	vector<int> knowStudentsIndices;
	vector<string> result(n, "");
	for (int i = 0; i < n; i++)
	{
		if (names[i] != "?")
		{
			knowStudentsIndices.push_back(i);
			result[i] = names[i];
		}
	}
	
	for (int i = 0; i < knowStudentsIndices.size(); i++)
	{
		int index = knowStudentsIndices[i];
		
		int middle = -1;
		if (i == 0)
		{
			middle = -1;
		}
		else
		{
			int distance = index - knowStudentsIndices[i - 1] - 1;
			middle = index - (distance / 2) - 1;
		}
		string leftSequence = "";
		for (int m = index - 1; m > middle; m--)
		{
			leftSequence += "left of ";
			result[m] = leftSequence + names[index];
		}

		middle = -1;
		if (i == knowStudentsIndices.size() - 1)
		{
			middle = n;
		}
		else
		{
			int distance = knowStudentsIndices[i + 1] - index - 1;
			middle = index + (distance / 2) + 1;

			if (distance % 2 == 1)
			{
				result[middle] = "middle of " + names[index] + " and " + names[knowStudentsIndices[i + 1]];
			}
		}
		string rightSequence = "";
		for (int m = index + 1; m < middle; m++)
		{
			rightSequence += "right of ";
			result[m] = rightSequence + names[index];
		}

	}

	for (const auto& query : queries)
	{
		cout << result[query - 1] << endl;
	}

	return 0;
}