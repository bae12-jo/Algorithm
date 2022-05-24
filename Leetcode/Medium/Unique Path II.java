class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        int width = obstacleGrid[0].length;
        int[] ways = new int[width];
        
        // edge case
        if(obstacleGrid[0][0]==1) return 0;
        
        // base case
        ways[0] = 1;
        
        for(int[] row: obstacleGrid){
            for(int i=0; i<width; i++){
                if(row[i]==1) ways[i]=0;
                else if(i>0) ways[i] += ways[i-1];
            }
        }
        
        return ways[width-1];
    }
}
