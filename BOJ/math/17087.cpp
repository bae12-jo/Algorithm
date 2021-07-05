#include <iostream>
#include <vector>
const int MAX=100;
using namespace std;

int GCD(int a, int b){
    if (b==0) return a;
    else return GCD(b, a%b);
}

int main(){
    int n, s;
    cin >> n >> s;
    vector<int> a(n); // �������� ��ġ���� �Ÿ�
    for(int i=0; i<n; i++){
        int x;
        cin >> x;
        int diff = x-s;
        if(diff < 0) diff = - diff;
        a[i] = diff; 
    }
    int ans = a[0];
    for(int i=1; i<n; i++){ // �Ÿ��� �ִ�����
        ans = GCD(ans, a[i]);
    }
    cout << ans << '\n';
}