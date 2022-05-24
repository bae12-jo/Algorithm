class Solution {
    public int uniquePaths(int m, int n) {
        
        // 내려갈 수 있는 횟수와 오른쪽으로 이동할 수 있는 횟수는 모든 경로에서 동일함 2중 포문
        // base case는 출발점으로 두고 1 way로 채우기, 목적지에서 답이 모이는 구조
        
        // base case
        int[][] ways = new int[m][n];
        for(int[] way: ways) Arrays.fill(way, 1); // right only and down only has one case each
        
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                ways[i][j] = ways[i-1][j] + ways[i][j-1];
            }
        }
        
        return ways[m-1][n-1];
        
        
    }
}
