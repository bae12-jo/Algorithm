#include <iostream>

using namespace std;

long long gcd(long long a, long long b){
    while(b!=0){
        long long r = a%b;
        a = b;
        b = r;
    }
    return a;
}

int main(){

    long long a, b;
    // 입력이 최대 2^65이므로 long long 자료형 사용
    cin >> a >> b;

    long long ans = gcd(a, b);
    for (long long i=0; i<ans; i++) cout << 1;

    return 0;
}