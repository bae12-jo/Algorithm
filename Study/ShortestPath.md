# Shortest Path Problem 최단 경로 문제

* 두 노드를 잇는 간선의 비용이 최소가 되는 경로를 찾는 것
* 최단 경로 문제에서 Undirect, direct 여부는 중요하지 않음
* 모든 노드의 가중치가 같다면 BFS 알고리즘으로도 최단 경로를 해결할 수 있음
* 주요 알고리즘: 다익스트라, 벨만-포드, 플로이드-와샬

|알고리즘|다익스트라|벨만포드|플로이드-와샬|
|:---:|:---:|:---:|:---:|
|음수 가중치|비허용|허용||
|negative cycle|비허용|비허용||
|strategy|greedy|greedy|dp|
|usage|one to all|one to all|all to all|

negative cycle이란? 사이클의 총합이 음수가 되는 것, 무한히 돌게 되므로 경로라고 볼 수 없음

## 시간복잡도 
* 다익스트라 O(|V|^2+|E|) 혹은 O(|V|log|E|)
* 벨만포드 O(|V||E|)

## Optimal Substructure
* 최단 거리 알고리즘의 핵심은 `최단 경로의 부분 경로는 최단 경로` 이다.
| D(s, v) = D(s, u) + W(u, v)
* 최단 경로를 분해하면 시작노드 s에서 v에 이르는 최단경로는 s에서 u까지의 최단경로에 u에서 v사이의 가중치를 더한 값이다.
* 이처럼 어떤 문제의 최적해가 부분문제들의 최적해로 구성된다면 해당 문제는 optimal substrcture를 가지므로 DP나 Greedy 기법으로 해결할 수 있다.
* 최단 경로는 사이클을 포함하지 않는다. (음수 사이클이 없다는 가정 하에서)

## Edge Relaxation
* 변 경감 연산은 최단 경로를 구하는 과정에서 경로를 구성하고 있는 엣지 가중치의 합을 줄여나간다.
* 최단 경로 알고리즘은 모든 edge에 대해서 relaxation을 기본 연산으로 사용하며 다만 알고리즘별로 relaxation 연산을 어떤 순서로 하느냐 차이가 있다.
* 기존에 알고 있던 경로보다 더 나은 경로를 알게 되어 갱신하는 과정이다.
* 최소 스패닝 트리를 구하는 프림 알고리즘도 정점을 기준으로 최소 간선을 선택하므로 relaxation이 일어난다. (kruskal은 cut property를 사용한다)
* relaxation이란 처음에 무한대로 설정되어 있던 최단 경로가 더 빠른 경로로 대체되는 것을 말한다.

## 최단 경로 문제 유형

(1) Single Sourse (단일 출발), One to All | 다익스트라, 벨만-포드
- 단일 노드 v에서 출발하여 그래프 내 모든 다른 노드로의 최단 경로를 한번에 다 찾는 문제

(2) Single Destincation (단일 도착)
- 목적지 v가 주어지면 모든 노드들로부터 출발하는 가장 짧은 경로를 찾는 문제 (무방향 그래프에서 단일 출발 문제를 반대로 뒤집은 것)

(3) Single Pair (단일 쌍), One to One | 다익스트라, 벨만-포드를 GPU 병렬 처리
- 주어진 꼭지점 u와 v에 대해 u에서 v까지 최단 경로를 찾는 문제
- 최악의 경우 시간복잡도가 Single Source 문제보다 나은 알고리즘이 없음, 즉 Single Source를 사용하다가 목적에 도달하면 멈추는 방식을 사용
- Single Pair 만을 위한 알고리즘 중에 유의미하게 유용한 알고리즘이 없음

(4) All Pair (전체 쌍) All-to-All | 플로이드-워샬 혹은 다익스트라, 벨만-포드를 GPU 병렬 처리
- 그래프 내 모든 노드 쌍들 사이의 최단 경로를 찾는 문제

# I. Single Source (One to All)
* 음수 사이클이 없는 가중치 방향 그래프 G=(V,E)와 출발 노드 S∈V가 입력으로 주어진다
* 처음에는 d[s]=0, d[v]=∞ 로 시작한다.
* 알고리즘이 진행됨에 따라 감소하면서 d[v]>=δ(s, v)를 유지하다가 d[v]=δ(s,v)가 되면 종료한다.
* s에서 v까지 최단 경로상에서 v의 직전 노드(predecessor)를 π[v]라고 한다. 그런 노드가 없는 경우 π[v]=NULL

# I-1. Bellman-Ford
* `n-1`번 반복하면서 edge relaxation을 해준다.
* edge relaxation이 종료되어 모든 최단 경로가 찾아진 후에 d[v] > d[u] + w(u, v)인 에지가 있다면 그것은 음수 사이클이 존재한다는 의미이다. (최단 경로 존재하지 않음)
* 즉 최단 경로가 존재하지 않다는 것을 알아낼 수 있다.
* 음수 가중치를 허용하지만 음수 사이클은 허용하지 않는다. (음수 사이클이 존재한다는 것은 최단 거리가 없음을 의미한다.)

## 동작
![image](https://user-images.githubusercontent.com/84948636/157872554-71e81688-f0ff-498f-a11a-00094445f030.png)

(1) 시작 노드를 제외한 모든 노드의 가중치를 무한대로 초기화 한다.
(2) relaxation order를 갱신한다.
(3) n-1 라운드 동안 반복한다.

### 최악의 경우
* 벨만포드는 다익스트라에 비해 시간복잡도가 훌륭한 알고리즘은 아니다. O(|V||E|)이므로 최대 O(n^2)이 된다.
* 중간 노드가 갱신될 때마다, 그 노드에 인접한 노드들이 매번 값을 바꿔야 한다.
* Dense graph의 경우 엣지 수가 노드수의 제곱에 근사하므로 간단히 표현하면 최대 V^3이 된다.
* 노드가 완전히 d[v]를 종료한 후에, 인접 노드의 탐색이 시작되어야 한다. -> 벨만 포드가 아닌 다익스트라가 대두된 계기가 된다.

```java
import java.io.*;
import java.util.StringTokenizer;

class Bus{
	int u;
	int v;
	int val;
	public Bus(int u,int v, int val) {
		this.u = u;
		this.v = v;
		this.val = val;
	}
}

public class Bellman-ford {
	static int n,m;
	static Bus[] e;
	static long[] dist;
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		e = new Bus[m];
		
		
		// 1. 출발 노드 설정
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			e[i] = new Bus(u,v,val);
		}
		
		// 2. 최단거리 테이블 초기화
		dist = new long[n+1];
		for(int i=1; i<n+1; i++) {
			dist[i] = INF;
		}
		
		// 벨만포드 알고리즘 실행 (true: 음수 순환 존재, false: 음수 순환 존재x)
		if(bellmanford(1)) { // 음수 순환 존재하면 -1 출력 
			System.out.println(-1);
		}
		else {
			// 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단거리 출력 
			for(int i=2; i<n+1; i++) {
				if(dist[i] == INF) {// 도달할 수 없으면 -1 
					System.out.println("-1");
				}
				else { // 최단 거리 출력 
					System.out.println(dist[i]);
				}
			}
		}
		
	}
	static boolean bellmanford(int start){
		
		dist[start] = 0;
		
		// n번 반복 (음수 간선 순환 체크안하려면 n-1번 반복)
		for(int i=0; i<n; i++) {
			// 매 반복마다 모든 간선을 확인 
			for(int j=0; j<m; j++) {
				int cur = e[j].u;
				int next = e[j].v;
				int cost = e[j].val;
						
				// 도달 불가능한 정점이 음수가중치일 경우 계산이 이상해지므로 도달 불가능한 곳은 건너뛰기
				if(dist[e[j].u] == INF) continue;
				
				// 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧은 경우 
				if(dist[next] > (dist[cur] + cost)) {
					dist[next] = dist[cur] + cost;
							
					// n번째 라운드에서 값이 갱신된다면 음수 순환 존재 
					if (i == n-1) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
```


# I-2. Dijkstra
* BFS를 기본으로 한다.
* 출발점은 0, 나머지 노드의 최단 거리는 무한대로 초기화하고 시작한다.
* 최소인 노드를 찾고, 그 노드의 인접 노드로의 간선만 relaxation을 한다.
* 음수 가중치를 허용하지 않는다. 
* 이미 방문한 정점이 최단거리를 갖기 때문에 거기에 인접한 다음 방문 노드가 거리를 갱신했을 때 그 노드까지의 최단 거리 도출을 보장할 수 있다.
* dist[u] = dist[v] + d[v][u] // d(u)=min v∉s d(v) 인 노드 u에 대해서 d(u)는 s에서 u까지의 최단 경로의 길이다.

## 동작
![image](https://user-images.githubusercontent.com/84948636/157872761-1ccc6ebb-302e-4666-91aa-aa5c1644330f.png)

(1) 시작 노드를 제외한 모든 노드의 가중치를 무한대로 초기화 한다. (시작 노드 가중치는 0)
(2) 아직 방문하지 않은 정점 중 거리가 가장 짧은 정점을 하나 선택해 방문한다.
(3) 해당 정점에서 인접하고 아직 방문하지 않은 정점들의 거리를 갱신한다.

## 시간복잡도 
* prim 알고리즘과 동일
* 우선순위 큐를 사용하지 않고 구현할 경우 O(n^2)
* 이진힙을 우선순위 큐로 사용할 경우 O(nlog2n+mlong2n)
* 피보나치 힙을 사용하면 O(nlog2n+m)에 구현 가능 (타잔의 논문)

```java
class dijkstra_example{
	static List<Node>[] list;
	static int[] dist;
	static boolean[] visited[];
	
	// 연결리스트로 그래프 구현
	class Node implements Comparable<Node>{
		int to;
		int weight;
		
		Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o){
			return Integer.compare(this.weight - o.weight);
		}
	}
	
	// 그래프 초기화
	static void input(int n, int maps[][]){
		visited = new boolean[n+1];
		dp = new int[n+1];
		
		list = new ArrayList[n+1];
		
		for(int i=1; i<=n; ++i) list[i] = new ArrayList<>();
		
		for(int i=0; i<maps.length; i++){
			int v1 = maps[i][0];
			int v2 = maps[i][1];
			int w = maps[i][2];
			
			list[v1].add(new Node(v2, w));
			list[v2].add(new Node(v1, w));
		}
		
		dijkstra(1);
		
		for(int num: dist) System.out.println(num + " ");
		
		
	}
	
	// 우선순위큐로 구현한 다익스트라
	static void dikstra(int start){
		Queue<Node> q = new PriorityQueue<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		q.add(new Node(start, 0));
		
		while(!q.isEmpty()){
			Node node = q.poll();
			int to = node.to;
			
			if(check[to]) continue;
			check[to] = true;
			
			for(Node next: list[to]){
				if(dist[next.to] > dist[to] + next.weight){
					dist[next.to] = dist[to] + next.weight;
					q.add(new Node(next.to, dist[next.to]));
				}
			}
		}
	}
	
	public static void main(String[] args){
		int[][] a = {{1,2,2}, {1,4,1}, {1,3,5}, {2,3,3}, {2,4,2}, {3,4,3}, {3,5,1}, {4,5,1}, {5,6,2}, {3,6,5}};
		int n=6;
		input(n,a);
	}
}
```

![image](https://user-images.githubusercontent.com/84948636/157911532-b74b801c-1ee7-4d65-a557-9289bcb3d590.png)
![image](https://user-images.githubusercontent.com/84948636/157911548-1df57619-c959-4025-bb4f-788fa706c2d6.png)


# II. All Pair (All to All)

# II-1. Floyd-warshall
* DP를 이용하는 대표적인 알고리즘
* 모든 노드가 1에서 n사이라는 것이 중요 G=(V,E), V={1,2,...,n}
