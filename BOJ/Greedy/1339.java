import java.util.*;
import java.io.*;

public class BOJ_1339{
	
	public static int N, sum, index = 9;
	public static int alphabet[] = new int[26];
	public static String[] list;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new String[N];
		
		for(int i=0; i<N; i++){
			list[i] = br.readLine();
		}
		
		for(int i=0; i<N; i++){
		
			String word = list[i];
			int len = word.length();
			
			int pow = 1;
			for(int j=len-1; j>=0; j--){
				alphabet[(int)word.charAt(j)-'A']+=pow;
				pow *= 10;
			}
		}
		
		Arrays.sort(alphabet);
		
		for(int i=25; i>=0; i--){
			if(alphabet[i]==0) break;
			
			sum+=alphabet[i]*index;
			index--;
			
		}
		
		System.out.println(sum);
		
		
	
	}
}
