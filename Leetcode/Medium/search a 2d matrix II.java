/* Divide and conquer
* Time: O(n log n)
* Space: O(log n)
*/

class Solution {
    
    private boolean searchRectangle(int[][] matrix, int x1, int y1, int x2, int y2, int target){
        
        if(x1>x2 || y1>y2) return false;
        
        if(target < matrix[x1][y1] || target > matrix[x2][y2]) return false;
        
        int mid = y1 + (y2 - y1) / 2;
        
        int rowIdx = x1;
        while(rowIdx <= x2 && matrix[rowIdx][mid] <= target){
            if(matrix[rowIdx][mid]==target) return true;
            rowIdx++;
        }
        
        return searchRectangle(matrix, rowIdx, y1, x2, mid-1, target) || searchRectangle(matrix, x1, mid+1, rowIdx-1, y2, target);
        
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        
        if(matrix.length==0) return false;
        
        return searchRectangle(matrix, 0, 0, matrix.length-1, matrix[0].length-1, target);
        
    }
}

/* Search Space Reduction
* Time: O(n+m)
* Space: O(1)
*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int row = matrix.length-1;
        int col = 0;
        
        while(row>=0 && col<=matrix[0].length-1){
            if(matrix[row][col]<target) col++;
            else if(matrix[row][col]>target) row--;
            else return true;
        }
        
        return false;
        
    }
}
