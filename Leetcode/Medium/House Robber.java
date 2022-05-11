class Solution {
    public int rob(int[] nums) {
        
        // edge case
        if(nums.length==0) return 0;
        
        
        int nextHouse = nums[nums.length-1];
        int nextnextHouse = 0;
        
        for(int i=nums.length-2; i>=0; --i){
            int current = Math.max(nextHouse, nextnextHouse+nums[i]);
            
            nextnextHouse = nextHouse;
            nextHouse = current;
        }
        
        return nextHouse;
        
    }
}
