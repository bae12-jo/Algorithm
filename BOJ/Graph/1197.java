// https://algs4.cs.princeton.edu/15uf/WeightedQuickUnionPathCompressionUF.java.html

import java.util.*;
import java.io.*;

/*
3 3
1 2 1
2 3 2
1 3 3
*/

public class BOJ_1197{
	
	static int V, E; // Number of Vertices, Number of Edges	
	static int[] parent; // parent[i] = parent of i
	static int[] size; // size[i] = number of sites in tree rooted at i
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	
	static FastIO io = new FastIO();

	public static class Edge implements Comparable<Edge>{
		
		int v1;
		int v2;
		int weight;
		
		Edge(int v1, int v2, int weight){
			this.v1=v1;
			this.v2=v2;
			this.weight=weight;
		}
		
		@Override
		public int compareTo(Edge e){
			return this.weight-e.weight;
		}
		
	}
	
	public static void input() throws IOException{
		
		V = io.nextInt();
		E = io.nextInt();
		
		for(int i=0; i<E; ++i){
			int v1 = io.nextInt();
			int v2 = io.nextInt();
			int weight = io.nextInt();
			pq.offer(new Edge(v1, v2, weight));
		}
		
	}
	
	public static void kruskal() {
		
		parent = new int[V+1];
		size = new int[V+1];
		for(int i=1; i<=V; ++i){
			parent[i] = i;
			size[i] = 1;
		}
		
		int count = 0; // n-1이 되면 스패닝 트리가 완성됨
		int mstWeight = 0;
		
		while(!pq.isEmpty()){
			if(count==V-1) break;
			
			Edge now = pq.poll();
			if(find(now.v1)!=find(now.v2)){
				mstWeight += now.weight;
				union(now.v1, now.v2);
				count++;
			}
			
		}
		
		io.print(mstWeight);
		io.flushbuffer();
		
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
