import java.util.*;
import java.io.*;

public class BOJ_15666{

	public static int n, m;
	public static boolean[] visited;
	public static int[] input;
	public static int[] arr;
	public static int before;
	public static StringBuilder sb = new StringBuilder();
	
	public static void dfs(int depth, int at, int before){
		if(depth==m){
			for(int val: arr){
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return ;
		}
		
		for(int i=at; i<n; i++){
				if(before!=input[i]){
					before = input[i];
					arr[depth] = input[i];
					dfs(depth+1, i, -1);
				}
		}
	}

	public static void main(String[] args) throws IOException{
		
		/* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken()); */
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		input = new int[n];
		visited = new boolean[n];
		arr = new int[m];
		
		sc.nextLine();
		
		for(int i=0; i<n; i++){
			input[i] = sc.nextInt();
		}
		
		Arrays.sort(input);
		
		dfs(0, 0, -1);
		System.out.println(sb);
	
	}
}
