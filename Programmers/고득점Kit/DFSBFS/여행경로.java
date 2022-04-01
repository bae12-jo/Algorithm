// 여러가지 경로를 문자열로 받아서 정렬 후 문자열 배열로 변환
// string을 추가한다는 점에서 효율이 좋아보이지 않음

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

// 스택을 활용한 방법 
// 궁금한 점: 함수의 인자로 전달받은 Stack<String>을 List에 그대로 넣으면 값이 반영되지 않음 왜일까?
import java.util.*;

class Solution {
    
    List<Stack<String>> routeList = new ArrayList<>();
    boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        
        // initialize visited
        visited = new boolean[tickets.length];
        
        // make route stack
        Stack<String> route = new Stack<>();
        route.push("ICN");
        
        DFS(tickets, route, 0);
        
        // sort routeList in alphabet order
        if(routeList.size()>1){
            Collections.sort(routeList, new Comparator<Stack<String>>() {
                @Override
                public int compare(Stack<String> o1, Stack<String> o2){
                    for(int i=0; i<o1.size(); ++i){
                        String s1 = o1.get(i);
                        String s2 = o2.get(i);
                        
                        if(!s1.equals(s2)){
                            return s1.compareTo(s2);
                        }
                    }
                    return 0;
                }
            });
        }
        
        Stack<String> tmpStack = routeList.remove(0);
        String[] answer = new String[tmpStack.size()];
        for(int i=0; i<tmpStack.size(); ++i){
            answer[i] = tmpStack.get(i);
        }
        
        return answer;
    }
    
    public void DFS(String[][] tickets, Stack<String> route, int count){
        // add the route to routeList when route exists
        if(count==tickets.length){
            Stack<String> res = new Stack<>();
            for(String s : route){
                res.push(s);
            }
            // Stack<String> res = (Stack<String>) route.clone(); 로 대체 가능, 이게 더 빠름
            routeList.add(res); // route를 그대로 전달하면 안 됨 why?
            return;
        }
        
        String src = route.peek();
        
        // add airport to route when it's not visited yet
        for(int i=0; i<tickets.length; ++i){
            if(tickets[i][0].equals(src) && !visited[i]){
                visited[i]=true;
                route.push(tickets[i][1]);
                DFS(tickets, route, count+1);
                visited[i]=false;
                route.pop();
            }
        }
        
    }
}
