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

void inorder(int node, int depth) { // ���̸� ����ϸ鼭 ��ȸ ����
    if (node == -1) return;
    inorder(a[node].left, depth+1); // ���� �ڽ� ��ȸ
    a[node].order = ++order; // ���� ������ �̿��ؼ� ���� ���
    a[node].depth = depth; // ���� ���ؼ� ���
    inorder(a[node].right, depth+1); // ������ �ڽ� ��ȸ
}

int main() {
    int n;
    cin >> n;
    for (int i=0; i<n; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        a[x].left = y; // x�� ���� �ڽ� y
        a[x].right = z; // x�� ������ �ڽ� z
				// ��Ʈ�� �ݵ�� 1�� �ƴ� �� ����
        if (y != -1) cnt[y] += 1; // y�� ��Ʈ�� �ƴϸ� �θ��� ���� ���� ������
        if (z != -1) cnt[z] += 1; // z�� ��Ʈ�� �ƴϸ� �θ��� ���� ���� ������
    }
    int root = 0;
    for (int i=1; i<=n; i++) {
        if (cnt[i] == 0) { // �θ� ���� ��带 ��Ʈ�� ����
            root = i;
        }
    }

    inorder(root, 1);
    int maxdepth = 0;
    // ���� ���� ���� ���ʰ� ������ ��� ��ġ ���ϱ�
    for (int i=1; i<=n; i++) {
        int depth = a[i].depth;
        int order = a[i].order;
        if (left[depth] == 0) {
            left[depth] = order;
        } else {
            left[depth] = min(left[depth], order); // ���� ���� ��� ���ϱ�
        }
        right[depth] = max(right[depth], order); // ���� ������ ��� ���ϱ�
        maxdepth = max(maxdepth, depth); // ���� ���� ���� ���ϱ�
    }
    // ��ü �������� ���� ���ʰ� ������ ��� ���ϱ�
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