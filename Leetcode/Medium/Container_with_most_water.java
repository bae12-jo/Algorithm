/* Brute Force(1) Time Limit Exceeded */

class Solution {
    
    public int maxLocalArea(int[] height, int end){
        
        int area = 0;
        int width = 1;
        for(int i=end-1; i>=0; i--){
            int top = Math.min(height[end], height[i]);
            area = Math.max(area, top*width);
            width++;
        }
        // System.out.println(area);
        return area;
        
    }
    
    public int maxArea(int[] height) {
        
        Stack<Integer> stack = new Stack<>();
        int local_area = 0;
        int max_area = 0 ;
        
        for(int i=1; i<height.length; i++){
            local_area = maxLocalArea(height, i);
            max_area = Math.max(local_area, max_area);
        }
        return max_area;
    }
}

/* Brute Force(2) Time Limit Exceeded */
class Solution {

    public int maxArea(int[] height) {
        int max_area = 0 ;
        
        for(int i=0; i<height.length; i++){
            for(int j=i+1; j<height.length; j++){
                max_area = Math.max(max_area, Math.min(height[i], height[j])*(j-i));
            }

        }
        return max_area;
    }
}

/* Two Pointer */
class Solution {

    public int maxArea(int[] height) {
        
        int start = 0, end = height.length-1;
        int maxArea = 0;
        
        while(start<end){
            int top = Math.min(height[start], height[end]);
            maxArea = Math.max(maxArea, top*(end-start));
            
            if(height[start]>height[end]){
                end--;
            }else if(height[start]<height[end]){
                start++;
            }else{
                if(height[start+1]>height[end-1]){
                    start++;
                }else{
                    end--;
                }
            }
        }
        
        return maxArea;

        
    }
}


