class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        int N = 9;
        
        boolean[][] rows = new boolean[N][N];
        boolean[][] cols = new boolean[N][N];
        boolean[][] boxes = new boolean[N][N];
        
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(board[r][c]=='.') continue;
    
                int pos = board[r][c]-'1'; // get nums as 0-indexes
                
                // check rows
                if(rows[r][pos]) return false;
                rows[r][pos]=true;
                
                // check cols
                if(cols[c][pos]) return false;
                cols[c][pos]=true;
                
                // check box
                int idx = (r/3)*3 + c/3;
                if(boxes[idx][pos]) return false;
                boxes[idx][pos]=true;
            }
        }
        
        return true;
    }
}
