/*

�ƹ� ���� ���� ������ �Ϸ��ٰ� ������ �ּ�, �ִ��� ������ �־��� ���� ����

���� ����� �ξ� �Է°� �� �ּ�, �ִ븸 ã���� �ߴ�

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