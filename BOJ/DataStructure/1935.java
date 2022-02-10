import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1935 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 피연산자 개수에 맞게 배열 생성
		int[] arr = new int[N];
		
		Stack<Double> s = new Stack<Double>();
		
		// 후위 표기식
		String str = br.readLine();
		// 피연산자 값 저장
		for(int i = 0; i< N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
	
		for(int i = 0; i < str.length(); i++) {
			// 문자열에서 각 문자 파싱
			char c = str.charAt(i);
			
			// 피연산자라면 스택에 저장
			if(c >= 'A' && c <= 'Z') {
				s.push((double)arr[c-'A']);
			}
			// 연산자를 만나면 스택에서 2개를 뽑아서 연산
			else {
				double num2 = s.pop();
				double num1 = s.pop();
				switch(c) {
				case '+':
					s.push(num1 + num2);
					break;
				case '-':
					s.push(num1 - num2);
					break;
				case '*':
					s.push(num1 * num2);
					break;
				case '/':
					s.push(num1 / num2);
					break;
				}
			}
		}
		// 소수점 둘째자리까지 출력
		System.out.printf("%.2f",s.pop());
	}
}
