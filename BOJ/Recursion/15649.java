import java.util.*;
import java.io.*;

public class BOJ_15649{

	public static int n, m;
	public static boolean[] visited;
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int depth){
		if(depth==m){
			for(int val: arr){
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return ;
		}
		
		for(int i=1; i<=n; i++){
			if(!visited[i]){ // 직전 레벨의 수와 동일한 수는 탐색하지 않음
				visited[i] = true;
				arr[depth] = i;
				dfs(depth+1);
				visited[i]=false;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		visited = new boolean[n+1];
		
		dfs(0);
		System.out.println(sb);
	
	}
}
