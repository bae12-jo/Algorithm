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


/* 글로벌 필드 없는 버전 */

class Solution {
    
    public void getCombine(int n, int k, int start, List<List<Integer>> res, List<Integer> list){
        
        // base case
        if(list.size()==k){
            res.add(new ArrayList(list));
            return;
        }
        
        for(int i=start; i<=n+1-(k-list.size()); ++i){ // n 대신 동적 범위를 주는 것이 성능 향상에 큰 도움이 됨
            list.add(i);
            getCombine(n, k, i+1, res, list);
            list.remove(list.size()-1);
        }
        
    }
    
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        getCombine(n, k, 1, res, new ArrayList());
        
        return res;
        
    }
}
    
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        getCombine(n, k, 1, res, list);
        
        return res;
        
    }
}
