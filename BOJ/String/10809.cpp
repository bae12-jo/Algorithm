#include <iostream>
#include <string>

using namespace std;

int main(){

    string str;
    int chr[26];
    // 0이 아닌 수로 초기화하려면 for문
    // -1로 하려면 fill_n
    // memset도 활용 가능

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