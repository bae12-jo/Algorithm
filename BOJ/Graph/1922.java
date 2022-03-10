/* Kruskal - int[][][] graph */

import java.util.*;
import java.io.*;

class BOJ_1922{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N, M, cost = 0;
	static int[][] graph;
	static int[] parent;
	
	// 그래프 입력 받기
	static void input() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new int[M][3];
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[i][0] = n1;
			graph[i][1] = n2;
			graph[i][2] = w;
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
		
		// 간선의 오름차 순으로 graph 정렬하기
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		// 간선 비용이 적은 것부터 추가
		for(int i=0; i<M; i++){
			// 사이클이 발생하지 않는 경우에만 추가
			if(find(graph[i][0])!=find(graph[i][1])){
				cost += graph[i][2];
				union(graph[i][0], graph[i][1]);
				// System.out.println(cost);
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

/* Kruskal - Priority<Edge> graph */

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


/* Prim - PriorityQueue<Edge>, ArrayList<Edge>[], boolean[] visited*/
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
