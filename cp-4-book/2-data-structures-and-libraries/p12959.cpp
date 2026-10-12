#include <iostream>
#include <vector>

using namespace std;

/*
3 3
1 1 1 1 2 2 2 3 3
2 3
0 0 1 0 2 0
*/

int main()
{
	int J, R;
	while (cin >> J >> R)
	{
		vector<int> victoryPoints(J);
		for (int r = 0; r < R; r++)
		{
			for (int j = 0; j < J; j++)
			{
				int points;
				cin >> points;
				victoryPoints[j] += points;
			}
		}

		int winner = J;
		int maxPoints = victoryPoints[J - 1];
		for (int j = J - 2; j >= 0; j--)
		{
			if (victoryPoints[j] > maxPoints)
			{
				maxPoints = victoryPoints[j];
				winner = j + 1;
			}
		}
		cout << winner << endl;
	}
	return 0;
}