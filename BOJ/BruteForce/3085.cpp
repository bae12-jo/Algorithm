#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 인접한 두 칸을 고르는 시간 복잡도 O(N^2)
// 상하좌우르 모두 고르는 경우 O(4N^2)
// 오른쪽과 아래만 고르는 경우 O(2N^2)

// 한 행에서 같은 수를 찾는 시간 복잡도 O(N^2)
// 모든 방법의 개수가 N^2이고 
// 그 방법을 이용해 같은 색으로 이루어진 가장 긴 연속 부분 행 또는 열을 고르는 게 O(N^2)
// 따라서 전체 O(N^2)인데 N<=50이므로 2500^2는 그리 큰 수가 아니다


int check(vector<string> &a){
    int n = a.size();
    int ans = 1;
    for (int i=0; i<n; i++){ // 행과 열의 크기가 같기 때문에 한번에 처리 가능
        int cnt = 1; // 0번 행열만 있다면 연속된 것이 1인 것과 같음
        for (int j=1; j<n; j++){ // 행에 대해 비교
            if (a[i][j] == a[i][j-1]){ // 같으면 1 증가
                cnt += 1;
            }else{ // 같지 않으면 다시 1로 초기화
                cnt = 1;
            }
            if (ans < cnt) ans = cnt; // 매번 최대값 비교
        }
        cnt = 1;
        for (int j=1; j<n; j++){ // 열에 대해 비교
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
            if (j+1 < n){ // 오른쪽과 교환
                swap(a[i][j], a[i][j+1]);
                int temp = check(a); // 최대 연속 행 및 열 찾기
                if (ans < temp) ans = temp;
                swap(a[i][j], a[i][j+1]); // 원래 상태로 복구
            }
            if(i+1 < n){ // 아래과 교환
                swap(a[i][j], a[i+1][j]);
                int temp = check(a); // 최대 연속 행 및 열 찾기
                if (ans < temp) ans = temp; 
                swap(a[i][j], a[i+1][j]); // 원래 상태로 복구
            }
        }
    }
    cout << ans << '\n';
    return 0;
}

// 아무 것도 교환하지 않은 상태에서 정답을 구하고
// 한 쌍을 교환한 후에 연결된 3개의 행렬만 다시 검사하는 방식
// O(N^3)으로 줄일 수 있음