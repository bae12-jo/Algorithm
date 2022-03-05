import java.util.*;
import java.io.*;

public class BOJ_1912{

	public static BufferedReader br;
	public static StringTokenizer st;

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int arr[] = new int[n];
		int max = Integer.MIN_VALUE;
		int sum = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		// 최초 합 및 최대값 세팅
		int initNum = Integer.parseInt(st.nextToken());
		sum = max = initNum;
		
		// 연속 수열의 최대합 갱신
		for(int i=1; i<n; i++){
			int a = Integer.parseInt(st.nextToken());
			sum = sum+a > a ? sum+a : a;
			if(sum>max) max = sum;
			
		}
		 System.out.println(max);
	}
}
