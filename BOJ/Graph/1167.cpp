#include <cstring>
#include <vector>
#include <iostream>

using namespace std;

// vector�� �ڷ����� ���� ����ü ����
struct Node
{
    int index;
    int dist;
};
int v, maxDist, maxNode;
bool visit[100001]; // �湮Ȯ��
vector<Node> graph[100001];

void dfs(int node, int dist)
{
    // �湮�� ���� return
    if (visit[node])
        return;
    // maxDist ����
    if (maxDist < dist)
    {
        maxDist = dist;
        maxNode = node;
    }
    visit[node] = true; //�湮 üũ
    // ���� �������� ���� �� ������� dfs ����
    for (int i = 0; i < graph[node].size(); i++)
    {
        int nextIndex = graph[node][i].index;
        int nextDist = graph[node][i].dist;
        dfs(nextIndex, nextDist + dist);
    }
}
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> v;
    int fr, to, dist;
    for (int i = 1; i < v + 1; i++)
    {
        cin >> fr;
        while (true)
        {
            cin >> to;
            if (to == -1)
                break;
            cin >> dist;
            graph[fr].push_back({to, dist});
            graph[to].push_back({fr, dist});
        }
    }

    // ������ ���� 1���� ���� �Ÿ��� �� ���� ã��
    dfs(1, 0);
    memset(visit, 0, sizeof(visit));
    maxDist = 0;
    // 1�� ���� �� �������� �ٽ� dfs �����Ͽ� Ʈ���� ���� ã��
    dfs(maxNode, 0);

    cout << maxDist << '\n';

    return 0;
}