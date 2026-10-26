#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

/*
5
#-##----#
 -----#-
  ---#-
   -#-
    -
4
#-#-#--
 #---#
  ##-
   -
0


*/

int main()
{
	int tc = 1;
	string line;
	while (getline(cin, line))
	{
		int n = stoi(line.c_str());
		if (n == 0) break;
		int m = 2 * n - 1;
		vector<string> triangle(n, string(m, '#'));
		for (int i = 0; i < n; i++)
		{
			getline(cin, line);
			for (int j = 0; j < line.length(); j++)
			{
				if (line[j] != ' ')
				{
					triangle[i][j] = line[j];
				}
			}
		}

		// Precompute the # (black fields) as a prefix sum array for each row
		vector<vector<int>> prefixSum(n, vector<int>(m + 1, 0));
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				prefixSum[i][j + 1] = prefixSum[i][j] + (triangle[i][j] == '#');
			}
		}

		// Create a function to check if they're only white fields from certain range(left, right) int the current row
		auto isWhite = [&](int row, int left, int right) {
			if (row < 0 || row >= n || left < 0 || right >= m) return false;
			return prefixSum[row][right + 1] - prefixSum[row][left] == 0;
		};

		// We calculate the largest area of a triangle that can be formed with only white fields
		int largestArea = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				if (triangle[i][j] != '-') continue;
				bool down = (j - i) % 2 == 0; // Determine if the triangle points down or up based on the row index
				int dir = down ? -1 : 1;
				for (int k = 0; ; k++)
				{
					int row = i + k * dir;
					int left = j - k;
					int right = j + k;
					if (!isWhite(row, left, right))
					{
						break;
					}
					largestArea = max(largestArea, (k + 1) * (k + 1));
				}
			}
		}

		printf("Triangle #%d\nThe largest triangle area is %d.\n\n", tc++, largestArea);
	}
	return 0;
}