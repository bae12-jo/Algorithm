#include <iostream>
#include <stack>
#include <string>
using namespace std;
string valid(string s){
    int cnt = 0;
    for (int i=0; i<s.size(); i++){
        if (s[i]=='('){ // ���� ��ȣ�� ���ÿ� ī��Ʈ
            cnt += 1;
        }else{
            cnt -= 1;
        }
        if (cnt < 0){ // �ݴ� ��ȣ�� ���� ���
            return "NO"; 
        }
    } // ��� ������ �����µ�
    if (cnt == 0){ // ������ ��� �ùٸ� ��ȣ
        return "YES";
    }else{ // ���� ��ȣ�� ���� ���
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