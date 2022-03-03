import java.util.*;
import java.io.*;

public class BOJ_13458{

	public static BufferedReader br;
	public static StringTokenizer st, st2;

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		long count = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		st2 = new StringTokenizer(br.readLine(), " ");
		
		int b = Integer.parseInt(st2.nextToken());
		int c = Integer.parseInt(st2.nextToken());
		
		
		for(int i=0; i<n; i++){
			int tmp = Integer.parseInt(st.nextToken())-b;
			count++;
			if(tmp>0) count+=(tmp+c-1)/c;
		}
		
		System.out.println(count);
	
	}
}
