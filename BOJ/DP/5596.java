import java.util.*;
import java.io.*;

public class BOJ_5569{
	
	public static int map[][][][]; // x좌표, y좌표, 직전 방향, 향후 방향 변경 가능 여부
	public static int row, col;	 // 목적지 좌표
	public static int result = 0; // 모든 이동 경우의 수
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new int[row+1][col+1][2][2];
		
		/* DP 배열 초기화 */
		/* (1,1)에서 같은 방향으로만 이동하므로 모두 방향 변경 가능한 상태 */
		for(int i=1; i<=row; i++){
			map[i][1][1][1] = 1;
		}
		for(int i=1; i<=col; i++){
			map[1][i][0][1] = 1;
		}
		
		/* 경우의 수 계산 */
		/* 직전 방향: 오른쪽 0, 아래 1, 향후 방향 변경 가능 여부: 불가 0, 가능 1 */
		for(int i=2; i<=row; i++){
			for(int j=2; j<=col; j++){
				// 오른쪽에서 왔는데 변경 가능한 경우
				map[i][j][0][1] = (map[i][j-1][0][0]+map[i][j-1][0][1])%100000;
				// 오른쪽에서 왔는데 변경 불가한 경우
				map[i][j][0][0] = (map[i][j-1][1][1]);
				// 위쪽에서 왔는데 변경 가능한 경우
				map[i][j][1][1] = (map[i-1][j][1][0]+map[i-1][j][1][1])%100000;
				// 위쪽에서 왔는데 변경 불가능한 경우
				map[i][j][1][0] = (map[i-1][j][0][1]);
			}
		}
		
		result = (map[row][col][0][0]+map[row][col][0][1]+map[row][col][1][0]+map[row][col][1][1])%100000;
		System.out.println(result);
		
		// 모듈러 연산은 전체 다 합쳤을 때 1회 필수, 부분 계산 시 최소 1회 필수
	}
}
