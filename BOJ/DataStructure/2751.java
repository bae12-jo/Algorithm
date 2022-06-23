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
