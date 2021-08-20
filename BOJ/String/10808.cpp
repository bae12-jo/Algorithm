#include <iostream>
#include <string>

using namespace std;

int main(){

    string str;
    int len;

    cin >> str;
    len = str.length();

    int chr[26] = {0};

    for(int i=0; i<len; i++){
        chr[str[i]-97] += 1;
    }
    for(int i=0; i<26; i++){
        cout << chr[i] << " ";
    }

}