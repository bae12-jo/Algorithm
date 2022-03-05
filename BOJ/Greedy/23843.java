import java.util.*;
import java.io.*;

class Main{

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int n, m;
	// static PriorityQueue<Integer> input = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
	static PriorityQueue<Integer> power = new PriorityQueue<>();
	static int[] input;

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		input = new int[m];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		// 배열에 다 넣고 정렬하는 것과 처음부터 내림차순 우선순위 큐에 넣는 방법이 있음
		
		for(int i=0; i<m; i++){
			//input.offer(Integer.parseInt(st.nextToken()));
			input[i] =Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		if(m<n){
			System.out.println(input[input.length-1]);
			return;
		}
		
		for(int i=0; i<n; i++){
			power.offer(0);
		}
		
		for(int i=m-1; i>=0; i--){
			power.offer(power.poll()+input[i]);
		}
        
        while(power.size()!=1){
            power.poll();
        }
		
		System.out.println(power.poll());
		
	}
}
