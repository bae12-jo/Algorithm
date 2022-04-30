// https://www.acmicpc.net/problem/23291

package bojForm;

import java.util.*;
import java.io.*;

public class 어항정리 {
	
	static int T, N, K;
	static int[][] D = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static StringTokenizer st;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\23291_어항정리_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			
			bw.write("#" + String.valueOf(testcase) + " ");
	
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 맵 초기화
			map = new int[N][25];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; ++i) map[i][0] = Integer.parseInt(st.nextToken());
			
			int turn = 0;
			
			while(maxDiff(K)) {
				turn++;
				
				addFish();
				firstFold();
				
				spread();
				unFold();
				secondFold();
				spread();
				unFold();
				
			}
	
			bw.write(String.valueOf(turn));
			bw.write('\n');	
		}
		
		br.close();
		bw.flush();
		br.close();
		
	}
	
	private static void addFish() {
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; ++i) {
			min = Math.min(min, map[i][0]);
		}
		
		for(int i=0; i<N; ++i) {
			if(map[i][0]==min) map[i][0]++;
		}
	}
	
	private static void secondFold() { // 2번 접어서 원래의 1/4 너비를 만들기
		
		// 1 -> 1/2
		for(int i=0; i<N/2; ++i) {  // 복사할 너비는 전체의 1/2만큼
			map[N-i-1][1] = map[i][0]; // 복사할 높이는 1칸
			map[i][0]=0; // 비우기
		}
		
		// 1/2 -> 1/4
		for(int i=0; i<N/4; ++i) { // 복사할 너비 전체의 1/4만큼
			for(int j=0; j<2; ++j) { // 복사할 높이는 2칸
				map[N-i-1][3-j] = map[N/2+i][j]; // 복사 시작점 선정 중요
				map[N/2+i][j]=0;
			}
		}
		
		
		
	}
	
	private static void unFold() {
		
		int start = 0; // 복사를 시작할 위치 (공중부양된 어항의 우측하단)
		while(map[start][0]==0) start++; // 값이 존재하는 곳 까지 이동
		
		int index = 0; // 새로운 공간의 위치 포인터
		int[] line= new int[N]; // 펼칠 공간 준비
		for(int i=start; i<N; ++i) { // 어항의 가장 왼쪽 행의
			for(int j=0; j<25; ++j) { // 바닥부터 아래 순서대로
				if(map[i][j]==0) break; // 다음 행으로 이동
				line[index++] = map[i][j];
				map[i][j] = 0; // 비우기
			}
		}
		
		for(int i=0; i<N; ++i) { // 맵에 정리된 어항 넣어주기
			map[i][0] = line[i];
		}
		
	}
	
	private static void spread() { // bfs로 탐색하면서 더할 값 찾아주기
		
		int[][] save = new int[N][25]; // 한번에 반영되어야 하므로 더할 값을 임시 배열에 저장
		boolean[][] visited = new boolean[N][25];
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<25; ++j) {
				visited[i][j] = true; // 비교 주체 방문처리
				if(map[i][j]==0) continue; // 빈 위치는 건너뛰기
				
				for(int d=0; d<4; ++d) {
					int nx = i+D[d][0];
					int ny = j+D[d][1];
					if(inMap(nx, ny) && !visited[nx][ny] && map[nx][ny]!=0) {
						int diff = (map[i][j]-map[nx][ny])/5; // 두 수의 차이가 5이상 난다면
						if(diff>0) { // 절대값을 취해버리면 둘 중 누가 더 큰지 어떻게 알지..?
							save[i][j]-=diff;
							save[nx][ny]+=diff;	
						}else {
							save[i][j]+=Math.abs(diff);
							save[nx][ny]-=Math.abs(diff);
						}
					}
				}
			}
		}
		
		for(int i=0; i<N; ++i) {
			for(int j=0; j<25; ++j) {
				map[i][j] += save[i][j];
			}
		}
	}
	
	private static void firstFold() {
		
		int start = 0; // 복사할 어항의 우하단 (너비의 가장 왼편)
		int width = 1; // 접힌 어항의 너비 (1칸은 너비 1) -> 행의 개수
		int height = 1; // 접힌 어항의 높이 (1칸은 높이 1) -> 열의 개수
		
		while(start+width+height<=N) {
			// 이동 대상 어항의 가장 오른쪽 행부터, 이동 목표 어항의 바닥이 되도록 
			for(int i=width-1; i>=0; i--) { // 너비의 가장 오른쪽부터
				for(int j=0; j<height; j++) { // 가장 낮은 것부터
					// 공중부양할 어항의 좌상단 위치
					int nx = start + width + j; // 옮기려는 너비만큼 떨어진 곳에서부터 순차적으로 한칸씩
					int ny = width - i; // 한단씩 쌓아올림
					// 시계 방향 90도 회전
					map[nx][ny] = map[start+i][j];
					map[start+i][j]=0; // 비워주기
				}
			}
			
			start += width; // 옮긴 어항 너비만큼 start 키워주기
			if(width==height) height++; // 접힌 어항이 정사각형이라면 다음에는 높이가 커질 것
			else width++; // 높이가 먼저 커졌다면 그 다음엔 너비가 따라커질 것
		}
		
		
	}
	
	private static boolean maxDiff(int k) { // 가장 큰 수와 가장 작은 수의 차이가 K 초과인지 검사
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<N; ++i) {
			min = Math.min(min, map[i][0]);
			max = Math.max(max, map[i][0]);
		}
		
		return max-min>k; // k 초과라면 true이므로 어항정리를 반복해야함
	}
	
	private static boolean inMap(int x, int y) {
		return x>=0 && x<N && y>=0 && y<25;
	}

}
