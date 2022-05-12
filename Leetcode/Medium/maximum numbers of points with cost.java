class Solution {
    public long maxPoints(int[][] points) {
        
        int n = points.length, m = points[0].length;
        if(n==0) return 0;
        
        long[] prev = new long[m], curr = new long[m];
        
        
        for(int i=0; i<n; ++i){
            long max = 0;
            for(int j=0; j<m; ++j){ // 왼->오; 같은 열의 직전 값과 다른 열의 값 중 큰 것을 구하기
                max = Math.max(max-1, prev[j]);
                curr[j] = max;
            }
            
            for(int j=m-1; j>=0; j--){ // 왼<-오; 같은 열의 직전 값과 다른 열의 값 중 큰 것을 구하기
                max = Math.max(max-1, prev[j]);
                curr[j] = Math.max(max, curr[j]) + points[i][j]; // 직전 열에서 가져온 최대 값과 현재 위치의 값을 더하기
            }
            
            prev = curr;
        }
        
        // find maximum result
        long ans = -1;
        for(int i=0; i<m; i++) ans = Math.max(ans, curr[i]);
        return ans;        
    }
}
