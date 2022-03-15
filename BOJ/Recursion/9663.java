import java.util.*;
import java.io.*;

class BOJ_9663{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[] arr;
	static int N;
	static int count = 0;
	
	public static boolean Possibility(int col){
		for(int i=0; i<col; ++i){
			//System.out.println("?? : " +col+" "+i);
			if(arr[col]==arr[i]){ // 같은 행에 존재하는 경우
				//System.out.println("같은 행 : " +arr[col]+" "+arr[i]);
				return false;
			}
			else if(Math.abs(col - i) == Math.abs(arr[col]-arr[i])){ // 대각선에 놓이는 경우 (열의 차와 행의 차가 같은 경우)
				//System.out.println("대각선 : " +arr[col]+" "+arr[i]);
				return false;
			}
		}
		return true;
	}
	
	public static void nQueen(int depth){
		if(depth==N){ // 행을 다 채우면 카운트 증가 시키고 종료
			count++;
			return;
		}
		
		for(int i=0; i<N; ++i){
			arr[depth] = i;
			if(Possibility(depth)){ //해당 열의 i번째 행에 놓을 수 있는지 검사하는 함수
				//System.out.println("possible!");
				nQueen(depth+1);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		nQueen(0);
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
	
	}
}
