class Solution {
    public int maxProfit(int[] prices) {
        
        int maxProfit = 0;
        int valley = prices[0];
        int peek = prices[0];
        
        int idx = 0;
        while(idx<prices.length-1){
            // find valley first
            while(idx<prices.length-1 && prices[idx]>=prices[idx+1]) idx++; 
            valley = prices[idx];
            // find peek next
            while(idx<prices.length-1 && prices[idx]<=prices[idx+1]) idx++;
            peek = prices[idx];
            
            maxProfit += peek-valley;
        }
        
        return maxProfit;
    }
}
