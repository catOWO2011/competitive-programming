#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int h, w;
    while (cin >> h >> w)
    {
        vector<string> grid(h);
        for (int i = 0; i < h; i++)
        {
            cin >> grid[i];
        }
        
        double polygon_area = 0;
        for (int i = 0; i < h; i++)
        {
            bool open = false;
            for (int j = 0; j < w; j++)
            {
                if (grid[i][j] == '/' || grid[i][j] == '\\')
                {
                    // This means is a triangle and since each unit square is 1, we assume that the area is 1/2 or 0.5
                    open = !open;
					polygon_area += 0.5;
                }
                else {
                    if (open)
                    {
                        // We assume that we have a square and the area is 1
                        polygon_area += 1;
                    }
                }
            }
        }
        cout << polygon_area << endl;
    }
    return 0;
}