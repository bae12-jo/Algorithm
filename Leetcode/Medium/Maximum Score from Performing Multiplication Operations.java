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

/* Bottom-up */

class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        
        int N = nums.length, M = multipliers.length;
        int[][] cache = new int[M+1][M+1]; // M 인덱스의 값이 0이 되도록 하기 위해 (out of bound 방지)
        
        // base case가 iteration의 처음이 되어야함!
        for(int total=M-1; total>=0; total--){
            for(int left=total; left>=0; left--){
                int mult = multipliers[total];
                int right = N-1-(total-left);
                cache[total][left] = Math.max(nums[left]*mult+cache[total+1][left+1], nums[right]*mult+cache[total+1][left]);
            }
        }
        
        return cache[0][0];
        
    }
}
