package bojForm;

import java.io.*;
import java.util.*;

public class 마법사상어와파이어볼 {
	
	static int T, N, M, K;
	static StringTokenizer st;
	static ArrayList<FireBall>[][] map; // 위치에 파이어볼이 여러개 존재하는지 검사하기 위함
	static ArrayList<FireBall> list = new ArrayList<>(); // 전체 파이어볼을 관리하기 위함
	static int[][] D = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	
	static class FireBall{
		int r, c, m, d, s;

		public FireBall(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
		
	}
	
	private static int getWeight() { // 파이어볼의 총 질량 구하기
		
		int count = 0;
		for(FireBall f: list) { // 맵이 아니라 파이어볼 리스트를 사용
			count += f.m;
		}		
		return count;
	}
	
	private static void move() { // 이동 방향과 속도 고려해서 파이어볼 위치 이동
		
		for(FireBall f: list) {
			int nr = (f.r + N + D[f.d][0] * (f.s%N)) % N; // N을 더해야 음수값 방지됨
			int nc = (f.c + N + D[f.d][1] * (f.s%N)) % N;
			// 파이어볼의 위치 업데이트
			f.r = nr;
			f.c = nc;
			// 맵에도 표시
			map[nr][nc].add(f);
		}
		
	}
	
	private static void check() { // 한 위치에 파이어볼이 2개 이상 있을 때 나누기
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				// 파이어볼이 단 한개라면 - 다음 명령에서 다시 채워질테니 비우기
				if(map[i][j].size()==1) map[i][j].clear();
				// 파이어볼이 아예 없거나, 방문 지워진 경우 순서 건너뛰기
				if(map[i][j].size()<1) continue;
				
				int totalM = 0, totalS = 0; // 파이어볼의 총 질량과 총 속도
				boolean allOddOrEven = true; // 방향이 모두 짝수/홀수라면 true(0)
				int prevDir = map[i][j].get(0).d; // 첫번째 파이어볼의 방향 저장
				
				for(FireBall f: map[i][j]) {
					totalM += f.m;
					totalS += f.s;
					// 이전 파이어볼과 방향(짝/홀)이 다른지 검사
					if(prevDir%2!=f.d%2) allOddOrEven=false;
					else prevDir=f.d;
					// 파이어볼 목록에서 지우기
					list.remove(f);
				}
				
				totalS/=map[i][j].size();
				map[i][j].clear(); // 위치 비우기
				if((totalM/=5)==0) continue; // 나누어진 질량이 0이라면 건너뛰기
				
				// 파이어 볼 4개 만들기
				if(allOddOrEven) { // 모두 짝수거나 모두 홀수라면
					for(int k=0; k<8; k+=2)	list.add(new FireBall(i, j, totalM, k, totalS));
				}else {
					for(int k=1; k<8; k+=2)	list.add(new FireBall(i, j, totalM, k, totalS));
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\20056_마법사상어와파이어볼_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			
			bw.write("#" + testcase + " ");
		
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 맵 초기화
			map = new ArrayList[N][N];
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					map[i][j] = new ArrayList<FireBall>();
				}
			}
			
			// 파이어볼 정보 입력 받기
			for(int i=0; i<M; ++i) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken())-1;
				int m = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new FireBall(r, c, m, d, s));	
			}			
			
			// 명령 수행
			for(int i=0; i<K; i++) {
				move(); // 방향과 속도 고려해서 파이어볼 이동
				check(); // 한 칸에 여러 파이어볼 위치하는지 확인
			}
			
			// 남은 파이어 볼의 총 질량 구하기
			bw.write(String.valueOf(getWeight()));
			bw.write('\n');
			
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

}
