// https://www.acmicpc.net/problem/21609

package bojForm;

import java.io.*;
import java.util.*;

public class 상어중학교 {

	static int T, N, M, point;
	static int INF = Integer.MAX_VALUE;
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[][] map;
	static boolean[][] visited;
	static int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static PriorityQueue<Group> q = new PriorityQueue<>(new Comparator<Group>() {

		@Override
		public int compare(Group o1, Group o2) {
			if(o2.size==o1.size && o2.rainbow==o1.rainbow && o2.x==o1.x) return o2.y-o1.y;
			else if(o2.size==o1.size && o2.rainbow==o1.rainbow) return o2.x-o1.x;
			else if(o2.size==o1.size) return o2.rainbow-o1.rainbow;
			return o2.size-o1.size;
		}
		
	});
	
	static class Block{ // bfs 큐가 담을 블록의 위치
		int x;
		int y;
		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Group{ // 블록 그룹의 기준 위치와 크기
		int x;
		int y;
		int color;
		int rainbow;
		int size;
		
		public Group(int x, int y, int color, int rainbow, int size) { // alt+shift+s
			this.x = x;
			this.y = y;
			this.color = color;
			this.rainbow = rainbow;
			this.size = size;
		}	

	}
	
	private static void findGroups(int x, int y, int color) { // 그룹별 사이즈 찾기
		
		int c=0, n=0; // 일반 블록의 개수, 무지개 블록의 개수
		Queue<Block> bfsq = new LinkedList<Block>();
		visited[x][y] = true;
		boolean[][] zero = new boolean[N][N];
		bfsq.offer(new Block(x, y));
		c++;
		
		while(!bfsq.isEmpty()) {
			Block now = bfsq.poll();
			for(int i=0; i<4; ++i) {
				int nx = now.x+D[i][0];
				int ny = now.y+D[i][1];
				if(nx >= 0 && nx < N && ny>=0 && ny<N && map[nx][ny]==color && !visited[nx][ny]) {
					visited[nx][ny] = true;
					bfsq.offer(new Block(nx, ny));
					c++;
				}
				else if(nx >= 0 && nx < N && ny>=0 && ny<N && map[nx][ny]==0 && !zero[nx][ny]) {
					zero[nx][ny] = true;
					bfsq.offer(new Block(nx, ny));
					n++;
				}
			}
		}
		
		// 그룹을 큐에 넣기
		if(c>0 && c+n>1) q.offer(new Group(x, y, color, n, c+n));
		
	}
	
	private static void removeGroup(int x, int y, int color) { // 그룹 지우기
	
		for(boolean[] v: visited) {
			Arrays.fill(v, false);
		}		
		Queue<Block> bfsq = new LinkedList<Block>();
		visited[x][y] = true;
		map[x][y]=INF;
		boolean[][] zero = new boolean[N][N];
		bfsq.offer(new Block(x, y));
		
		while(!bfsq.isEmpty()) {
			Block now = bfsq.poll();
			x = now.x;
			y = now.y;
			for(int i=0; i<4; ++i) {
				int nx = x+D[i][0];
				int ny = y+D[i][1];
				if(nx >= 0 && nx < N && ny>=0 && ny<N && map[nx][ny]==color && !visited[nx][ny]) {
					visited[nx][ny] = true;
					map[nx][ny]=INF;
					bfsq.offer(new Block(nx, ny));
				}
				else if(nx >= 0 && nx < N && ny>=0 && ny<N && map[nx][ny]==0 && !zero[nx][ny]) {
					zero[nx][ny] = true;
					map[nx][ny]=INF;
					bfsq.offer(new Block(nx, ny));
				}
			}
		}
		
	}
	
	private static void rotate() { // 배열 돌리기 (90도 반시계 방향)
		
		for(int layer=0; layer<N/2; layer++) { // 각 레이어마다 돌리기
			int first = layer; // 각 레이어의 시작 위치
			int last = N-layer-1; // 각 레이어의 종료 위치
			for(int i=first; i<last; ++i) {
				int offset = i-first; // i와 반대 방향으로 채워 넣는 경우를 대비
				int top = map[first][i]; // 위쪽 원소 미리 저장해두기
				
				// 배열 돌리기가 헷갈린다면 모서리 첫 위치를 기억하자! (자라나는 방향의 첫 원소)				
				// 위 <- 오
				map[first][i] = map[i][last];
				// 오 <- 아래
				map[i][last] = map[last][last-offset];
				// 아래 <- 왼
				map[last][last-offset] = map[last-offset][first];
				// 왼 <- 위
				map[last-offset][first] = top;
			}
		}
		
	}
	
	private static void gravity() {
		
		for(int i=0; i<N; ++i) { // 열
			int offset = 0;
			for(int j=N-1; j>=0; --j) { // 행
				int block = map[j][i];
				if(block==INF) offset+=1;
				else if(block==-1) offset=0;
				else{
					map[j+offset][i] = block;
					if(offset>0) map[j][i] = INF;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		// System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\21609_상어중학교_input.txt"));
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// T = Integer.parseInt(br.readLine());
		
		// for(int testcase=1; testcase<=T; testcase++) {
			//bw.write("#" + String.valueOf(testcase) + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			point=0;
			
			// 배열 입력 받기
			map = new int[N][N];
			for(int[] m: map) {
				Arrays.fill(m, 0);
			}
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			while(true) { // 블록 그룹이 있는 한 오토 플레이 무한 반복
			
				// 우선순위 큐 초기화
				q.clear();
				
				// 가장 크기가 큰 그룹 찾기
				visited = new boolean[N][N];
				for(boolean[] v: visited) {
					Arrays.fill(v, false);
				}
				for(int i=0; i<N; ++i) {
					for(int j=0; j<N; ++j) {
						int block = map[i][j];
						if(!visited[i][j] && block!=-1 && block!=0 && block!=INF) {
							findGroups(i, j, block);
						}
						
					}
				}
				
				// base-case 더 이상 블록 그룹이 없다면 탈출
				if(q.size()==0) break;
				
				// 가장 큰 그룹 비우기
				Group g = q.poll();
				point += Math.pow(g.size, 2);
				System.out.println(g.x + " " + g.y + " " + g.color + " " + g.size );
				removeGroup(g.x, g.y, g.color);
				
				// 중력 작용
				gravity();
				
				// 배열 돌리기
				rotate();
				
				// 중력 작용
				gravity();
				
			}
			
			bw.write(String.valueOf(point));
			// bw.write('\n');
		// }

		
		br.close();
		bw.flush();
		bw.close();
		
	}
	
}
