import java.util.*;

class Solution {
    
    static int[] pattern1 = {1, 2, 3, 4, 5};
    static int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    static HashMap<Integer, Integer> count = new HashMap<>();
    
    public Integer[] solution(int[] answers) {
        
        // Get the number of correct answer per each pattern
        for(int i=0; i<answers.length; ++i){
            if(answers[i]==pattern1[i%5]) count.put(1, count.getOrDefault(1, 0)+1);
            if(answers[i]==pattern2[i%8]) count.put(2, count.getOrDefault(2, 0)+1);
            if(answers[i]==pattern3[i%10]) count.put(3, count.getOrDefault(3, 0)+1);
        }
        
        // Sort HashMap in descending order
        List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(count.entrySet());
        entryList.sort(new Comparator<Map.Entry<Integer, Integer>>(){
           @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                return o2.getValue() - o1.getValue();
            }
        });
        
        // Find patterns with maximum of correct answer count
        int max = 0;
        List<Integer> tmp = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: entryList){
            if(!(entry.getValue()==0) && entry.getValue()>=max){
                tmp.add(entry.getKey());
                max = entry.getValue();
            }
        }
        
        Integer[] answer = tmp.toArray(new Integer[tmp.size()]);      
        
        return answer;
    }
}
