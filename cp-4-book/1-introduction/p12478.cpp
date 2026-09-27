#include <cstdio>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

bool isAnagram(const string& str1, const string& str2) {
	if (str1.length() != str2.length()) {
		return false;
	}
	int count[256] = { 0 };
	for (char c : str1) {
		count[c]++;
	}
	for (char c : str2) {
		count[c]--;
		if (count[c] < 0) {
			return false;
		}
	}
	return true;
}

bool searchWordFromLeftToRight(const string (&grid)[9], const string& word, bool (&visited)[9][9]) {
    for (int row = 0; row < 9; ++row) {
        for (int col = 0; col <= 9 - word.length() && !visited[row][col]; ++col) {
            bool found = isAnagram(grid[row].substr(col, word.length()), word);
            if (found) {
				for (int k = 0; k < word.length(); k++) {
					visited[row][col + k] = true;
				}
                return true;
            }
        }
    }
	return false;
}

bool searchWordFromRightToLeft(const string (&grid)[9], const string& word, bool (&visited)[9][9]) {
	for (int row = 0; row < 9; ++row) {
		for (int col = 9 - 1; col >= word.length() - 1 && !visited[row][col]; --col) {
			bool found = isAnagram(grid[row].substr(col - word.length() + 1, word.length()), word);
			if (found) {
				for (int k = 0; k < word.length(); k++) {
					visited[row][col - k] = true;
				}
				return true;
			}
		}
	}
    return false;
}

bool searchWordFromTopToBottom(const string(&grid)[9], const string& word, bool (&visited)[9][9]) {
	for (int col = 0; col < 9; ++col) {
		for (int row = 0; row <= 9 - word.length() && !visited[row][col]; ++row) {
			string verticalWord = "";
			for (int k = 0; k < word.length(); k++) {
				verticalWord += grid[row + k][col];
			}
			bool found = isAnagram(verticalWord, word);
			if (found) {
				for (int k = 0; k < word.length(); k++) {
					visited[row + k][col] = true;
				}
				return true;
			}
		}
	}
	return false;
}

bool searchWordFromBottomToTop(const string(&grid)[9], const string& word, bool (&visited)[9][9]) {
	for (int col = 0; col < 9; ++col) {
		for (int row = 9 - 1; row >= word.length() - 1 && !visited[row][col]; --row) {
			string verticalWord = "";
			for (int k = 0; k < word.length(); k++) {
				verticalWord += grid[row - k][col];
			}
			bool found = isAnagram(verticalWord, word);
			if (found) {
				for (int k = 0; k < word.length(); k++) {
					visited[row - k][col] = true;
				}
				return true;
			}
		}
	}
	return false;
}

int main()
{
    // Define a 9x9 grid of characters
    const string grid[9] = {
		"OBIDAIBKR",
		"RKAULHISP",
		"SADIYANNO",
		"HEISAWHIA",
		"IRAKIBULS",
		"MFBINTRNO",
		"UTOYZIFAH",
		"LEBSYNUNE",
		"EMOTIONAL"
    };

    // Define a list of names to search for 
    vector<string> names = { "RAKIBUL", "ANINDYA", "MOSHIUR", "SHIPLU", "KABIR", "SUNNY", "OBAIDA", "WASI" };

    for (const auto& name : names) {
      int occurrences = 0;
      bool visited[9][9] = { false }; // Reset visited array for each name

      if (searchWordFromLeftToRight(grid, name, visited)) {
			//cout << "Found " << name << " from left to right." << endl;
            ++occurrences;
        }
      if (searchWordFromRightToLeft(grid, name, visited)) {
        //cout << "Found " << name << " from right to left." << endl;
        ++occurrences;
      }
      if (searchWordFromTopToBottom(grid, name, visited)) {
        //cout << "Found " << name << " from top to bottom." << endl;
        ++occurrences;
      }
      if (searchWordFromBottomToTop(grid, name, visited)) {
        //cout << "Found " << name << " from bottom to top." << endl;
        ++occurrences;
      }
      if (occurrences > 1) {
        cout << name << endl;
      }
    }

    return 0;
}