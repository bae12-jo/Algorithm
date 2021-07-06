#include <iostream>
#include <algorithm>

using namespace std;

int DP[1000001];

int main()
{
	int n;
	cin >> n;
	for(int i=2; i<=n; ++i)
	{
		DP[i]=DP[i-1]+1;
		if(i%2==0) DP[i]=min(DP[i], DP[i/2]+1);
		if(i%3==0) DP[i]=min(DP[i], DP[i/3]+1);
	}
	cout<<DP[n]<<endl;
	return 0;
}

/*
[3���� ������, 2�� ������, 1�� ����]
���� ������� �� ��� ������ ���� �� ����
�ݷ�: 10
10 -> 5 -> 4 -> 2 -> 1 (���� ������ ������ ���)
10 -> 9 -> 3 -> 1 (�� ������ �ּڰ�)
*/

/*
// Top-down ����� ������� �� �ð� ���⵵�� O(N)
int go(int n){
	if (n==1) return 0;
	if (d[n]>0) return d[n]; // �޸������̼�
	d[n] = go(n-1)+1; // i���� 1�� ���� ���
	if(n%2==0){ // i�� 2�� ������ ���
		int tmp = go(n/2)+1;
		if (d[n]>tmp) d[n]=tmp;
	}
	if(n%3==0){ // i�� 3���� ������ ���
		int tmp = go(n/3) + 1;
		if (d[n]>tmp) d[n]=tmp;
	}
	return d[n];
}
*/

/*
// Bottom up ����� ������� �� �ð� ���⵵ O(N)
d[1] = 0; // ���� ���� ũ���� ������ ���� �����ֱ�
for (int i=2; i<=n; i++){ // �� ���� ���� ���� 2���� ���� ū ���� n���� ��� �����ֱ�
	d[i] = d[i-1]+1; // i���� 1�� ���� ���
	if(i%2==0 && d[i] > d[i/2]+1){ // i�� 2�� ������ ���
		d[i] = d[i/2]+1;
	}
	f(i%3==0 && d[i] > d[i/3]+1){ // i�� 3���� ������ ���
		d[i] = d[i/2]+1;
	}
}
*/