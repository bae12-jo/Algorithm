#include <iostream>
#include <algorithm>
using namespace std;

// 9c2 = 9*8/2*1 = 36

int a[9];
int n = 9;
int main(){
    int sum = 0;
    for (int i=0; i<n; i++){
        cin >> a[i];
        sum += a[i]; // 9���� Ű�� ���� ����
    }
    sort(a,a+n); // �� ����, ��� �ð����⵵ n log n
    for (int i=0; i<n; i++){
        for (int j=i+1; j<n; j++){
            if ((sum-a[i]-a[j])==100){ // 7���� Ű�� ���� 100
                for(int k=0; k<n; k++){ 
                    if(i==k || j==k) continue;
                    cout << a[k] << '\n'; // Ű�� ���
                }
                return 0; // ������ ������ ���� ���� �ϳ��� ����ϰ� ����
            }
        }
    }
    return 0;
}