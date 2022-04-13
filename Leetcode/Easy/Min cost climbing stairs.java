class Solution {
    public int minCostClimbingStairs(int[] cost) {
        
        int n = cost.length;
        if(n==1) return 1;
        
        int[] dp = new int[n+1];
        dp[1] = cost[0];
        dp[2] = cost[1];
        
        for(int i=3; i<=n; ++i){
            dp[i] = cost[i-1] + Math.min(dp[i-1], dp[i-2]);
        }
        
        return Math.min(dp[n-1], dp[n]);
    }
}
