# 문제
![](https://images.velog.io/images/bae12/post/cf96a793-6b07-4728-965d-95511da72f24/image.png)
![](https://images.velog.io/images/bae12/post/40442f71-51e8-46d5-bc16-7bfa0348e586/image.png)

# 풀이

### Trial 1st 풀이

```python
import math
def solution(progresses, speeds):
    left = []
    result = []
    for i, v in enumerate(progresses):
        left.append(math.ceil((100-v)/speeds[i]))
    
    key = left[0]
    count = 1
    for i in range(1, len(left)):
        if key >= left[i]:
            count += 1
        else:
            left.append(count)
            key = left[i]
            count = 1
    result.append(count)
        
    return result
```

풀이는 다음과 같다.

1. progresses 만큼 돌면서 100을 만드는 횟수를 구하여 left 리스트에 저장한다.
2. left 리스트의 첫번째 원소를 키로 잡고 count를 1로 초기화 한다.
3. 같거나 작은 원소가 나오면 원소면 count를 1씩 추가해주고, 더 큰 원소가 나오면 그동안 쌓은 count를 결과 리스트에 추가한 후 새로운 키로 업데이트 해주고 count는 1로 초기화한다.

![](https://images.velog.io/images/bae12/post/9c4a6906-0be8-4a6d-93e3-3aadfe93aadf/image.png)

풀리긴 했지만 몇 가지 아쉬운 점이 있다.

첫째로 100으로 딱 나누어 떨어지지 않는 숫자를 올림해주는 방법이 마음에 안 든다. math 라이브러리를 불러와서 math.ceil 함수를 썼는데 이것보다 더 좋은 방법은 없었을까?

(progress + iterator*speeds) ≥ 100

위와 같이 더해주면서 100 이상인지를 확인하면 올림 기능을 사용하지 않을 수 있다. 

두번째로 아쉬운 점은 이 문제 유형이 스택/큐인데 내가 짠 코드는 스택의 특징이 잘 드러나지 않는다. 첫번째 인덱스 원소만 pop() 되도록 짜면 큐처럼 구현할 수 있다. (이때 `pop(0)` 처럼 인덱스 0을 명시해주어야 한다.)

### Trial 2nd 풀이

1. iterator 역할을 하는 변수를 선언하고, 첫번째 원소가 진도 100% 이상이 될 때까지 증가시킨다. 
2. 조건을 만족하면 progresses와 speeds에서 각각 첫번째 원소를 pop 한다.
3. 0으로 선언했던 배포할 기능의 수를 의미하는 변수 count를 1개 증가시킨다.
4. 이제 progresses[0]은 그 다음 작업 진도를 가리킨다. i 만큼 작업했을 때 100 이상이라면 동일하거나 적은 시간이 소요되는 것이므로, 동일하게 두 큐에서 각각 pop하고 count도 증가시킨다.
5. 100이 안 되는 작업을 만나면 이 전까지의 count를 정답 리스트에 추가하고 count를 0으로 초기화한다.
6. progresses 큐가 빌 때까지 위 작업을 반복한다. 

이를 코드로 나타내면 다음과 같다.

```python
def solution(progresses, speeds):
    print(progresses)
    print(speeds)
    answer = []
    time = 0
    count = 0
    while len(progresses)> 0:
        if (progresses[0] + time*speeds[0]) >= 100:
            progresses.pop(0)
            speeds.pop(0)
            count += 1
        else:
            if count > 0:
                answer.append(count)
                count = 0
            time += 1
    answer.append(count)
    return answer
```

![](https://images.velog.io/images/bae12/post/2baea5c4-9202-411d-9ba6-03121e3b86dc/image.png)

코드는 더 예뻐진 것 같은데 시간은 오히려 늘어났다.

첫번째 코드에서는 o(1) 만에 작업 완료까지 걸리는 시간을 구한 반면, 두번째 코드에서는 while문을 돌리면서 찾아가기 때문인 것 같다.

문제의 의도에 맞는지는 모르겠지만... math 라이브러리를 사용할 수 있다면 첫번째 코드처럼 짜는 것도 나쁘지 않은 것 같다.

### Trial 3rd 풀이

교수님께서 zip() 함수 사용을 추천해주셨다.

** zip 함수 **

길이가 같은 iterable 자료형(리스트, 튜플)의 각 요소를 인덱스 순서대로 묶어서 새로운 iterable 자료형을 생성한다.

```python
a = [1, 2, 3]
b = ['mon', 'tue', 'wed']
c = ['red', 'green', 'blue']
new_zip = zip(a, b, c)
```

위와 같이 작성한 후 print(type(new_zip))을 해보면 `class<'zip'>`으로 출력된다.

 `<class 'zip'>`

print(list(new_zip)) 을 해서 list 타입으로 변환할 수 있다.

 `[(1, 'mon', 'red'), (2, 'tue', 'green'), (3, 'wed', 'blue')]`

```python
for x, y in zip(range(10), range(10)):
	print(x, y)
```

iterable 자료형을 만들지 않고 for 문을 이용해서 새로운 zip 클래스를 만드는 것도 가능하다.

```python
dict_num = { x:y for x, y in zip(range(10),range(10))}
```

zip() 함수와 comprehension을 이용해서 딕셔너리 생성도 가능하다.

`{0: 0, 1: 1, 2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9}`

zip() 함수 외에 또 한가지 새로 알게 된 것은 python 나누기 연산 방식이다.

c에서는 나누기 연산 시 소수점 뒷부분을 완전히 버려서 나눗셈에 대한 정수부를 얻는다.

반면 python 에서는 몫에 대해서 `내림`을 사용한다.

예시와 함께 살펴보면 다음과 같다.

| C(버림) | Python(내림) |
| -----: | -----: |
|7/3 = 2.333 ... = 2|7/3 = 2.333 ... = 2|
|7/-3 = -2.333 ... = -2|7/3 = -2.333 = -3|

내림의 몫이 달라짐에 따라 나머지 값도 변경된다. 

| C(버림) | Python(내림) |
| -----: | -----: |
|7 = 3 * 2 + 1|7 = 3 * 2 + 1|
|7 = (-3) * (-2) + 1|7 = (-3) * (-3) + (-2)|



C언어에서는 X % Y  (7%3) 연산 결과 부호가 X의 부호와 같다. 또한, 3으로 나누나 -3으로 나누나 같은 결과가 나온다. 반면에 Python에서는 X % Y (7%-3) 연산 결과의 부호가 Y의 부호와 같다.

위에서 `math.ceil` 을 사용하지 않고 소수점 올리기 연산을 어떻게 할까? 고민했었는데

python의 내림 연산 특성을 이용하면 간단하게 해결할 수 있다.

100-x가 아니라 x-100으로 음수를 구한 후 나누고 다시 -를 붙여 양수로 돌려주면 된다.

```python
-((p-100)//s
```

또한 리스트에서 음수 인덱스는 뒤에서부터 거꾸로 센다.

```python
a = list(range(10))
print(a) # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(a[-1], a[-2]) # 9 8
```

-1 인덱스를 사용하는 것은, 리스트에 가장 마지막에 들어간 원소를 추적하기 위함이다.

본 문제에서는 소요 시간이 늘어날 경우 zip의 첫번째 칼럼에 업데이트 되므로 result[-1][0]과 -((p-100)//s)를 비교하면서 result에 저장된 것보다 소요 시간이 더 늘어난 경우를 찾아낼 수 있다.

최종 코드!

```python
def solution(progresses, speeds):

    result=[]

    for p, s in zip(progresses, speeds):

        if len(result)==0 or result[-1][0]<-((p-100)//s):

            result.append([-((p-100)//s),1])

        else:

            result[-1][1]+=1

    return [i[1] for i in result]
```

![](https://images.velog.io/images/bae12/post/961f86c1-9f77-4859-be8b-d0ffea164801/image.png)

앞의 2개 코드와 비교했을 때 테스트 케이스 7번부터 시간이 줄어드는 것을 확인할 수 있다!
