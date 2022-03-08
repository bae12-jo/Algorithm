import java.util.*;
import java.io.*;

class BOJ_12851{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int N, K;
	static int[] time = new int[1000001];
	static int minTime = Integer.MAX_VALUE;
	static int count;
	
	static void input() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		br.close();
		
	}
	
	static void solution(){
		
		if(N>=K){
			System.out.println((N-K)+ "\n1");
			return ;
		}
		
		bfs();		
		
		System.out.println(minTime + "\n" + count);
		
	}
	
	static void bfs(){
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(N);
		time[N]=1;
		
		while(!q.isEmpty()){
			int now = q.poll();
			
			if(minTime<time[now]) return; // 현재 시간보다 더 오래 걸리면 탐색 중지
			
			for(int i=0; i<3; i++){
				int next;
				
				if(i==0) next = now+1;
				else if (i==1) next = now-1;
				else next = now*2;
				
				if(next<0 || next>1000000) continue;
				
				if(next==K){
					minTime = time[now];
					count++;
				}
				
				// 처음 방문하거나, 다른 경우의 수에서 방문했지만 방문 시간이 동일한 경우
				if(time[next]==0 || time[next]==time[now]+1){
					q.offer(next);
					time[next] = time[now]+1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		
		input();
		solution();
	
	}
}
