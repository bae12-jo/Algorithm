#include <iostream>
using namespace std;
const int MAX=1000000;
int prime[MAX];
int pn=0; // 소수 갯수 카운트
bool check[MAX+1];

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // 에라토스테네스의 체를 미리 구현해두기
    for (int i=2; i<=MAX; i++){
        if (check[i]==false){ // 소수가 있을 때
            prime[pn++] = i;
            for (int j=i+i; j<=MAX; j+=i){ // 해당 소수의 배수 제거
                check[j] = true;
            }
        }
    }

    // 사용자로부터 소수 입력 받기
    while(true){
        int n;
        cin >> n;
        if (n==0){
            break;
        }
        for(int i=1; i<pn; i++){
            if(check[n-prime[i]]==false){
                cout << n << " = " << prime[i] << " + " << n-prime[i] << '\n';
                break;
            }
        }
    }
    return 0;
}