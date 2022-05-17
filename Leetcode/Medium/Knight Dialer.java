class Solution {
    
    final int MOD = 1000000007;
    int[][] dir = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
    int count = 0;
    int[][] cache;
    
    public int knightDialer(int n) {
        
        cache = new int[n][dir.length];
        
        for(int i=0; i<10; i++){
            count = (count + countPos(n-1, i)) % MOD;
        }        
        
        return count;
    }
    
    private int countPos(int n, int src){
        
        if(n==0) return 1;
        
        if(cache[n][src]!=0) return cache[n][src];
        
        int cnt = 0;
        for(int dst: dir[src]){
            cnt = (cnt + countPos(n-1, dst)) % MOD;
        }
        
        cache[n][src] = cnt;
        
        return cnt;
        
    }
}
