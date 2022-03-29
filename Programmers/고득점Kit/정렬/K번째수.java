// 배열 복사 직접 구현

import java.util.*;

class Solution {
    
    public static int sortNewArray(int[] array, int[] command){
        int start = command[0];
        int end = command[1];
        int k = command[2];
        
        int[] newArr = new int[end-start+1];
        
        int index = 0;
        for(int i=0; i<array.length; ++i){
            if(i<start-1 || i>end-1) continue;
            newArr[index++] = array[i];
        }
        
        Arrays.sort(newArr);
        return newArr[k-1];
    }
    
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        int index = 0;
        for(int[] command: commands){
            answer[index++] = sortNewArray(array, command);
        }
        
        return answer;
    }
}


// copyRangeOf 사용
/*

`copyRangeOf()`의 시간복잡도는 O(k)이다. k는 복사할 아이템의 bits 사이즈

`copyOf`는 항상 0번째 인덱스부터 시작한다는 차이가 있다. 원본 배열 길이보다 더 긴 길이를 주면 0으로 채워진다. Syntax -> copyOf(int[] original, int newLength)

Arrays.copyRangeOf() uses System.arraycopy() which uses native code (could use memcpy for example - depending on JIT implementation) under the hood.

The "magic" behind copying with System.arraycopy() is making one call to copy a block of memory instead of making n distinct calls.

That means that using Arrays.copyOfRange() will definitely be more efficient comparing to any other solution you'll choose to implement by yourself.

Further, I don't see how a binary search could help here: an array has a direct access - and here we now exactly what are the src, dst and how many items should we copy.

From big-O perspective, the complexity will be O(n*k) where n is the number of items to copy and k is the size (in bits) of each item. Space complexity is the same.

*/
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i=0; i<commands.length; i++){
            int[] temp = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }

        return answer;
    }
}
