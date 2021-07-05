#include <iostream>
#include <vector>
const int MAX=100;
using namespace std;

int GCD(int a, int b){
    if (b==0) return a;
    else return GCD(b, a%b);
}

int main(){
    int n;
    cin >> n;
    vector<int> a;
    while(n--){
        int a[MAX];
        int x;
        cin >> x;
        for(int i=0; i<x; i++){
            cin >> a[i];
        }
        long long sum=0;
        for(int i=0; i<x-1; i++){
            for(int j=i+1; j<x; j++){
                sum += GCD(a[i],a[j]);
            }
        }
        cout << sum << '\n';
    }
}