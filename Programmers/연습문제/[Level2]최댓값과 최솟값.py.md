# 최소값과 최대값

내 풀이

```python
def solution(s):
    arr = s.split()
    min = max = int(arr[0])
    for i in range(1, len(arr)):
        if int(arr[i]) < min:
            min = int(arr[i])
        if max < int(arr[i]):
            max = int(arr[i])
    answer = "{} {}".format(min, max)
    return answer
```

리스트 comprehension을 이용하면 훨씬 짧게 구현 가능하다

```python
def solution(s):
    arr = s.split()
    int_arr = [int(i) for i in arr]
    return "{} {}".format(min(int_arr), max(int_arr))
```
