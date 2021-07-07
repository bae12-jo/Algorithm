#include <iostream>
#include <stack>
#include <string>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    string str;
    cin >> str;
    int n = str.size();
    stack<int> s;
    int ans = 0;

    for(int i=0; i<n; i++){
        if(str[i]=='('){
            s.push(i);
        }else{
            if (s.top()+1 == i){
                s.pop();
                ans += s.size();
            }else{
                s.pop();
                ans += 1;
            }
        }
    }
    cout << ans << '\n';
    return 0;
}