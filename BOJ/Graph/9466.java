import java.io.*;
import java.util.*;

public class BOJ_9466{
	
	static BufferedReader br;
	static BufferedWriter bw;
	static int T;
	static int N;
	static int[] graph;
	static int cycle;
	static boolean[] visited; // 방문 여부
	static boolean[] complete; // 방문 노드 탐색 종료 여부
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int now){
		visited[now] = true;
		int next = graph[now];
		
		if(!visited[next]) dfs(next); 
		else if(!complete[next]){ // 해당 방문 노트 탐색이 종료되지 않았는데 방문되었다면 그 노드는 사이클이 있는 노드

			for(int i=next; i!=now; i=graph[i]) cycle++; // 사이클에 해당 하는 모든 노드 카운트
			cycle++; // 자기 자신을 가리키는 경우도 사이클에 포함됨
		}
		
		complete[now] = true; // 해당 노드 탐색 완료
	}
	
	public static void solution() throws IOException{
		
		N = Integer.parseInt(br.readLine());
			
		graph = new int[N+1];
		visited = new boolean[N+1];
		complete = new boolean[N+1];
		
		for(int i=0; i<=N; ++i){
			visited[i] = false;
			complete[i] = false;
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=1; i<=N; ++i){
			graph[i] = Integer.parseInt(st.nextToken()); // out edge 입력 받기
		}
		
		cycle = 0;
		
		for(int i=1; i<=N; ++i){
			if(!visited[i]) dfs(i);
		}
		
		sb.append(Integer.valueOf(N-cycle)).append("\n");
		// System.out.println(ans);
		
	}
	
	public static void main(String[] args) throws IOException{
		
		// System.setIn(new FileInputStream("C:/Users/bailey/Desktop/BOJ/input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0){
			solution();
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
}
