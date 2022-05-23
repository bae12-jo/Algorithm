class Solution {
    public int maxProfit(int[] prices) {
        
        int n = prices.length;
        
        if(n==0) return 0; // no prices data
        if(n<3){ // when only one transaction is possible
            int maxProfit = 0;
            for(int i=1; i<n; i++) maxProfit += Math.max(0, prices[i]-prices[i-1]);
            return maxProfit;
        }
                
        int hold = -prices[0], sold = Integer.MIN_VALUE, inaction = 0;
        for(int i=1; i<n; i++){
            hold = Math.max(hold, inaction-prices[i]);
            inaction = Math.max(inaction, sold);
            sold = hold+prices[i];
        }
        
        return Math.max(sold, inaction);
        
    }
}
