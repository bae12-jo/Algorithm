#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;
    vector<int> a(n);
    for(int i=0 ; i<n; i++){
        cin >> a[i];
    }
    vector<int> d(n);
    vector<int> d2(n);
    for(int i=0; i<n; i++){ // 최장 증가 수열 (LIS) 구하기
        d[i]=1;
        for(int j=0; j<i; j++){
            if(a[j]<a[i] && d[j]+1 > d[i]){
                d[i] = d[j]+1;
            }
        }
    }    
    for(int i=n-1; i>=0; i--){ // 최장 감소 수열 (LDS) 구하기
        d2[i]=1;
        for(int j=i+1; j<n; j++){
            if (a[i]>a[j] && d2[j]+1 > d2[i]){
                d2[i] = d2[j]+1;
            }
        }
    }
    int ans=0;
    for(int i=0; i<n; i++){ // 모든 i번째에서 증감 변화가 이루어진다고 보고 전부 계산
        if (ans < d[i]+d2[i]-1){
            ans = d[i]+d2[i]-1;
        }
    }
    cout << ans << '\n';
    return 0;
}