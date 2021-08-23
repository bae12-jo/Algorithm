#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main(){

    string str;
    cin >> str;

    vector<string> vec(str.size());

    for(int i=0; i<str.size(); i++){
        string a;
        for (int j=i; j<str.size(); j++){
            a += str[j];
        }
        vec[i] = a;
    }

    sort(vec.begin(), vec.end());

    for(int i=0; i<str.size(); i++){
        cout << vec[i] << '\n';
    }

}