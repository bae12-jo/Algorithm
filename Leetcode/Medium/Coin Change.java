// Top down

class Solution {
    public int coinChange(int[] coins, int amount) {
        
        if(amount<1) return 0;
        return coinChange(coins, amount, new int[amount]);
        
    }
    
    private int coinChange(int[] coins, int remain, int[] count){
        // base case
        if(remain<0) return -1;
        if(remain==0) return 0;
        
        // memoization
        if(count[remain-1]!=0) return count[remain-1];
        
        // get min num
        int min = Integer.MAX_VALUE;
        for(int coin: coins){
            int tmp = coinChange(coins, remain-coin, count);
            if(tmp>=0 && tmp<min) min=1+tmp;
        }
        count[remain-1] = (min==Integer.MAX_VALUE) ? -1:min;
        return count[remain-1];
    }
}

// Bottom up

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
