import java.util.*;
import java.io.*;

public class BOJ_22856{
	
	static int n, edges, rightEdge;
	static int tree[][];
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		tree = new int[n+1][2];
		edges = n-1;
		
		
		while(n-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			tree[d][0] = l;
			tree[d][1] = r;
		}

		Stack<Integer> stack = new Stack<>();
		if(tree[1][1]!=-1){
			stack.push(tree[1][1]);
			rightEdge++;
		}
		while(!stack.isEmpty()){
			int nextRight = stack.pop();
			if(nextRight==-1) return;
			if(tree[nextRight][1]!=-1){
				stack.push(tree[nextRight][1]);
				rightEdge++;
			}
		}
		
		System.out.println((2*edges)-rightEdge);
		
	}
}
