# 문제
* 가장 긴 팰린드롬을 찾아라

# 풀이

1. 
글자수가 1개이거나 전체가 팰린드롬인 경우
문자열을 그대로 반환한다.
```
# return s when it is a palindrome itself.
        if len(s)<2 or s==s[::-1]:
            return s
```


2.
투 포인터를 이용해 팰린드롬인 경우 좌우를 넓혀간다.
```
# find palindrome and expand right and left
        def expand(left: int, right: int) -> str:
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left += 1
                right -= 1
                return s[left+1:right]
```


3.
팰린드롬은 2가지 패턴으로 나눌 수 있다.

(1) aa 짝수개로 확장되는 경우
(2) aba 홀수개로 확장되는 경우

따라서 2개의 포인터를 슬라이딩 윈도우처럼 좌에서 우로 움직이며 최대치를 찾는다.

```
class Solution:
    def longestPalindrome(self, s: str) -> str:
        
        
        # return s when it is a palindrome itself.
        if len(s) < 2 or s == s[::-1]:
            return s
        
        # ind palindrome and expand right and left
        def expand(left: int, right: int) -> str:
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            return s[left + 1:right]


        result = ''
        # find longest palindromic substring
        for i in range(len(s) - 1):
            result = max(result,
                         expand(i, i + 1),
                         expand(i, i + 2),
                         key=len)
        return result
```

![](https://images.velog.io/images/bae12/post/9189caf7-f3fd-412b-b945-d7fdd9cb8a4e/image.png)

