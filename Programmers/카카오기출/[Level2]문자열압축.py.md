# 문제

문자열을 입력 받아서 연속 등장하는 문자의 수를 세어 압축한다
aabbaccc 라면 2a2ba3c(연속횟수, 연속문자 조합)으로 표현 가능하다
이때 연속되지 않는 문자는 1을 생략한다
이렇게 구할 수 있는 조합 중 가장 길이가 짧은 조합의 길이를 출력하는 문제이다.

# 풀이

answer는 문제에서 주어진 최대 문자열 길이 1000으로 초기화 (for 문 밖에 선언할 것)
res는 압축 문자열을 저장할 공간
cnt는 동일한 문자열의 몇 번 등장하는 지 저장하는 공간
tmp는 비교할 문자열(길이는 1에서 최대 문자열 전체 길이까지)

1. 문자열을 n 길이만큼 슬라이싱 하여 tmp에 임시 저장한다.
2. 문자열을 n 간격으로 건너뛰며 tmp와 동일한 문자열인지 비교한다.
- 동일하다면, tmp가 몇번 연속 나오는지 세는 변수 count를 1씩 늘려준다.
- 다르다면, (1) count가 전혀 늘어나지 않았다면(1이라면) res에 tmp만 넣어준다.
           (2) count가 1이 아니라면 그동안 센 count와 tmp를 모두 res에 넣어준다
3. tmp는 다음 n개만큼의 문자열로 새로 슬라이싱해서 넣어주고, cnt도 1로 초기화 한다.
4. 문자열 끝에 닿을 때까지 반복
5. answer와 구한 res 중 길이가 짧은 것을 answer에 넣어준다.

### 고민이 더 필요한 영역

다른 블로그 풀이를 보니 4번 항목에서 문자열 끝까지가 아닌 문자열 절반까지만 돌리는 사람이 많았다. 나는 그래야 할 근거를 못 찾아서 일단 끝까지 돌렸다..

단, 이때 문자열 길이+1을 해줘야 문자열 길이가 1인 히든 테스트케이스를 통과할 수 있다.

# 코드

```python
def solution(s):
    answer = 1000
    for i in range(1, len(s)+1):
        res = ""
        cnt = 1
        tmp = s[:i]
        
        for j in range(i, len(s)+i, i):
            if s[j:j+i] == tmp:
                cnt += 1
            else:
                if cnt == 1:
                    res += tmp
                else:
                    res += str(cnt)+tmp
                    print(res)
                tmp = s[j:j+i]
                cnt = 1
        answer = min(answer, len(res))
    return answer
```

![](https://images.velog.io/images/bae12/post/efb4a51c-859c-40f6-9af2-0b6f863dbc75/image.png)
![](https://images.velog.io/images/bae12/post/0547c20f-01d8-4262-a9d8-d26f7120e799/image.png)
