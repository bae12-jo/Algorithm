class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        
        // duplicated elements of seqs have to occur once -> to make shortest
        // only superseq.. supersequence can contains number which is not in any seqs... that's tricky to think at first
        // which means nums of supersequences have to same with num of unique elements from all seqs 
        // even though there's duplicate elements in seqs, if order of elements is reverse, it can not make nums.. that's another tricky point
        
        if(sequences==null || sequences.size()==0) return false;
        int[] order = new int[nums.length+1]; // index 0 is 0, to store index of each value in nums
        for(int i=0; i<nums.length; i++){
            // if there is bigger num which is not in seq, or 0 return false
            if(nums[i]<=0 || nums[i]>nums.length) return false;
            order[nums[i]]=i;
        }
        
        boolean pairs[] = new boolean[nums.length]; // check each element in nums have pairs with any elements in seqs
        
        for(List<Integer> seq: sequences){
            for(int i=0; i<seq.size(); i++){
                if(seq.get(i)<=0 || seq.get(i)>nums.length) return false; // not in range of nums.length
                if(i==0 && seq.get(i)==nums[0]) pairs[0] = true; // for first index of nums
                if(i>0 && order[seq.get(i-1)]>=order[seq.get(i)]) return false; // when it's not same relative order with nums
                if(i>0 && order[seq.get(i-1)]+1==order[seq.get(i)]) pairs[order[seq.get(i)]] = true;
                
            }
        }
        
        for(boolean p: pairs) if(!p) return false; // check if there is no corresponding nums with any seqs
        
        return true;        
        
    }
}
