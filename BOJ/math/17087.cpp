#include <iostream>
#include <vector>
const int MAX=100;
using namespace std;

int GCD(int a, int b){
    if (b==0) return a;
    else return GCD(b, a%b);
}

int main(){
    int n, s;
    cin >> n >> s;
    vector<int> a(n); // 동생들의 위치와의 거리
    for(int i=0; i<n; i++){
        int x;
        cin >> x;
        int diff = x-s;
        if(diff < 0) diff = - diff;
        a[i] = diff; 
    }
    int ans = a[0];
    for(int i=1; i<n; i++){ // 거리의 최대공약수
        ans = GCD(ans, a[i]);
    }
    cout << ans << '\n';
}