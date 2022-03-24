# Minimum Spanning Tree 최소 스패닝 트리

## 개념
그래프 내의 모든 정점을 연결한 트리를 Spanning Tree라고 하며 Minimum Spanning Tree란 그래프 내의 모든 정점을 연결하는 간선의 총합이 가장 작은 트리를 말한다.
* 그래프에서 최소 스패닝 트리는 1개 이상일 수 있다.
* 스패닝 트리에서 N개의 정점을 모두 연결하는 간선은 최소 N-1개이다.
* 최소 스패닝 트리에서는 정점 간 사이클이 존재하면 안 된다.
* 최소 스패닝 트리를 구현하는 대표적인 알고리즘은 `크루스칼`과 `프림`이다.

## Kruskal와 Prim 비교
| Algoritm | Kruskal | Prim |
|:---:|:---:|:---:|
| Focus on | Edge | Node |
| Concept | Greedy | Greedy |
| Used Structure | PriorityQueue<Edge>, Union-Find, parent[N] | PriorityQueue<Edge>, List<Edge>, boolean visited[n+1] |
| Usages | Sparse Graph | Dense Graph |

![image](https://user-images.githubusercontent.com/84948636/159829397-07558097-1e27-4ea8-98be-d6003aab45a7.png)
  
- 크루스칼은 그래프 내 간선의 수가 적은 '희소 그래프'에 적합하다.
- 프림은 그래프 내 간선의 수가 많은 '밀집 그래프'에 적합하다.
- 백준 1922번 문제 포함 PS 문제에서는 대체로 크루스칼이 좀 더 빠르다.

# Kruskal

## 복잡도

크루스칼의 복잡도는 정렬 알고리즘의 성능에 달려있다.
일반적으로 Quick Sort를 사용하므로 `O(eloge)` 이다.
참고로 우선순위큐의 삽입 시 복잡도는 O(logn)이다.

## 동작

1. 그래프 간선을 가중치의 오름차순으로 정렬한다. (우선순위 큐)
  - 3차원 배열에 입력 받고 정렬하는 방법
  - Comparable을 구현한 클래스를 파라미터로 하는 우선순위 큐에 입력 받는 방법
2. 간선을 하나씩 선택하여 MST 집합에 추가한다. (parent 배열, union-find)
  - 이때 사이클을 생성하지 않도록 부모를 저장하는 parent 배열을 만든 후, find를 통해 부모를 비교한다.
  - 부모가 같은 경우 사이클이 생성되므로 집합에 포함하지 않고, 다르면 포함한 후 union으로 한 집합으로 만들어준다.
3. 문제 정의에 따라 간선의 비용을 더해주거나, 간선 집합을 반환한다.

## 주의
Edge는 두 정점과 간선을 인스턴스 변수로 갖는다.

## 샘플 코드

```java

/*
sample input(첫 줄의 첫 숫자는 정점의 개수, 두 번째 숫자는 간선의 개수).
6 9
1 6 5
2 4 6
1 2 7
3 5 15
5 6 9
3 4 10
1 3 11
2 3 3
4 5 7
 */

import java.util.*;
import java.io.*;

class BOJ_1922{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N, M, cost = 0;
	static PriorityQueue<Edge> graph;
	static int[] parent;
	
	static class Edge implements Comparable<Edge>{
		int v1;
		int v2;
		int w;
		
		Edge(int v1, int v2, int w){
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e){
			return this.w - e.w;
		}
	}
	
	// 그래프 입력 받기
	static void input() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new PriorityQueue<>();
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			graph.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		br.close();
	}
	
	// 크루스칼
	static int kruskal(){
		
		// 부모를 저장할 배열 선언 후 자기 자신을 부모로 갖도록 초기화
		parent = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		int edgeNum = 0; // 간선 수가 노드-1이 되면 종료
		while(!graph.isEmpty()){
			if(edgeNum == N-1) break ;
			
			Edge e = graph.poll();
			if(find(e.v1)!=find(e.v2)){
				cost += e.w;
				union(e.v1, e.v2);
				edgeNum++;
			}
			
		}
		
		return cost;
		
	}
	
	// 부모 찾기
	static int find(int x){
		if(parent[x] == x){
			return x;
		}
		else return find(parent[x]);
	}
	
	// 더 작은 부모로 통일하기
	static void union(int x, int y){
		int a = find(x);
		int b = find(y);
		if(a>b){
			parent[a] = b;
		}else{
			parent[b] = a;
		}
	}

	public static void main(String[] args) throws IOException{
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input();
		//System.out.println(kruskal());
		bw.write(String.valueOf(kruskal()));
		bw.flush();
		bw.close();
	}
}
```
  
# Prim 
  
## 복잡도

반복문이 정점의 수 n만큼 반복하고, 내부 반복문이 n번 반복되므로 시간 복잡도는 `O(n^2)`이다.
  
## 동작
  
1. 시작 정점을 MST 집합에 포함한다.
2. 앞 단계에서 만들어진 MST 집합의 정점과 인접한 정점 중 최소 간선을 가지는 정점을 선택한다.
3. 트리가 n-1개의 간선을 가질 때까지 반복한다.
  
## 주의점
  
Undirect Graph의 경우 {V1, V2, W}와 {V2, V1, W}를 모두 리스트에 입력해주어야 한다.
Edge는 다음 정점과 간선을 인스턴스 변수로 갖는다.
  
## 샘플 코드

``` java
import java.util.*;
import java.io.*;

class BOJ_1922{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N, M, cost = 0;
	static PriorityQueue<Edge> pq;
	static ArrayList<Edge>[] list;
	
	static class Edge implements Comparable<Edge>{
		int to;
		int cost;
		
		Edge(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge e){
			return this.cost - e.cost;
		}
	}
	
	// 그래프 입력 받기
	static void input() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) list[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list[n1].add(new Edge(n2, e));
			list[n2].add(new Edge(n1, e));
		}
		
		br.close();
	}
	
	// 프림
	static int prim(){
		
		boolean[] visited = new boolean[N+1];
		pq = new PriorityQueue<>();
		
		pq.offer(new Edge(1, 0));
		
		int edgeNum = -1; // 1번 노드는 실질적으로 간선 추가가 안되므로 카운트에서 제외
		while(!pq.isEmpty()){
			if(edgeNum == N-1) break ; // 간선 수가 노드-1와 같아지면 종료
			
			Edge e = pq.poll();
			
			if(visited[e.to]) continue;
			
			visited[e.to] = true;
			
			cost += e.cost;
			edgeNum++;
			
			for(Edge next: list[e.to]){
				if(visited[next.to]) continue;
				pq.offer(next);
			}
			
			// System.out.println(e.to + " " + cost);
			
		}
		
		return cost;
		
	}

	public static void main(String[] args) throws IOException{
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input();
		//System.out.println(prim());
		bw.write(String.valueOf(prim()));
		bw.flush();
		bw.close();
	}
}
```
