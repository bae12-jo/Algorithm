#include <iostream>
using namespace std;

int GCD(int a, int b){
    while(b!=0){
        int r = a%b;
        a = b;
        b = r;
    }
    return a;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    while(n--){
        int a, b;
        cin >> a >> b;
        int g;
        g = GCD(a, b);
        cout << (a*b)/g <<'\n';
    }

    return 0;
}