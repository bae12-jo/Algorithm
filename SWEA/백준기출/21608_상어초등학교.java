// https://www.acmicpc.net/problem/21608

package bojForm;

import java.io.*;
import java.util.*;

public class 상어초등학교 {

	static StringTokenizer st;
	
	static int T; // 테스트 케이스 개수
	static int n; // 교실의 행 혹은 열 크기
	static int s; // 학생 고유 번호
	static int[][] D = {{-1, 0}, {0, 1}, {1,0}, {0, -1}}; // 상, 우, 하, 좌
	
	// 학생 위치와 선호하는 친구 목록 담을 클래스
	static class Buddy { // 인스턴스를 매번 새로 만들어서 값을 넣어줘야함
		// 자신의 위치
		int x;
		int y;
		// 친구들 목록
		int[] buddies;
		
		public Buddy(int x, int y, int[] buddies) {
			this.x=x;
			this.y=y;
			this.buddies=buddies;
		}
	}
	
	// 비어 있는 주변 좌석의 개수 구하기
	private static void getNearEmptySeat(int[][] nearEmptySeat){
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				int empty = 4; // 기본값: 4 (상하좌우)
				if(i==0 || i==n-1) empty--; // 맨 앞자리 혹은 맨 뒤자리
				if(j==0 || j==n-1) empty--; // 가장 왼쪽 자리 혹은 가장 오른쪽 자리
				nearEmptySeat[i][j] = empty;
			}
		}
	}
	
	// 앉을 자리 찾기
	private static void findSeat(int s, int[] buddies, Map<Integer, Buddy> bList, int[][] classroom, int[][] nearEmptySeat) {
		
		// 좋아하는 친구가 인접하다면 점수 추가하기
		int[][] score = new int[n][n];
		for(int b: buddies) { // 친구 4명의 위치를 파악
			if(bList.containsKey(b)) { // 좋아하는 친구가 이미 자리에 앉아있다면
				Buddy student = bList.get(b);
				int x = student.x;
				int y = student.y;

				for(int i=0; i<4; ++i) {
					int nx = x+D[i][0];
					int ny = y+D[i][1];
					if(nx>=0 && nx<n && ny>=0 && ny<n && classroom[nx][ny]==0) { // 친구 근처 자리가 비었다면
						score[nx][ny]+=10; // 친구 점수 10점 추가
					}					
				}
			}
		}		
		
		// 빈자리 점수 추가하기
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				score[i][j] += nearEmptySeat[i][j];
			}
		}
		
		// 위치, 자리 점수 MAX 초기화 (-1로)
		int sx = -1;
		int sy = -1;
		int maxScore = -1;
		
		// 1. left top -> right bottom 클래스 배열을 탐색하면서 이미 누군가 앉은 자리는 건너뜀
		// 2. 빈자리라면 좋아하는 친구 4명과 최대한 많이 인접한 곳이 갱신될때마다 위치와 인접 친구 수, 인접 빈자리 수 업데이트
		// 3. 인접 친구수가 동일한데 인접 빈자리수가 다르다면 업데이트 
		// 2, 3번을 묶어서 하나의 점수 배열로 처리
		for(int i=0; i<n; ++i) {
			for(int j=0; j<n; ++j) {
				if(classroom[i][j]==0 && maxScore<score[i][j]) {
					sx = i;
					sy = j;
					maxScore = score[i][j];
				}
			}
		}
				
		// 학생 자리를 교실 배열에 업데이트
		classroom[sx][sy] = s;
		// bList에 학생 업데이트 해주기 - 주소는 다른데 같은 값이 들어가는 현상이 발생.. why...? 즉 새로 만든 인스턴스의 값이 이전 인스턴스의 값에도 영향을 미침..
		//Buddy bd = new Buddy(sx, sy, buddies);
		bList.put(s, new Buddy(sx, sy, buddies));
		
		// 빈자리 배열 업데이트
		for(int i=0; i<4; ++i) {
			int nx = sx+D[i][0];
			int ny = sy+D[i][1];
			if(nx>=0 && nx<n && ny>=0 && ny<n && classroom[nx][ny]==0) { // 그 자리가 누군가 앉을 후보라면
				nearEmptySeat[nx][ny]--; // 인접 빈 자리 수 하나 감소
			}					
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\21608_상어초등학교_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; ++testcase) {
			bw.write("#" + String.valueOf(testcase) + " ");
			n = Integer.parseInt(br.readLine()); // 교실의 행 혹은 열 크기 받기
			
			int[][] classroom, nearEmptySeat; // 학생이 앉은 자리를 표시할 배열, 비어 있는 주변 좌석 개수를 담을 배열
			Map<Integer, Buddy> bList = new HashMap<>(); // 학생의 위치와 친구목록을 빠르게 가져오기 위함
			classroom = new int[n][n]; // 교실 배열 크기 초기화
			nearEmptySeat = new int[n][n];
			getNearEmptySeat(nearEmptySeat); // 주변의 빈자리 개수 초기화
			
			for(int i=1; i<=n*n; ++i) { // 학생 수만큼 입력 받기
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken()); // 학생 고유번호
				int idx = 0;
				int[] buddies = new int[4]; // 친한 친구 배열 초기화; // 선호하는 4명 목록
				while(idx<4) { // 좋아하는 친구 목록
					buddies[idx++] = Integer.parseInt(st.nextToken());
				}
				findSeat(s, buddies, bList, classroom, nearEmptySeat); // 자리 찾기
			}
			
			int ans = 0; // 총 만족도			
			
			// 만족도 조사
			for(int i=1; i<=n*n; ++i) { // 모든 학생에 대해
				Buddy student = bList.get(i);
				int cnt = 0;
				for(int buddy: student.buddies) {
					if((Math.abs(student.x-bList.get(buddy).x)+Math.abs(student.y-bList.get(buddy).y))==1) {
						cnt++;
					}
				}
				if(cnt==1) ans+=1;
				else if(cnt==2) ans+=10;
				else if(cnt==3) ans+=100;
				else if(cnt==4) ans+=1000;
			}
			
			bw.write(String.valueOf(ans));
			bw.write('\n');			
		}
		
		br.close();
		bw.flush();
		bw.close();	
		
	}

}
