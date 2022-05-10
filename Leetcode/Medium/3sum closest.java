class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        // diff target-3sum
        int diff = Integer.MAX_VALUE;
        
        // binary search with 3 pointers
        Arrays.sort(nums);
        for(int base=0; base<nums.length; ++base){
            
            // end case
            if(diff==0) break;
            
            // define two pointers
            int left = base+1;
            int right = nums.length-1;
            
            while(left<right){
                int tmpSum = nums[base]+nums[left]+nums[right];
                
                // update smallest diff
                if(Math.abs(target-tmpSum)<Math.abs(diff)) diff = target-tmpSum;
                
                if(tmpSum<target) ++left;
                else --right;
            }
        }
        
        return target-diff;
        
    }
}
