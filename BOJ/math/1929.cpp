#include <iostream>
using namespace std;
const int MAX=1000000;
bool check[MAX+1];

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 에라토스테네스의 체를 미리 구현해두기
    check[0] = check[1] = true; // 0과 1 제외
    for (int i=2; i*i<=MAX; i++){
        if (check[i]==false){ // 소수가 있을 때
            for (int j=i+i; j<=MAX; j+=i){ // 해당 소수의 배수 제거
                check[j] = true;
            }
        }
    }

    // 사용자로부터 범위 입력받기
    int m, n;
    cin >> m >> n;
    for (int i=m; i<=n; i++){ // m부터 n 사이 검사
        if(check[i]==false){
            cout << i << '\n';
        }
    }

    return 0;
}