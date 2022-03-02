import java.util.*;
import java.io.*;




public class BOJ_1260{

	public static int graph[][];
	public static int n, m, v;
	public static boolean visitedD[];
	public static boolean visitedB[];
	
	public static void bfs(int root){
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int head = queue.poll();
			visitedB[head] = true;
			System.out.print(head + " ");
			for(int i=1; i<=n; i++){
				if((graph[head][i]==1) && (!(visitedB[i]))){
					queue.offer(i);
					visitedB[i] = true;
				}
			}
		}
	}
	
	public static Stack<Integer> stack = new Stack<>();
	public static void dfs(int root){
		stack.push(root);
		while(!stack.isEmpty()){
			int head = stack.pop();
			System.out.print(head + " ");
			visitedD[head] = true;
			for(int i=1; i<=n; i++){
				if((graph[head][i]==1) && !(visitedD[i])){
					dfs(i);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		v = sc.nextInt();
		
		graph = new int[n+1][n+1];
		visitedD = new boolean[n+1];
		visitedB = new boolean[n+1];
		
		for(int i=1; i<=m; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		dfs(v);
		System.out.println();
		bfs(v);
	
	}
}
