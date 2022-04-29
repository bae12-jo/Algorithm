package bojForm;

import java.io.*;
import java.util.*;

public class 마법사상어와비바라기 {
	
	static int T, N, M;
	static int[][] map; // 맵
	static boolean[][] cloud; // 구름
	static List<int[]> move; // [0] 이동 방향, [1] 이동 속도
	static int[][] D = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}}; // 대각선 4 방향
	static int[][] MD = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}}; // [i][]은 방향 8개, [i][0]은 x, [i][1]은 y
	static StringTokenizer st;
	
	private static int getWater() {
		
		int totalWater = 0;
		
		 for(int i=0; i<N; ++i) { // 기존의 구름을 탐색하면서
			 for(int j=0; j<N; ++j) {
				 if(map[i][j]>=0) { // 바구니에 물이 있다면
					 totalWater += map[i][j];
				 }
			 }
		 }
		 
		 return totalWater;
		
	}
	
	private static void bibarigi() {
		
		// M 만큼 반복
		for(int[] m : move) {
			
			// move - 구름 움직이고 물의 양 증가, 결과를 기존 구름 배열로 할당 -> 반환 뉴 구름
			cloud = moveCloud(m[0], m[1]);
			
			// 물 복사 버그 (구름이 있었던 모든 곳에 대해 진행, 이때 새 배열에 반영한 후 한번에 기존 맵으로 할당) -> 반환 뉴 맵
			map = copyWater();			
			
			// 바구니의 모든 물이 2칸 이상인 곳에 구름 생성 후 물의 양 2 줄이기 (이때 기존 구름 활용)
			// 새 구름 맵에 업데이트 후 반환하기 -> 반환 뉴 구름
			cloud = makeCloud();
			
		}
		
	}
	
	private static boolean[][] makeCloud(){
		
		boolean[][] newCloud = new boolean[N][N];
		
		for(int i=0; i<N; ++i) { // 기존의 구름을 탐색하면서
			 for(int j=0; j<N; ++j) {
				 if(map[i][j]>=2 && cloud[i][j]==false) { // 물의 양이 2 이상이면서 기존에 구름이 존재하지 않았다면
					 map[i][j]-=2; // 물의 양을 2만큼 줄이기
					 newCloud[i][j]=true; // 구름 생성
				 }
			 }
		 }		
		
		return newCloud;
	}
	
	private static int[][] copyWater(){
		
		int[][] newMap = new int[N][N];
		for(int i=0; i<N; ++i) newMap[i] = Arrays.copyOf(map[i], N);
		
		 for(int i=0; i<N; ++i) { // 기존의 구름을 탐색하면서
			 for(int j=0; j<N; ++j) {
				 if(cloud[i][j]==true) { // 구름이 존재할 때, 즉 물이 증가한 칸에 대해서
					 int count = 0; // 물이 존재하는 대각선 방향 바구니의 수
					 for(int d=0; d<4; d++) {
						 int nx = i+D[d][0];
						 int ny = j+D[d][1];
						 if(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]>0) count++; // 맵 안에 있고 물이 있다면
					 }
					 newMap[i][j] += count;
				 }
			 }
		 }
		
		return newMap;
		
	}
	
	private static boolean[][] moveCloud(int d, int s){
		
		 boolean[][] newCloud = new boolean[N][N]; // 구름 이동 결과
		 
		 for(int i=0; i<N; ++i) { // 기존의 구름을 탐색하면서
			 for(int j=0; j<N; ++j) {
				 if(cloud[i][j]==true) { // 구름이 존재할 때
					 //  ←, ↖, ↑, ↗, →, ↘, ↓, ↙
					 int nx = ( i + N + ( MD[d][0] * s%N ) ) % N;
					 int ny = ( j + N + ( MD[d][1] * s%N ) ) % N;
					 newCloud[nx][ny] = true;
					 map[nx][ny]++;
				 }
			 }
		 }
		 
		 return newCloud;
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\21610_마법사상어와비바라기_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			
			bw.write("#" + String.valueOf(testcase) + " ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 맵 초기화
			map = new int[N][N];			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 구름 초기화
			cloud = new boolean[N][N];
			cloud[N-1][0] = cloud[N-1][1] = cloud[N-2][0] = cloud[N-2][1] = true;
			
			// 이동 정보 입력
			move = new ArrayList<int[]>(); //  [0]은 방향, [1]은 속도
			for(int i=0; i<M; ++i) {
				st = new StringTokenizer(br.readLine());
				int d = Integer.parseInt(st.nextToken())-1;
				int s = Integer.parseInt(st.nextToken());
				move.add(new int[] {d, s});
			}
			
			// 비바라기
			bibarigi();
			
			// 물의 양 구하기
			bw.write(String.valueOf(getWater()));
			bw.write('\n');
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

}
