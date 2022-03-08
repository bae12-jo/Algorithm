import java.util.*;
import java.io.*;

class BOJ_21922{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	static int n, m, count;
	static boolean[][] windy;
	static int[][] map;
	static int[][] D = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 0 아래, 1 위, 2 오른쪽, 4 왼쪽
	static Queue<Node> queue = new LinkedList<>();

	static int toLeft = -1;
	static int toRight = 1;
	static int fromRight = 1;
	
	static class Node{
		
		int x;
		int y;
		int dir;
		
		Node(int x, int y, int dir){
			this.x=x;
			this.y=y;
			this.dir=dir;
		}
		
	}
	
	
	static void input() throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		windy = new boolean[n][m];
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9){
					windy[i][j] = true;
					count++;
					for(int k=0; k<4; k++){
						int nx = i+D[k][0];
						int ny = j+D[k][1];
						queue.offer(new Node(nx, ny, k));
					}
				}
			}				
		}
		
		// System.out.println("done");
		
		br.close();
		
	}
	
	static void solution(){
		
		while(!queue.isEmpty()){
			Node node = queue.poll();
			int r = node.x;
			int c = node.y;
			int dir = node.dir;
			
			if(r<0 || c<0 || r>=n || c>=m) continue;
			
			if(!windy[r][c]) count++;
			windy[r][c] = true;
			
			switch(map[r][c]){
				case 0: // 진행 방향 그대로
					for(int i=0; i<4; i++){
						if(dir==i){
							int nx = r+D[i][0];
							int ny = c+D[i][1];
							queue.offer(new Node(nx, ny, i));
						}
					}
					break;
				case 1: // 상하만 그대로
					for(int i=0; i<2; i++){
						if(dir==i){
							int nx = r+D[i][0];
							int ny = c+D[i][1];
							queue.offer(new Node(nx, ny, i));
						}
					}
					break;
				case 2: // 좌우만 그대로
					for(int i=2; i<4; i++){
						if(dir==i){
							int nx = r+D[i][0];
							int ny = c+D[i][1];
							queue.offer(new Node(nx, ny, i));
						}
					}
					break;
				case 3: 
					// 0 아래, 1 위, 2 오른쪽, 3 왼쪽
					// 상이었으면 우, 하였으면 좌, 좌였으면 하, 우였으면 상
					if(dir==0) queue.offer(new Node(r, c-1, 3));
					if(dir==1) queue.offer(new Node(r, c+1, 2));
					if(dir==2) queue.offer(new Node(r-1, c, 1));
					if(dir==3) queue.offer(new Node(r+1, c, 0));
					break;
				case 4:
					// 0 아래, 1 위, 2 오른쪽, 3 왼쪽
					// 상이었으면 좌, 하였으면 우, 좌였으면 상, 우였으면 하
					if(dir==0) queue.offer(new Node(r, c+1, 2));
					if(dir==1) queue.offer(new Node(r, c-1, 3));
					if(dir==2) queue.offer(new Node(r+1, c, 0));
					if(dir==3) queue.offer(new Node(r-1, c, 1));
					break;
				default:
					break;
			}
			
		}
		
	}

	public static void main(String[] args) throws IOException{
		
		input();
		solution();
		System.out.println(count);
	
	}
}
