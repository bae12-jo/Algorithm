#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n; cin >> n;
	
	vector<char> vtr;
	
	while (n != 0)
	{
		if (n % 2 == 0)
		{
			vtr.push_back('0');
			n = -(n / 2);
		}
		else
		{
			if (n > 0) n = -(n / 2);
			else n = (-n + 1) / 2; 
			vtr.push_back('1');
		}
		
	}
	reverse(vtr.begin(), vtr.end());

	if (vtr.size() == 0)
	{
		cout << "0" << "\n";
	}
	else
	{
		for (auto k : vtr)
		{
			cout << k;
		}
		cout << "\n";
	}
	

}