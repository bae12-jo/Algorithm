import java.util.*;
import java.io.*;

public class BOJ_15650{

	public static int n, m;
	public static boolean[] visited;
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int depth, int at){
		if(depth==m){
			for(int val: arr){
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return ;
		}
		
		for(int i=at; i<=n; i++){
			arr[depth] = i;
			dfs(depth+1, at+1);
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		
		dfs(0, 1);
		System.out.println(sb);
	
	}
}
