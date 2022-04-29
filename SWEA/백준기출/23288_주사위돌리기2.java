package bojForm;

import java.util.*;
import java.io.*;

public class 주사위굴리기2 {
	
	static int T, N, K, M, ans;
	static StringTokenizer st;
	static int[][] map;
	static int[][] dice;
	static int[][] D = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // 0동 1북 2서 3남
	static int d; // 주사위 이동방향
	
	
	static class Now{
		int x;
		int y;
		public Now(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static boolean inMap(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
	private static void initDice() {
		dice = new int[4][4];
		dice[0][1] = 2;
		dice[1][0] = 4;
		dice[1][1] = 6; // 항상 바닥면
		dice[1][2] = 3;
		dice[1][3] = 1;
		dice[2][1] = 5;
		dice[3][1] = 1;
	}
	
	private static void moveDice(int d) {
		if(d==0) { // 동쪽
			int temp = dice[1][0];
			for(int i=1; i<4; ++i) dice[1][i-1] = dice[1][i];
			dice[1][3] = temp;
			dice[3][1] = dice[1][3]; // 천장 업데이트
		}else if(d==1) { // 북쪽
			int temp = dice[3][1];
			for(int i=1; i<4; ++i) dice[4-i][1] = dice[4-i-1][1];
			dice[0][1] = temp;
			dice[1][3] = dice[3][1]; // 천장 업데이트
		}else if(d==2) { // 서쪽
			int temp = dice[1][3];
			for(int i=1; i<4; ++i) dice[1][4-i] = dice[1][4-i-1];
			dice[1][0] = temp;
			dice[3][1] = dice[1][3]; // 천장 업데이트
		}else if(d==3) { // 남쪽
			int temp = dice[0][1];
			for(int i=1; i<4; ++i) dice[i-1][1] = dice[i][1];
			dice[3][1] = temp;
			dice[1][3] = dice[3][1]; // 천장 업데이트
		}
	}
	
	private static void getPoint(int x, int y) {
		
		int num = 1; // 최소 1개
		
		boolean[][] visited = new boolean[N][M];
		Deque<int[]> stack = new ArrayDeque<>();
		
		stack.add(new int[] {x, y});
		visited[x][y]=true;
		while(!stack.isEmpty()) {
			int[] now = stack.pop();
			int xx = now[0], yy = now[1];
			for(int i=0; i<4; ++i) {
				int nx = xx + D[i][0];
				int ny = yy + D[i][1];
				if(!inMap(nx, ny)) continue;
				else if(!visited[nx][ny] && map[nx][ny]==map[x][y]) {
					stack.add(new int[] {nx, ny});
					visited[nx][ny]=true;
					num++;
				}
			}
		}
		
		ans += num*map[x][y];
		
	}
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\23288_주사위돌리기2_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			
			bw.write("#" + String.valueOf(testcase) + " ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 맵 초기화
			map = new int[N][M];
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			initDice();
			d = 0; // 주사위 초기 방향 (동)
			Now now = new Now(0, 0); // 주사위 초기 위치
			ans = 0; // 점수 초기화
			
			// K번 주사위 이동
			while(K-->0) {
				
				// 주사위 위치 이동
				if(inMap(now.x+D[d][0], now.y+D[d][1])) {
					now.x = now.x+D[d][0];
					now.y = now.y+D[d][1];
					moveDice(d); // 주사위 회전
				}else {
					d=(d+2)%4; // 방향 반전
					now.x = now.x+D[d][0];
					now.y = now.y+D[d][1];
					moveDice(d); // 주사위 회전
				}
				
				// 점수 획득
				getPoint(now.x, now.y);
				
				// 주사위 방향 결정
				if(map[now.x][now.y]>dice[1][1]) {
					d=(d+1)%4;
					//System.out.println(dice[1][1] + " " + d);
				}
				else if(map[now.x][now.y]<dice[1][1]) {
					d=(d+3)%4;
					//System.out.println(dice[1][1] + " " + d);
				}
				
			}
			
			
			// 폭발한 구슬 총합
			bw.write(String.valueOf(ans));
			bw.write('\n');
		}
		
		br.close();
		bw.flush();
		br.close();
		
	}
	
}
