#include <iostream>
using namespace std;
const int MAX=1000000;
bool check[MAX+1];

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    // �����佺�׳׽��� ü�� �̸� �����صα�
    check[0] = check[1] = true; // 0�� 1 ����
    for (int i=2; i*i<=MAX; i++){
        if (check[i]==false){ // �Ҽ��� ���� ��
            for (int j=i+i; j<=MAX; j+=i){ // �ش� �Ҽ��� ��� ����
                check[j] = true;
            }
        }
    }

    // ����ڷκ��� ���� �Է¹ޱ�
    int m, n;
    cin >> m >> n;
    for (int i=m; i<=n; i++){ // m���� n ���� �˻�
        if(check[i]==false){
            cout << i << '\n';
        }
    }

    return 0;
}