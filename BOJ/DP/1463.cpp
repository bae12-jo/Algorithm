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
[3으로 나누기, 2로 나누기, 1을 빼기]
위의 순서대로 할 경우 정답을 구할 수 없음
반례: 10
10 -> 5 -> 4 -> 2 -> 1 (위의 순서를 지켰을 경우)
10 -> 9 -> 3 -> 1 (이 문제의 최솟값)
*/

/*
// Top-down 방식을 사용했을 때 시간 복잡도는 O(N)
int go(int n){
	if (n==1) return 0;
	if (d[n]>0) return d[n]; // 메모이제이션
	d[n] = go(n-1)+1; // i에서 1을 빼는 경우
	if(n%2==0){ // i를 2로 나누는 경우
		int tmp = go(n/2)+1;
		if (d[n]>tmp) d[n]=tmp;
	}
	if(n%3==0){ // i를 3으로 나누는 경우
		int tmp = go(n/3) + 1;
		if (d[n]>tmp) d[n]=tmp;
	}
	return d[n];
}
*/

/*
// Bottom up 방식을 사용했을 때 시간 복잡도 O(N)
d[1] = 0; // 제일 작은 크기의 문제의 정답 구해주기
for (int i=2; i<=n; i++){ // 그 다음 작은 문제 2부터 가장 큰 문제 n까지 모두 구해주기
	d[i] = d[i-1]+1; // i에서 1을 빼는 경우
	if(i%2==0 && d[i] > d[i/2]+1){ // i를 2로 나누는 경우
		d[i] = d[i/2]+1;
	}
	f(i%3==0 && d[i] > d[i/3]+1){ // i를 3으로 나누는 경우
		d[i] = d[i/2]+1;
	}
}
*/