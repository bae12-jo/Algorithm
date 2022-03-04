import java.util.*;
import java.io.*;

/*
Adjacency Matrix과 PriorityQueue 활용해 모든 정점까지 최단 비용을 구한다
시간복잡도 O((V^2)log V)

궁금한 점..
Q. graph를 2차원 배열로 선언하는 것과, 도착점과 비용을 저장하는 node 클래스를 따로 만들어 arraylist로 만드는 것 중 뭐가 더 효율적인가?
- 아마도 matrix는 메모리 낭비가 있을 것 같고 간선이 유의미하게 많지 않다면 대체로 리스트가 유리해보인다.
- 간선의 추가 삭제가 빈번한 경우 matrix가 낫고, 노드의 추가 삭제가 많은 경우 list가 낫다.
Q. prioirityqueue를 정수형 배열로 만드는 것과, node로 만드는 것 중 뭐가 더 효율적인가?
- 위와 비슷한 관점, 다만 여기서는 배열이라고 더 공간이 낭비되는 것은 아님
*/

public class Dijkstra{
	
	static int n, e; // node개수, edge 개수
	static int[][] graph; // graph의 모든 간선
	static int[] dist; // 최단 거리
	
	
	static void dijkstra(int src){
		// int값 2개를 쌍으로 넣어줘야 함
		// 두 값의 첫번재 인덱스로 비교하는 람다함수, a는 최단 거리를 b에는 정점의 번호를
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0]-b[0]);
		boolean[] visited = new boolean[n+1];
		for(int i=0; i<n; ++) dist[i] = Integer.MAX_VALUE;
		dist[src] = 0; // 주어진 위치는 0으로 초기화 (자기 자신으로 가는데 0이 걸리므로)
		pq.add(new int[] {0, src});
		
		while(!pq.isEmpty()){
			int[] crr = pq.poll(); // 최단거리가 더 작은 배열이 dequeue됨
			int u = curr[1]; // 1번 인덱스에 있는 정점의 번호를 꺼냄
			if(visited[u]) continue; // 방문한 노드라면 건너뜀, 시작 노드가 같은 값이 큐에 여러개 있을 수 있음
			
			visited[u] = true; // 시작 노드 방문 표시
			for(int v=0; v<n; ++v){ // u로부터 다른 노드로 탐색
				if(dist[v]>dist[u] + graph[u][v]){ // 방문하지 않은 dist는 무한대값
					dist[v] = dist[u]+graph[u][v]; // 더 작은 값 업데이트
					pq.add(new int[] {dist[v], v});
				}
			}
		}
	}

	public static void main(String[] args){
	
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt():
		e = sc.nextInt():
		
		// graph 초기화		
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(i==j) graph[i][j] = 0;
				else graph[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// graph에 간선 채우기
		for(int i=0; i<e; i++){
			int u = sc.nextInt():
			int v = sc.nextInt():
			int cost = sc.nextInt():
			graph[u][v] = graph[v][u] = cost;
		}
		
		// 최단 경로 구하기
		dijkstra(0);
		
		// 결과 출력
		for(int i=0; i<n; i++){
			Sysetm.out.print(dist[0]+" ");
		}
	
	}

}
