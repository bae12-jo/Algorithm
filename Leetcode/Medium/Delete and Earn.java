class Solution {
    public int deleteAndEarn(int[] nums) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int k = 0; // largest num
        for(int n: nums){
            map.put(n, map.getOrDefault(n, 0)+n);
            k = Math.max(k, n);
        }
        
        int n = map.size(); // nums of unique values
        
        int prevMax = 0, currMax = 0;
        
        if(k<n+n*Math.log(n)/Math.log(2)){ // iterate over all data until meet k (bottom up with two variables)
            
            prevMax = 0;
            currMax = map.getOrDefault(1, 0);
            
            for(int i=2; i<=k; i++){
                int tmp = currMax;
                currMax = Math.max(currMax, prevMax+map.getOrDefault(i, 0));
                prevMax = tmp;
            }
            
        }else{ // iterate over valid elements
            
            List<Integer> order = new ArrayList<>(map.keySet());
            Collections.sort(order);
            
            prevMax = 0;
            currMax = map.get(order.get(0));
            
            for(int i=1; i<order.size(); i++){
                
                int currElement = order.get(i);
                int tmp = currMax;
                
                if(currElement==order.get(i-1)+1) currMax = Math.max(currMax, prevMax+map.get(currElement));
                else currMax += map.get(currElement);
                
                prevMax = tmp;
                
            }
            
        }
               
        return currMax;
                
    }
}
