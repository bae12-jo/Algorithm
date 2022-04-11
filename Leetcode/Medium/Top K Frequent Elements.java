/* bucket sort */

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        
        List<Integer>[] bucket = new List[nums.length+1];
        
        // buckest sort
        for(int key : map.keySet()){
            int freq = map.get(key);
            if(bucket[freq]==null){
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        
        //List<Integer> answer = new ArrayList<>();
        int[] answer = new int[k];
        int count = 0;
        for(int pos = bucket.length-1; pos>=0 ; pos--){
            if(bucket[pos]!=null){
                for(int i=0; i<bucket[pos].size(); ++i){
                    if(count==k) break;
                    answer[count++] = bucket[pos].get(i);
                }
            }
        }        
        
        return answer;
        
    }
}
