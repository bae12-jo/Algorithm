#include <iostream>
#include <stack>
#include <cmath>//pow
using namespace std;

int A, B, m;
int Dex = 0;
stack<int>Anum;
stack<int>Bnum;

//A���� �������� ��ȯ
void getDex() {
	int p = 0;
	while (!Anum.empty()) {
		int cur = Anum.top();
		Dex += pow(A, p)*cur;
		Anum.pop();
		p++;
	}
}

//������ B�������� ��ȯ
void getBnum() {
	while (Dex != 0) {
		int num = Dex % B;
		Dex = Dex / B;
		Bnum.push(num);
	}
}

void solution() {
	cin >> A >> B >> m;
	for (int i = 0; i < m; i++) {
		int n;
		cin >> n;
		Anum.push(n);
	}
	getDex();
	getBnum();
	while (!Bnum.empty()) {
		cout << Bnum.top() << ' ';
		Bnum.pop();
	}
	cout << endl;
}

int main() {
	solution();
	return 0;
}