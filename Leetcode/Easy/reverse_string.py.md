# 문제

* 주어진 문자열 뒤집기
* 공간 복잡도 O(1)

# 풀이

(1) index slicing

```
# index slicing (not working) in-place O(1) memory
s = s[::-1]
```

가장 쉽게 생각할 수 있는 방식이지만 본 문제에서는 동작하지 않는다. = 할당 연산자는 별도의 객체를 생성하여 참조를 복사한다. 

```
# index slicing (working)
s[:] = s[::-1]
```

[:] 슬라이싱을 쓰면 참조가 아닌 값을 복사한다. 공간 복잡도를 피해갈 수 있는 트릭이지만 일반적이지 않다.

![](https://images.velog.io/images/bae12/post/a4b045b7-216c-4462-bb7c-2a2dd3dcab95/image.png)


(2) reverse()
```
s.reverse()
```

다음으로 간단한 방법은 reverse()를 사용하는 것이다. 시간 복잡도는 O(n)이다.

![](https://images.velog.io/images/bae12/post/262d51b5-2438-47c8-a608-6038b0467536/image.png)

(3) two-pointer  방식
```
# Using Two Pointer
start, end = 0, len(s)-1
while start < end:
	s[start], s[end] = s[end], s[start]
  start += 1
	end -= 1
```
포인터를 2개 이용해 양끝에서 중간으로 다가오며 swap 해준다. 가장 전통적인 풀이이다.

![](https://images.velog.io/images/bae12/post/c67bce06-37a9-4e7d-b51b-85a5da525afd/image.png)
