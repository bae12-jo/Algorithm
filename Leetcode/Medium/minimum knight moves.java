class Solution {
    
    // cache
    private Map<String, Integer> cache = new HashMap<>(); // coordinate, step
    
    private int dfs(int x, int y){
        
        String key = x+","+y;
        
        // there is cached data, return it
        if(cache.containsKey(key)) return cache.get(key);
        
        // when it's start point -> base case
        if(x+y==0) return 0;
        // when it's close neighbor (takes 2 steps to reach)
        else if(x+y==2) return 2;
        // when it's reachable point by only 1 steps
        else{
            int step = Math.min(dfs(Math.abs(x-1), Math.abs(y-2)), dfs(Math.abs(x-2), Math.abs(y-1))) + 1;
            cache.put(key, step);
            return step;
        }       
        
    }
    
    
    
    public int minKnightMoves(int x, int y) {        
        
        return dfs(Math.abs(x), Math.abs(y));
        
    }
}
