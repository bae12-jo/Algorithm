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

int LCM(int a, int b){
    return a*b/GCD(a, b);
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int a, b;
    cin >> a >> b;

    int g = GCD(a, b);
    int l = LCM(a, b);

    cout << g << '\n' << l << '\n';

    return 0;
}