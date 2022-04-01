class Solution {
    
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        DFS(numbers, 0, target, 0);
        return answer;
    }
    
    public void DFS(int[] numbers, int depth, int target, int sum){
        if(depth==numbers.length){
            if(sum==target) answer++;
        }else{
            DFS(numbers, depth+1, target, sum+numbers[depth]);
            DFS(numbers, depth+1, target, sum-numbers[depth]);
        }
    }
}
