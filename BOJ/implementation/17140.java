import java.util.*;
import java.io.*;

class BOJ_17140{

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int r, c, k;
	static int arr[][] = new int[100][100];
	
	static class NumCount implements Comparable<NumCount>{
		int data;
		int count;
		
		NumCount(int d, int c){
			this.data = d;
			this.count = c;
		}
		
		@Override
		public int compareTo(NumCount n){
			if(count>n.count) return 1;
			else if(count==n.count) return 0;
			else return -1;
		}
	}
	
	public static void input() throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<3; ++i){
			st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
			/* for(int j=0; i<3; ++j){
				arr[i][j] = Integer.parseInt(st.nextToken());
			} */	
		}	
		br.close();
	}
	
	public static int solution(){
		
		int rsize = 3, csize = 3;
		
		for(int t=0; t<=100; ++t){
			
			if(arr[r][c]==k) return t; // end condition
			
			if(rsize>=csize){
			
				for(int i=0; i<rsize; i++){
					
					// 음.. 해시맵으로 하는 방법도 있지 않을까? 대신 정렬이 복잡할듯
					// https://coder-in-war.tistory.com/entry/%EA%B0%9C%EB%85%90-29-Sorting-In-Javafeat-%EC%82%AC%EC%9A%A9%EC%9E%90%EC%A0%95%EC%9D%98-%EA%B0%9D%EC%B2%B4-%EC%A0%95%EB%A0%AC
					// comparator 2개 써서 정렬하는 거 시도해보기
					int count[] = new int[101];
					List<NumCount> list = new LinkedList<>();
					
					// 출현 빈도 업데이트
					for(int j=0; j<csize; ++j){
						count[arr[i][j]]++;
					}
					
					// 알파벳 순으로 정렬
					for(int c=1; c<=100; ++c){
						if(count[c]>0) list.add(new NumCount(c, count[c]));
					}
					// 출현 빈도순으로 정렬
					list.sort((lnc, rnc)->{
						return lnc.count-rnc.count;
					});
					// 배열에 최대 100까지 저장
					int idx = 0;
					for(NumCount n: list){
						if(idx>=99) break;
						arr[i][idx++] = n.data;
						arr[i][idx++] = n.count;
					}
					if(idx>csize) csize=idx;
					// 배열의 빈 공간 9으로 채우기
					for(; idx<100; ++idx){
						arr[i][idx] = 0;
					}
			
				}
			
			}else{
				
				for(int i=0; i<csize; i++){
					
					// 음.. 해시맵으로 하는 방법도 있지 않을까? 대신 정렬이 복잡할듯
					int count[] = new int[101];
					List<NumCount> list = new LinkedList<>();
					
					// 출현 빈도 업데이트
					for(int j=0; j<rsize; ++j){
						count[arr[j][i]]++;
					}
					
					// 알파벳 순으로 정렬
					for(int c=1; c<=100; ++c){
						if(count[c]>0) list.add(new NumCount(c, count[c]));
					}
					// 출현 빈도순으로 정렬
					list.sort((lnc, rnc)->{
						return lnc.count-rnc.count;
					});
					// 배열에 최대 100까지 저장
					int idx = 0;
					for(NumCount n: list){
						if(idx>=99) break;
						arr[idx++][i] = n.data;
						arr[idx++][i] = n.count;
					}
					if(idx>rsize) rsize=idx;
					// 배열의 빈 공간 9으로 채우기
					for(; idx<100; ++idx){
						arr[idx][i] = 0;
					}
			
				}
			
				
			}
		}
		
		return -1;
		
	}
	

	public static void main(String[] args) throws IOException{
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input();
		// System.out.println(solution());
		bw.write(String.valueOf(solution()));
		bw.close();
	
	}
}
