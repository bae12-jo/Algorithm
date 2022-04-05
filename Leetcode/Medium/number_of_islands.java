class Solution {
    
    static int[][] D = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public void DFS(char[][] grid, int x, int y){
        
        if(x<0 || x>=grid.length || y<0 || y>=grid[0].length || grid[x][y]=='0') return;
        while(grid[x][y]=='1'){
            grid[x][y] = '0';
            for(int i=0; i<4; ++i){
                int nx = x + D[i][0];
                int ny = y + D[i][1];
                DFS(grid, nx, ny);
            }
        }
    }
    
    public int numIslands(char[][] grid) {
        
        int count = 0;
        
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[0].length; ++j){
                if(grid[i][j]=='1') {
                    DFS(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
        
    }
}
