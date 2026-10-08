#include <iostream>
#include <vector>

using namespace std;

/*
2
1 -3
6
40 0 -41 0 41 42
4
300 450 449 450 0
*/

int main()
{
    int N;
    while (cin >> N && N != 0)
    {
        vector<int> H(N + 2);
		for (int i = 0; i < N; ++i)
		{
			cin >> H[i];
		}
		H[N] = H[0];
		H[N + 1] = H[1];
		bool increasing = H[0] < H[1];
		int peaks = 0;
		for (int i = 1; i < N + 1; ++i)
		{
			if (increasing && H[i] > H[i + 1])
			{
				peaks++;
				increasing = false;
			}
			else if (!increasing && H[i] < H[i + 1])
			{
				peaks++;
				increasing = true;
			}
		}
		cout << peaks << endl;
    }
    return 0;
}