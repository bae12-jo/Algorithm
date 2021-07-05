#include <iostream>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    cin >> n;

    int f=1;
    for(int i=1; i<=n; i++){
        f*=i;
    }
    cout << f;

    return 0;
}