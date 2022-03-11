/* TreeSet을 이용하는 문제 - 600ms */
// 이 풀이는 난이도가 다르지만 문제는 같은 데이터가 들어왔을 때 업데이트가 안 된다는 단점이 있으나 백준에서 통과가 된다...

import java.util.*;
import java.io.*;

class BOJ_21939{

	
	static class Problem implements Comparable<Problem>{
		int num;
		int difficulty;
		
		Problem(int n, int d){
			this.num = n;
			this.difficulty = d;
		}
		
		@Override
		public int compareTo(Problem p){
			if(difficulty == p.difficulty) return num - p.num;
			else return difficulty - p.difficulty;
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		TreeSet<Problem> ts = new TreeSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i=0; i<N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int difficulty = Integer.parseInt(st.nextToken());
			ts.add(new Problem(num, difficulty));
			map.put(num, difficulty);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();
			
			switch(command){
				case "add":
					int num = Integer.parseInt(st.nextToken());
					int difficulty = Integer.parseInt(st.nextToken());
					ts.add(new Problem(num, difficulty));
					map.put(num, difficulty);
					break;
				case "recommend":
					int option = Integer.parseInt(st.nextToken());
					if(option==1) sb.append(ts.last().num).append("\n");
					else if(option==-1) sb.append(ts.first().num).append("\n");
					break;
				case "solved":
					int idx = Integer.parseInt(st.nextToken());
					ts.remove(new Problem(idx, map.get(idx)));
					map.remove(idx);
					break;
					
			}
		}
	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
}

/* PriorityQueue를 2개 사용! (중복 입력 및 삭제 구현이 관건) - 452ms */

import java.util.*;
import java.io.*;

// TreeSet을 이용하는 문제!!!
// PriorityQueue를 2개 사용해도 됨!!! (중복 입력되는 걸 어떻게 해결할지?)

class BOJ_21939{

	
	static class Problem implements Comparable<Problem>{
		int num;
		int difficulty;
		
		Problem(int n, int d){
			this.num = n;
			this.difficulty = d;
		}
		
		@Override
		public int compareTo(Problem p){
			if(difficulty == p.difficulty) return num - p.num;
			else return difficulty - p.difficulty;
		}
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int newest[] =  new int[100001];
		
		PriorityQueue<Problem> maxHeap = new PriorityQueue<>((p1, p2
		) -> {
			if(p1.difficulty == p2.difficulty){
				return p2.num - p1.num;
			}
			return p2.difficulty - p1.difficulty;
			
		});
		
		PriorityQueue<Problem> minHeap = new PriorityQueue<>((p1, p2) -> {
			if(p1.difficulty == p2.difficulty){
				return p1.num - p2.num;
			}
			return p1.difficulty - p2.difficulty;
		});
		
		for(int i=0; i<N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int difficulty = Integer.parseInt(st.nextToken());
			Problem p = new Problem(num, difficulty);
			maxHeap.offer(p);
			minHeap.offer(p);
			newest[num] = difficulty;
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();
			
			switch(command){
				case "add":
					int num = Integer.parseInt(st.nextToken());
					int difficulty = Integer.parseInt(st.nextToken());
					Problem p = new Problem(num, difficulty);
					maxHeap.offer(p);
					minHeap.offer(p);
					newest[num] = difficulty;
					break;
				case "recommend":
					int option = Integer.parseInt(st.nextToken());
					if(option==1){
						while(newest[maxHeap.peek().num]!=maxHeap.peek().difficulty){
							maxHeap.poll();
						}
						sb.append(maxHeap.peek().num).append("\n");
						break;
					}
					else if(option==-1) {
						while(newest[minHeap.peek().num]!=minHeap.peek().difficulty){
							minHeap.poll();
						}
						sb.append(minHeap.peek().num).append("\n");
						break;
					}
				case "solved": 
					// 원하는 번호가 나올 때까지 큐에서 전부 뺐다가 다시 넣을 것는 방법도 있으나..
					// 애초에 최신 값만 들어있는 배열을 만드는 것이 나을 듯 (메모리는 조금 낭비됨)
					int idx = Integer.parseInt(st.nextToken());
					newest[idx] = 0;
					break;
					
			}
		}
	
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
}
