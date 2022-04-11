//iterative

class Solution {
    
    public int search(int[] nums, int target) {
        
        int l = 0;
        int r = nums.length-1;
        
        while(l<=r){
            
            int mid = l + (r-l)/2;
            if(nums[mid]==target) return mid;
            
            if(nums[l] <= nums[mid]){
                if(nums[l]<=target && target<nums[mid]) r = mid-1;
                else l = mid+1;
            }else{
                if(nums[mid]<target && nums[r]>=target) l = mid+1;
                else r = mid-1;
            }
            
        }
        
        return -1;
        
    }
}


//recursive

class Solution {
    
    public int BinarySearch(int[] nums, int l, int r, int target){
        
        if(l>r) return -1;
        
        int mid = (l+r) / 2;
        
        if(nums[mid]==target) return mid;
        
        if(nums[l] <= nums[mid]){
            if(target>=nums[l] && target<=nums[mid]) return BinarySearch(nums, l, mid-1, target);
            return BinarySearch(nums, mid+1, r, target);
        }
        
        // nums[l] > nums[mid]
        if(target>=nums[mid] && target<=nums[r]) return BinarySearch(nums, mid+1, r, target);
        return BinarySearch(nums, l, mid-1, target);
         
    }
    
    public int search(int[] nums, int target) {
        
        return BinarySearch(nums, 0, nums.length-1, target);
        
    }
}
