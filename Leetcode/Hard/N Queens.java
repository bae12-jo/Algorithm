/* using hashset */

class Solution {
    
    private List<List<String>> result = new ArrayList<List<String>>();
    private HashSet<Integer> cols = new HashSet<>();
    private HashSet<Integer> diagonals = new HashSet<>();
    private HashSet<Integer> antiDiagonals = new HashSet<>();
    
    private boolean isPossible(int col, int currDiagonal, int currAntiDiagonal){
        if(cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) return false;
        return true;        
    }
    
    private List<String> charToStrList(char[][] board, int size){
        List<String> list = new ArrayList<String>();
        for(int i=0; i<size; ++i) list.add(new String(board[i]));
        return list;
    }
    
    private void backtrack(int size, int depth, char[][] chessBoard){
        
        // Base case - N Queens have been placed
        if(depth==size){ // 체스 한 판이 완성된 상태
            result.add(charToStrList(chessBoard, size));
            return;
        }
        
        for(int col=0; col<size; ++col){
            int currDiagonal = depth-col; // x-y
            int currAntiDiagonal = depth+col; // x+y
            
            if(!isPossible(col, currDiagonal, currAntiDiagonal)) continue;
            
            // before backtrack
            cols.add(col);
            diagonals.add(currDiagonal);
            antiDiagonals.add(currAntiDiagonal);
            chessBoard[depth][col] = 'Q';
            
            backtrack(size, depth+1, chessBoard);
            
            // after backtrack
            cols.remove(col);
            diagonals.remove(currDiagonal);
            antiDiagonals.remove(currAntiDiagonal);
            chessBoard[depth][col] = '.';
        }
    }
    
    
    public List<List<String>> solveNQueens(int n) {
        
        char chessBoard[][] = new char[n][n];
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                chessBoard[i][j]='.';
            }
        }
        
        backtrack(n, 0, chessBoard);
        
        return result;        
        
    }
}

/* using boolean array */

// backtracking

class Solution {
    
    private List<List<String>> result = new ArrayList<List<String>>();
    private char[][] chessBoard;
    private boolean[] cols;
    private boolean[] diagonals;
    private boolean[] antiDiagonals;
    
    // dfs with backtracking
    public void dfs(int row, int n){
        
        // base condition
        if(row==n){
            List<String> list = new ArrayList<>();            
            for(int i=0; i<n; ++i){
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<n; ++j){
                    sb.append(chessBoard[i][j]);   
                }
                list.add(sb.toString());
            }
            
            result.add(list);
            return;
        }
        
        for(int col=0; col<n; ++col){
            
            if(!cols[col] && !diagonals[row-col+n] && !antiDiagonals[row+col]){
                
                // before tracking
                cols[col] = diagonals[row-col+n] = antiDiagonals[row+col] = true;
                chessBoard[row][col] = 'Q';
                
                dfs(row+1, n);
                
                // after tracking
                cols[col] = diagonals[row-col+n] = antiDiagonals[row+col] = false;
                chessBoard[row][col] = '.';
            }
            
        }
        
    }
        
    public List<List<String>> solveNQueens(int n) {
        
        cols = new boolean[n];
        diagonals = new boolean[20];
        antiDiagonals = new boolean[20];
        chessBoard = new char[n][n];
        
        for(int i=0; i<n; ++i){
            for(int j=0; j<n; ++j){
                chessBoard[i][j] = '.';
            }
        }
        
        dfs(0, n);
        
        return result;
        
    }
}
