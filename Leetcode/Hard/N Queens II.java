class Solution {
    
    private boolean[] cols;
    private boolean[] diagonals;
    private boolean[] antiDiagonals;
    private int count = 0;
    
    private void dfs(int row, int n){
        //base case
        if(row==n){
            count++;
            return;
        }
        
        for(int col=0; col<n; ++col){
            if(!cols[col] && !diagonals[row-col+n] && !antiDiagonals[row+col]){
                cols[col] = diagonals[row-col+n] = antiDiagonals[row+col] = true;
                dfs(row+1, n);
                cols[col] = diagonals[row-col+n] = antiDiagonals[row+col] = false;
            }
        }
    }
    public int totalNQueens(int n) {
        
        cols=new boolean[n];
        diagonals=new boolean[2*n];
        antiDiagonals=new boolean[2*n];
        
        dfs(0, n);
        
        return count;
        
    }
}
