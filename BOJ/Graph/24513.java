import java.util.*;
import java.io.*;


public class BOJ_24513{
	
	private static int row;
	private static int col;
	private static int[][] map;
	private static int[] infected; // -1: hasVaccine, 0: virus1, 1: virus2, 2: virus3
	private static int[][][] propaTime; // virus1, virus2, virus3
	private static Queue<Status> queue;
	final static int dx[] = {0, 1, 0, -1}; // 2차원 배열에서는 상하
	final static int dy[] = {-1, 0, 1, 0}; // 2차원 배열에서는 좌우
	final static int INF = 4000000; // 미감염 지역의 시간을 무한대로 초기화
	
	static class Status{
		int x, y, numVirus, reachTime;
		public Status(int x, int y, int numVirus, int reachTime){
			this.x=x;
			this.y=y;
			this.numVirus=numVirus;
			this.reachTime=reachTime;
		}
	}
	
	static void bfs(){
		
		int x, y, nV, rT, v1, v2;
		
		while(!queue.isEmpty()){
			Status now = queue.poll();
			x = now.x;
			y = now.y;
			nV = now.numVirus;
			rT = now.reachTime;
			v1 = propaTime[x][y][1]==INF ? 0:1; // v1 감염 시간
			v2 = propaTime[x][y][2]==INF ? 0:2; // v2 감염 시간
			//System.out.println(x+ " " +y+" "+v1 + " " + v2);
			map[x][y] = v1+v2; // 마을 상태 업데이트
			infected[v1+v2]++; // 바이러스 별 감염 마을 수 업데이트
			if(v1+v2==3) continue;
			next: for(int i=0; i<4; i++){ // 0: 하, 1: 우, 2: 상, 3:좌
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx>=0 && ny>=0 && nx<row && ny<col && map[nx][ny]==0 && propaTime[nx][ny][nV]>rT+1 && propaTime[nx][ny][3-nV]>rT){
					propaTime[nx][ny][nV]=rT+1;
					if(propaTime[nx][ny][3-nV]==INF) queue.offer(new Status(nx, ny, nV, rT+1));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new int[row][col];
		infected = new int[4];
		propaTime = new int[row][col][3];
		queue = new LinkedList<>();
		
		for(int i=0;i<row;i++)
		    for(int j=0;j<col;j++)
		        for(int k=0;k<3;k++)
		            propaTime[i][j][k]=INF;
		
		for(int i=0; i<map.length; i++){
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<map[0].length; j++){
				int val = Integer.parseInt(st2.nextToken());
				// 마을 정보 초기화
				map[i][j] = val;
				// 바이러스가 있는 마을의 정보를 큐에 넣고, 위치와 종류를 check에 저장
				if(val>=1 && val<=2){
					queue.offer(new Status(i, j, val, 0));
					propaTime[i][j][val]=0;
				}
			}
		}
		
/* 		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		} */
		
		bfs();
		
/* 		for(int i=0; i<row; i++){
			for(int j=0; j<col; j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		} */
		
		System.out.println(infected[1]+" " +infected[2]+" "+infected[3]);
	
	}
}

/*
* 2차원 배열에서 row, col 조건문 거꾸로 준게 원인 이었음... 바보냐...?
* propaTime[nx][ny][nV]>rT+1 는 같은 바이러스가 마주칠 경우를 대비, 전파 속도는 1 이상 차이날 수 없음
*/
