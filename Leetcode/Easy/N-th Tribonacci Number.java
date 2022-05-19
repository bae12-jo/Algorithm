class Solution {
    public int tribonacci(int n) {
        
        int[] cache = new int[n+1];
        Arrays.fill(cache, -1);
        return tribonacci(cache, n);
    }
    
    private int tribonacci(int[] cache, int n){
        
        // base case
        if(n==0) return 0;
        if(n==1 || n==2) return 1;
       
        // memoization
        if(cache[n]>-1) return cache[n];        
        
        // recurrence relations
        cache[n] = tribonacci(cache, n-1)+tribonacci(cache, n-2)+tribonacci(cache, n-3);
                        
        return cache[n];
    }
}
