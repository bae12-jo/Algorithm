import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class BOJ_2605{
	public static void main(String[] args) throws NumberFormatException, IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// define integer array size of n
		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		
		// push each input into integer array
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++){
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer> list = new LinkedList<>();
		
		// make head
		list.add(1);
		
		// insert nodes into list 
		for (int i=2; i<=n; i++){
			list.add(list.size()-num[i-1], i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++){
			sb.append(list.get(i)+" ");
		}
		
		System.out.print(sb.toString());
		
	}
}
