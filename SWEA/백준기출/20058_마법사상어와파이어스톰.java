package bojForm;

import java.io.*;
import java.util.*;

public class 마법사상어와파이어스톰 {

	static int T, N, Q, maxSize, totalIce;
	static int[][] map;
	static int[] command;
	static int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
	static StringTokenizer st;
	
	private static void rotate(int row, int col, int length, int[][] newMap) { // 시계 방향 90도 회전
		
		for(int r=0; r<length; r++) {
			for(int c=0; c<length; c++) {
				newMap[row+c][length-1+col-r] = map[row+r][col+c];
			}
		}
		
	}
	
	private static int[][] melt() { // 한 번에 녹은 얼음 반영
		
		int[][] meltedMap = new int[N][N];
		for(int i=0; i<N; ++i) meltedMap[i] = Arrays.copyOf(map[i], N);
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				int ice = 0;
				// 얼음이 없다면
				if(map[i][j]==0) continue;
				for(int d=0; d<4; ++d) {
					int nx = i + D[d][0];
					int ny = j + D[d][1];
					// 맵 안에 있으면서 얼음이라면
					if(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]>0) ice++;
				}
				// 주변에 얼음이 3개 미만으로 있다면 1 감소
				if(ice<3) meltedMap[i][j]--;
			}
		}				
		
		return meltedMap;
		
	}
	
	private static int[][] divideAndRotate(int l) { // 크기 별로 나눠서 배열을 회전
			
			int length = (int) Math.pow(2, l);
			
			// 격자 돌리기 - r,c가 유동적일 때는 새 공간을 쓰는 것이 좋다
			int[][] rotatedMap = new int[N][N];
			for(int j=0; j<N; j+=length) {
				for(int k=0; k<N; k+=length) {
					rotate(j, k, length, rotatedMap);
				}
			}
			
			return rotatedMap;
		
	}
	
	private static void maxGroup() { // bfs로 탐색
		
		boolean[][] visited = new boolean[N][N];
		maxSize = 0; // 면적이 가장 넓은 그룹
		Deque<int[]> stack = new ArrayDeque<>();
		totalIce = 0; // 전체 얼음의 양
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				
				if(map[i][j]>0 && !visited[i][j]) {
					stack.add(new int[] {i, j});
					visited[i][j] = true;
					totalIce+=map[i][j];
					int count = 1; // 개별 그룹의 면적
					
					while(!stack.isEmpty()) {
						int[] now = stack.pop(); // getFirst는 peek임
						int x = now[0];
						int y = now[1];
						
						for(int d=0; d<4; d++) {
							int nx = x+D[d][0];
							int ny = y+D[d][1];
							
							if(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]>0 && !visited[nx][ny]) {
								count++;
								visited[nx][ny] = true;
								stack.add(new int[] {nx, ny});
								totalIce+=map[nx][ny];
							}
						}
					}
					
					maxSize = Math.max(maxSize, count);
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\20058_마법사상어와파이어스톰_input.txt"));		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			bw.write("#" + String.valueOf(testcase) + " ");
			bw.write('\n');
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			// 맵 초기화
			N = (int) Math.pow(2, N);
			map = new int[N][N];
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 명령어 초기화
			command = new int[Q];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<Q; i++) command[i] = Integer.parseInt(st.nextToken());
			
			// 파이어 스톰
			for(int i=0; i<Q; i++) {
				// 나누기
				map = divideAndRotate(command[i]);
				// 얼음 녹이기
				map = melt();
			}
			
			maxGroup();
			
			// 얼음 출력의 합과 가장 큰 덩어리가 차지하는 칸의 개수
			bw.write(String.valueOf(totalIce) + '\n' + String.valueOf(maxSize));
			bw.write('\n');
			
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}
	
}
