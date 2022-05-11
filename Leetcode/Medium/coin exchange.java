class Solution {
    public int coinChange(int[] coins, int amount) {
        
        int max = amount+1; // calculate from 0, to know if there is possible combination to make amount or not
        int[] dp = new int[amount+1];
        Arrays.fill(dp, max);
        
        dp[0] = 0; // there is no coin 0
        
        for(int i=1; i<=amount; ++i){
            for(int j=0; j<coins.length; ++j){
                if(coins[j]<=i) dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
        
    }
}
