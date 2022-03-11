import java.util.*;
import java.io.*;

class BOJ_14284{
	
	static int N, M, start, end, cost;
	static List<Edge>[] list;

	static class Edge implements Comparable<Edge>{
		
		int to;
		int weight;
		
		Edge(int t, int e){
			this.to = t;
			this.weight = e;
		}
		
		@Override
		public int compareTo(Edge o){
			return this.weight - o.weight;
		}
		
	}
	
	static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for(int i=1; i<=N; ++i) list[i] = new ArrayList<>();
		
		for(int i=0; i<M; ++i){
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[n1].add(new Edge(n2, w));
			list[n2].add(new Edge(n1, w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		br.close();
	}
	
	static int dijkstra(){
		
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Edge> q = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		q.offer(new Edge(start, 0));
		dist[start]=0;
		
		while(!q.isEmpty()){
			
			Edge now = q.poll();
			if(visited[now.to]) continue;
			
			visited[now.to] = true;
			if(now.to==end) {
				return dist[now.to];
			}
			
			for(Edge next: list[now.to]){
				if(dist[next.to]>dist[now.to]+next.weight){
					dist[next.to]=dist[now.to]+next.weight;
					q.add(new Edge(next.to, dist[next.to]));			
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input();
		bw.write(String.valueOf(dijkstra()));
		bw.flush();
		bw.close();		
	}

}
