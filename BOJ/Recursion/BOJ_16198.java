package SILVER;

import java.util.*;
import java.io.*;

public class BOJ_16198 {
	
	static int N;
	static int MAX = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// init N, list
		N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) list.add(Integer.parseInt(st.nextToken()));
		
		getEnergy(list, 0); // backtracking
		
		bw.write(String.valueOf(MAX));
		br.close();
		bw.flush();
		bw.close();
		
	}
	
	private static void getEnergy(List<Integer> list, int sum) {
		
		// end case
		if(list.size()<=2) {
			if(MAX<sum) MAX=sum;
			return;
		}
		
		for(int i=1; i<list.size()-1; i++) {
			int selected = list.get(i);
			int tmp = list.get(i-1)*list.get(i+1);
			list.remove(i); // make a branch
			getEnergy(list, sum+tmp); // deep dive into the branch
			list.add(i, selected); // undo
		}
	}	
	

}
