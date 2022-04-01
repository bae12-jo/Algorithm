// 문제 조건
// (1) 출발점이 begin 단어로 고정되어 있으며 conntected components에 대해 고민할 필요가 없다

class Solution {
    
    boolean[] visited;
    int answer = 51;
    
    public int solution(String begin, String target, String[] words) {
        
        visited = new boolean[words.length];
        DFS(words, begin, target, 0);
        return answer == 51 ? 0 : answer;
    }
    
    public void DFS(String[] words, String begin, String target, int count){
        
        if(begin.equals(target)){
            answer = (answer > count) ? count : answer;
            return;
        }
        
        for(int i=0; i<words.length; ++i){
            
            if(visited[i]) continue;
        
            int sameChar = 0;
            for(int j=0; j<begin.length(); ++j){
                if(begin.charAt(j)==words[i].charAt(j)) sameChar++;
            }
            
            if(sameChar==begin.length()-1){
                visited[i]=true;
                DFS(words, words[i], target, count+1);
                visited[i]=false;
            }
            
        }     
        
    }
}
