package bojForm;

import java.util.*;
import java.io.*;

public class 온풍기안녕 {
	
	static int T, R, C, K, W;
	static Room[][] board; // 각 방의 온도와 4방향의 장애물 여부를 가진 2차원 Room 배열
	static StringTokenizer st;
	static List<Warmer> wList; // 온풍기 목록
	static List<Room> targetRoom; // 조사해야 하는 방 목록
	static int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 0상 1우 2하 3좌
	static final int up = 0, right = 1, down = 2, left = 3; // 온풍기 입력을 바꾸기 위함
	
	public static void main(String[] args) throws IOException{
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\23289_온풍기안녕_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			
			bw.write("#" + String.valueOf(testcase) + " ");
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 맵 초기화
			board = new Room[R][C];
			wList = new ArrayList<>();
			targetRoom = new ArrayList<>();
			
			// 초콜릿 초기화
			int chocolate = 0;
			
			// 조사해야 할 방, 온풍기 초기화
			for(int i=0; i<R; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<C; ++j) {
					board[i][j] = new Room();
					int num = Integer.parseInt(st.nextToken());
					if(num!=0) {
						if(num==5) {
							targetRoom.add(board[i][j]); // 방의 주소값 복사, 즉 값 공유됨
						}else {
							int dir = getDir(num);
							wList.add(new Warmer(i, j, dir));
						}
					}
				}
			}
			
			// 장애물 초기화
			W = Integer.parseInt(br.readLine());
			for(int i=0; i<3; ++i) {
				st = new StringTokenizer(br.readLine());
				int x =Integer.parseInt(st.nextToken());
				int y =Integer.parseInt(st.nextToken());
				int dir =Integer.parseInt(st.nextToken());
				setWall(x, y, dir);
			}
			
			while(chocolate<=100) {
				
				// 온풍기 바람 발사
				runWarmer();
				
				// 온도 조절
				moderateHeat();
				
				// 외곽 모서리 감소
				getLower();
				
				chocolate++;
				if(pass()) { // 프로그램 종료
					bw.write(String.valueOf(chocolate));
					return;
				}
			}
			
			bw.write(String.valueOf(101));		
			
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}



	private static void moderateHeat() {
	
		int[][] heat = new int[R][C];
		for(int i=0; i<R; ++i) {
			for(int j=0; j<C; ++j) {
				Room room = board[i][j];
				for(int d=0; d<4; ++d) {
					int nx = i+D[d][0];
					int ny = j+D[d][1];
					if(inMap(nx, ny) && !room.isWalled[d]) {
						Room nextRoom = board[nx][ny];
						int moderated = (room.temp - nextRoom.temp)/4;
						if(moderated>0) {
							heat[i][j] -= moderated;
							heat[nx][ny] += moderated; 
						}else {
							heat[i][j] += Math.abs(moderated);
							heat[nx][ny] -= Math.abs(moderated); 
						}
					}
				}
			}
		}
		
	}	

	
	private static int getDir(int dir) {
		// 설정 0상 1우 2하 3좌
		// 입력 1우 2좌 3상 4하
		if(dir==1) return 1;
		if(dir==2) return 3;
		if(dir==3) return 0;
		else return 2;
	}
	
	private static void runWarmer() {
		
		for(Warmer w: wList) {
			w.wind();
		}
	}
	
	private static boolean pass() { // 조사해야 할 방의 온도가 모두 K 이상인지 검사
		for(Room r: targetRoom) {
			if(r.temp>K) continue;
			else return false;
		}
		return true;
	}
	
	private static void setWall(int x, int y, int t) { // 장애 정보를 각 방에 업데이트
		if(t==0) {
			board[x-1][y].isWalled[down] = true;
			board[x][y].isWalled[up] = true;
		}else {
			board[x][y].isWalled[right] = true;
			board[x][y+1].isWalled[left] = true;
		}
	}
	
	private static void getLower() { // 외곽 방 온도 낮추기
		for(int i=0; i<R; ++i) {
			if(board[i][0].temp<0) board[i][0].temp--; // 왼
			if(board[i][C-1].temp>0) board[i][C-1].temp--; // 오
		}
		for(int i=1; i<C-1; ++i) {
			if(board[0][i].temp>0) board[0][i].temp--; // 위
			if(board[0][i].temp>0) board[R-1][i].temp--; // 아래
		}
	}
	
	private static boolean inMap(int x, int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
	
	static class Warmer{
		int x, y;
		int dir; // 온풍기 발사 방향
		
		public Warmer(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
		public void wind() { // bfs로 온풍기 바람 발사
			int front = dir; // 정면
			int leftside = (dir+3)%4; // 좌측
			int rightside = (dir+1)%4; // 우측
			
			boolean[][] visited = new boolean[R][C];
			visited[x+D[dir][0]][y+D[dir][1]] = true; // 온풍기 바로 앞칸 부터 시작
			Queue<Hot> queue = new LinkedList<>();
			queue.offer(new Hot(x+D[dir][0], y+D[dir][1], 5));
			
			while(!queue.isEmpty()) { // 온풍기가 보낸 바람이 각 방에 도달하는 구조
				Hot hot = queue.poll();
				board[hot.x][hot.y].temp += hot.layer; // 열기를 방에 전달
				
				// 정면 바람
				int nx = hot.x + D[front][0];
				int ny = hot.y + D[front][1];
				// 맵을 벗어나지 않고, 방문학 적이 없으며, 바람의 진행 방향에 장애물이 없는지 검사
				if(inMap(nx, ny) && !visited[nx][ny] && board[hot.x][hot.y].isWalled[front]) {
					visited[nx][ny]=true; // 방문 표시
					queue.offer(new Hot(nx, ny, hot.layer-1)); // 발사된 열기 저장 (온도 감소)
				}
				
				// 측면 바람 (오른쪽: 오른쪽->정면, 왼쪽: 왼쪽->정면) -- 각 방향 정보를 상수로 저장해둔 이유
				// nx, ny는 재활용 측면으로 재활용하고 nx2, ny2를 다시 정의할 것
				// 두 방향 모두 장애물 검사를 해야하고, 도착지점을 이미 방문한 적 있는지 체크해야함
				int nx2, ny2;
				
				// 좌측
				nx = hot.x + D[leftside][0]; nx2 = nx + D[front][0];
				ny = hot.y + D[leftside][1]; ny2 = ny + D[front][1];
				// 두 방향 모두 유효하고 장애물이 없는지 확인하기 + 최종 도착점이 방문한 적 없는 곳인지 확인
				if(inMap(nx, ny) && inMap(nx2, ny2) && !visited[nx2][ny2]) {
					if(board[hot.x][hot.y].isWalled[leftside] && board[nx][ny].isWalled[front] && !visited[nx2][ny2]) {
						visited[nx2][ny2] = true;
						queue.offer(new Hot(nx2, ny2, hot.layer-1));
					}
				}
				
				// 우측
				nx = hot.x + D[rightside][0]; nx2 = nx + D[front][0];
				ny = hot.y + D[rightside][1]; ny2 = ny + D[front][1];
				// 두 방향 모두 유효하고 장애물이 없는지 확인하기 + 최종 도착점이 방문한 적 없는 곳인지 확인
				if(inMap(nx, ny) && inMap(nx2, ny2) && !visited[nx2][ny2]) {
					if(board[hot.x][hot.y].isWalled[rightside] && board[nx][ny].isWalled[front] && !visited[nx2][ny2]) {
					visited[nx2][ny2] = true;
					queue.offer(new Hot(nx2, ny2, hot.layer-1));
					}
				}
			}
			
		}
	}
	
	static class Room{
		int temp; // 온도
		boolean[] isWalled;
		
		public Room() {
			temp = 0; // 기본 0도
			isWalled = new boolean[4]; // 상, 우, 하, 좌
		}
	}
	
	static class Hot{
		int x, y, layer;

		public Hot(int x, int y, int layer) {
			this.x = x;
			this.y = y;
			this.layer = layer;
		}
	}

}
