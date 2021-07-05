#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n;
    cin >> n;

    vector<int> a(n);
    vector<int> ans(n);

    for(int i=0; i<n; i++){
        cin >> a[i];
    }
    stack<int> s;
    s.push(0); // 예외
    for(int i=1; i<n; i++){
        if(s.empty()){
            s.push(i); // 오큰수를 구할 인덱스 저장
        }
        while (!s.empty() && a[s.top()] < a[i]){ // 오큰수를 구한 경우
            ans[s.top()] = a[i];
            s.pop();
        }
        s.push(i); // 오큰수를 구하지 못한 인덱스 저장
    }
    while (!s.empty()){
        ans[s.top()] = -1; // 끝내 오큰수를 찾지 못한 경우
        s.pop();
    }
    for (int i=0; i<n; i++){
        cout << ans[i] <<' ';
    }
    cout << '\n';
    return 0;
}