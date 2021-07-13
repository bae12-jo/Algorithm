#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// ������ �� ĭ�� ���� �ð� ���⵵ O(N^2)
// �����¿츣 ��� ���� ��� O(4N^2)
// �����ʰ� �Ʒ��� ���� ��� O(2N^2)

// �� �࿡�� ���� ���� ã�� �ð� ���⵵ O(N^2)
// ��� ����� ������ N^2�̰� 
// �� ����� �̿��� ���� ������ �̷���� ���� �� ���� �κ� �� �Ǵ� ���� ���� �� O(N^2)
// ���� ��ü O(N^2)�ε� N<=50�̹Ƿ� 2500^2�� �׸� ū ���� �ƴϴ�


int check(vector<string> &a){
    int n = a.size();
    int ans = 1;
    for (int i=0; i<n; i++){ // ��� ���� ũ�Ⱑ ���� ������ �ѹ��� ó�� ����
        int cnt = 1; // 0�� �࿭�� �ִٸ� ���ӵ� ���� 1�� �Ͱ� ����
        for (int j=1; j<n; j++){ // �࿡ ���� ��
            if (a[i][j] == a[i][j-1]){ // ������ 1 ����
                cnt += 1;
            }else{ // ���� ������ �ٽ� 1�� �ʱ�ȭ
                cnt = 1;
            }
            if (ans < cnt) ans = cnt; // �Ź� �ִ밪 ��
        }
        cnt = 1;
        for (int j=1; j<n; j++){ // ���� ���� ��
            if (a[j][i] == a[j-1][i]){
                cnt += 1;
            }else{
                cnt = 1;
            }
            if (ans < cnt) ans = cnt;
        }
    }
    return ans;
}

int main(){
    int n;
    cin >> n;
    vector<string> a(n);
    for (int i=0; i<n; i++){
        cin >> a[i];
    }
    int ans = 0;
    for (int i=0; i<n; i++){
        for (int j=0; j<n; j++){
            if (j+1 < n){ // �����ʰ� ��ȯ
                swap(a[i][j], a[i][j+1]);
                int temp = check(a); // �ִ� ���� �� �� �� ã��
                if (ans < temp) ans = temp;
                swap(a[i][j], a[i][j+1]); // ���� ���·� ����
            }
            if(i+1 < n){ // �Ʒ��� ��ȯ
                swap(a[i][j], a[i+1][j]);
                int temp = check(a); // �ִ� ���� �� �� �� ã��
                if (ans < temp) ans = temp; 
                swap(a[i][j], a[i+1][j]); // ���� ���·� ����
            }
        }
    }
    cout << ans << '\n';
    return 0;
}

// �ƹ� �͵� ��ȯ���� ���� ���¿��� ������ ���ϰ�
// �� ���� ��ȯ�� �Ŀ� ����� 3���� ��ĸ� �ٽ� �˻��ϴ� ���
// O(N^3)���� ���� �� ����