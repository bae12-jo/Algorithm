import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14925 {

	static int M, N, MAX = 0;
	static boolean[][] map;
	static int[][] result; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		
		map = new boolean[M][N];
		result = new int[M][N];
		StringTokenizer st = null;
		char tmp = ' ';
		for(int i = 0 ; i < M ; i++) { // 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				tmp = st.nextToken().charAt(0);
				if(tmp != '0')
					map[i][j] = true;
			}
		}
		
		for(int i = 0 ; i < M ; i++) { // 계산
			for(int j = 0 ; j < N ; j++) {
				
				if(!map[i][j]) {
					if(i == 0 || j == 0) {
						result[i][j] = 1;
						MAX = Math.max(1, MAX);
					}else {
						result[i][j] = Math.min(Math.min(result[i-1][j], result[i][j-1]), result[i-1][j-1]) + 1;
						MAX = Math.max(result[i][j], MAX);
					}
				}
			}
		}
		System.out.println(MAX);		
	}
}
