/*
* leftmost와 rightmost를 각각 구하는 방법
*/

class Solution {
    
    private int findLeftmost(int[] nums, int target){
        
        int start = 0, end = nums.length-1;
        int leftmost = -1;
        
        while(start<=end){
            int pivot = (start+end)/2;
            if(nums[pivot]>target) end = pivot-1;
            else if(nums[pivot]<target) start = pivot+1;
            else{
                leftmost = pivot; // save potential leftmost
                end = pivot-1;
            }
        }
        
        return leftmost;      
    }
    
    private int findRightmost(int[] nums, int target){
        
        int start = 0, end = nums.length-1;
        int rightmost = -1;
        
        while(start<=end){
            int pivot = (start+end)/2;
            if(nums[pivot]>target) end = pivot-1;
            else if(nums[pivot]<target) start = pivot+1;
            else{
                rightmost = pivot; // save potentail rightmost
                start = pivot+1;
            }
        }
        
        return rightmost;
    }
    
    public int[] searchRange(int[] nums, int target) {
        
        // edge case - there is empty array
        if(nums.length==0) return new int[]{-1, -1};
        
        // find leftmost index
        int left = findLeftmost(nums, target);
        
        // if there is no element same with target
        if(left==-1) return new int[]{-1, -1};
        
        // find rightmost index
        int right = findRightmost(nums, target);
        
        // return all.
        return new int[] {left, right};

    }
}
