// BST는 무엇을 탐색할지랑 범위 설정이 중요함
// 이 문제에서는 시간을 기점으로 탐색해나감

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        Arrays.sort(times);
        
        long answer = 0;
        
        // Set range between min and max time
        long left = 0;
        long right = (long)n*times[times.length-1];
        
        while(left<=right){
            long mid = (left+right)/2;
            long sum = 0;
            
            // Check it can be done within the given time
            for(int time: times){
                sum += mid/time;
                if(n<sum) break;
            }
            
            // If it can't be done within the given time, increase the time
            if(n>sum) left = mid+1;
            else {
                right = mid-1;
                answer = mid;
            }
        }
        
        return answer;
    }
}
