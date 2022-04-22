class Solution {
    
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    private void getCombine(int n, int k, int[] list, int idx, int start){
        
        if(idx==k){
            List<Integer> newList = new ArrayList<Integer>();
            for(int i=0; i<k; ++i){
                newList.add(list[i]);
            }
            result.add(newList);
            return;
        }
        
 
        for(int i=start; i<=n; ++i){
            list[idx] = i;
            getCombine(n, k, list, idx+1, i+1);
        }
        
    }
    
    public List<List<Integer>> combine(int n, int k) {
        int[] list = new int[k];
        
        getCombine(n, k, list, 0, 1);
        
        return result;
        
    }
}
