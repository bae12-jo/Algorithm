#include <iostream>
using namespace std;

// ���⵵�� 15*20*19=5700 ���� ũ�� ����

int main(){
    int e, s, m;
    cin >> e >> s >> m;
    // ��ⷯ ���꿡�� 0�� �Ǵ� �� �����ϱ� ���� ���� ó��
    e -= 1;
    s -= 1;
    m -= 1;
    for(int i=0; ; i++){
        if (i%15==e && i%28==s && i%19==m){
            cout << i+1 << '\n';
            break;
        }
    }
    return 0;
}