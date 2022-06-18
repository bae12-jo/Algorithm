// two sum과 완전히 동일한 문제, 다만 1-indexed 인 것을 조심하기

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<numbers.length; ++i){
            int complement = target-numbers[i];
            if(map.containsKey(complement)) return new int[] {map.get(complement)+1, i+1};
            map.put(numbers[i], i);
        }
        
        return null;
        
    }
}
