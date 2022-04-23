package formerProblems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 입국심사 {
	
static int T = 0;
static int n = 0, m = 0; // n: 심사대 개수, m: 입국 심사를 기다리는 사람
static int[] line;

public static long getMinTime() {
	
	Arrays.sort(line);
	
	long answer = 0; // long으로 해야 정답으로 처리됨
	long left = (long)line[0]; // 최선의 경우: 가장 빨리 끝내는 심사대의 최소 시간
	long right = (long)m*line[line.length-1]; // 최악의 경우: 모든 사람이 가장 오래 걸리는 심사대로 가는 경우
	
	while(left<=right) { // 최소, 최대를 물어보는 이분 탐색에서는 <= 으로 마지막 가능성까지 고려하는 게 좋음
		long mid = (left+right)/2; // mid는 각 심사대에 동일하게 주어진 시간 
		long canChecked = 0; // 모든 심사관이 mid분 동안 심사할 수 있는 사람의 총합
		
		for(int time: line) {
			canChecked += mid/time; // mid시간 동안 각 김사대가 처리할 수 있는 인원
			// 시간 내에 모든 입국 심사를 마칠 수 있는 경우 - 시간을 줄여야 함
			if(canChecked>=m) {
				answer = mid; // 현재 시간이 가능한 최소 시간일 수 있으므로 저장해줌
				right = mid-1;
				break;
			}
		}
		
		// 시간 내에 모든 입국 심사를 마칠 수 없는 경우 - 시간을 늘려야 함
		if(canChecked<m) {
			left = mid+1;
		}
	}
	
	return answer;
}
	
public static void main(String[] args) throws Exception {
		
		// System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\입국심사_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase<=T; ++testcase) {
			
			bw.write("#" + String.valueOf(testcase)+ " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			line = new int[n];
			
			for(int i=0; i<n; ++i) {
				line[i] = Integer.parseInt(br.readLine());
			}			
						
			bw.write(String.valueOf(getMinTime())+'\n');
		}
		
		bw.flush();
		bw.close();
	}

}
