import java.util.*;


class Solution {
    
    private int hourToMin(String s){
        // int minutes = (s.charAt(0)-'0')*600 + (s.charAt(1)-'0')*60;
        // minutes += (s.charAt(3)-'0')*10 + (s.charAt(4)-'0');
        // return minutes;
        
        return Integer.parseInt(s.substring(0, 2))*60 + Integer.parseInt(s.substring(3));
    }
    
    private String minToHour(int s){
        
        StringBuilder sb = new StringBuilder();
        
//         int hour = s/60;
//         int minute = s%60;
        
//         if(hour<10) sb.append("0").append(String.valueOf(hour));
//         else sb.append(String.valueOf(hour));
        
//         sb.append(":");
        
//         if(minute<10) sb.append("0").append(String.valueOf(minute));
//         else sb.append(String.valueOf(minute));
        
//         return sb.toString();
        
        return sb.append(String.format("%02d", s/60)).append(":").append(String.format("%02d", s%60)).toString();
        
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        // hh:mm -> mmmm (0~1439)
        for(String s: timetable){
            int minute = hourToMin(s);
            queue.offer(minute);
        }
        
        int startTime = 540; // 9*60
        int lastTime = 0;
        int count = 0;
        for(int i=0; i<n; ++i){
            count = 0;
            while(!queue.isEmpty() && queue.peek()<=startTime && count<m){
                // 마지막 순서에서 새치기 하기 위함
                lastTime = queue.poll()-1;
                count++;
            }
            startTime += t;
        }
        
        if(count<m) lastTime = startTime-t;
    
        
        return minToHour(lastTime);
    }
}
