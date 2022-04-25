class Solution {
    
    // int[] unsorted array
    // there can be several sequence
    // return maximum (empty array: 0 or min: 1)
    
    public int longestConsecutive(int[] nums) {
        
        // check range
        if(nums.length==0) return 0;
        
        
        HashSet<Integer> set = new HashSet<>();
        // put all e into set - O(n)
        for(int num: nums) set.add(num);    
        
        int maxL = 0;
        
        // get a element one by one -> O(n)
        for(int num: set){
        // if find e-1 in set -> continue;
            if(!set.contains(num-1)){
                int curr = num; // first element of sequence
                int length = 1;
                
                // find last element of sequence
                while(set.contains(curr+1)){
                    curr++;
                    length++;
                }
                
                // find longest each time
                maxL = Math.max(maxL, length);
            }

        }
        
        return maxL;
    }
}
