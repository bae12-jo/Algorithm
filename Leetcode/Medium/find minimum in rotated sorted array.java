class Solution {
    public int findMin(int[] nums) {
        
        // edge case - there is only one element
        if(nums.length==1) return nums[0];
        
        // init pointers
        int l = 0, r = nums.length-1;
        
        // if there is no rotation
        // when last one is bigger than first one, it's not rotated
        if(nums[r]>nums[0]) return nums[0];
        
        // binary search
        while(l<=r){
            int mid = (l+r)/2;
            
            // if find start point
            if(nums[mid]>nums[mid+1]) return nums[mid+1];
            else if(nums[mid-1]>nums[mid]) return nums[mid];
            // in the middle of rotations
            else{
                if(nums[mid]>nums[0]) l=mid+1;
                else r=mid-1;
            }
            
        }
        
        return -1;
    }
}
