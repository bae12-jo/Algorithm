# DAG (Direct Acyclic Graph)
* 순환하는 사이클이 존재하지 않는 유방향 그래프
* 위상정렬 문제에서 답은 여러개가 될 수 있음

## 용어
* incoming edge: 진입 간선
* outgoing edge: 진출 간선
* indegree: 임의의 정점으로 들어오는 간선의 수 (진입 간선이 0이면 indegree가 0)
* outdegree: 임의의 정점에서 나가는 간선의 수

# 알고리즘 1
> **가장 앞에 와야 하는 노드부터 찾는 알고리즘** 직관적으로 이해하기 쉬움
![image](https://user-images.githubusercontent.com/84948636/159153357-c4e67ce2-0d6b-4c7a-b410-455cc889d539.png)
* 수행시간 O(n+m)
* n번을 돌면서 indegree가 0인 것을 선택
* m개를 탐색하면서 정점 u와 u의 진출간선을 모두 제거한다.

## 구현 시 생각해 볼 점
1. 만약 0인 노드가 존재하지 않는다면?
2. indegree를 어떻게 계산하고 0인 정점을 어떻게 찾을 것인가?
3. 정점 u와 그 진출간선을 어떻게 제거할 것인가?

# 알고리즘 2
> **가장 뒤로 가야할 노드부터 찾는 알고리즘** DAG이기 때문에 보장되는 것
![image](https://user-images.githubusercontent.com/84948636/159210223-0fcb15da-69d2-48e3-a16f-315060494353.png)
* 모든 노드에 대해서 방문 여부를 저장
* 연결리스트를 만들어서 정렬 결과를 담을 것
* 임의의 아직 방문하지 않은 노드에 대해서 DFS-TS

![image](https://user-images.githubusercontent.com/84948636/159210298-16e89831-b120-4b6e-940b-a7f5aa996e75.png)
* for문까지는 기존의 DFS와 동일
* for문을 나왔다는 것은 더 이상 진행할 깊은 노드가 없다는 것으로, 해당 노드를 연결리스트에 맨 앞에 추가

![image](https://user-images.githubusercontent.com/84948636/159210452-6c5ec891-c7c1-4e12-a377-e552301f1bec.png)
* '계란 넣기'가 먼저 리스트에 들어가고 이후에 '수프 넣기'가 들어감
