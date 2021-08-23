#include <iostream>
#include <string>

using namespace std;

int main(){

    string a, b, c, d;
    cin >> a >> b >> c >> d;

    string ab = a+b, cd=c+d;
    cout << stoll(ab)+stoll(cd);

}