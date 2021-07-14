#include <iostream>
using namespace std;
bool broken[10]; // ��ư�� ���峪 ������ true, �ƴϸ� false
int possible(int c) {
    if (c==0){ // �̵��Ϸ��� ä���� 0�� ���� ��ⷯ ������ �ݷʰ� �ǹǷ� ���� ó��
		if(broken[0]){
			return broken[0] ? 0 : 1;
        }
	}
    int len = 0; // ���� ���̸� Ȯ��
    while (c > 0) {
        if (broken[c % 10]) {
        return 0;
        }
    len += 1;
    c /= 10;
    }
    return len;
}
int main() {
    int n;
    cin >> n;
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int x;
        cin >> x;
        broken[x] = true;
    }
    // ���� ��ư�� ������ �ʴ� ��츦 �ʱⰪ���� ����
    int ans = n - 100;
    if (ans < 0) {
        ans = -ans;
    }
    for (int i = 0; i <= 1000000; i++) {
        int c = i;
        int len = possible(c);
        if (len > 0) { // 0�̸� �Ұ���
            int press = c - n;
            if (press < 0) {
                press = -press;
            }
            if (ans > len + press) { // �ּҰ��� �Ź� ��
                ans = len + press;
            }
        }
    }
    printf("%d\n", ans);
    return 0;
}