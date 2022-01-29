# 문제

프로그래머스 > LEVEL1 >  모의고사

![](https://images.velog.io/images/bae12/post/a37c287d-a5c1-40b3-a4fc-b756267aaabb/image.png)

# 풀이

### 1st 풀이

```python
def solution(answers):
    sheet1 = [1, 2, 3, 4, 5]
    sheet2 = [2, 1, 2, 3, 2, 4, 2, 5]
    sheet3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    cnt1, cnt2, cnt3 = 0, 0, 0
    answer = []
    
    i = 0
    while i<len(answers):
        i1 = i%5
        i2 = i%8
        i3 = i%10
        if answers[i] == sheet1[i1]:
            cnt1 += 1
        if answers[i] == sheet2[i2]:
            cnt2 += 1
        if answers[i] == sheet3[i3]:
            cnt3 += 1
        i+=1
        
    highest = max(cnt1, cnt2, cnt3)
    
    if highest == cnt1:
        answer.append(1)
    if highest == cnt2:
        answer.append(2)
    if highest == cnt3:
        answer.append(3)
    
    return answer
```

직관적으로 짠 코드이다.

1. 수포자 3명의 패턴을 리스트 3개에 각각 넣는다
2. 수포자 3명이 맞춘 정답 개수를 저장할 변수 3개를 만들고 0으로 초기화 한다
3. 문제 배열 길이만큼 while문을 돌면서 수포자 3명의 패턴과 일치할 때마다 정답 개수 변수를 업데이트 해준다. 이때 수포자 3명의 패턴 길이가 상이하므로 모듈러 연산을 통해 패턴별 반복자를 만들어준다.
4. 정답 개수가 담긴 변수 중 최고값을 찾는다.
5. 최고값과 동일한 변수가 있다면 해당 수포자 번호를 리스트에 추가해준다.

좋지도 나쁘지도 않은 코드같다.

좀 더 코드 길이를 줄여볼 방법이 없을까?

우선 수포자 3명의 패턴을 하드코딩한 부분이 눈에 들어왔다. 이 패턴도 인자로 받아올 수 있었다면 반복되는 가장 긴 수열을 고르는 방식으로 패턴 추출을 시도했을 것 같다. 하지만 문제 설명에만 적혀있었기 때문에 더 이상 수정은 불가능해보인다.

수포자 3명의 정답 개수 저장할 변수를 각각 만들었지만, 배열 하나로 합칠 수도 있다. 뒤에서 임의 접근하여 값을 바꿔줄 것이므로 0으로 미리 초기화 해둔다.

```python
scores = [0, 0, 0]
```

수포자 3명의 패턴 길이를 하드코딩하여 모듈려 연산으로 반복자를 구했는데, 눈으로 세지 않고 함수를 쓰는 방법도 있다. 반복자를 여러개 설정하기 위해서 while문이 아닌 for문으로 바꾸고 `enumerate`를 써서 인덱스와 값을 모두 불러온다. 불러온 인덱스에 `i%len(패턴리스트)` 처럼 모듈러 연산을 해주고 if 문 조건에 넣어준다. score의 인덱스도 하드코딩하지 않고 멋있게 처리해주고 싶었지만 패턴 리스트가 고정적이기 때문에 괜히 손댔다가 불필요한 코드만 작성하게 될 것 같아서 관뒀다. 

```python
for i, ans in enumerate(answers):
	if ans == sheet1[i%len(sheet1)]:
			score[0] += 1
 	if ans == sheet2[i%len(sheet2)]:
			score[1] += 1
 	if ans == sheet3[i%len(sheet3)]:
			score[2] += 1
```

마지막으로 아까 정답 개수를 저장한 변수를 리스트 하나로 만들어 뒀기 때문에 if 문 3개를 반복문으로 묶어줄 수 있다. 이때 인덱스+1 해주는 것을 잊지 말자.

```python
for i, v in enumerate(score):
	if v == max(score):
			answer.append(i+1)
```

이렇게 수정한 전체 코드는 다음과 같다.

### 2nd 풀이

```python
def solution(answers):
    sheet1 = [1, 2, 3, 4, 5]
    sheet2 = [2, 1, 2, 3, 2, 4, 2, 5]
    sheet3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    scores = [0, 0, 0]
    answer = []
    
    for i, ans in enumerate(answers):
        if ans == sheet1[i%len(sheet1)]:
                scores[0] += 1
        if ans == sheet2[i%len(sheet2)]:
                scores[1] += 1
        if ans == sheet3[i%len(sheet3)]:
                scores[2] += 1
    
    for i, v in enumerate(scores):
	    if v == max(scores):
		    answer.append(i+1)
    
    return answer
```

# 속도 비교

1번

![](https://images.velog.io/images/bae12/post/8a681697-d26c-4a2a-b254-9a5390b586cd/image.png)

2번

![](https://images.velog.io/images/bae12/post/181cbebc-ec0a-455e-98a8-6ffeef7464f2/image.png)

테스트 케이스 7번 이후부터 속도가 조금씩 개선된 것을 확인할 수 있다.
