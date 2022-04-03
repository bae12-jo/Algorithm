import java.util.*;
import java.io.*;

public class BOJ_1774{
	
	static int N, M; // Number of Vertices, Number of Edges	
	static int[] parent; // parent[i] = parent of i
	static int[] size; // size[i] = number of sites in tree rooted at i
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static FastIO io = new FastIO();

	public static class Edge implements Comparable<Edge>{
		
		int v1;
		int v2;
		double weight;
		
		Edge(int v1, int v2, double weight){
			this.v1=v1;
			this.v2=v2;
			this.weight=weight;
		}
		
		@Override
		public int compareTo(Edge e){
			if(this.weight < e.weight) return -1;
			else return 1;
		}
		
	}
	
	public static class Coordinate{
		int index;
		double x; // getDistance 연산을 위해서 double로 선언해줌
		double y; // getDistance 연산을 위해서 double로 선언해줌
		
		Coordinate(int index, double x, double y){
			this.index = index;
			this.x = x;
			this.y = y;
		}
	}
	
	public static double getDistance(Coordinate c1, Coordinate c2){
		// 피타고라스 정리를 이용해서 좌표 평면에서 두 점 사이의 거리 구하기
		return Math.sqrt(Math.pow(c1.x-c2.x, 2) + Math.pow(c1.y-c2.y, 2));
	}
	
	public static void input() throws IOException{
		
		N = io.nextInt();
		M = io.nextInt();
		
		Coordinate[] coord = new Coordinate[N+1];
		
		// 좌표 저장
		for(int i=1; i<=N; ++i){
			int newX = io.nextInt();
			int newY = io.nextInt();
			coord[i] = new Coordinate(i, newX, newY);
		}
		
		parent = new int[N+1];
		size = new int[N+1];
		for(int i=1; i<=N; ++i){
			parent[i] = i;
			size[i] = 1;
		}
		
		// 이미 연결된 좌표들 집합으로 묶어주기
		for(int i=1; i<=M; ++i){
			int connectedX = io.nextInt();
			int connectedY = io.nextInt();
			union(connectedX, connectedY);
		}
		
		// 좌표간 거리를 저장
		for(int i=1; i<=N; i++){
			for(int j=i+1; j<=N; ++j){
				pq.offer(new Edge(coord[i].index, coord[j].index, getDistance(coord[i], coord[j])));
			}
		}
				
	}
	
	public static void kruskal() throws IOException{
		
		int count = pq.size(); // n-1이 되면 스패닝 트리가 완성됨
		double mstWeight = 0;
		
		while(!pq.isEmpty()){
			if(count==0) break;
			
			Edge now = pq.poll();
			if(find(now.v1)!=find(now.v2)){
				mstWeight += now.weight;
				union(now.v1, now.v2);
				count--;
			}
			
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		bw.write(String.format("%.2f", mstWeight));
		bw.flush();
		bw.close();
		
	}
	/*
	* path compression 최적화
	* 트리의 높이를 최소화 해주기 위한 작업
	* 노드 x의 루트를 찾을 때마다 x와 루트 사이의 모든 루트의 부모를 루트로 바꿔줌
	
	*/ 
	public static int find(int x){
		// if(parent[x] == x) return x;
		// return parent[x] = find(parent[x]);
		int root = x;
		while(root != parent[root]){ // root를 찾아 올라감
			root = parent[root];
		}
		while(x != root){ // x부터 root 사이에 있는 노드들의 부모를 모두 root로 바꿔줌
			int newX = parent[x];
			parent[x] = root;
			x = newX;
		}
		return root;
	}
	
	/*
	* weighted union  최적화
	* 높이가 더 높은 트리에 상대적으로 높이가 작은 트리가 자식으로 들어가면 전체 높이 변화가 없다는 점을 이용
	* 높이가 더 큰 트리를 부모로 삼도록 사이즈 조정해줌
	*/
	public static void union(int x, int y){
		x = find(x);
		y = find(y);
		
		if(x==y) return;
		
		if(size[x]>size[y]){
			parent[y] = x;
			size[x] += size[y];
		}else{
			parent[x] = y;
			size[y] += size[x];
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		input();
		kruskal();
		
	}
	
	private static class FastIO {
		private static final int EOF = -1;
		
		private static final byte ASCII_space = 32;
		private static final byte ASCII_minus = 45;
		private static final byte ASCII_0 = 48;
		private static final byte ASCII_9 = 57;
		
		private final DataInputStream din;
		private final DataOutputStream dout;
		
		private byte[] inbuffer;
		private int inbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outbufferpointer;
		
		private byte[] bytebuffer;
		
		private FastIO() {
			this.din = new DataInputStream(System.in);
			this.dout = new DataOutputStream(System.out);
			
			this.inbuffer = new byte[65536];
			this.inbufferpointer = 0;
			this.bytesread = 0;
			this.outbuffer = new byte[65536];
			this.outbufferpointer = 0;
			
			this.bytebuffer = new byte[20];
		}
		
		private byte read() {
			if (inbufferpointer == bytesread)
				fillbuffer();
			return bytesread == EOF ? EOF : inbuffer[inbufferpointer++];
		}
		
		private void fillbuffer() {
			try {
				bytesread = din.read(inbuffer, inbufferpointer = 0, inbuffer.length);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		private void write(byte b) {
			if (outbufferpointer == outbuffer.length)
				flushbuffer();
			outbuffer[outbufferpointer++] = b;
		}
		
		private void flushbuffer() {
			if (outbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				outbufferpointer = 0;
			}
		}
		
		private int nextInt() {
			byte b;
			while(isSpace(b = read()))
				;
			boolean negative = false;
			if (b == '-') {
				negative = true;
				b = read();
			}
			int result = b - '0';
			while (isDigit(b = read()))
				result = result * 10 + b - '0';
			return negative ? -result : result;
		}
		
		private void print(int i) {
			if (i == 0) {
				write(ASCII_0);
			} else {
				if (i < 0) {
					write(ASCII_minus);
					i = -i;
				}
				int index = 0;
				while (i > 0) {
					bytebuffer[index++] = (byte) ((i % 10) + ASCII_0);
					i /= 10;
				}
				while (index-- > 0) {
					write(bytebuffer[index]);
				}
			}
		}
		
		private boolean isSpace(byte b) {
			return b <= ASCII_space && b >= 0;
		}
		
		private boolean isDigit(byte b) {
			return b >= ASCII_0 && b <= ASCII_9;
		}
	}
}
