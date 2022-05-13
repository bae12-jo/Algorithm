class Solution {
    
    private void cleanQueue(int i, int k){ // first one of queue is always biggest
        // remove index out of sliding window -> do once
        if(!dq.isEmpty() && dq.getFirst()==i-k) dq.removeFirst();
        // remove indexes smaller than now one - keep the biggest one -> do max k-1 time
        while(!dq.isEmpty() && nums[i] > nums[dq.getLast()]) dq.removeLast();
    }
    
    private ArrayDeque<Integer> dq = new ArrayDeque<Integer>(); // store index, not value
    private int[] nums;
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int N = nums.length;
        if(N*k==0) return new int[0];
        if(k==1) return nums;
        
        this.nums = nums;
        int maxIdx = 0;
        int maxValue[] = new int[N-(k-1)];
        
        // init deque
        for(int i=0; i<k; i++){
            
            cleanQueue(i, k);
            dq.addLast(i);
            
            if(nums[i] > nums[maxIdx]) maxIdx = i;
            
        }        
        maxValue[0] = nums[maxIdx];
        
        // fill out max array
        for(int i=k; i<N; ++i){
            cleanQueue(i, k);
            dq.addLast(i);
            maxValue[i-(k-1)] = nums[dq.getFirst()];
        }
        
        return maxValue;
        
    }
}
