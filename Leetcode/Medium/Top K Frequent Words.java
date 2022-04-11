class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String s: words){
            map.put(s, map.getOrDefault(s, 0)+1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>( // 빈도수 내림차순, 빈도수 같다면 이름 사전순 저장
            (a, b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue()-a.getValue()
        );
        
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            pq.offer(entry);
        }        
        
        List<String> result = new ArrayList<>();
        
        for(int i=0; i<k; ++i){
            result.add(pq.poll().getKey());
        }
        
        return result;
    }
}
