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
        int currHold = 0, currSold = 0, currInaction = 0;
        for(int i=1; i<n; i++){
            currHold = Math.max(hold, inaction-prices[i]);
            currSold = hold+prices[i];
            currInaction = Math.max(inaction, sold);
            hold = currHold;
            sold = currSold;
            inaction = currInaction;
        }
        
        return Math.max(sold, inaction);
        
    }
}
