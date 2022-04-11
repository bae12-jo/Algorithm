/* Bucket Sort O(n) */

class Solution {
    public String frequencySort(String s) {
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<s.length(); ++i){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        
        int maxFreq = Collections.max(map.values()); // 시간 복잡도는? O(n)
        
        List<List<Character>> buckets = new ArrayList<>();
        for(int i=0; i<=maxFreq; ++i){
            buckets.add(new ArrayList<Character>());
        }
        
        for(Character key: map.keySet()){
            int freq = map.get(key);
            buckets.get(freq).add(key);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=buckets.size()-1; i>=1; i--){
            for(Character c: buckets.get(i)){
                for(int j=0; j<i; ++j){ 
                    sb.append(c);
                }
            }
        }
        
        return sb.toString();
        
    }
}


/* HashMap + PriorityQueue O(n log n) */

class Solution {
    public String frequencySort(String s) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<s.length(); ++i){
            map.put(String.valueOf(s.charAt(i)), map.getOrDefault(String.valueOf(s.charAt(i)), 0)+1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()
        );
        
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            pq.offer(entry);
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(!pq.isEmpty()){
            Map.Entry<String, Integer> now = pq.poll();
            for(int i=0; i<now.getValue(); ++i) 
                sb.append(now.getKey());
        }
        
        return sb.toString();
        
    }
}
