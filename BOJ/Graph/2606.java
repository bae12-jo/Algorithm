import java.util.*;
import java.io.*;

public class BOJ_2606{

	public static List<Integer>[] graph;
	public static boolean visited[];
	public static int n, m;
	public static BufferedReader br;
	public static StringTokenizer st;
	public static int count = 0;
	
	/* 스택 사용 */
	public static Stack<Integer> stack = new Stack<>();
	public static void findVirus(int root){
		stack.push(root);
		visited[root] = true;
		while(!stack.isEmpty()){
			int curr = stack.pop();
			for(int i: graph[curr]){
				if(!visited[i]){
					stack.push(i);
					visited[i]=true;
					count+=1;
				}
			}
		}
	}
	
	/* 재귀 사용 */
	public static Stack<Integer> stack = new Stack<>();
	public static void findVirus(int root){
		int head = root;
		stack.push(head);
		visited[head] = true;
		while(!stack.isEmpty()){
			int curr = stack.pop();
			for(int i: graph[curr]){
				if(!visited[i]){
					findVirus(i);
					count+=1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for(int i=0; i<=n; i++){
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++){		
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		findVirus(1);
		System.out.println(count);
	
	}
}
