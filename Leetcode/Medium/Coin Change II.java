class Solution {
    public int change(int amount, int[] coins) {
        
        // base case
        int[] ways = new int[amount+1];
        if(amount==0) return 1;
        ways[0] = 1; // if amount is zero, there is one case, choose nothing
        
        for(int coin: coins){
            for(int target=coin; target<=amount; target++){
                ways[target] += ways[target-coin];
            }
        }
        
        return ways[amount];
    }
}
