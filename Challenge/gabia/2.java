// 42.3/50 (wrong answer)

import java.util.*;

public class Solution {
    public int solution(String p) {
        int ans = -1;

        int answer = 0;
        boolean isRight = false;
        int toLeft = 0, toRight = 0;

        if(p.length()==1) {
            return p.charAt(0)==' ' ? -1 : 1;
        }
        
        for(int i=0; i<p.length(); ++i){
            if(i==0 && p.charAt(i)=='>'){
                isRight = true;
                ++toRight;
                continue;
            }else if(i==0 && p.charAt(i)=='<'){
                ++toLeft;
                continue;
            }else{
                if(p.charAt(i)=='>' && isRight){ // 처음부터 끝까지 > 인 경우
                    ++toRight;
                    if(i==p.length()-1) {
                        //System.out.print("toRight : " + toRight);
                        answer += toRight;
                    }
                    continue;
                }else if(p.charAt(i)=='>' && !(isRight)){ // 왼쪽으로 가다가 중간에 > 만난 경우
                    ++toRight;
                    if(toLeft==i) {
                        //System.out.println(i);
                        //System.out.println("toLeft ? : " + toLeft);
                        answer += toLeft;
                    }
                    if(i==p.length()-1){
                        answer += toRight;
                    }
                    toLeft = 0;
                    isRight = true;
                    continue;
                }else if(p.charAt(i)=='<' && isRight){ // 오른쪽으로 가다가 <를 만난 경우 경우
                    // if(toRight == i) {
                    //     //System.out.print("toLeft : " + toLeft);
                    //     answer += toRight;
                    // }
                    toLeft = 0;
                    toRight = 0;
                    isRight = false;
                    continue;
                }else if(p.charAt(i)=='<' && !(isRight)){ // 처음부터 끝까지 <인 경우
                    ++toLeft;
                    if(toLeft == p.length()) {
                        //System.out.print("toLeft : " + toLeft);
                        answer += toLeft;
                    }
                    continue;
                }
            }
        }

        ans = answer == 0 ? -1 : answer;
        return ans;
    }
}
