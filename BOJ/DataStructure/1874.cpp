#include <iostream>
#include <stack>
#include <string>
using namespace std;

int main() {
    int n;
    string ans;
    stack<int> s;

    cin >> n;

    int m=0;  // 스택에 들어간 마지막 수

    while(n--){
        int x;
        cin >> x;
        if (x>m) {
            while (x>m){ // 출력하고 싶은 수가 스택에 쌓일 때까지 push
                s.push(++m);
                ans += '+';
            }
            s.pop();
            ans += '-';
        }else{
            bool found = false;
            if (!s.empty()){ // 출력하고 싶은 수가 나올때까지 pop
                int top = s.top();
                s.pop();
                ans += '-';
                if (x == top){
                    found = true;
                }
            }
            if(!found){ // 한 번이라도 false가 있으면 NO 출력
                cout << "NO" <<'\n';
                return 0;
            }
        }
    }
    for (auto x : ans){
        cout << x << '\n';
    }

    return 0;
}