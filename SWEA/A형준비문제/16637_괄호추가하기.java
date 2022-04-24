package bojForm;

import java.io.*;

// https://www.acmicpc.net/problem/16637

public class 괄호추가하기 {
	
	static int T;
	static int N;
	static String s;
	static int MAX = Integer.MIN_VALUE;
	
	private static int calculate(int tmp, char opr, int opd) { // tmpResult, operator, operand
		if(opr=='*') return tmp*opd;
		else if(opr=='-') return tmp-opd;
		return tmp+opd;
	}
	
	private static void addParentheses(int idx, int resBuffer) {
		
		// 종료조건
		if(idx>=N) {
			MAX = Math.max(MAX, resBuffer);
			return;
		}
		
		// 괄호를 추가하지 않는 경우 - 누적 계산 결과에 다음 피연산자 연산을 수행해줌 A+B
		addParentheses(idx+2, calculate(resBuffer, s.charAt(idx-1), s.charAt(idx)-'0'));
		
		// 괄호를 추가하는 경우 - idx부터 시작하는 괄호를 추가 A+(B+C)
		// 왜 (A+B)가 아니냐면 이미 괄호 없는 경우에서 계산한 경우의 수이기 때문에 중복을 방지하기 위함임
		if(idx+2<N) { // idx 뒤로 연산자, 피연산자가 최소 한 쌍 이상 남아있는 경우
			int tmp = calculate(s.charAt(idx)-'0', s.charAt(idx+1), s.charAt(idx+2)-'0');
			resBuffer = calculate(resBuffer, s.charAt(idx-1), tmp);
			addParentheses(idx+4, resBuffer);
		}		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("C:\\Users\\bailey\\Desktop\\SWEA\\16637_괄호추가하기_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase= 1; testcase<=T; ++testcase) {
			
			bw.write("#"+testcase+" ");
		
			N = Integer.parseInt(br.readLine());
			s = br.readLine();
			
			addParentheses(2, s.charAt(0)-'0'); // 맨 앞에 있는 피연산자는 버퍼에 넣고 시작, 인덱스는 2번째 피연산자부터
			
			bw.write(String.valueOf(MAX));
			bw.write('\n');
			
			MAX = Integer.MIN_VALUE;
			
		}
		
		br.close();		
		bw.flush();
		bw.close();		
	}

}
