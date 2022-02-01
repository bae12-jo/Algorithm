# 문제
* 문자 로그는 숫자 로그보다 앞선다
* 문자 로그는 사전식으로 정렬되며, 내용이 동일한 경우 식별자 순서로 정렬한다.
* 숫자 로그는 입력 순서를 유지한다.

# 풀이

1.
문자 로그와 숫자 로그의 정렬 방식이 다르므로 분리해준다.
dig1, let1로 표현된 식별자로 분류하는 건 복잡하다.
데이터가 숫자인지 문자인지로 구분하는 것이 훨씬 간편하므로 `split()[1]`을 기준으로 분류하며, `isdigit()` 함수를 이용한다.
```python
letter, digit = [], []
        for log in logs:
            if log.split()[1].isdigit():
                digit.append(log)
            else:
                letter.append(log)
```

2.
문자 로그를 정렬해야 하는데, 데이터를 사전식으로 정렬하되 내용이 같으면 식별자 순으로 정렬해야 한다. 즉 정렬 키 값이 2개이므로 익명함수 람다를 이용한다.

```python
letter.sort(key= lambda x: (x.split()[1:], x.split()[0]))
```

3.
정렬된 문자로그와 숫자로그를 차례대로 반환한다.
```python
return letter + digit
```

전체 코드는 다음과 같다.


```python
class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        
        # split letter and digit logs by index 1
        letter, digit = [], []
        for log in logs:
            if log.split()[1].isdigit():
                digit.append(log)
            else:
                letter.append(log)
        
        # sort letter by content first, indentifier second
        letter.sort(key=lambda x: (x.split()[1:], x.split()[0]))
        
        # return letter and digit resepectively
        return letter + digit
 ```
 
 ![](https://images.velog.io/images/bae12/post/a1b03be0-bc36-4109-b170-24a60a028b68/image.png)
