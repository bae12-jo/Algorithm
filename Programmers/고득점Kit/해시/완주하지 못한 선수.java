import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> paticipantList = new HashMap<>();
        
        // set <participant, duplication count> hash table
        for(String s: participant){
            Integer count = paticipantList.get(s);
            if(count==null) paticipantList.put(s, 1); 
            else paticipantList.put(s, ++count);
        }
        
        // decrease duplication count based on completion list
        for(String s: completion){
            if(paticipantList.containsKey(s)){
                Integer count = paticipantList.get(s);
                paticipantList.put(s, --count);
            }
        }
        
        // find out the person with duplication count of 1
        for(String s: paticipantList.keySet()){
            int count = paticipantList.get(s);
            if(count!=0) answer = s;
        }
        
        // return the name of the person who did not cross the finish line 
        return answer;
    }
}
