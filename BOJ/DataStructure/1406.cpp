// 스택을 활용해서 풀었으나 연결리스트로도 해결 가능

#include <iostream>
#include <stack>
#include <cstring>
using namespace std;
char a[600000];

int main() {
    stack<char> left, right;
    cin >> a;
    int n = strlen(a);
    for (int i=0;i<n;i++){
        left.push(a[i]);
    }
    int m;
    cin >> m;
    while(m--){
        char cmd;
        cin >> cmd;
        if (cmd=='L'){
            if(!left.empty()){
                right.push(left.top());
                left.pop();
            }
        }else if(cmd=='D'){
            if(!right.empty()){
                left.push(right.top());
                right.pop();
            }
        }else if(cmd=='B'){
            if(!left.empty()){
                left.pop();
            }
        }else if(cmd=='P'){
            char c;
            cin >> c;
            left.push(c);

        }
    }
    while(!left.empty()){
        right.push(left.top()); // 왼쪽 스택을 모두 오른쪽 스택으로 옮겨주기
        left.pop();
    }
    while(!right.empty()){
        cout << right.top();
        right.pop();
    }
    cout << '\n';
    return 0;
}