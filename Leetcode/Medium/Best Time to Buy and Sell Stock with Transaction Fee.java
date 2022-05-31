class Solution {
    public int maxProfit(int[] prices, int fee) {
        
        int without = 0, with = -prices[0];
        
        for(int i=1; i<prices.length; i++){
            without = Math.max(without, with+prices[i]-fee);
            with = Math.max(with, without-prices[i]);
        }
        
        return without;
        
    }
}
