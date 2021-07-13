#include <iostream>
#include <algorithm>
using namespace std;

// 9c2 = 9*8/2*1 = 36

int a[9];
int n = 9;
int main(){
    int sum = 0;
    for (int i=0; i<n; i++){
        cin >> a[i];
        sum += a[i]; // 9명의 키의 합을 저장
    }
    sort(a,a+n); // 퀵 정렬, 평균 시간복잡도 n log n
    for (int i=0; i<n; i++){
        for (int j=i+1; j<n; j++){
            if ((sum-a[i]-a[j])==100){ // 7명의 키가 합이 100
                for(int k=0; k<n; k++){ 
                    if(i==k || j==k) continue;
                    cout << a[k] << '\n'; // 키를 출력
                }
                return 0; // 가능한 정답이 여러 개면 하나만 출력하고 종료
            }
        }
    }
    return 0;
}