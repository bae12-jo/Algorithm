#include <iostream>
using namespace std;
bool broken[10]; // 버튼이 고장나 있으면 true, 아니면 false
int possible(int c) {
    if (c==0){ // 이동하려는 채널이 0인 경우는 모듈러 연산의 반례가 되므로 예외 처리
		if(broken[0]){
			return broken[0] ? 0 : 1;
        }
	}
    int len = 0; // 수의 길이를 확인
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
    // 숫자 버튼을 누르지 않는 경우를 초기값으로 설정
    int ans = n - 100;
    if (ans < 0) {
        ans = -ans;
    }
    for (int i = 0; i <= 1000000; i++) {
        int c = i;
        int len = possible(c);
        if (len > 0) { // 0이면 불가능
            int press = c - n;
            if (press < 0) {
                press = -press;
            }
            if (ans > len + press) { // 최소값을 매번 비교
                ans = len + press;
            }
        }
    }
    printf("%d\n", ans);
    return 0;
}