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

// 문자 하나만 다른 단어 찾는 기능을 함수화한 코드

public class WordConversion {
    int answer;                
    boolean[] used;               
    public int solution(String begin, String target, String[] words) {
        answer = 51; // maximum number of words length (which is given in the problem)
        used = new boolean[words.length];
        dfs(begin, target, 0, words);
        return answer == 51 ? 0 : answer;
    }
    
    public void dfs(String presentWord, String target, int count,String[] words) {
        if(presentWord.equals(target)) {
            answer = (answer > count) ? count : answer;
            return;
        }
        
        for(int i = 0; i < words.length; i++) {  
            if(!used[i] && check(presentWord, words[i])) {
                used[i] = true;
                dfs(words[i],target,count+1, words);
                used[i] = false;
            }
        }
    }
    
    public boolean check(String presentWord, String nextWord) {  
        int count = 0;
        for(int i = 0; i < presentWord.length(); i++) {
            if(presentWord.charAt(i) != nextWord.charAt(i)) {
                count++;
            }
        }
        return count == 1 ? true : false;
    }
}
