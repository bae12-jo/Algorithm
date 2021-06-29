/*

아무 생각 없이 정렬을 하려다가 문제에 최소, 최대의 범위가 주어진 것을 보고

각각 상수를 두어 입력값 중 최소, 최대만 찾도록 했다

*/

#include <iostream>
using namespace std;

int main(void)
{
	int n, a, min=1000000, max=-1000000;
	cin>>n;
	while(cin>>a)
	{
		min = min > a ? a : min;
        max = max < a ? a : max;			
	}
	cout<<min<<" "<<max;
	
    return 0;
}