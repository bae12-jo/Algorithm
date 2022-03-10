import java.util.*;
import java.io.*;

class BOJ_22857{

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] list = new int[N];
		
		// 수열 입력 받기
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++){
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = list[0]%2; // 홀수의 개수
		int ans = list[N-1]%2==0 ? 1:0; // 최대 짝수 수열 길이
		int l = 0, r = 0;
		while(l <= r && r < N){ // 전체 수열을 돌면서
			if(cnt<=K && r+1<N){ // 홀수 개수가 K 이하거나, r이 끝에 도달하지 않았다면
				r++;
				if(list[r]%2==1) cnt++; // 오른쪽 포인터 확장 (홀수를 세가면서)
			} else {
				if(list[l]%2==1) cnt--; // 왼쪽 포인터 확장 (그동안 셌던 홀수 무시)
				l++;
			}
		
			if(cnt<=K){
				ans = Math.max((r-l+1-cnt), ans);
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(ans));
		bw.flush();
		bw.close();
		
	}
}
