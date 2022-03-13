import java.util.*;

class Solution {
    public int solution(int[] estimates, int k) {
        int answer = 0;

        int start = 0;
        int end = k;
        int ans = 0;

        for(int i=start; i<end; ++i) ans += estimates[i];

        if(estimates.length == k) return ans;

        start++;
        end++;

        answer = ans;

        while(start<end){
            if(end>estimates.length) break;
            ans -= estimates[start-1];
            ans += estimates[end-1];           
            answer = Math.max(ans, answer);
            start+=1;
            end+=1;
        }

        return answer;
    }
}
