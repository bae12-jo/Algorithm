#include <iostream>
#include <algorithm>
#define left _left
#define right _right
using namespace std;
struct Node {
    int left, right;
    int order, depth;
};
Node a[10001];
int left[10001];
int right[10001];
int cnt[10001];
int order = 0;

void inorder(int node, int depth) { // 깊이를 기록하면서 순회 진행
    if (node == -1) return;
    inorder(a[node].left, depth+1); // 왼쪽 자식 순회
    a[node].order = ++order; // 전역 변수를 이용해서 순서 기록
    a[node].depth = depth; // 높이 구해서 기록
    inorder(a[node].right, depth+1); // 오른쪽 자식 순회
}

int main() {
    int n;
    cin >> n;
    for (int i=0; i<n; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        a[x].left = y; // x의 왼쪽 자식 y
        a[x].right = z; // x의 오른쪽 자식 z
				// 루트가 반드시 1일 아닐 수 있음
        if (y != -1) cnt[y] += 1; // y가 루트가 아니면 부모의 수를 전부 세어줌
        if (z != -1) cnt[z] += 1; // z가 루트가 아니면 부모의 수를 전부 세어줌
    }
    int root = 0;
    for (int i=1; i<=n; i++) {
        if (cnt[i] == 0) { // 부모가 없는 노드를 루트로 지정
            root = i;
        }
    }

    inorder(root, 1);
    int maxdepth = 0;
    // 레벨 별로 가장 왼쪽과 오른쪽 노드 위치 구하기
    for (int i=1; i<=n; i++) {
        int depth = a[i].depth;
        int order = a[i].order;
        if (left[depth] == 0) {
            left[depth] = order;
        } else {
            left[depth] = min(left[depth], order); // 가장 왼쪽 노드 구하기
        }
        right[depth] = max(right[depth], order); // 가장 오른쪽 노드 구하기
        maxdepth = max(maxdepth, depth); // 가장 깊은 레벨 구하기
    }
    // 전체 레벨에서 가장 왼쪽과 오른쪽 노드 구하기
    int ans = 0;
    int ans_level = 0;
    for (int i=1; i<=maxdepth; i++) {
        if (ans < right[i]-left[i]+1) {
            ans = right[i]-left[i]+1;
            ans_level = i;
        }
    }
    cout << ans_level << ' ' << ans << '\n';
    return 0;
}