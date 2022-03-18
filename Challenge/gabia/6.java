// 유사한 문제 http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1685

// 2차원 누적합 채워두고 (x, y) 경계값을 기준으로 a, b, c, d 영역의 k 사이즈 정사각형의 최대값 2개를 고르고 전체의 최대값을 반환

import java.util.*;
import java.io.*;

class Gabia6{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N = 5;
	static int[][] S = new int[N+2][N+2];
	static int[][] dyA = new int[N+2][N+2];
	static int[][] dyB = new int[N+2][N+2];
	static int[][] dyC = new int[N+2][N+2];
	static int[][] dyD = new int[N+2][N+2];
	
	static int get_sum(int x1, int y1, int x2, int y2){
		return S[x2][y2] - S[x1-1][y2] - S[x2][y1-1] + S[x1-1][y1-1];
	}
	
	static int solution(int[][] grid, int K){
		int answer = -1;
		
		// 누적합 배열 S 채우기
		for(int i=1; i<N+1; ++i){
			for(int j=1; j<N+1; ++j){
			S[i][j] = grid[i-1][j-1] + S[i-1][j] + S[i][j-1] - S[i-1][j-1];
			}
		}
		
		// dyA
		for(int i=K; i<N+1; ++i){
			for(int j=K; j<N+1; ++j){
				dyA[i][j] = Math.max(Math.max(dyA[i-1][j], dyA[i][j-1]), get_sum(i-K+1, j-K+1, i, j));
			}
		}
		
		// dyB
		for(int i=K; i<N+1; ++i){
			for(int j=N-K+1; j>0; --j){
				dyB[i][j] = Math.max(Math.max(dyB[i-1][j], dyB[i][j+1]), get_sum(i-K+1, j, i, j+K-1));
			}
		}
		
		// dyC
		for(int i=N-K+1; i>0; --i){
			for(int j=K; j<N+1; ++j){
				dyC[i][j] = Math.max(Math.max(dyC[i+1][j], dyC[i][j-1]), get_sum(i, j-K+1, i+K-1, j));
			}
		}
		
		// dyD
		for(int i=N-K+1; i>0; --i){
			for(int j=N-K+1; j>0; --j){
				dyD[i][j] = Math.max(Math.max(dyD[i+1][j], dyD[i][j+1]), get_sum(i, j, i+K-1, j+K-1));
			}
		}
		
		// 경계선마다 최대값 구하기
		for(int i=1; i<=N; ++i){
			for(int j=1; j<=N; ++j){
				Integer[] arr = new Integer[4];
				arr[0] = dyA[i][j];
				arr[1] = dyB[i][j+1];
				arr[2] = dyC[i+1][j];
				arr[3] = dyD[i+1][j+1];
				Arrays.sort(arr, Collections.reverseOrder());
				answer = Math.max(answer, arr[0]+arr[1]);
			}
		}
		
		return answer;
	}

	public static void main(String[] args) throws IOException{
		
		/* int[][] grid = {{3, 4, 5}, {2, 3, 4}, {1, 2, 3}};
		int K = 1; */
		
		int[][] grid = {{2, 1, 1, 0, 1}, {1, 2, 0, 3, 0}, {0, 1, 5, 1, 2}, {0, 0, 1, 3, 1}, {1, 2, 0, 1, 1}};
		int K = 2;
		
		System.out.println(solution(grid, K));
		
	}
}
