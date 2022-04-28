// https://www.acmicpc.net/problem/20057

package bojForm;

import java.io.*;
import java.util.*;

public class 마법사상어와토네이도 {
	
	static int T, N, outSand;
	static int[][] map;
	static int[][] TD = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // 0 왼, 1 아, 2 오, 3위 - 토네이도 이동 방향
	static int[][] SDX = {{-1,1,-2,-1,1,2,-1,1,0,0}, {-1,-1,0,0,0,0,1,1,2,1}, {1,-1,2,1,-1,-2,1,-1,0,0}, {1,1,0,0,0,0,-1,-1,-2,-1}}; // 모래가 흩날리는 X 방향
	static int[][] SDY = {{1,1,0,0,0,0,-1,-1,-2,-1}, {-1,1,-2,-1,1,2,-1,1,0,0}, {-1,-1,0,0,0,0,1,1,2,1}, {1,-1,2,1,-1,-2,1,-1,0,0}}; // 모래가 흩날리는 Y 방향
	static int[] ratio = {1,1,2,7,7,2,10,10,5}; // 모래가 흩날리는 비율
	static int[] K = {1, 1, 2, 2}; // 토네이도 이동 방향 별 움직이는 거리
	static StringTokenizer st;
	
	private static void blowSand(int row, int col, int dir) {
		
		int sand = map[row][col];
		
		int totalBlow = 0;
		
		for(int i=0; i<9; ++i) {
			// 이동 방향 찾기
			int nx = row + SDX[dir][i];
			int ny = col + SDY[dir][i];
			// 흩날릴 모래 양 구하기
			int blowedSand = (sand*ratio[i])/100;
			// 원 위치에서 빠져나온 모래 양 구하기
			totalBlow += blowedSand;
			// 맵 밖으로 나간 경우
			if(nx<0 || nx>=N || ny<0 || ny>=N) outSand += blowedSand;
			else map[nx][ny] += blowedSand;
		}
		
		// a 위치 구하기
		int nx = row+SDX[dir][9];
		int ny = col+SDY[dir][9];
		
		// System.out.println(sand + " " + totalBlow);
		
		// 맵 밖으로 나간 경우
		if(nx<0 || nx>=N || ny<0 || ny>=N) outSand += sand-totalBlow;
		// 아니라면 a로 모래 이동
		else map[nx][ny] += sand-totalBlow;
		
		// 원 위치 비우기
		map[row][col]=0;		
		
	}
	
	
	private static void tornado(int row, int col, int dir) {
		
		int layer = 0;
		
		while(true) {			
			for(int i=0; i<4; ++i) {
				for(int j=1; j<=layer+K[i]; j++) {
					row += TD[i][0];
					col += TD[i][1];
					if(col<0) return; // 맵 밖으로 나가면 종료
					blowSand(row, col, i);
				}
			}
			layer+=2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\20057_마법사상어와토네이도_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			bw.write("#" + testcase + " ");
			
			N = Integer.parseInt(br.readLine());
			
			// 맵 초기화
			map = new int[N][N];
			
			for(int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 모래양 초기화
			outSand = 0;
			
			// 토네이도
			tornado(N/2, N/2, 0);
			
			// 맵 밖으로 빠져나간 모래의 양 출력
			bw.write(String.valueOf(outSand));
			bw.write('\n');
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

}
