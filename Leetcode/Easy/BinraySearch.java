class Solution {
    public int search(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length-1;
        
        //Arrays.sort(nums);
        
        while(left<=right){
            int mid = left + ((right - left) >> 1);
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target) {
                left=mid+1;
                mid=right;
            }else if(nums[mid]>target){
                right=mid-1;
            }
            
        }
        
        return -1;
        
    }
}
