// PriorityQueue

import java.io.*;
import java.util.*;

public class Main{
	
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// init T
		int T = Integer.parseInt(br.readLine());
		PriorityQueue pq = new PriorityQueue<Integer>();
		
		for(int testcase=0; testcase<T; testcase++){
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		for(int testcase=0; testcase<T; testcase++){
			bw.write(String.valueOf(pq.poll())+'\n');
		}		

		br.close();
		bw.flush();
		bw.close();
		
	}

}

// array sort

import java.io.*;
import java.util.*;

public class BOJ_2751{
	
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// init T
		int T = Integer.parseInt(br.readLine());
		int arr[] = new int[T];
		
		for(int testcase=0; testcase<T; testcase++){
			arr[testcase] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
		
		for(int testcase=0; testcase<T; testcase++){
			bw.write(String.valueOf(arr[testcase])+'\n');
		}		

		br.close();
		bw.flush();
		bw.close();
		
	}

}
