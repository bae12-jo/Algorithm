import java.util.*;
 
public class Solution{
    boolean[] visited;
    ArrayList<String> routeList;
 
    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];    
        routeList = new ArrayList<String>();
        int count = 0;                             
        dfs(count, "ICN", "ICN", tickets);                
        Collections.sort(routeList); // 경로가 여러개라면 알파벳 순으로 가장 앞서는 것 선택                  
        String[] answer = routeList.get(0).split(" ");    
        return answer;
    }
    
    public void dfs(int count, String src, String answer, String[][]tickets) {
        if(count == tickets.length) { // 온전한 경로가 있는 경우에만 저장 (경로가 없는 곳은 무시)  
            routeList.add(answer);   
            return;
        }
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && tickets[i][0].equals(src)) {  
                visited[i] = true;                                
                dfs(count+1, tickets[i][1],answer+" "+tickets[i][1] , tickets);  
                visited[i] = false;
            }
        }
        return;
    }
}
