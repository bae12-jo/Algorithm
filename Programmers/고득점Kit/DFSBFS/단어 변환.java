// 문제 조건
// (1) 출발점이 begin 단어로 고정되어 있으며 conntected components에 대해 고민할 필요가 없다
// (2) begin에서 target으로 가기까지 단어 배열에 따라 경우의 수가 여러개 나올 수 있지만 count 자체는 동일하다

class Solution {
    
    boolean[] visited;
    int answer = 0;
    
    public int solution(String begin, String target, String[] words) {
        
        visited = new boolean[words.length];
        
        DFS(words, begin, target, 0);
        
        return answer;
    }
    
    public void DFS(String[] words, String begin, String target, int count){
        
        if(begin.equals(target)){
            answer = ++count;
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
                DFS(words, words[i], target, count++);
                visited[i]=false;
            }
            
        }     
        
    }
}
