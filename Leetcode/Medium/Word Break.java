/* Top-down */

class Solution {
    
    int[] dp; // -1 INF, 1 true, 0 false
    String s;
    List<String> wordDict;
    
    public boolean wordBreak(String s, List<String> wordDict) {
        
        dp = new int[s.length()];
        Arrays.fill(dp, -1);
        this.s=s;
        this.wordDict=wordDict;
        return wordBreak(s.length()-1);
        
    }
    
    private boolean wordBreak(int idx){
        
        // base case
        if(idx<0) return true;
        
        // recurrence relation
        if(dp[idx]==-1){
            for(String word: wordDict){
                if(idx>=word.length()-1 && wordBreak(idx-word.length())){
                    if(s.substring(idx-word.length()+1, idx+1).equals(word)){
                        dp[idx]=1;
                        break;
                    }
                }
            }
        }
        
        if(dp[idx]==-1) dp[idx]=0; // mark as visited (false)
        
        return dp[idx]==1;
        
    }
}

/* Bottom-up */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        boolean[] dp = new boolean[s.length()];
        
        // recurrence relation
        // i를 마지막으로 하는 word가 존재하는지, i에서 word를 뺀 인덱스까지가 true 였는지
        
        for(int i=0; i<s.length(); i++){
            for(String word: wordDict){
                if(i>=word.length()-1 && (i==word.length()-1 || dp[i-word.length()])){
                    if(s.substring(i-(word.length()-1), i+1).equals(word)){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        
        return dp[s.length()-1];
        
    }
}
