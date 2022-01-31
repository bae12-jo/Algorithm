# 문제

![](https://images.velog.io/images/bae12/post/0012ffcd-c505-4f8a-8db3-90d9979afc7e/Untitled.png)
![](https://images.velog.io/images/bae12/post/da8b8160-abb8-4397-9fc5-31032442a8c3/Untitled%20(1).png)


# 풀이

### Trial 1st 풀이

```python
def solution(n, lost, reserve):
    
    for ans in lost:
        if ans-1 in reserve:
            reserve.remove(ans-1)
        elif ans+1 in reserve:
            reserve.remove(ans+1)
        else:
            n-=1
    
    return n
```

처음에는 모든 경우에 대해서 완벽한 하나의 풀이를 찾고자 했으나 이 문제는 greedy 방식으로 해결해야 한다는 걸 깨닫고, 각 단계마다 최적의 해를 구하는 방식을 취했다.

1. lost를 돌면서 하나 크거나 하나 작은 값이 reserve에 있으면 해당값을 reserve에서 지운다.
2. 둘 다 불가능하면 체육복을 빌릴 수 없는 학생이 존재하는 것이므로 전체 학생 수에서 1을 뺀다.

### Trial 1st 결과

![](https://images.velog.io/images/bae12/post/000badcd-e19c-4f0d-b932-c67044e780a2/Untitled%20(2).png)

일부 테케에 대해서 실패했다.

최적해 찾는 방법이 잘못됐나 싶어 문제를 다시 읽어보니 제한사항에서 놓친 부분을 발견했다.

- 여벌의 체육복을 가져온 학생이 체육복을 도난 당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난 당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.

lost와 reserve에 중복되는 번호가 존재할 가능성이 있다. 그렇다면 각 리스트에 중복되는 값을 어떻게 없앨까? for문을 돌면서 not in 으로 unique한 원소만 가져올까?

좀 더 똑똑한 방법이 없을까 찾아보니 파이썬에는 `set` 생성자라는 게 있었다.

```python
s1 = {} #set 선언
s2 = {1, 2, 3, 4} #set에 원소 넣기
s2 = set(list1) #list를 set으로 변경
s3 = set([1, 2, 3]) #set에 list 넣기
```

[점프 투 파이썬](https://wikidocs.net/16044)

이름대로 집합을 구현해놓은 연산자이며 합집합, 교집합, 차집합, 대칭차집합을 지원했다.

중복 원소를 허용하지 않으며 (중복 입력하면 자동으로 지운다), 순서를 따로 저장하지 않아서 for문을 돌리면 어떤 것부터 나올지 알 수 없다.

이 문제에서는 set 연산자의 차집합(-) 기능을 사용해 겹치는 원소를 빠르게 제거할 수 있었고, 시간 복잡도는 O(1)으로 for 문 돌리는 것보다 훨씬 빠르다.  (주어진 학생 수가 30을 넘지 않으므로 본 문제에서는 for문을 써도 큰 무리는 없지 않을까 싶다.)

```python
diff_lost = set(lost) - set(reserve)
diff_reserve = set(reserve) - set(lost)
```

### Trial 2nd 풀이

```python
def solution(n, lost, reserve):

    diff_lost = set(lost) - set(reserve)
    diff_reserve = set(reserve) - set(lost)

    for ans in diff_lost:
        if ans-1 in diff_reserve:
            diff_reserve.remove(ans-1)
        elif ans+1 in diff_reserve:
            diff_reserve.remove(ans+1)
        else:
            n-=1

    return n
```

결과는 성공!

![](https://images.velog.io/images/bae12/post/ffa3697a-9728-48fe-9ab3-c1f63f582c48/Untitled%20(3).png)
