# Divide and Conquer

A divide-and-conquer algorithm works by recursively breaking the problem down into two or more subproblems of the same or related type, until these subproblems become simple enough to be solved directly. Then one combines the results of subproblems to form the final solution.
classic example: merge sort, quick sort

```python
def divide_and_conquer( S ):
    # (1). Divide the problem into a set of subproblems.
    [S1, S2, ... Sn] = divide(S)

    # (2). Solve the subproblem recursively,
    #   obtain the results of subproblems as [R1, R2... Rn].
    rets = [divide_and_conquer(Si) for Si in [S1, S2, ... Sn]]
    [R1, R2,... Rn] = rets

    # (3). combine the results from the subproblems.
    #   and return the combined result.
    return combine([R1, R2,... Rn])
```

Divide : n>=2 일때 문제 S를 부분 문제의 집합으로 쪼갠다. 일반적으로 부분 문제는 1개 이상이다.
Conquer : 각 문제를 recursively하게 해결한다. 보통 이 부분이 생각해내기 어렵다. recurrence relationship을 찾아내는 것이 핵심!
Combine : 반복적으로 얻은 부분 문제의 합을 합쳐나간다.

## Merge Sort

Time Complexity: O(n long n)
Space Complexity: O(n)


##### Top dowm approach
![image](https://user-images.githubusercontent.com/84948636/164183442-ad2de36f-481d-48d2-aed4-dbe6c056b57c.png)


```java
import java.util.Arrays;
public class Solution {
    
    public int [] merge_sort(int [] input) {
      if (input.length <= 1) {
        return input;
      }
      int pivot = input.length / 2;
      int [] left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot));
      int [] right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.length));
      return merge(left_list, right_list);
    }
    
    public int [] merge(int [] left_list, int [] right_list) {
      int [] ret = new int[left_list.length + right_list.length];
      int left_cursor = 0, right_cursor = 0, ret_cursor = 0;

      while (left_cursor < left_list.length && 
             right_cursor < right_list.length) {
        if (left_list[left_cursor] < right_list[right_cursor]) {
          ret[ret_cursor++] = left_list[left_cursor++];
        } else {
          ret[ret_cursor++] = right_list[right_cursor++];
        }
      }
      // append what is remain the above lists
      while (left_cursor < left_list.length) {
        ret[ret_cursor++] = left_list[left_cursor++];
      }
      while (right_cursor < right_list.length) {
        ret[ret_cursor++] = right_list[right_cursor++];
      }  
      return ret;
    }
}
```

##### Bottom up approach
![image](https://user-images.githubusercontent.com/84948636/164183366-a17556a5-71fc-4de4-89d7-384fcfd7531a.png)

Divide each elements at very beginning, and conquer it recursively



