class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        
        // base case
        int totalSum = 0, maxSum = nums[0], minSum = nums[0], tmpMax = 0, tmpMin = 0;
        
        for(int n: nums){
            tmpMax = Math.max(tmpMax+n, n);
            tmpMin = Math.min(tmpMin+n, n);
            maxSum = Math.max(tmpMax, maxSum);
            minSum = Math.min(tmpMin, minSum);
            totalSum += n;
        }
        
        // corner case - if maxSum is negative, all num of arr is negative
        return maxSum > 0 ? Math.max(maxSum, totalSum-minSum) : maxSum;
        
    }
}
