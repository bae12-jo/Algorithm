class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int length = nums.length+1; // unreachable value
        int idx = 0;
        
        for(int i=0; i<nums.length; i++){
            
            target -= nums[i];
            
            while(target<=0){
                length = Math.min(length, i-idx+1);
                target += nums[idx++];
            }
            
        }
        
        return length % (nums.length+1); // if it has a init value, return 0
        
    }
}
