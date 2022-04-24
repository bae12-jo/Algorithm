class Solution {
    
    
    private void backtrack(int[] candidates, List<List<Integer>> res, LinkedList<Integer> buffer, int start, int remain){
        
        //base case
        if(remain == 0){
            res.add(new ArrayList<Integer>(buffer));
            return;
        }else if(remain < 0){
            return;
        }
        
        //backtrack
        for(int i=start; i<candidates.length; ++i){
            buffer.add(candidates[i]);
            backtrack(candidates, res, buffer, i, remain-candidates[i]);
            buffer.removeLast();
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<Integer> buffer = new LinkedList<Integer>();
        
        // candidates, res, buffer, remain, target
        backtrack(candidates, res, buffer, 0, target);
        
        return res;
    }
}
