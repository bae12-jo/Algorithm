# 문제
![](https://images.velog.io/images/bae12/post/3dfb6543-a2b0-40f7-b92b-ff0f13a598a9/image.png)

# 풀이
```python
def solution(s):
    answer = ""
    if len(s)%2==0:
        return s[(len(s)//2)-1]+s[(len(s)//2)]
    else:
        return s[len(s)//2]
```

너무 짧아서 당황스러운 문제였다.

다른 사람들의 풀이를 보니 천하제일숏코딩 대회가 열렸다.

그 중 가장 인상 깊었던 풀이는 다음과 같다.

```python
def solution(s):
    return s[(len(s)-1)//2:len(s)//2+1]
```

1. `홀수//2`  와 `홀수-1//2` 가 같다는 점
2. 슬라이싱 [시작:끝] 에서 시작~끝-1 인덱스만 돈다는 점
3. 짝수 길이 문자열의 가운데 두 글자 중 첫번째는 `홀수-1//2` 와 같다는 점

위 세가지 속성을 활용한 멋진 풀이다.
