class Solution {
    
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        
        List<Integer> result = new ArrayList<>();
        
        // base case (when k is same with lenght of given array)
        if(arr.length == k){
            for(int i=0; i<k; ++i){
                result.add(arr[i]);
            }
            return result;
        }
                
        
        // find closest element using binary search (근접한 두 값 중에서는 큰 값으로 설정됨)
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(arr[mid]>=x) right = mid;
            else left = mid+1;
        }
        
        
        // initilalize our sliding window's bounds
        // left and right pointer are set as same value and they are closest number to x
        // but pointer always have bigger one between two cloest numbers to x
        // so left should be extend for edge cases.
        left--;
        
        // sliding window size : right-left-1
        // the reason why < is, to remove leftmost and rightmost index to get answer
        // because left or right pointer can not be in answer list in the end
        while(right-left-1 < k){ 
            
            if(left==-1){
                right++;
                continue;
            }
            
            if(right==arr.length || Math.abs(arr[left]-x) <= Math.abs(arr[right]-x)){
                left--;
            }else if(left==-1 || Math.abs(arr[left]-x) > Math.abs(arr[right]-x)){
                right++;
            }
        }
        
        
        // Build and return the window
        for(int i=left+1; i<right; ++i){
            result.add(arr[i]);
        }
        
        
        return result;
        
    }
}
