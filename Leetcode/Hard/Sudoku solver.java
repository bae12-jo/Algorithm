/* array */

class Solution {
  // box size
  int n = 3;
  // row size
  int N = n * n;

  int [][] rows = new int[N][N + 1];
  int [][] columns = new int[N][N + 1];
  int [][] boxes = new int[N][N + 1];

  char[][] board;

  boolean sudokuSolved = false;

  public boolean couldPlace(int d, int row, int col) {
    /*
    Check if one could place a number d in (row, col) cell
    */
    int idx = (row / n ) * n + col / n;
    return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
  }

  public void placeNumber(int d, int row, int col) {
    /*
    Place a number d in (row, col) cell
    */
    int idx = (row / n ) * n + col / n;

    rows[row][d]++;
    columns[col][d]++;
    boxes[idx][d]++;
    board[row][col] = (char)(d + '0');
  }

  public void removeNumber(int d, int row, int col) {
    /*
    Remove a number which didn't lead to a solution
    */
    int idx = (row / n ) * n + col / n;
    rows[row][d]--;
    columns[col][d]--;
    boxes[idx][d]--;
    board[row][col] = '.';
  }

  public void placeNextNumbers(int row, int col) {
    /*
    Call backtrack function in recursion
    to continue to place numbers
    till the moment we have a solution
    */
    // if we're in the last cell
    // that means we have the solution
    if ((col == N - 1) && (row == N - 1)) {
      sudokuSolved = true;
    }
    // if not yet
    else {
      // if we're in the end of the row
      // go to the next row
      if (col == N - 1) backtrack(row + 1, 0);
        // go to the next column
      else backtrack(row, col + 1);
    }
  }

  public void backtrack(int row, int col) {
    /*
    Backtracking
    */
    // if the cell is empty
    if (board[row][col] == '.') {
      // iterate over all numbers from 1 to 9
      for (int d = 1; d < 10; d++) {
        if (couldPlace(d, row, col)) {
          placeNumber(d, row, col);
          placeNextNumbers(row, col);
          // if sudoku is solved, there is no need to backtrack
          // since the single unique solution is promised
          if (!sudokuSolved) removeNumber(d, row, col);
        }
      }
    }
    else placeNextNumbers(row, col);
  }

  public void solveSudoku(char[][] board) {
    this.board = board;

    // init rows, columns and boxes
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        char num = board[i][j];
        if (num != '.') {
          int d = Character.getNumericValue(num);
          placeNumber(d, i, j);
        }
      }
    }
    backtrack(0, 0);
  }
}



/* boolean array */

// 왜 hashset은 안 쓰는가? pair를 contains 하려면 클래스를 별도로 구현해야 하는데 너무 시간이 오래 걸려서 interview에서는 적합하지 않음


class Solution {
    
    //HashSet<Pair<Integer, Integer>> rows = new HashSet<Pair<Integer, Integer>>();
    //HashSet<Pair<Integer, Integer>> cols = new HashSet<Pair<Integer, Integer>>();
    //HashSet<Pair<Integer, Integer>> boxes = new HashSet<Pair<Integer, Integer>>();
    boolean[][] rows;
    boolean[][] cols;
    boolean[][] boxes;
    
    private void checkAssigned(char[][] board){
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board[0].length; ++j){
                if(board[i][j]!='.'){
                    int num = (int)(board[i][j]-'0');
                    //System.out.println(num);
                    //rows.add(new Pair(i, num));
                    //cols.add(new Pair(j, num));
                    //boxes.add(new Pair((i/3)*3+(j/3), num));
                    rows[i][num] = true;
                    cols[j][num] = true;
                    boxes[(i/3)*3+(j/3)][num] = true;
                }
            }
        }
    }
        
    // find empty slot to assign new number
    private Pair<Integer, Integer> findUnassigned(char[][] board){
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board[0].length; ++j){
                if(board[i][j]=='.') return new Pair(i, j);
            }
        }
        return new Pair(-1, -1);
    }
    
    // check if num can be placed at board[i][j]
    private boolean isPossible(int row, int col, int num, char[][] board){

        
        if(rows[row][num]) return false;
        if(cols[col][num]) return false;
        if(boxes[(row/3)*3+(col/3)][num]) return false;
        //System.out.println(row + " " + col + " " + ((row/3)*3+(col/3)) + "yes");
        return true;
    }
    
    private boolean solve(char[][] board){
        Pair<Integer, Integer> target = findUnassigned(board);
        
        int row = target.getKey();
        int col = target.getValue();
        
        if(row==-1 && col==-1) return true;
        
        for(int i=1; i<=9; ++i){
            //System.out.print(num);
            if(isPossible(row, col, i, board)){
                board[row][col] = (char)(i+'0');
                rows[row][i] = true;
                cols[col][i] = true;
                boxes[(row/3)*3+(col/3)][i] = true;
                
                if(solve(board)) return true;
                
                board[row][col] = '.';
                rows[row][i] = false;
                cols[col][i] = false;
                boxes[(row/3)*3+(col/3)][i] = false;
                
            }
        }
        
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        
        int n = board.length;
        rows = new boolean[n][n+1];
        cols = new boolean[n][n+1];
        boxes = new boolean[n][n+1];
        
        checkAssigned(board);
        solve(board);
        
    }
}



/* without extra space */

// 구현하기는 좋은 솔루션인데 런타임이 불규칙함 (for문을 매번 돌리기 때문인 듯)

class Solution {
    
    
    // find empty slot to assign new number
    private Pair<Integer, Integer> findUnassigned(char[][] board){
        for(int i=0; i<board.length; ++i){
            for(int j=0; j<board[0].length; ++j){
                if(board[i][j]=='.') return new Pair(i, j);
            }
        }
        return new Pair(-1, -1);
    }
    
    // check if num can be placed at board[i][j]
    private boolean isPossible(int row, int col, char num, char[][] board){
        for(int i=0; i<9; ++i){
            //System.out.println(num + " " + board[i][col] + " " + board[row][i]);
            if(num==board[i][col]) return false;
            if(num==board[row][i]) return false;
        }
        
        int boxRow = (row/3)*3;
        int boxCol = (col/3)*3;
        
        for(int i=boxRow; i<boxRow+3; ++i){
            for(int j=boxCol; j<boxCol+3; ++j){
                //System.out.println(num + " " + board[i][j]);
                if(num==board[i][j]) return false;
            }
        }
        
        return true;
    }
    
    private boolean solve(char[][] board){
        Pair<Integer, Integer> target = findUnassigned(board);
        
        int row = target.getKey();
        int col = target.getValue();
        
        if(row==-1 && col==-1) return true;
        
        for(int i=1; i<=9; ++i){
            char num = (char)(i+'0');
            //System.out.print(num);
            if(isPossible(row, col, num, board)){
                board[row][col] = num;
                if(solve(board)) return true;
                board[row][col] = '.';
            }
        }
        
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        
        solve(board);
        
    }
}
