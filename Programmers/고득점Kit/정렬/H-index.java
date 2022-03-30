import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int i=0; i<citations.length; ++i){
            int h = citations.length-i;
            // it the number of remain element is over H, return H
            if(h<=citations[i]) return h;
        }
        
        return answer;
    }
}
