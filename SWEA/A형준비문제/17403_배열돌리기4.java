import java.io.*;
import java.util.*;


public class Main {

	static int T, N, M, O;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] map; // 값이 들어 있는 배열
	static int[][] command; // 연산 목록
	static int ans;
	
	private static int[][] copyMap(){ // 2차원 배열 복사
		int[][] newMap = new int[N][M];
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				newMap[i][j] = map[i][j];
			}
		}
		
		return newMap;
	}
	
	
	private static void calPoint(int r, int c, int s, int[][] map) { // 배열의 좌표 구하기
		
		for(int layer=0; layer<s; layer++) {
			
			// 시작 좌표 (중심으로 갈수록 값 커짐)
			int x1 = r-s+layer-1;
			int y1 = c-s+layer-1;
			// 끝 좌표 (중심으로 갈수록 값 작아짐)
			int x2 = r+s-layer-1;
			int y2 = c+s-layer-1;
			
			rotate(x1, y1, x2, y2, map);
		}
	}
	
	private static void rotate(int x1, int y1, int x2, int y2, int[][] map) {
		
		int tmp = map[x1][y1];
		
		// 왼
		for(int i=x1+1; i<=x2; ++i) map[i-1][y1] = map[i][y1];
		// 아래
		for(int i=y1+1; i<=y2; ++i) map[x2][i-1] = map[x2][i];
		// 오
		for(int i=x2-1; i>=x1; --i) map[i+1][y2] = map[i][y2];		
		// 위
		for(int i=y2-1; i>=y1; --i) map[x1][i+1] = map[x1][i];
		
		map[x1][y1+1] = tmp;
		
	}
	
	private static int getMin(int[][] tmp) {
		int min = Integer.MAX_VALUE;
		int m = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				m += tmp[i][j];
			}
			min = Math.min(min, m);
			m = 0;
		}
		
		return min;
	}
	
	private static void permutation(boolean[] visited, LinkedList<Integer> list, int size) { // 커맨드 순서 채우고 순서별 최소값 구하기 
		
		// base-case
		if(list.size()==size) {
			
			int[][] tmpMap = copyMap();
			
			for(int i: list) {
				calPoint(command[i][0], command[i][1], command[i][2], tmpMap);
			}
			
			ans = Math.min(ans, getMin(tmpMap));
			
		}
		
		// 순서 채우기
		for(int i=0; i<O; ++i) {
			if(!visited[i]) {
				visited[i]=true;
				list.add(i);
				
				permutation(visited, list, size);
				
				visited[i]=false;
				list.removeLast();
			}
		}
		
	}

	
	public static void main(String[] args) throws IOException{
		
		//System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\17406_배열돌리기4_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//T = Integer.parseInt(br.readLine());
		
		//for(int testcase=1; testcase<=T; testcase++) {
		//	bw.write("#" + String.valueOf(testcase) + " ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			O = Integer.parseInt(st.nextToken());
			
			// 배열 입력 받기
			map = new int[N][M];
			command = new int[O][3];
			for(int[] m: map) {
				Arrays.fill(m, 0);
			}
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<O; ++i) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				command[i] = new int[] {r, c, s};
			}
		
			ans = Integer.MAX_VALUE; // 값 초기화
			permutation(new boolean[O], new LinkedList<Integer>(), O);
			
			bw.write(String.valueOf(ans));
			//bw.write('\n');
		//}

		
		br.close();
		bw.flush();
		bw.close();
		
	}
	
}



// 회전을 delta로 구현한 코드 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Operation {
	int r;
	int c;
	int s;

	public Operation(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class Main {

	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static int[][] arr;
	public static int[][] operated;
	public static Operation[] ops;
	public static int N, M, K;
	public static int min;
	public static boolean[] isPerformed;
	public static int[] sequence;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);

		isPerformed = new boolean[K];
		ops = new Operation[K];
		sequence = new int[K];
		arr = new int[N + 1][M + 1];
		operated = new int[N + 1][M + 1];

		for (int i = 1; i <= N; ++i) {
			line = br.readLine().split(" ");
			for (int j = 1; j <= M; ++j) {
				arr[i][j] = Integer.parseInt(line[j - 1]);
			}
		}

		for (int i = 0; i < K; ++i) {
			line = br.readLine().split(" ");
			ops[i] = new Operation(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
		}

		min = Integer.MAX_VALUE;
		dfs(0);
		
		System.out.println(min);

	}

	public static void dfs(int performed) {
		if (performed == K) {
			operateAndCalc();
			return;
		}

		for (int i = 0; i < K; ++i) {
			if (!isPerformed[i]) {
				sequence[performed] = i;
				isPerformed[i] = true;
				dfs(performed + 1);
				isPerformed[i] = false;
			}
		}

	}

	public static void operateAndCalc() {
		initializeOperated();
		for (int i = 0; i < K; ++i) {
			operate(ops[sequence[i]]);
		}
		calc();
	}

	public static void initializeOperated() {
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				operated[i][j] = arr[i][j];
			}
		}
	}

	public static void operate(Operation op) {
		int r = op.r;
		int c = op.c;
		int s = op.s;
		for (int i = 0; i < s; ++i) {
			int curX = c - s + i;
			int curY = r - s + i;
			int dir = 0;
			int before = operated[curY][curX];
			int tmp = 0;
			while (dir < 4) {
				curX += dx[dir];
				curY += dy[dir];
				tmp = operated[curY][curX];
				operated[curY][curX] = before;
				before = tmp;

				int nextX = curX + dx[dir];
				int nextY = curY + dy[dir];
				if (nextX < c - s + i || nextX > c + s - i || nextY < r - s + i || nextY > r + s - i) {
					dir++;
				}
			}
		}
	}

	public static void calc() {
		for (int i = 1; i <= N; ++i) {
			int sum = 0;
			for (int j = 1; j <= M; ++j) {
				sum += operated[i][j];
			}
			min = min < sum ? min : sum;
		}
	}
}
