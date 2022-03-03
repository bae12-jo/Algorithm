// 최적화 필요


import java.util.*;
import java.io.*;

public class BOJ_1388{

	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	public static int n, m;
	public static int[][] map;
	public static int count = 0;
	public static boolean visited[][];
	public static boolean r;
	public static boolean c;
	
	// public static Stack<Integer> stack = new Stack<>();
	public static void getNum(){
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(map[i][j] == 0){
					r = true;
				}
				if(map[i][j]==1){
					if(r){
						count+=1;
						r = false;
					}
					if(!(visited[i][j])){
						getCol(i, j);
					}
				}
			}
			if(r) {
				count+=1;
				r = false;
				//System.out.println(count);
			}
		}
	}
	
	public static void getCol(int row, int col){
			if(map[row][col]==1){
				visited[row][col]=true;
				c = true;			
				if(row+1<n){
					getCol(row+1, col);
				}
				if(row+1==n && c){
					count+=1;
					c = false;
					return ;
				}
			}
			else{
				if(c){
					count+=1;
					c = false;
				}
				return ;
			}
	}

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine(), "");
			String line = st.nextToken();
			for(int j=0; j<m; j++){
				char c = line.charAt(j);
				map[i][j] = (c == '|') ? 1 : 0;
			}
		}
		
		getNum();
		System.out.println(count);
	
	}
}

<- 최적화 코드 ->

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


	static int N, M;
	static char[][] room;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(getWoodsCnt());
	}

	private static int getWoodsCnt() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (room[i][j] == '.')
					continue;

				cnt++;
				visitWood(i, j, room[i][j]);
			}
		}

		return cnt;

	}

	private static void visitWood(int r, int c, char shape) {
		if (!isIn(r, c) || room[r][c] != shape) {
			return;
		}

		room[r][c] = '.';
		if (shape == '-') {
			// 모양이 -면 오른쪽으로만 확인하며 같은 방문 체크
			visitWood(r, c + 1, shape);
		} else {
			// 모양이 ㅣ면 아래로맨 확인하며 같은 모양 방문 체크
			visitWood(r + 1, c, shape);
		}
	}

	private static boolean isIn(int r, int c) {
		return r < N && c < M;
	}

	private static void input() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		room = new char[N][];
		for (int i = 0; i < N; i++) {
			room[i] = in.readLine().toCharArray();
		}
	}

}
