# Adjacency Matraix

## 무방향
![image](https://user-images.githubusercontent.com/84948636/161183614-1932dc3a-e8ad-4c96-bbde-1cf50a6f72b3.png)
* 임의의 노드와 인접한 노드가 많은 dense graph에서 유리
* 어떤 노드에 인접한 모든 노드를 찾기 위해 항상 한 행을 모두 조회해야 하므로 O(n)이 걸린다.
* 이는 간선 개수가 많은 dense graph로 갈수록 연결리스트와 차이가 줄어든다.
* 인접행렬의 장점은 간선의 존재를 검사하는데 O(1) 시간밖에 걸리지 않는다는 것이다.

## 유방향
* 유방향에서 인접행렬은 비대칭이며, self-edge가 존재할 수 있다.

## 가중치 그래프
* 가중치가 0일 수 있으므로, 가중치가 없을 경우 INF로 초기화해줘야 한다.
* INF는 Integer.MAX_VALUE를 써도 되고 (이 경우 오버플로우를 방지하기 위한 사전 처리를 해줘야함) 문제 조건에 맞는 충분히 큰 수로 설정하면 된다.

# Adjacency List

## 무방향
![image](https://user-images.githubusercontent.com/84948636/161183841-b3273ec8-5d21-4920-934f-beb99598772a.png)
* 무방향 그래프에서 node 개수가 n일 때 edge의 개수 m은 최대 n(n-1)/2이므로 최악의 경우 O(n^2)의 복잡도를 가진다. (m은 0일수도 있다)
* 임의의 노드와 인접한 노드가 적은 그래프에서 임의의 노드에 인접한 모든 노드를 찾을 때 효율성 발휘 가능하지만 dense graph로 갈수록 최대 n-1시간이 걸린다.
* 즉, 리스트의 장점인 인접한 모든 노드 찾기 성능은 dense graph로 갈수록 행렬과 비슷해지는 반면, 기본적으로 리스트가 행렬보다 간선 존재 찾기가 오래 걸린다.
* 리스트에 저장된 노드의 전체 개수는 2m개이다. (a->b와 b->a가 각각 저장되므로)

## 유방향
* 유방향에서 인접리스트는 m개의 노드를 가진다.


# 주의할 점
* 모든 노드 쌍이 서로 연결된 그래프를 connected graph라고 부른다.
  * 그래프가 unconnected graph라면 BFS/DFS에 의해서 모든 노드가 방문되지 않을수도 있으므로 모든 노드에서 각각 시작해서 BFS/DFS를 n번 돌려야함
* 그래프가 유방향 그래프라면 directed graph라고 부른다.
  * 그래프가 directed graph라면 BFS/DFS에 의해서 모든 노드가 방문되지 않을수도 있으므로 모든 노드에서 각각 시작해서 BFS/DFS를 n번 돌려야함 
> '경로가 없는 경우는 없다'는 지문이 없다면 unconnected graph의 가능성을 염두해둬야 한다.


# BFS (Breadth-First Search)
* tree에서 level order traversal 원리와 같음
* 동심원 형태로 레벨을 높여가며 모든 정점을 순회하는 알고리즘이다.
* 인접행렬로 구현 시 시간복잡도는 O(n^2)이다.
 * unvisited만 큐에 들어가므로 while문은 n번 이상 돌지 않으며
 * 각 노드에 인접한 모든 노드를 한 행 단위로, 즉 n개를 조회하게 된다.
* 인접리스트로 구현 시 시간복잡도는 O(n+m)이다.
  * unvisited만 큐에 들어가므로 while문은 n번 이상 돌지 않으며
  * 각 노드에 인접한 모든 노드 degree(v)을 조회하게 된다, 모든 노드의 degree(v)의 총합은 2m이다.
  * 물론 간선이 아주 많은 최악의 경우 O(n^2)까지 늘어날 수 있다.

![image](https://user-images.githubusercontent.com/84948636/161183034-29eb931c-40b7-4978-af71-ee9e2b9c1fec.png)
* 입력: 방향 혹은 무방향 그래프 G=(V,E), 그리고 출발노드 s∈V
* d[v] = s에서 v까지의 최단 경로의 길이(에지의 개수)
* π[v] = s로부터 v까지의 최단 경로상에서 v의 직전 노드(predecessor)

![image](https://user-images.githubusercontent.com/84948636/161190723-60e68a87-84e2-4cae-a3b3-65cd03262493.png)
> 각 노드 v와 π[v]를 연결하는 간선들로 구성된 트리를 BFS 트리라고 한다. (트리 상의 어떤 간선도 2개의 layer를 건너가지 않는다.)
* 출발점 S로부터 Li에 속한 노드까지의 최단 경로의 길이는 i이다. (여기서 경로의 길이는 경로에 속한 에지의 개수를 의미한다.)
* BFS는 순회 외에도, 각 노드에 대해서 최단 경로의 길이를 구할 수 있다.

![image](https://user-images.githubusercontent.com/84948636/161190844-a56f3126-14cd-4f17-a514-45422d18d528.png)
* 최단 경로를 출력하기

# DFS (Depth-First Search)
* tree에서 inorder, preorder, postorder traversal 원리와 같음
* 가장 간단한 구현은 재귀를 이용하는 것, 가장 중요한 것은 visited 여부를 체크해서 탐색을 멈추고 호출 지점으로 돌아가는 것
* DFS를 반복하여 모든 노드를 방문하는 경우 시간 복잡도는 O(n+m)이다.
