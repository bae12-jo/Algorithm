import java.util.*;
import java.io.*;

class BOJ_21937{

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] list = new ArrayList[N+1];
		for(int i=1; i<=N; i++){
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[to].add(from);
		}
		
		int K = Integer.parseInt(br.readLine());

		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(K);
		
		boolean visited[] = new boolean[N+1];
		visited[K] = true;
		while(!queue.isEmpty()){
			int to = queue.poll();
			for(Integer from: list[to]){
				if(!visited[from]){
					visited[from]=true;
					queue.offer(from);
					count++;
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
	}
}
