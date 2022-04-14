import java.util.*;

class Solution {
    
    public int solution(int cacheSize, String[] cities) {
        
        if(cacheSize==0) return cities.length*5;
        
        Deque<String> cache = new ArrayDeque<>();
        int answer = 0;
        
        for(int i=0; i<cities.length; ++i){
            String city = cities[i].toLowerCase();
            
            // cache hit
            if(cache.remove(city)){
                cache.addFirst(city);
                answer+=1;
            // cache miss
            }else{
                // cache is not full
                if(cache.size()<cacheSize){
                    cache.addFirst(city);
                    answer += 5;
                // cache is full
                }else{
                    cache.removeLast();
                    cache.addFirst(city);
                    answer+=5;
                }
            }
        }
        
        return answer;
    }
}
