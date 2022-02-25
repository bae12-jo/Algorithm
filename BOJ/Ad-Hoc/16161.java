import java.util.*;
import java.io.*;

public class Main {
    
	private static int N = 0;
	private static int maxLen = 1;
	private static int localLen = 0;
	private static int start = 0;
	private static int end = 0;
	private static long[] arr;

	public static void Main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
        
		arr = new long[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++){
			arr[i] =Long.parseLong(st.nextToken());			
		}

		while (start <= end && end < N - 1) {
			if (arr[end] < arr[end + 1]) { 
				end++;
			} else if (arr[end] == arr[end + 1]) {
				isEvenPalindrome();
				maxLen = Math.max(maxLen, 2 * localLen);
				start = end + 1;
				end++;
			} else {
				isOddPalindrome();
				maxLen = Math.max(maxLen, 2 * localLen + 1);
				start = end + 1;
				end++;

			}

		}

		System.out.println(maxLen);
	}
	
	public static void isEvenPalindrome(){
		for (localLen = 0; localLen <= end - start; localLen++) {
			if (end + 1 + localLen >= N || arr[end - localLen] != arr[end + 1 + localLen]) {
				break;
			}
		}
	}
	
	public static void isOddPalindrome(){
		for (localLen = 0; localLen <end - start; localLen++) {
			if (end + 1 + localLen >= N || arr[end - 1 - localLen] != arr[end + 1 + localLen]) {
				break;
			}
		}
	}

}
