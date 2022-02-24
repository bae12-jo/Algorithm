import java.io.*;
import java.util.*;


public class BOJ_2884
{
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
	    int Hour = Integer.parseInt(st.nextToken());
	    int Min = Integer.parseInt(st.nextToken());
	    
	    Min-=45;

	    if(Min<0){
			Hour--;
			if(Hour<0) Hour+=24;
			Min+=60;	        
	    }
	    
	    System.out.print(Hour + " " + Min);
	}
}
