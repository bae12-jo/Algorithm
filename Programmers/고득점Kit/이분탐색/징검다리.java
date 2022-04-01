// 문제에서 n개의 돌을 제거하라고 했지만, 최대 n개의 돌을 제거하라고 이해해야 한다.

import java.util.*;

class Solution {
    
    int answer = 0;
    
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        BST(rocks, 0, distance, n);
        
        return answer;
    }
    
    public void BST(int[]rocks, int left, int right, int n){
        while(left<=right){
            int removedRocks = 0;
            int prevRock = 0;
            int mid = (left+right)/2;
            for(int i=0; i<rocks.length; ++i){
                if(rocks[i]-prevRock<mid){
                    removedRocks++;
                    if(removedRocks>n) break;
                }
                else prevRock = rocks[i];
            }
            if(removedRocks>n) right=mid-1; // 너무 많은 돌이 제거되는 경우 거리를 줄여야함
            else{ // 돌이 n개 이하로 제거되는 경우 거리를 늘리는 시도
                left = mid+1;
                answer = answer>mid?answer:mid; // 다만 현재가 최대값일 수 있으므로 저장하고 넘어가기
            }
        }
        
    }
}
