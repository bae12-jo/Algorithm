import java.util.*;


class WUPC_UnionFind{
	
	static int[] parent; // parent[i] = parent of i
	static int[] size; // size[i] = number of sites in tree rooted at i

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
	* weighted union 최적화
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
	
	public static void main(String[] args){
		
		int V = 10;
		parent = new int[V+1];
		size = new int[V+1];
		
		// 부모와 트리 높이 배열 초기화
		for(int i=1; i<=V; ++i){
			parent[i] = i;
			size[i] = 1;
		}
		
		// 집합으로 묶기
		for(int i=1; i<V; ++i){
			if(find(i)!=find(i+1)) union(i, i+1);
		}
		
		// 부모 출력
		for(int i=1; i<=V; ++i){
			System.out.println(parent[i]);
		}
	}
}
