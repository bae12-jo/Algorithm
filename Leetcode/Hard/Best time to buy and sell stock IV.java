// DP

class Solution {
    public int maxProfit(int k, int[] prices) {
        
        int length = prices.length;
        
        // when input is 0
        if(length<=0 || k<=0) return 0;
        
        int maxProfit = 0;
        
        // number of trading data is not less than k (n is 1, 2, 3)
        if(2*k>length){
            for(int i=1; i<length; ++i) maxProfit += Math.max(0, prices[i]-prices[i-1]);
            return maxProfit;
        }
        
        
        int[][][] dp = new int[length][k+1][2]; // day number, transaction number, stock holding state
        
        for(int i=0; i<length; ++i){
            for(int j=0; j<=k; ++j){
                dp[i][j][0] = -10000000; // initialize the array with -INF (maximum length*maximum price)
                dp[i][j][1] = -10000000;
            }
        }
            
    
        // set starting value
            dp[0][0][0] = 0; // first day, no transaction, no stock
            dp[0][1][1] = -prices[0]; // firstd day, first transaction, has stock
        
        for(int i=1; i<length; ++i){
            for(int j=0; j<=k; ++j){
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1]+prices[i]); // keep not holding stock or selling
                if(j>0) dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][0]-prices[i]); // keep hoding stock or buying
            }
        }
        
        // maximum of dp[length][k][0] from k=0 to k=k
        for(int i=0; i<=k; ++i) maxProfit = Math.max(maxProfit, dp[length-1][i][0]);
            
        return maxProfit;
    }

}
