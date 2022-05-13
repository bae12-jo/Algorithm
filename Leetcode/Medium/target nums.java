class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        
        // get max sum from nums
        int maxSum = Arrays.stream(nums).sum();
        
        // edge case
        if(Math.abs(target)>maxSum) return 0;
        
        // init dp
        int[] dp = new int[maxSum*2+1];
        
        // real sum : -3 -2 -1  0  1  2  3
        // dp idx   :  0  1  2  3  4  5  6
        
        dp[maxSum+nums[0]] = 1;
        dp[maxSum-nums[0]] += 1;
        
        for(int i=1; i<nums.length; ++i){ // idx
            int[] next = new int[maxSum*2+1]; // to reduce 1D, use temp array
            for(int sum = -maxSum; sum<=maxSum; sum++){// result of expression
                if(dp[sum+maxSum]>0){// if there is valid value
                    next[sum+maxSum+nums[i]] += dp[sum+maxSum];
                    next[sum+maxSum-nums[i]] += dp[sum+maxSum];
                }
            }
            dp = next;
        }
        
        return dp[target+maxSum];
        
    }
}
