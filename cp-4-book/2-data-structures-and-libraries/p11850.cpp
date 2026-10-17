#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
2
0
900
8
1400
1200
1000
800
600
400
200
0
0
*/

int main()
{
	int n;

	while(cin >> n && n != 0)
	{
		vector<int> locations(n);
		for(int i = 0; i < n; i++)
		{
			cin >> locations[i];
		}
		sort(locations.begin(), locations.end());
		bool possible = true;
		for (int i = 0; i < n - 1; i++)
		{
			if (locations[i + 1] - locations[i] > 200)
			{
				possible = false;
				break;
			}
		}

		if (possible && locations[n - 1] != 1422 && (1422 - locations[n - 1]) * 2 > 200)
		{
			possible = false;
		}

		if (possible)
		{
			cout << "POSSIBLE" << endl;
		}
		else
		{
			cout << "IMPOSSIBLE" << endl;
		}
	}

	return 0;
}