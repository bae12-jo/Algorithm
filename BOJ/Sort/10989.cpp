#include <iostream>

using namespace std;

/* �Է°��� �ִ� 10^7�̴�. 
10^7 * 4byte = 40MB�̹Ƿ� �Է� ���� ���� ���� �� �Է� �޾Ƽ� �����ϰ� �Ǹ�
���ѵ� 8MB�� �޸� �ʰ��ع����� ���ڸ� ī��Ʈ �صξ��ٰ� ǥ����� �ؾ��Ѵ� */

int main(){
    /* ����� ����ȭ�� �� ���ָ� �ð��ʰ��� �� */
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int num;
    int count[10001] = {0};
    cin >> num;

    for(int i=0; i<num; i++){
        int tmp;
        cin >> tmp;
        count[tmp]+=1;
    }

    for(int i=0; i<10001; i++){
        for(int j=0; j<count[i]; j++)
        cout << i << '\n';
    }
}