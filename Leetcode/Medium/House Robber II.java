class Solution {
    public int rob(int[] nums) {
        
        // edge cse
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        
        return Math.max(rob(nums, 0, nums.length-1), rob(nums, 1, nums.length));
        
    }
    
    private int rob(int[] nums, int start, int end){
        
        int preMax = 0;
        int currMax = 0;
        
        for(int i=start; i<end; i++){
            int tmpMax = Math.max(currMax, preMax+nums[i]);
            preMax = currMax;
            currMax = tmpMax;
        }
        
        return currMax;
        
    }
}
