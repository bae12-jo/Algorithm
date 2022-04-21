# Back Tracking

Backtracking is a general algorithm for finding all (or some) solutions to some computational problems (notably Constraint satisfaction problems or CSPs), which incrementally builds candidates to the solution and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot lead to a valid solution.

classic examples: Tree traversal, N-Queen, Sudoku problem

```python
def backtrack(candidate):
    if find_solution(candidate):
        output(candidate)
        return
    
    # iterate all possible candidates.
    for next_candidate in list_of_candidates:
        if is_valid(next_candidate):
            # try this partial candidate solution
            place(next_candidate)
            # given the candidate, explore further.
            backtrack(next_candidate)
            # backtrack
            remove(next_candidate)
```

## N-Queens Problem

The N-queens puzzle is the problem of placing N{N}N queens on an [N×N][N \times N][N×N] chessboard such that no two queens can attack each other. One is asked to count the number of solutions to place the N{N}N queens on the board.

```java
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
                
                // try partial
                cols[col] = diagonals[row-col+n] = antiDiagonals[row+col] = true;
                chessBoard[row][col] = 'Q';
                
                // explore further
                dfs(row+1, n);
                
                // back track
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
```
