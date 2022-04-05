// without global fields

class Solution {
    
    public boolean DFS(int[][] grid, int x, int y){
        
        if(x<0 || x>=grid.length || y<0 || y>=grid[0].length){
            return false;
        }
        
        if(grid[x][y]==1){
            return true;
        }
        
        grid[x][y] = 1;
        
        return DFS(grid, x+1, y) & DFS(grid, x-1, y) & DFS(grid, x, y+1) & DFS(grid, x, y-1);
    }
        
    
    public int closedIsland(int[][] grid) {
        
        int count = 0;
        
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[0].length; ++j){
                if(grid[i][j]==0 && DFS(grid, i, j)){
                    count++;
                }
            }
        }
        
        return count;
    }
}


// with global fields

class Solution {
    
    static int[][] D = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean isClosedIsland;
    
    public void DFS(int[][] grid, int x, int y){
        
        if(x<0 || x>=grid.length || y<0 || y>=grid[0].length){
            isClosedIsland = false;
            return;
        }
        
        if(grid[x][y]==1){
            return;
        }
        
        while(grid[x][y] == 0){
            grid[x][y]=1;
            for(int i=0; i<4; ++i){
                int nx = x + D[i][0];
                int ny = y + D[i][1];
                DFS(grid, nx, ny);
            }
        }
        
    }
    
    public int closedIsland(int[][] grid) {
        
        int count = 0;
        
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[0].length; ++j){
                if(grid[i][j]==0){
                    DFS(grid, i, j);
                    if(isClosedIsland){
                        count++;
                    }else{
                        isClosedIsland = true;
                    }
                }
            }
        }
        
        return count;
    }
}
