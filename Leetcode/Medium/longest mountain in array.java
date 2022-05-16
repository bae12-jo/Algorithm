class Solution {
    public int longestMountain(int[] arr) {
        
        if(arr.length<3) return 0;
        
        int N = arr.length;
        
        int maxLen = 0, base = 0;
        
        while(base<N){
            
            int end = base;
            
            // increasing
            if(end+1<N && arr[end]<arr[end+1]){
                while(end+1<N && arr[end]<arr[end+1]) end++;   
                
                // decreasing
                if(end+1<N && arr[end]>arr[end+1]){
                    while(end+1<N && arr[end]>arr[end+1]) end++;
                    
                    // end of decreasing
                    maxLen = Math.max(maxLen, end-base+1);
                }
            }
            
            base = Math.max(end, base+1);
            
        }
        
        return maxLen;        
        
    }
}
