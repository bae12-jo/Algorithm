# Dynamic Programming

Dynamic Programming (DP) is a technique that solves some particular type of problems in Polynomial Time. Dynamic Programming solutions are faster than the exponential brute method and can be easily proved for their correctness.

## Property

### Overlapping Subproblems

Divide and conquer와 같이 DP는 부분 문제를 풀어나가며 전체 답을 구하는 방법이다. DP는 주로 동일한 부분 문제들이 반복될 때 사용된다.
이때 부분 문제들이 중복되는 현상이 발생하며(분할정복과의 차이점) 중복 계산되지 않도록 저장하는 과정이 필요하다.
반대로 말하면 중복 부분 문제가 없다면 DP를 사용할 필요가 없다. (예를 들면 바이너리 서치는 공통 부분 문제가 없다)
룩업 테이블(Look up Table)이란? 룩업테이블은 주어진 연산에 대해 precomputed 미리 계산된 결과들의 집합(배열)을 가리킨다.

### Optimal Substructure

shortest path problem이 대표적인 optimal substructure property를 가진다. (플로이스 워셜, 벨만 포드 알고리즘)
The standard All Pair Shortest Path algorithm like Floyd–Warshall and Single Source Shortest path algorithm for negative weight edges like Bellman–Ford are typical examples of Dynamic Programming. 
부분해를 모으다보면 전체 문제의 최적해에 도달하게 된다는 것이다. 이때 가능한 부분해가 여러가지면 안된다. (longest path prolbem은 optimal substructure가 아니다)


## Tabulation vs Memoization

![image](https://user-images.githubusercontent.com/84948636/163958360-2d15e14b-96f7-45ce-b079-8454f2ba8d21.png)

ref: https://www.geeksforgeeks.org/tabulation-vs-memoization/

tabulation은 bottom-up 접근법에서 사용되는 최적화 방식으로 도표를 작성하듯이 모든 값을 구한 후, 마지막에 원하는 값을 질의한다.
문제가 복잡해질수록 코드로 구현하기 어려워지나 실행 속도는 빠른 편이다.

memoization은 top-down 방식에서 사용되는 최적화 방식으로 원하는 결과값에 먼저 접근하고 구할 수 없다면 연산에 필요한 다른 값을 찾아나가는 방식이다
직관적으로 떠올리기 쉽고 코드화하기도 쉬우나 재귀 호출의 특성상 실행 속도가 느리다.
