#include <iostream>
#include <stack>
#include <string>
using namespace std;
string valid(string s){
    int cnt = 0;
    for (int i=0; i<s.size(); i++){
        if (s[i]=='('){ // 여는 괄호만 스택에 카운트
            cnt += 1;
        }else{
            cnt -= 1;
        }
        if (cnt < 0){ // 닫는 괄호가 남는 경우
            return "NO"; 
        }
    } // 모든 과정이 끝났는데
    if (cnt == 0){ // 스택이 비면 올바른 괄호
        return "YES";
    }else{ // 여는 괄호가 남는 경우
        return "NO";
    }
}

int main() {
    int n;
    cin >> n;
    while (n--) {
        string s;
        cin >> s;
        cout << valid(s) <<'\n';                
    }
    return 0;
}