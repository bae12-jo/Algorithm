import java.util.*;
import java.io.*;

class BOJ_2003{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N;
	static long M;
	static int[] arr;

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// System.out.println("input done");
		
		int start = 0;
		int count = 0;
		while(start<N){
			if(arr[start]==M){
				count++;
				start++;
				continue;
			}
			int sum = arr[start];
			int end = start+1;
			while(end<N){
				sum+=arr[end];
				end++;
				if(sum==M){
					count++;
					break;
				}
			}
			start++;
		}
		
		//System.out.println(count);
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		
	
	}
}
