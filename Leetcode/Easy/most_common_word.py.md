# 문제
* banned에 포함된 단어를 제외하고 주어진 문자 중 가장 빈도수가 높은 단어를 구하라
* 대소문자를 구분하지 않는다.


# 풀이

파이썬이 확실히 유리한 문제 유형이다.
리스트 컴프리헨션을 통해서 아래 4개를 한 큐에 끝낸다.
(1) 문자만 추출 
(2) 소문자로 변환 
(3) 단어 단위로 자르기 
(4) banned에 포함된 단어 제거

```
words = [word for word in re.sub('[^\w]', " ", paragraph).lower().split() if not in banned]
```

이후 빈도수 계산에 특화된 collection.Counter 객체로 만들어준다.

이때 주의할 점은 collection.Counter는 딕셔너리를 한번 더 래핑한 구조라는 점이다. 
most_common(1)을 통해 뽑으면 [["key", "value"]] 형태로 출력된다.
여기서 key 값만 뽑고 싶다면 첫번째 객체의 첫번째 요소를 뜻하는 [0][0]를 반드시 붙여줘야한다.

전체 코드는 다음과 같다.
```
class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        
        words = [word for word in re.sub('[^\w]', " ", paragraph)
                 .lower().split() if word not in banned]
        
        return collections.Counter(words).most_common(1)[0][0]       
```
![](https://images.velog.io/images/bae12/post/6272da19-e76d-4bc0-a249-1ec79888aa3b/image.png)
