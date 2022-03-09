/* brute force */

class Solution {
    
    public int lengthOfLocalSubstring(String s, int start){
        int end = start;
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i=start; i<s.length(); i++){
            Integer count = map.get(s.charAt(i));
            if(count==null){
                map.put(s.charAt(i), 0);
                end++;
            }
            else if(count==0) {
                return end-start;
            }
        }
        
        return end-start;
    }
    
    public int lengthOfLongestSubstring(String s) {
        
        int start = 0;
        int end = 0;
        int maxLen = 0;
        int localLen = 0;
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<s.length(); i++){
            localLen = lengthOfLocalSubstring(s, i);
            if(localLen > maxLen) maxLen = localLen;
            
        }
        
        return maxLen;
        
    }
}


/* sliding window */

class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        
        int maxLen = 0;
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int start=0, end=0; end<s.length(); end++){
            if(map.containsKey(s.charAt(end))) start = Math.max(map.get(s.charAt(end)), start);
        
            maxLen = Math.max(maxLen, end-start+1);
            map.put(s.charAt(end), end+1);
        }
        
        
        return maxLen;
        
    }
}
