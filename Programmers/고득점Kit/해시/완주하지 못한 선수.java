/* Basic Code */


import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> participantList = new HashMap<>();
        
        // set <participant, duplication count> hash table
        for(String s: participant){
            Integer count = participantList.get(s);
            if(count==null) participantList.put(s, 1); 
            else participantList.put(s, ++count);
        }
        
        // decrease duplication count based on completion list
        for(String s: completion){
            if(paticipantList.containsKey(s)){
                Integer count = participantList.get(s);
                participantList.put(s, --count);
            }
        }
        
        // find out the person with duplication count of 1
        for(String s: participantList.keySet()){
            int count = participantList.get(s);
            if(count!=0) answer = s;
        }
        
        // return the name of the person who did not cross the finish line 
        return answer;
    }
}


/* getOrDefault 사용 */
// 궁금한 점: getOrDefault에 ++나 -- 연산자를 바로 사용하면 variable required but value 라는 에러가 뜬다. 왜 일까?

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> participantList = new HashMap<>();
        
        // set <participant, duplication count> hash table
        for(String s: participant) participantList.put(s, participantList.getOrDefault(s, 0)+1);
        
        // decrease duplication count based on completion list
        for(String s: completion) participantList.put(s, participantList.get(s)-1);
        
        // find out the person with duplication count of 1
        for(String s: participantList.keySet()){
            int count = participantList.get(s);
            if(count!=0) answer = s;
        }
        
        // return the name of the person who did not cross the finish line 
        return answer;
    }
}
