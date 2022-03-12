import java.util.*;

// 3번 문제 - 대각선을 한 번 지나는 모든 경로의 수 구하기
// 대각선으로 연결된 두 정점 A, B가 있을 때 시작점으로부터 A로 가는 경로의 수와 시작점으로부터 B로 가는 경로의 수는 동일하다
// 대각선의 두 정점을 인덱스로 나타내는 것이 관건 + w, h가 거꾸로 나오고 어떤 점이 주어질 때 (x, y)가 각각 w와 h 중 어디인지 확실히 체크하기
// 대각선의 두 정점과 시작점, 도착점 간 경로 경우의 수는 DP로 구할 수 있음

class sk3 {
    public static long solution(int width, int height, int[][] diagonals) {

        long answer = 0;
		int MOD = 10000019;
		
		// dp 배열 만들기
        long[][] dp = new long[width+1][height+1];
		dp[0][0] = 1;
		
		for(int i=0; i<=width; ++i){
			for(int j=0; j<=height; ++j){
				if(i==0 && j==0) continue;
				if(i>0) dp[i][j] += dp[i-1][j];
				if(j>0) dp[i][j] += dp[i][j-1];
				dp[i][j] %= MOD;
			}
		}
        
		// 대각선으로 연결된 두점 A,B까지의/부터의 경우의 수 구하기
		for(int i=0; i<diagonals.length; ++i){
			
			int x = diagonals[i][0];
			int y = diagonals[i][1];
			
			// System.out.println(x + " " +y);
			
			long cntAB = ( dp[x][y-1] * dp[width-x+1][height-y] ) % MOD;			
			long cntBA = ( dp[x-1][y] * dp[width-x][height-y+1] ) % MOD;
			
			answer += ( cntAB + cntBA ) % MOD;
			answer %= MOD;
			
		}
        
        return answer;
    }
	
	public static void main(String[] main){
		
		int width = 51;
		int height = 37;
		int[][] diagonals = {{17, 19}};
		
		/*
		int width = 2;
		int height = 2;
		int[][] diagonals = {{1, 1}, {2, 2}};
		*/
		
		System.out.println(solution(width, height, diagonals));
	}
}
