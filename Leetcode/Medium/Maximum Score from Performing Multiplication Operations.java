/* Top-down */

class Solution {
    
    int[][] cache;
    int[] nums;
    int[] multipliers;
    int N, M;
    
    public int maximumScore(int[] nums, int[] multipliers) {
        
        this.nums=nums;
        this.multipliers=multipliers;
        N = nums.length;
        M = multipliers.length;
        cache = new int[M][M];
        return maximumScore(0, 0);
        
    }
    
    private int maximumScore(int total, int left){
        
        // base case
        if(total==M) return 0;
        
        int mult = multipliers[total];
        int right = N-1-(total-left);
        
        // memoization
        if(cache[total][left]!=0) return cache[total][left];
        
        cache[total][left] = Math.max(mult*nums[left]+maximumScore(total+1, left+1), mult*nums[right]+maximumScore(total+1, left));        
            
        return cache[total][left];
    }
    
}
