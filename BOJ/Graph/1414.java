
/*
유방향 그래프를 프림 알고리즘과 연결리스트로 구현할 때
1. 입력된 방향만 리스트에 넣어준다. 단방향 때처럼 (a,b), (b,a) 양방향을 동일한 값으로 넣어줄 필요가 없다.
2. (a,b)와 (b,a)의 값이 다를 수 있으므로 모두 조회하여 더 적은값을 찾아준다.
3. 주의할 점은 실제로 입력 받은 간선이 없다고 해서 리스트를 비우는게 아니라 무한대값으로 채워줘야 한다.
4X4 {{0100}, {0020}, {0000}, {0030}} 3번째 행을 비워두면 아무값도 조회가 안되므로 4행으로 넘어갈 수 없다.
따라서 유의미한 값이 없는 간선도 모두 채워줘야 하며 시간복잡도도 증가하므로 연결리스트보다 배열이 나은 선택일 수 있다.
4. 배열 메모리 공간이 아깝고 양방향을 모두 조회하는 게 번거롭다면 프림이 아닌 크루스칼로 비교적 간단하게 해결할 수 있다.
*/


// (1) Prim + Int Array

import java.util.*;
import java.io.*;

class BOJ_1414{
	
	static int N;
	static boolean valid;
	static int[][] graph;
	static boolean[] visited;
	static int total = 0;
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge e){
			return this.weight - e.weight;
		}
	}
	
	public static int MST(int index){
		PriorityQueue<Edge> q = new PriorityQueue<>();
		
		int count = -1;
		int result = 0;
		
		visited = new boolean[N];
		
		q.offer(new Edge(index, 0));
		
		while(!q.isEmpty()){
			if(count==N-1) break;
			
			Edge now = q.poll();
			
			if(visited[now.to]) continue;
			visited[now.to] = true;
			
			result += now.weight;
			count++;
			
			int nowComputer = now.to;
			
			for(int i=0; i<N; ++i){
                if(graph[i][nowComputer] == Integer.MAX_VALUE && graph[nowComputer][i]==Integer.MAX_VALUE) continue;
				q.offer(new Edge(i, Math.min(graph[i][nowComputer], graph[nowComputer][i])));
			}
			
		}
		
		return count<N-1 ?-1:total-result;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		
		for(int j=0; j<N; j++){
			String s = br.readLine();
			for(int i=0; i<N; ++i){
				int n = (int)s.charAt(i);
				if(n>=97 && n<=122) n = n-96;
				else if(n>=65 && n<=90) n = n-38;
				else if(n==48) n = Integer.MAX_VALUE;
                graph[j][i] = n;
                if(n!=Integer.MAX_VALUE) total += n;
				
			}
		}
		
		int result = -1;
		for(int i=0; i<N; ++i){
			result = Math.max(MST(i), result);
		}		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();	
	
	}
}
