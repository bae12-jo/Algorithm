import java.io.*;
import java.util.*;

public class SWEA_1204{

	public static void main(String[] args) throws Exception{
		
		// System.setIn(new FileInputStream("C:/Users/bailey/Desktop/SWEA/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++){
			
			if(testcase != Integer.parseInt(br.readLine())) break;
			
			bw.write("#" + String.valueOf(testcase) + " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			HashMap<Integer, Integer> scores = new HashMap<>();
			
			int max = 0;
			int mode = 0;
			while(st.hasMoreTokens()){
				int score = Integer.parseInt(st.nextToken());
				scores.put(score, scores.getOrDefault(score, 0)+1);
				int count = scores.get(score);
				max = max > count ? max : count;
				if(max==count) mode = score;
			}
			
			bw.write(String.valueOf(mode)+'\n');
			
		}
		
		bw.flush();
		bw.close();
		
	}

}
