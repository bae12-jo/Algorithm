import java.util.*;
import java.io.*;

class BOJ_1600{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	// static StringBuilder sb;
	static int k, w, h;
	static int[][] map;
	static int[][] md = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 원숭이 이동 방향
	static int[][] hd = {{2, -1}, {1, -2}, {2, 1}, {1, 2}, {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}}; // 말 이동 방향
	static boolean[][][] visited; // x, y, 말 이동 잔여 횟수
	
	static class Node{
		int x;
		int y;
		int count;
		int k;
		
		public Node(int x, int y, int count, int k){
			this.x=x;
			this.y=y;
			this.count=count;
			this.k=k;
		}
	}
	
	static void input() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		k = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new int[h][w];
		visited = new boolean[h][w][k+1];
		
		for(int i=0; i<h; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<w; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}				
		}
		
		br.close();
		
	}
	
	static int solution(int x, int y){
		
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x, y, 0, k)); // (0,0) 에서 시작
		visited[x][y][k] = true; // (0,0) 마킹
		
		
		while(!queue.isEmpty()){
			Node curr = queue.poll();
			
			// 도착점 도달 시 움직임 수 반환
			if(curr.x == h-1 && curr.y == w-1) return curr.count;
			
			// 원숭이 움직임
			for(int i=0; i<4; i++){
				int nx = curr.x + md[i][0];
				int ny = curr.y + md[i][1];
				if(nx>=0 && ny>=0 && nx < h && ny < w && !visited[nx][ny][curr.k] && map[nx][ny]==0){
					visited[nx][ny][curr.k] = true;
					queue.offer(new Node(nx, ny, curr.count+1, curr.k));
				}
			
			}
			
			// 말 움직임
			if(curr.k>0){
				for(int i=0; i<8; i++){
					int nx = curr.x + hd[i][0];
					int ny = curr.y + hd[i][1];
					if(nx>=0 && ny>=0 && nx<h && ny<w && !visited[nx][ny][curr.k-1] && map[nx][ny]==0){
						visited[nx][ny][curr.k-1] = true;
						queue.offer(new Node(nx, ny, curr.count+1, curr.k-1));
					}
				}
			}
			
		}
		
		return -1;
		
	}

	public static void main(String[] args) throws IOException{
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input();
		bw.write(String.valueOf(solution(0,0)));
		bw.close();
	
	}
}
