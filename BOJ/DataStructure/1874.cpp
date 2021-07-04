#include <iostream>
#include <stack>
#include <string>
using namespace std;

int main() {
    int n;
    string ans;
    stack<int> s;

    cin >> n;

    int m=0;  // ���ÿ� �� ������ ��

    while(n--){
        int x;
        cin >> x;
        if (x>m) {
            while (x>m){ // ����ϰ� ���� ���� ���ÿ� ���� ������ push
                s.push(++m);
                ans += '+';
            }
            s.pop();
            ans += '-';
        }else{
            bool found = false;
            if (!s.empty()){ // ����ϰ� ���� ���� ���ö����� pop
                int top = s.top();
                s.pop();
                ans += '-';
                if (x == top){
                    found = true;
                }
            }
            if(!found){ // �� ���̶� false�� ������ NO ���
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