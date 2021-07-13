#include <iostream>
using namespace std;

// 복잡도는 15*20*19=5700 으로 크지 않음

int main(){
    int e, s, m;
    cin >> e >> s >> m;
    // 모듈러 연산에서 0이 되는 걸 방지하기 위한 예외 처리
    e -= 1;
    s -= 1;
    m -= 1;
    for(int i=0; ; i++){
        if (i%15==e && i%28==s && i%19==m){
            cout << i+1 << '\n';
            break;
        }
    }
    return 0;
}