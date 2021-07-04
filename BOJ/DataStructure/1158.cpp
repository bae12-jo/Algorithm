// 큐를 안 써도 되지만 억지로 큐를 연습하게 하는 문제

#include <iostream>
#include <queue>
using namespace std;
int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    queue<int> q;
    for(int i=1;i<=n;i++){
        q.push(i);
    }
    cout<<"<";
    for(int i=0;i<n-1;i++){
        for (int j=0;j<m-1;j++){
            q.push(q.front());
            q.pop();
        }
        cout<<q.front()<<", ";
        q.pop();
    }
    cout<<q.front()<<">";
    return 0;
}