class Solution {
    
    // Count connected components
    int answer = 0;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        
        visited = new boolean[n];
        
        for(int i=0; i<computers.length; ++i){
            if(!visited[i]) {
                DFS(computers, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void DFS(int[][] computers, int start){
        visited[start] = true;
        
        for(int i=0; i<computers[start].length; i++){
            if(i!=start && !visited[i] && computers[start][i]==1){
                DFS(computers, i);
            }
        }
    }
}
