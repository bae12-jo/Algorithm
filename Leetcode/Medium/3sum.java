// (1) sort array + hashset
// 	165 ms	122.1 MB

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        Set<List<Integer>> result = new HashSet<>();
        
        if(nums.length<=2) return Collections.emptyList();
        
        Arrays.sort(nums);
        
        if(nums[0]>0) return Collections.emptyList();
        
        for(int i=0; i<nums.length-2; ++i){
            int base = nums[i];
            int left = i+1;
            int right = nums.length-1;
            
            if(nums[right]<0) return Collections.emptyList();
            
            while(left<right){
                
                int sum = base + nums[left] + nums[right];
                
                List<Integer> temp = new ArrayList<>();
                
                if(sum==0){
                    temp.add(base);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    right--;
                    left++;
                    while(nums[left]==nums[left-1] && left<right) left++;
                    while(nums[right]==nums[right+1] && left<right) right--;
                }
                if(sum<0) left++;
                if(sum>0) right--;
            }
        }
        
        return new ArrayList<>(result);
        
    }
}

// (2) sort array + arraylist
// 	31 ms	59.2 MB

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(nums.length<=2) return Collections.emptyList();
        
        Arrays.sort(nums);
        
        if(nums[0]>0) return Collections.emptyList();
        
        for(int i=0; i<nums.length-2; ++i){
            int base = nums[i];
    
            if(i>0 && base==nums[i-1]) continue;
            
            int left = i+1;
            int right = nums.length-1;
            
            if(nums[right]<0) return Collections.emptyList();
            
            while(left<right){
                
                int sum = base + nums[left] + nums[right];
                
                if(sum==0){
                    result.add(Arrays.asList(base, nums[left], nums[right]));
                    right--;
                    left++;
                    while(nums[left]==nums[left-1] && left<right) left++;
                    while(nums[right]==nums[right+1] && left<right) right--;
                }
                if(sum<0) left++;
                if(sum>0) right--;
            }
        }
        
        //return new ArrayList<>(result);
        
        return result;
        
    }
}  
