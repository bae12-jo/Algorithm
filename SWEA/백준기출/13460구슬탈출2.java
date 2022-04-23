// package bojForm;

import java.io.*;
import java.util.*;

/**
 * @author bailey
 * https://www.acmicpc.net/problem/13460
 */

public class 구슬탈출2{
	
	//static int T = 0;
	static int n = 0;
	static int m = 0;
	static int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // Delta: 0 상, 1 우, 2 하, 3 좌
	static char[][] map;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	/* 색깔공 정보를 담을 클래스 */
	static class Ball{
		int r, c; // row, col
		boolean isRed; // red or not
		
		public Ball(int r, int c, boolean isRed) { // alt+shift+s 누른 후 필드로 생성자 만들기 선택
			this.r = r;
			this.c = c;
			this.isRed = isRed;
		}
		

		/** d 방향으로 ball을 이동했을 때 ball의 최종 위치 // 메소드명 드래그하고 alt+shift+j 누른 후 주석 생성
		 * d 방향으로 이동하다가 #을 만나면 이전 상태에서 종료하고 두 공이 겹치지 않도록 처리, O을 만나면 바로 종료
		 * @param d
		 * @return
		 */
		public Ball move(int d) {
			for(int i=1; ; ++i) { // 종료 조건을 만날 때까지 반복
				int nr = r + D[d][0]*i;
				int nc = c + D[d][1]*i;
				// 구멍을 만난다면
				if(map[nr][nc]=='O') {
					return new Ball(nr, nc, isRed);
				}
				// 벽을 만난다면 벽 이전에 멈추고 ball 위치에 가벽을 세운다
				else if(map[nr][nc]=='#') {
					Ball newBall = new Ball(nr-D[d][0], nc-D[d][1], isRed);
					map[newBall.r][newBall.c] = '#'; // 가벽은 이번 동작이 끝나면 치워야 함
					return newBall;
				}
			
			}
			
		}
	}
	
	static class BallPair{
		Ball red, blue;
		
		Ball[] order = new Ball[2];

		public BallPair(Ball red, Ball blue) {
			this.red = red;
			this.blue = blue;
		}	
		
		/** 진행 방향에 따라 red와 blue 중 무엇이 먼저 움직여야 되는지 판단
		 * @param d
		 */
		public void getOrder(int d) {
			if(d==0) { // 상
				order[0] = red.r < blue.r ? red : blue;
			}
			else if(d==1) { // 우
				order[0] = red.c < blue.c ? blue : red;
			}
			else if(d==2) { // 하
				order[0] = red.r > blue.r ? red : blue;
			}
			else if(d==3) { // 좌
				order[0] = red.c > blue.c ? blue : red;
			}
			order[1] = order[0]==red ? blue : red; // 공통 코드는 밖으로 빼기
		}
	}
	
	/** bfs로 보드 탐색
	 * @param red
	 * @param blue
	 * @return
	 */
	static int bfs(Ball red, Ball blue) {
		
		Queue<BallPair> queue = new LinkedList<BallPair>();
		boolean[][][][] visited = new boolean[n][m][n][m]; // red r, red c, blue r, blue c
		
		// 초기 설정
		queue.offer(new BallPair(red, blue));
		visited[red.r][red.c][blue.r][blue.c] = true;
		
		int size = 0, turn = 1; // 큐의 크기 만큼 돌아가며, 한번 다 돌았을 경우 1턴이 됨
		while(!queue.isEmpty()) {
			
      // 10번을 초과하면 종료
			if(turn>10) break;
			size = queue.size();
			
			while(size-- > 0) {
				// 가장 앞에 있는 볼 좌표 가져오기
				BallPair head = queue.poll();
				for(int d=0; d<D.length; ++d) {
					// 각각의 볼 이동시키기 - 누가 먼저? (BallPair에서 메소드 구현하기)
					head.getOrder(d);
					// 우선순위에 따라서 ball 이동시키기 (Ball에서 메소드 구현하기)
					Ball moveFirst = head.order[0].move(d);
					Ball moveSecond = head.order[1].move(d);
					// 두 공의 이동 과정에서 만들었던 가벽 제거
					if(map[moveFirst.r][moveFirst.c]=='#') map[moveFirst.r][moveFirst.c]='.';
					if(map[moveSecond.r][moveSecond.c]=='#') map[moveSecond.r][moveSecond.c]='.';						
					// 어느 공이 빨간색인가?
					Ball redBall = moveFirst.isRed ? moveFirst : moveSecond;
					Ball blueBall = redBall == moveFirst ? moveSecond : moveFirst;
					// 공들의 이동결과에 대한 판단
					// 파란공이 들어가면 fail -> continue
					// 빨간공이 들어가면 success -> break and return
					// 둘 다 안 들어갔다면 일반적인 bfs 탐색 해주면 됨
					if(map[blueBall.r][blueBall.c]=='O') continue;
					else if(map[redBall.r][redBall.c]=='O') return turn;
					else {
						if(!visited[redBall.r][redBall.c][blueBall.r][blueBall.c]) {
							visited[redBall.r][redBall.c][blueBall.r][blueBall.c] = true;
							queue.offer(new BallPair(redBall, blueBall));
						}
					}
				}
			}
			//if(turn==10) break;
			//else turn++;
			turn++;
		}
		
		// 10번 이하로 빨간 구슬을 빼낼 수 없는 경우
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\13460_구슬탈출2_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//T = Integer.parseInt(br.readLine());
		
		//for(int testcase=1; testcase<=T; ++testcase) {
			
			/* 입력 받기 */
			st = new StringTokenizer(br.readLine());		
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			Ball red = null, blue = null;
			
			map = new char[n][m];
			for(int r=0; r<n; ++r) {
				String s = br.readLine();
				//System.out.println(br.readLine().toCharArray());
				//map[n] = br.readLine().toCharArray();
				for(int c=0; c<m; ++c) {
					map[r][c] = s.charAt(c);
					if(map[r][c]=='R') red = new Ball(r, c, true);
					else if(map[r][c]=='B') blue = new Ball(r, c, false);
				}
			}
			
			/* 결과 출력 */
			//bw.write(String.valueOf("#" + testcase + " "));
			bw.write(String.valueOf(bfs(red, blue))+'\n');
		//}
		
		bw.flush();
		bw.close();
	}
}
