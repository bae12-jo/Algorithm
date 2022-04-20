/* (1) Dynamic Programming, Kadane's Algorithm 
* Time: O(n)
* Space : O(1)
*/

class Solution {
    public int maxSubArray(int[] nums) {
        
        // initalize using the first element
        int currSub = nums[0];
        int maxSub = nums[0];
        
        // start with the 2nd element
        for(int i=1; i<nums.length; ++i){
            // drop negative element which can make sum less than previous sum
            // currSub cumulative sum from first positive number which doesn't make sum negative
            currSub = Math.max(nums[i], currSub+nums[i]);
            // store maxSub at each iteration
            maxSub = Math.max(currSub, maxSub);
        }
        
        return maxSub;
    }
}

/* (2) Divide and Conquer
* Time: O(n+m)
* Space O(1)
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int row = matrix.length-1;
        int col = 0;
        
        while(row>=0 && col<=matrix[0].length-1){
            if(matrix[row][col]<target) col++;
            else if(matrix[row][col]>target) row--;
            else return true;
        }
        
        return false;
        
    }
}
