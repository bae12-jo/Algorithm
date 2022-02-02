# 문제
* 주어진 리스트에서 에너그램을 찾아서 모아 분류한 후, 출력하라
* 출력 순서는 자유

# 풀이
애너그램을 그룹별로 모아서 보여줘야 하므로 출력할 자료형은 list가 가장 좋아보인다.
```
result = collections.defaultdict(list)
```

애너그램을 찾는 가장 좋은 방법은 단어를 구성하는 문자를 사전식으로 정렬하는 것이다.
`sorted()` 함수는 문자열도 정렬 가능하며, 다만 그 결과가 개별 문자로 쪼개지기 때문에 다시 하나의 문자열로 붙이기 위해서는 `''.join()`이 필요하다.

애너그램 단어를 키로 두고 원래 단어를 값으로 주어 딕셔너리에 넣어준다.
```
for word in strs:
	result[''.join(sorted(word))].append(word)
```

결과는 애너그램별로 묶인 원래 단어들만 보여야 되므로 values로 출력해준다.
```
return result.values()
```

전체 코드는 다음과 같다.
```
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        
        result = collections.defaultdict(list)
        
        for word in strs:
            result[''.join(sorted(word))].append(word)
        
        return result.values()
        
```

![](https://images.velog.io/images/bae12/post/ec2e60f5-5584-4b92-ac2e-59ffdc44c6a7/image.png)


