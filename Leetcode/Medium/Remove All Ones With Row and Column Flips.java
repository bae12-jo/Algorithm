class Solution {
    public boolean removeOnes(int[][] grid) {
        
        if(grid.length==0 || grid[0].length==0) return true;
        
        for(int i=1; i<grid.length; i++){
            if(!reversable(grid[0], grid[i])) return false;
        }
        
        return true;
    }
    
    public boolean reversable(int[] a, int[] b){
        int count = 0;
        for(int i=0; i<a.length; i++){
            if(a[i]!=b[i]) count++;
        }
        return count==0 || count==a.length;
    }
}
