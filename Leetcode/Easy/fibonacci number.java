/* (1) Recursion
* Time: O(2^N) -- 2 power n, 2 to the n,
* Space: O(N) 
*/
class Solution {
    public int fib(int n) {
        
        if(n==0) return 0;
        if(n==1) return 1;
        return fib(n-1) + fib(n-2);
        
    }
}

/* (2) Bottom-up Approach using tabulation
* Time: O(N)
* Space: O(N)
* tabulation은 bottom-up 접근법에서 사용되는 최적화 방식으로 도표를 작성하듯이 모든 값을 구한 후, 마지막에 원하는 값을 질의한다
*/

class Solution {
    public int fib(int n) {
        
        if(n<=1) return n;
        int[] cache = new int[n+1];
        cache[1] = 1;
        for(int i=2; i<=n; i++){
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[n];
        
    }
}



/* (3) Top-down approach using memoization
* Time: O(n)
* Space: O(n)
* memoization은 top-down 방식에서 사용되는 최적화 방식으로 원하는 결과값에 먼저 접근하고 구할 수 없다면 연산에 필요한 다른 값을 찾아나가는 방식이다
*/

class Solution {
    
    private HashMap<Integer, Integer> cache = new HashMap<>(Map.of(0, 0, 1, 1));
    
    public int fib(int n) {
        if(cache.containsKey(n)) return cache.get(n);
        cache.put(n, fib(n-1) + fib(n-2));
        return cache.get(n);        
    }
}

/* (4) iterative bottom-up
* Time: O(n)
* Space: O(1)
*/

class Solution {
    public int fib(int n) {
        
        if(n<=1) return n;
        
        int curr = 0;
        int prev1 = 1; // n-1
        int prev2 = 0; // n-2
        
        for(int i=2; i<=n; ++i){
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return curr;
        
    }
}
