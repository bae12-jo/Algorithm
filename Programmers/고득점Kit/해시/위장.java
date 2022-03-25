import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> map = new HashMap<>();
        
        // set <name of outfit, case of each outfit> into hash table
        for(String[] s: clothes){
            map.put(s[1], map.getOrDefault(s[1], 0)+1);
        }
        
        // calculate the combination of every outfits including cases not being select from each
        for(String key: map.keySet()){
            answer *= map.get(key) + 1;
        }
        
        // return combination of every case except a case none of outfit selected
        return answer - 1;
    }
}
