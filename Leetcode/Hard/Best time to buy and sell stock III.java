/* 1 simluation */
// Time O(n) Space O(1)

// 재투자 지출을 -로 보는 관점
class Solution {
    public int maxProfit(int[] prices) {
        
        int firstTransactionCost = prices[0];
        int profitFromFirstTransaction = 0;
        
        int secondTransactionCost = -prices[0];
        int profitFromTwoTransactions = 0;
        
        for(int currPrice: prices){
            firstTransactionCost = Math.min(firstTransactionCost, currPrice);
            profitFromFirstTransaction = Math.max(profitFromFirstTransaction, currPrice-firstTransactionCost);
            
            secondTransactionCost = Math.max(secondTransactionCost, profitFromFirstTransaction-currPrice);
            profitFromTwoTransactions = Math.max(profitFromTwoTransactions, secondTransactionCost + currPrice);
        }
        
        return profitFromTwoTransactions;
        
    }
}

// 재투자 지출을 +로 보는 관점
class Solution {
    public int maxProfit(int[] prices) {
        
        int n = prices.length;
        if(n<=1) return 0;
        
        int t1Cost = Integer.MAX_VALUE, 
        t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0,
        t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            System.out.println(t1Cost + " " + t1Profit);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
            System.out.println(t2Cost + " " + t2Profit);
        }        
        
        
        return t2Profit;
        
    }
}

/* 2 array DP */
// Time O(n) Space O(n)

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
