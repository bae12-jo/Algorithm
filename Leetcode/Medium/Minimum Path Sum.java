class Solution {
    public int minPathSum(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;
        
        // base case
        int[] ways = new int[width];

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                if(i==0 && j==0) ways[j] = grid[0][0];
                else if(i==0) ways[j] = ways[j-1]+grid[0][j];
                else if(j==0) ways[j] = ways[j] + grid[i][0];
                else ways[j] = Math.min(ways[j], ways[j-1]) + grid[i][j];
            }
        }        
        
        return ways[width-1];
        
    }
}
