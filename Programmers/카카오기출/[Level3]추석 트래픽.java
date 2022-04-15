import java.text.SimpleDateFormat;
import java.util.*;

class Solution {
    public int solution(String[] lines) throws Exception{
        
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
        int answer = 0; // max amount of work per second
        int count = 0; // the number of completed works when each log done
        
        for(int i=0; i<lines.length; ++i){
            
            count = 0;
            
            // end time of previous log
            String[] log = lines[i].split(" ");
            Date prevTime = format.parse(log[1]); // Date로 바꾸고 싶으면 parse, String으로 바꾸고 싶으면 format (여기서는 getTime을 사용하기 위해 Date로 바꿔줌)
            double prevEndTime = prevTime.getTime(); // getTime() 메소드는 n개 이상의 date 타입간 대소 비교를 위해 사용 (long 타입으로 반환됨)
            
            // check logs which end after previous log
            for(int j=i; j<lines.length; ++j){
                // get end time of new log
                String[] newLog = lines[j].split(" ");
                Date endTime = format.parse(newLog[1]);
                double processSec = Double.parseDouble(newLog[2].substring(0, newLog[2].length()-1));
                
                // get start time of new log
                long startTime = (long)(endTime.getTime()-processSec*1000+1);
                
                if(prevEndTime+1000>startTime){ // 이전 로그의 종료시간에서 1초 미만을 지나는 시점에 시작되는 로그 개수 세기 (자기자신 포함)
                    count++;
                    answer = Math.max(answer, count);
                }               
                
            }
        }
        
        return answer;
    }
}
