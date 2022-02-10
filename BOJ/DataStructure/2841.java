import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2841 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static class guitar{
		int line;
		int num;
		guitar(int line, int num){
			this.line = line;
			this.num = num;
		}
	}
	
	static class stack{
		Stack<guitar> g = new Stack<guitar>();
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		// 멜로디에 포함되어 있는 음의 수
		int N = Integer.parseInt(st.nextToken());
		// 한 줄에 있는 프랫의 수
		int P = Integer.parseInt(st.nextToken());
		
		// 각 줄에 대해서 스택 선언
		stack[] g = new stack[N+1];
		for(int i=1; i<N; i++){
			g[i] = new stack();
		}
		
		// 손가락 움직임 횟수
		int count = 0;
		
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine(), " ");
			// 줄의 번호
			int line = Integer.parseInt(st.nextToken());
			// 그 줄에서 눌러야 하는 프랫의 번호
			int num = Integer.parseInt(st.nextToken());
			// 현재 기타 인스턴스 생성
			guitar now = new guitar(line, num);
			
			// 스택이 비어 있다면 push
			if(g[line].g.isEmpty()){
				g[line].g.add(now);
				count++;
			}
			// 스택의 top보다 새로 입력 받은 프랫이 더 높다면 push
			else if(g[line].g.peek().num<now.num){
				g[line].g.add(now);
				count++;
			}
			else{
				// 새로 입력 받은 프랫보다 높은 프랫은 pop
				while(!g[line].g.isEmpty()&&g[line].g.peek().num>now.num){
					g[line].g.pop();
					count++;
				}
				// 새로 입력 받은 프랫보다 pop이 낮아지면 push
				if(!g[line].g.isEmpty()&&g[line].g.peek().num<now.num){
					g[line].g.add(now);
					count++;
				}
				// 스택이 비면 push
				else if(g[line].g.isEmpty()){
					g[line].g.add(now);
					count++;
				}
			}
		}
		System.out.println(count);
	}
}
