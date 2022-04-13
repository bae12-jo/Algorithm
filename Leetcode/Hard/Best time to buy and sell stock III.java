/* 2 array DP */

class Solution {
    public int maxProfit(int[] prices) {
        
        int n = prices.length;
        
        if(n<=1) return 0;
        
        int leftMin = prices[0];
        int rightMax = prices[n-1];
        
        int[] leftProfit = new int[n];
        int[] rightProfit = new int[n+1]; // for comparing
        
        for(int l=1; l<n; ++l){
            
            leftProfit[l] = Math.max(leftProfit[l-1], prices[l]-leftMin);
            leftMin = Math.min(leftMin, prices[l]);
            
            int r = n-l-1;
            rightProfit[r] = Math.max(rightProfit[r+1], rightMax-prices[r]);
            rightMax = Math.max(rightMax, prices[r]);
            
        }
        
        int max = 0;
        for(int i=0; i<n; ++i){
            // System.out.println(leftProfit[i] + " " + rightProfit[i]);
            max = Math.max(max, leftProfit[i]+rightProfit[i+1]);
        }
        
        return max;
        
    }
}
