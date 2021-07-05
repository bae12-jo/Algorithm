#include <iostream>
using namespace std;

bool prime(int n){
    if (n<2){
        return false;
    }
    for (int i=2; i*i<=n; i++){ 
        // �Ǽ��� �ٻ簪�̹Ƿ� ��� ����, ������ ó���ϱ� ���� i*i ���
        if (n%i == 0){
            return false;
        }
    }
    return true; 
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, cnt=0;
    cin >> n;
    while(n--){
        int x;
        cin >> x;
        if(prime(x)){
            cnt += 1;
        }
    }
    cout << cnt;

    return 0;
}