#include <iostream>
#include <string>

using namespace std;

int main(){

    string str;
    int chr[26];
    // 0�� �ƴ� ���� �ʱ�ȭ�Ϸ��� for��
    // -1�� �Ϸ��� fill_n
    // memset�� Ȱ�� ����

    fill_n(chr, 26, -1);

    cin>>str;

    for(int i=0; i<str.size(); i++){
        if (chr[str[i]-97] == -1){
            chr[str[i]-97] = i;
        }
    }
    for(int i=0; i<26; i++){
        cout << chr[i] << " ";
    }
}