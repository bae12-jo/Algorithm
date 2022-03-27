import java.util.*;

class Solution {
    
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        
        int answer = 0;
        
        for(int i=0; i<scoville.length; ++i) queue.offer(scoville[i]);
        
        // If scoville of every food is already over K, return 0
        if(queue.peek()>=K) return 0;
        
        // Find out the number of mixing
        while(true){
            if(queue.peek()<K && queue.size()>=2){
                int first = queue.poll();
                int second = 2*queue.poll();
                // if two smallest number is 0, return -1
                if(first==0 && second==0) return -1;
                queue.offer(first+second);
                answer++;
            // if there is only one number under K, return -1
            }else if(queue.peek()<K && queue.size()<2){
                return -1;
            }else break;
        }
        
        return answer > 0 ? answer : -1;
    }
}
