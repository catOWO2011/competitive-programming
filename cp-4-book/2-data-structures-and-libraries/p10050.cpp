#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <numeric>

using namespace std;

/*
2
14
3
3
4
8
100
4
12
15
25
40

*/

int main()
{
	int T;
	cin >> T;
	while (T--)
	{
		int N;
		cin >> N;
		int P;
		cin >> P;
		vector<int> h(P);
		for (int i = 0; i < P; i++)
		{
			cin >> h[i];
		}

		vector<bool> isHartalDay(N + 1, false);
		for (const int& hartalDay : h)
		{
			for (int i = hartalDay; i <= N; i += hartalDay)
			{
				isHartalDay[i] = true;
			}
		}

		int holyDays[] = { 6, 7 };
		for (int day : holyDays)
		{
			for (int i = day; i <= N; i += 7)
			{
				isHartalDay[i] = false;
			}
		}

		int hartalCount = accumulate(isHartalDay.begin(), isHartalDay.end(), 0);
		cout << hartalCount << endl;
	}
	return 0;
}