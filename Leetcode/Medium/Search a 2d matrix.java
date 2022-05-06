/*
* sorted n*m matrix -> n*m array
*/


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int n = matrix[0].length; // col
        int m = matrix.length; // row
        int start = 0, end = n*m-1;
        
        if(n==0) return false;
        
        while(start<=end){
            
            int mid = (start+end)/2;
            int pivotValue = matrix[mid/n][mid%n];
            if(pivotValue==target) return true;
            else{
                if(pivotValue>target) end = mid-1;
                else start = mid+1;
            }
        }
        
        return false;
        
    }
}
