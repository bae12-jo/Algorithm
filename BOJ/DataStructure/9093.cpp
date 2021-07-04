#include <iostream>
#include <stack>
#include <string>
using namespace std;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    cin.ignore();
    // cin은 \n을 변수에 담지 않는다 (입력버퍼에 남겨준다) 
    // getline은 \n을 변수에 담는다 (입력버퍼에 남겨두지 않는다)
    // cin.ignore()는 버퍼의 맨 앞의 문자 하나를 지운다
    // 여기서는 getline이 개행부터 저장하여 첫 문자열이 공백이 되는 것을 막기 위해 사용
    // (1) cin과 getline을 연달아 사용할 때 버퍼를 비워주기 위해 ignore를 사용한다
    // (2) cin 다음 cin 을 받을 경우 전 버퍼에 있던 공백과 개행을 무시한다
    // (3) getline 다음 getline을 받을 경우 \n을 버퍼에 포함시키지 않기 때문에 비울 필요가 없다
    while (n--) {
        string str;
        getline(cin, str);
        str += '\n'; // 문자열 뒤에 개행문자 임의로 넣어주기
        stack<char> s;
        for (char ch : str){ // range based for 문
        // for (데이터 타입 elem : 데이터 리스트)
        // 시작과 끝을 명시하지 않아도 리스트를 처음부터 끝까지 순회해주는 반복문
            if (ch==' '|| ch=='\n'){ // 공백이나 개행이 나오면 역순으로 출력
                while(!s.empty()){
                    cout<<s.top();
                    s.pop();
                }
                cout << ch; // 개행 문자 또는 공백을 출력
            }else{
                s.push(ch); // 단어는 스택에 넣어주기
            }
        }
    }
    return 0;
}