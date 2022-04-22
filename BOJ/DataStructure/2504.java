import java.io.*;
import java.util.*;

public class BOJ_2504{
	
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> stack = new Stack<>();
		
		String input = br.readLine();
		
		int answer = 0;
		int capsule = 1;
		boolean isClose = false;
		
		for(int i=0; i<input.length(); ++i){
			
			char curr = input.charAt(i);
			switch(curr){
				case '(':
					capsule *= 2;
					stack.push('(');
					break;
				case '[':
					capsule *= 3;
					stack.push('[');
					break;
				case ')':
					if(stack.isEmpty() || stack.peek()!='('){
						isClose = true;
						break;
					}
					if(input.charAt(i-1)=='(') answer += capsule;
					stack.pop();
					capsule /= 2;
					break;
				case ']':
					if(stack.isEmpty() || stack.peek()!='['){
						isClose = true;
						break;
					}
					if(input.charAt(i-1)=='[') answer += capsule;
					stack.pop();
					capsule /= 3;
					break;		 
					
			}
			
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		if(isClose || !stack.isEmpty()) bw.write(String.valueOf(0));
		else bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		
	}
}
