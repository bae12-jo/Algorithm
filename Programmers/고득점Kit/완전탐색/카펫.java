// 직관적인 풀이
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int area = brown+yellow;
        
        int height = 0, width = 0;
        for(int i=1; i<=area; ++i){
            
            // check i is divisor of area
            if(area%i==0){
                height = i;
                width = area/i;
            }else{
                continue;
            }
            
            // height shouldn't be less than width
            if(height>width) continue;
            
            // if yellow can be made by divisors minus 2
            if((height-2)*(width-2)==yellow){
                answer[0] = width;
                answer[1] = height;
            }
        }
        
        return answer;
    }
}

// 기하학적 풀이
/* a는 완성된 카펫의 가로+세로입니다. b는 왜 저런식으로 써 놓으셨는진 모르겠지만 그냥 주어진 타일의 개수를 모두 합친 값입니다. 궁극적으로 answer 안에서 값을 구하는 방법을 기하학 적으로 보자면 우선 카펫의 가로 + 세로, 즉 a를 한변으로 하는 정사각형을 만든 후, 카펫모양으로 정사각형의 네 귀퉁이를 잘라내면, 즉 - 4 * b를 하면 가운데에 정사각형 형태의 타일이 남습니다. 루트를 이용하여 그 정사각형의 한 변의 길이를 구해주는데 그 한 변의 길이가 완성된 카펫의 가로와 세로의 길이 차이가 됩니다. 그러므로 더 긴쪽, 즉 가로를 구하기 위해선 가로와 새로를 더한 값에 차이를 더해주고 2로 나누고, 짧은 쪽을 구하기 위해선 빼고 2로 나눠주면 됩니다. 그림으로 그려보면 이해가 더 쉬운것 같네요. */
import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int a = (brown+4)/2;
        int b = brown+yellow;
        int[] answer = {(int)(a+Math.sqrt(a*a-4*b))/2,(int)(a-Math.sqrt(a*a-4*b))/2};
        return answer;
    }
}
