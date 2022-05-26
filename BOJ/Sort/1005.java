import java.io.*;
import java.util.*;

public class BOJ_1005{
	
	static int T, N, K, W;
	static int[] indegree;
	static int[] time;
	static int[] totalTime;
	static List<List<Integer>> graph;
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// init T
		T = Integer.parseInt(st.nextToken());
		
		for(int testcase=0; testcase<T; testcase++){
			
			// init N, K
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			queue = new LinkedList<>();
			indegree = new int[N+1];
			time = new int[N+1];
			totalTime = new int[N+1];
			
			// init time
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) time[i] = Integer.parseInt(st.nextToken());
			
			// init indegree and graph
			for(int i=0; i<=N; i++) graph.add(new ArrayList<Integer>());
			for(int i=0; i<K; i++){
				st = new StringTokenizer(br.readLine());
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				indegree[dst]++;
				graph.get(src).add(dst);
			}
			
			// init W
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			
			// find 0 indegree
			for(int i=1; i<=N; i++) if(indegree[i]==0){
				queue.add(i);
				totalTime[i] += time[i];
			}
			
			while(!queue.isEmpty()){
				int src = queue.poll();
				if(src==W) break; // end case
				for(int i=0; i<graph.get(src).size(); i++){ // search adjacent node
					int node = graph.get(src).get(i);
					if(totalTime[node]<totalTime[src]+time[node]) totalTime[node]=totalTime[src]+time[node]; // replace with bigger one
					if(--indegree[node]==0) queue.add(node);
				}
				
			}
			bw.write(String.valueOf(totalTime[W])+'\n');
		}

		br.close();
		bw.flush();
		bw.close();
		
	}

}
