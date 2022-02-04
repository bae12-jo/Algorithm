# 문제

![](https://images.velog.io/images/bae12/post/bf8f2f0d-f98d-43fe-9f28-2330485ffdc7/Screenshot%20from%202022-01-11%2011-28-54.png)
![](https://images.velog.io/images/bae12/post/d0da8dee-a7aa-4913-901b-6cfbbc7da79f/Screenshot%20from%202022-01-11%2011-29-20.png)
# 풀이

### Trial 1st 풀이

첫 시도에는 deque에서 앞쪽 2개의 값을 가져와서 연산하고 append 해주는 방식으로 풀었다.

```python
import collections
def solution(scoville, K):
    answer = 0
    deq = collections.deque(scoville)
    while deq[0]<K:
        a = deq.popleft()
        b = deq.popleft()
        deq.append(a + b * 2)
				sorted(deq)
        answer += 1
    return answer
```

일부 테스트 케이스만 통과했고 효율성은 다 통과하지 못했다.

### Trial 2nd 풀이

본 문제 카테고리가 힙이므로 이번에는 힙으로 접근해보았다.

- 파이썬에서 힙을 사용하려면 `heapq` 패키지를 import 한다.
- list를 heap으로 변환하고 싶다면 `heapify(list_name)` 메소드를 이용한다.
- heap은 기본적으로 최소 트리이다.
- 최소값 반환 및 삭제는 `heappop`(heap_name) , 값 추가는 `heappush(heap_name, value)` 이다.

```python
import heapq
def solution(scoville, K):
    answer = 0
    
    # 힙으로 변환
    heapq.heapify(scoville)
    
    while scoville[0]<K:
        a = heapq.heappop(scoville)
        b = heapq.heappop(scoville)
        heapq.heappush(scoville, a+b*2)
        answer += 1
    return answer
```

위의 코드를 그대로 힙으로 바꿨더니 효율성은 다 통과했는데 소수의 테스트케이스에서 실패 (런타임 에러)가 떴다. 

2가지를 추가해줬다. 

- 문제 해결이 불가능한 경우 -1 반환
- pop이 먼저 되어 힙이 비는 경우(인덱스 에러)를 방지
    - 방법1: 0번째 인덱스를 가져오지 말고 힙의 길이가 2 이상인 것을 체크하기
    - 방법2: heapreplace 사용하기 (본 문제에서는 적절하지 않음)

```python
import heapq
def solution(scoville, K):
    answer = 0
    
    # 힙으로 변환
    heapq.heapify(scoville)
    
    # 힙의 원소가 2개 이상임을 반복 조건으로 둠
    # pop이 먼저 되어 힙이 비어서 인덱스 에러가 나는 상황에 대비
    while len(scoville)>=2:
        # 최소값 반환
        a = heapq.heappop(scoville)
        
        # 최소값이 K보다 크면 종료
        if a>=K:
            return answer
        # 최소값과 그 다음 최소값을 연산해서 힙에 추가
        else:
            b = heapq.heappop(scoville)
            heapq.heappush(scoville, a+b*2)
            answer += 1
        
    if scoville[0]>=K:
        return answer
    # 문제 해결이 불가능한 경우 -1 return
    else:
        return -1
```

![](https://images.velog.io/images/bae12/post/546d09d2-12e9-41a1-b1bb-3e57f45b2027/Screenshot%20from%202022-01-11%2014-15-43.png)
