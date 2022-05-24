// max 2 consecutive same color
// twoBack을 기억해야함

class Solution {
    public int numWays(int n, int k) {
        
        // base case - first post has k possibility, second post has k*k possibility
        
        // recurrence relation
        // change color -> k-1 * prev -> (k-1) * (i-1)
        // use same color -> 1 * prev only if (prev != prevprev) 1*(i-1)
        // change color -> (k-1)*(i-2)
        // formular i = (k-1) * ((i-1) + (i-2))
        
        // edge case
        if(n<3) return (int)Math.pow(k, n);
        
        // base case
        //int[] ways = new int[n+1];
        //ways[1] = k; // first post
        //ways[2] = k*k; // second post
        int twoBack = k; // first post
        int oneBack = k*k; // second post
        
        for(int i=2; i<n; i++){
            int tmp = oneBack;
            oneBack = (k-1) * (oneBack + twoBack);
            twoBack = tmp;
        }
        
        return oneBack;
        
    }
}
