#include <iostream>

using namespace std;

/* 입력값은 최대 10^7이다. 
10^7 * 4byte = 40MB이므로 입력 받은 수를 전부 다 입력 받아서 저장하게 되면
제한된 8MB의 메모를 초과해버리므 숫자를 카운트 해두었다가 표준출력 해야한다 */

int main(){
    /* 입출력 최적화를 안 해주면 시간초과가 뜸 */
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