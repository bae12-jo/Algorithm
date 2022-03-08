// https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-1043-%EA%B1%B0%EC%A7%93%EB%A7%90-JAVA%EC%9E%90%EB%B0%94

import java.util.*;
import java.io.*;

class BOJ_1043{

	static int N, M;
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] party;
	static boolean[] know;
	static boolean[][] network;
	
	static void solution() throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 파티 참여자 수와 파티의 수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 거짓말을 알고 있는 사람의 수를 받아서 true 표시
		st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		if(len==0){ 
			System.out.println(M); 
			return ;
		}else{
			know = new boolean[N+1]; // 사람 번호가 인덱스가 될 것이므로 +1 해주기
			for(int i=0; i<len; i++){
				know[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		// 파티별 참가자 수를 입력 받으며 서로 아는 사람 표시
		
		party = new int[M][];
		network = new boolean[N+1][N+1]; // 사람 번호가 인덱스가 될 것이므로 +1 해주기
		
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			int memNum = Integer.parseInt(st.nextToken());
			party[i] = new int[memNum];
			
			// 첫번째 참가자 입력
			party[i][0] = Integer.parseInt(st.nextToken());
			
			// 같은 모임에 참여한 사람들끼리 아는 사람 표시
			for(int j=1; j<memNum; j++){
				party[i][j] = Integer.parseInt(st.nextToken());
				network[party[i][j]][party[i][j-1]] = network[party[i][j-1]][party[i][j]] = true;
			}
		}
		
		// 진실을 아는 사람이 주변 사람에게 퍼뜨리기
		for(int i=1; i<=N; i++){
			if(know[i]) dfs(i);
		}			
		
		int count = 0;
		for(int i=0; i<M; i++){
			if(!know[party[i][0]]) count++; // 같은 모임 사람은 모두 알거나 모두 모르므로 한명만 검사
		}
		
		System.out.println(count);
		
	}
	
	static void dfs(int n){
		
		for(int i=1; i<=N; i++){
			if(network[n][i] && !know[i]){
				know[i] = true;
				dfs(i);
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
				
		solution();

	}
}
