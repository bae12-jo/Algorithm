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
