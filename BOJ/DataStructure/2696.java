/* 정렬을 이용한 방식 (통과는 되지만 효율이 나쁨) */

import java.util.*;
import java.io.*;

class BOJ_2696{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	static int t, n, cnt = 0;
	static List<Integer> arr;
	
	// 우선순위 큐를 사용하는 방법도 있음

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		while(t-- > 0){
			
			n = Integer.parseInt(br.readLine());
			
			// 중앙값 개수
			sb.append((n+1)/2+"\n");
			
			// 배열 초기화
			arr = new ArrayList<>();
			
			for(int i=0; i<n; i++){
				if(i%10==0) st = new StringTokenizer(br.readLine());
				
				int num = Integer.parseInt(st.nextToken());
				arr.add(num);
				Collections.sort(arr);
				
				// 중앙값 저장
				if(i%2==0) {
					if(cnt==10 || i==n-1) { // 중앙값이 10개를 넘거나, 수열의 마지막인 경우
						sb.append(arr.get(i/2)+"\n");
						cnt = 0;
					}
					else {
						sb.append(arr.get(i/2)+" ");
					}
					cnt++;
					
				}
			}
		
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}


/* 우선순위 큐를 사용한 방식 (peek과 poll을 최소화 할수록 시간 빨라지는 듯?) */


import java.util.*;
import java.io.*;

class BOJ_2696{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	static int t, n, cnt = 0;
	static List<Integer> arr;
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		
		loop: while(t-- > 0){
			// 중앙값 이하를 저장하기 위한 최대힙(내림차순)
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
			// 중앙값 초과를 저장하기 위한 최소힙(오름차순)
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			n = Integer.parseInt(br.readLine());
			
			// 중앙값 개수
			sb.append((n+1)/2+"\n");
			
			
			for(int i=0; i<n; i++){
				if(i%10==0) st = new StringTokenizer(br.readLine());
				
				int num = Integer.parseInt(st.nextToken());
				
				if(n==1) {
					sb.append(num);
					break loop;
				}
				
				// 최대힙에 먼저 넣고, 최소힙에 넣기
				if(maxHeap.size()==minHeap.size()){
					maxHeap.offer(num);
				}else{
					minHeap.offer(num);
				}
				
				if(minHeap.size()!=0 && minHeap.peek()<maxHeap.peek()){
					minHeap.offer(maxHeap.poll());
					maxHeap.offer(minHeap.poll());
				}
				
				// 중앙값 저장
				if(i%2==0) {
					if(cnt==9 || i==n-1) { // 중앙값이 10개를 넘거나, 수열의 마지막인 경우
						sb.append(maxHeap.peek()+"\n");
						cnt = 0;
					}
					else {
						sb.append(maxHeap.peek()+" ");
					}
					cnt++;
					
				}
			}
		
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
