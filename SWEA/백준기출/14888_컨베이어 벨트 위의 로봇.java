// https://www.acmicpc.net/problem/20055

package bojForm;

import java.io.*;
import java.util.*;

public class 컨베이어벨트위의로봇 {
	
	static int T, N, K;
	static int[] belt;
	static boolean[] robot;
	static StringTokenizer st;
	
	private static boolean basecase() {
		int count = 0;
		for(int i=0; i<2*N; ++i) {
			if(belt[i]==0) count++;
		}
		return count>=K ? false:true;
	}
	
	private static int loop(int turn) {
		
		while(basecase()) {
			
			// 벨트 회전
			int tmp = belt[belt.length-1];
			for(int i=belt.length-1; i>0; --i) belt[i] = belt[i-1];
			belt[0] = tmp;
			
			// 벨트에 맞춰서 로봇 회전
			for(int i=robot.length-1; i>0; --i) robot[i] = robot[i-1];
			
			// 로봇 내리기
			robot[robot.length-1]=false;
			
			// 로봇 올리는 위치 초기화
			robot[0]=false;
			
			// 로봇 이동 (가장 오래된 로봇부터)
			for(int i=robot.length-1; i>0; --i) {
				// i-1: 이동하려는 로봇, i: 목표 위치
				if(robot[i-1] && !robot[i] && belt[i]>0) {
					robot[i-1]=false; // 로봇 떠남
					robot[i]=true; // 로봇 도착
					belt[i]--; // 내구성 감소
				}
			}
			
			// 로봇 올리기
			if(belt[0]>0) {
				robot[0]=true; // 로봇 도착
				belt[0]--; // 내구성 감소
			}
						
			turn++;
		}
		
		return turn;
	}
	
	
	public static void main(String[] args) throws IOException{
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\20055_컨베이어벨트_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			
			bw.write("#" + testcase + " ");
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 배열 초기화
			belt = new int[2*N];
			robot = new boolean[N];
			Arrays.fill(robot, false);
			
			st = new StringTokenizer(br.readLine());
			
			// 내구도 초기화
			for(int i=0; i<2*N; ++i) belt[i] = Integer.parseInt(st.nextToken());
			
			bw.write(String.valueOf(loop(0)));
			bw.write('\n');
			
		}
		
		br.close();
		bw.flush();
		bw.close();
		
	}

}
