#include <cstdio>
#include <iostream>

int main()
{
	int velocity;
	int time;
	while (std::cin >> velocity >> time)
	{
		int displacement = 2 * velocity * time;
		std::cout << displacement << "\n";
	}
	return 0;
}