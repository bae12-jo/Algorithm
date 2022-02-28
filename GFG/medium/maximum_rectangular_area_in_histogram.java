/* 스택을 이용한 풀이 (통과) */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    
    
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
		    long n = Long.parseLong(br.readLine().trim());
		    String inputLine[] = br.readLine().trim().split(" ");
		    long[] arr = new long[(int)n];
		    for(int i=0; i<n; i++)arr[i]=Long.parseLong(inputLine[i]);
		    System.out.println(new Solution().getMaxArea(arr, n));
		}
	}
}



// } Driver Code Ends


class Solution
{
        
        
    // The main function to find the maximum rectangular area under given
    // histogram with n bars
    static long getMaxArea(long hist[], long n) 
    {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();
        
        long max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        long area_with_top; // To store area with top bar as the smallest bar
     
        // Run through all bars of given histogram
        int i = 0;
        while (i < n)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || hist[s.peek()] <= hist[i])
                s.push(i++);
     
            // If this bar is lower than top of stack, then calculate area of rectangle 
            // with stack top as the smallest (or minimum height) bar. 'i' is 
            // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top
     
                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);
     
                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }
     
        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);
     
            if (max_area < area_with_top)
                max_area = area_with_top;
        }
     
        return max_area;

    }
        
}





/* 분할 정복을 이용한 풀이 (일부 테스트케이스 미통과) */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    
    
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
		    long n = Long.parseLong(br.readLine().trim());
		    String inputLine[] = br.readLine().trim().split(" ");
		    long[] arr = new long[(int)n];
		    for(int i=0; i<n; i++)arr[i]=Long.parseLong(inputLine[i]);
		    System.out.println(new Solution().getMaxArea(arr, n));
		}
	}
}



// } Driver Code Ends


class Solution
{
        
	static long getMaxArea(long hist[], long n){
		
		long lo = 0;
		long hi = n-1;
		
		if(n==1) return hist[0];
		
		long mid = (lo+hi)/2;

		long max = Math.max(getArea(hist, lo, mid), getArea(hist, mid+1, hi));
		
		max = Math.max(max, getLocalMaxArea(hist, lo, hi, mid));
		
		return max;
	
	}
	
	public static long getArea(long hist[], long lo, long hi){
	    
	    if(lo==hi) return 0;
	    if(lo+1 == hi) return hist[(int)lo];
	    
	    long mid = (lo+hi)/2;
	    
	    long max = Math.max(getArea(hist, lo, mid), getArea(hist, mid+1, hi));
	    
	    return max;
	    
	}
	
	public static long getLocalMaxArea(long hist[], long lo, long hi, long mid){
	
		long toLeft = mid;
		long toRight = mid;
		
		long height = hist[(int)mid];
		
		long maxArea = height;
		
		while(lo<toLeft && toRight<hi){
		
			/* 좌우 중 더 높은 곳으로 이동, 최대 높이는 기존값과 옮긴 값 중 더 작은 쪽으로 갱신*/
			if(hist[(int)toLeft-1]<hist[(int)toRight+1]){
				toRight++;
				height = Math.min(height, hist[(int)toRight]);
			}
			else{
				toLeft--;
				height = Math.min(height, hist[(int)toLeft]);
			}
			
			maxArea = Math.max(maxArea, height*(toRight-toLeft+1));
		}
		
		/* 오른쪽 구간이 남았다면 */
		while(toRight < hi){
		
			toRight++;
			height = Math.min(height, hist[(int)toRight]);
			maxArea = Math.max(maxArea, height*(toRight-toLeft+1));
			
		}
		
		/* 왼쪽 구간이 남았다면 */
		while(lo < toLeft ){
		
			toLeft--;
			height = Math.min(height, hist[(int)toLeft]);
			maxArea = Math.max(maxArea, height*(toRight-toLeft+1));
		}
		
		return maxArea;
	
	}
        
}



