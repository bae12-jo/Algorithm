class Solution:
    def trap(self, height: List[int]) -> int:
        
        # handle exception
        if not height:
            return 0
        
        # initailize variables
        volume = 0
        left, right = 0, len(height)-1
        left_max, right_max = height[left], height[right]
        
         
        # set end-condition
        while left < right:
            left_max, right_max = max(height[left], left_max), max(height[right], right_max)
            
            # move two-pointer to the heightest
            if left_max <= right_max:
                volume += left_max - height[left]
                left += 1
            else:
                volume += right_max - height[right]
                right -=1
        
        return volume
