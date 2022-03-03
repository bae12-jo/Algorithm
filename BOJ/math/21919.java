import java.util.*;
import java.io.*;

public class BOJ_21919{

	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static boolean findPrime(long num){
		for(long i=2; i*i<=num; i++){
			if(num%i==0) return false; 
		}
		return true;
	}

	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Set<Long> set = new HashSet<>();
		
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++){
			
			long a = Long.parseLong(st.nextToken());
			if(findPrime(a)){
				set.add(a);
			}
		}
		
		if(set.isEmpty()){
			
			System.out.println(-1);
			
		}else{
			
			Iterator it = set.iterator();
			long lcm = (Long)it.next();
			
			while(it.hasNext()){
				lcm *= (Long)it.next();
			}
			
			if(lcm!=1){
                System.out.println(lcm);
            }else{
                System.out.println(-1);
            }
		}
	
	}
}
