import java.io.*;
import java.util.*;

public class BOJ_2252{
	
	static int V, E;
	static int[] indegree;
	static List<List<Integer>> graph = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// init V, E
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// init indegree and graph
		indegree = new int[V+1];
		for(int i=0; i<=V; i++) graph.add(new ArrayList<Integer>());
		for(int i=0; i<E; i++){
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			indegree[dst]++;
			graph.get(src).add(dst);
		}
		
		// find 0 indegree
		for(int i=1; i<=V; i++) if(indegree[i]==0) queue.add(i);
		
		while(!queue.isEmpty()){
			int src = queue.poll();
			bw.write(src + " "); // append into the result list
			for(int i=0; i<graph.get(src).size(); i++){ // search adjacent node
				int node = graph.get(src).get(i);
				if(--indegree[node]==0) queue.add(node);
			}
			
		}
		br.close();
		bw.flush();
		bw.close();
		
	}

}
