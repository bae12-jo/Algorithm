# 문제
* 높이가 주어졌을 때 쌓이는 물의 양을 구하라

# 풀이

### 스택 이용하기

가장 먼저 스택을 이용해야겠다는 생각이 들었다. 막상 해보니 구현은 생각보다 어려웠다.

처음에는 직전 보다 높이가 낮아지면 그 차이만큼 물의 양을 계산하려고 했다. 이 방식으로 접근하면 그동안의 가장 높은 높이를 기억해둬야 하는데, 계속 쌓이는 스택의 특징과 맞지 않다는 생각이 들었다. 반대로 이전과 비교하여 현재 높이가 더 높을 때 직전 높이와 얼마나 떨어져 있는지를 구하여 물이 담긴 양을 구하는 것이 스택과 더 잘 맞는 접근이엇다.

기본적으로 주어진 리스트를 훑으며 스택을 쌓아나가는 구조이다. 문제에서 구하는 물의 총량을 volume으로 둔다.

```
stack = []
volume = 0

for i in range(len(height)):
	"""
    fill in here
    """
	stack.append(i)
```

이때 물의 양을 구하기 위해서는 바닥면의 높이가 필요하다. 이것은 직전 높이를 기준으로 한다.
```
bottom = stack.pop()
```

진행하다가 스택에 쌓인 마지막 높이보다 들어오는 높이가 높을 때, 직전 높이를 이용하여 물의 양을 구한다. 스택에 쌓인 것이 하나 뿐이라 pop 연산을 해서 스택이 비어버리면 다음 루프로 건너뛴다.


```
for i in range(len(height)):
	while stack and height[i] > height[stack[-1]]:
		bottom = stack.pop()
		
		if not stack:
			break
		
		width = i - stack[-1] - 1
		amount = min(height[i], height[stack[-1]])-height[bottom]
		volume += width * amount
		
	stack.append(i)
```

width는 현재 높이에서 비교할 다른 높이까지 거리가 얼마나 되는지 나타낸다.
(현재 높이 - 비교할 높이 - 1)

amount는 현재 현재 높이나 비교할 다른 높이 중 더 낮은 것과 앞서 구했던 bottom의 차이를 구한다. 즉 각 단계에서 쌓일 물의 높이를 구하는 것이다.
(min(height[i], height[stack[-1]])-height[bottom])

손으로 풀어보면 다음과 같다.

![](https://images.velog.io/images/bae12/post/9c1e6ee7-6374-4ffa-b380-db36d110c3b9/image.png)

스택을 이용한 풀이의 특징은 물이 층별로 쌓여나간다는 것이다. (뒤에서 소개할 투 포인터 방식은 인덱스 방향을 따라 옆으로 쌓여나간다.) 풀이에서 7번 인덱스를 보면, 처음에 bottom을 직전 높이인 6으로 잡았으나 비교할 직전 높이 4와 높이가 같아서 amount가 측정되지 않았다. 그러나 여전히 스택에 쌓인 높이보다 7이 큰 상황이므로 while 문을 돌면서 새로운 bottom을 4로 잡고 비교할 직전 높이를 3으로 잡아서 amount를 1로 얻어낸다. 즉 while문을 돌면서 이전에 계산한 물의 층은 제외하고 현재 높이에서 가장 가까운 높은 층의 물의 양을 계산하게 된다.

```
class Solution:
    def trap(self, height: List[int]) -> int:   
        stack = []
        volume = 0

        for i in range(len(height)):
            while stack and height[i] > height[stack[-1]]:
                bottom = stack.pop()

                if not stack:
                    break

                width = i - stack[-1] - 1
                amount = min(height[i], height[stack[-1]])-height[bottom]
                volume += width*amount

            stack.append(i)

        return volume
```

![](https://images.velog.io/images/bae12/post/f2a49924-8a0a-4c33-abbe-d3fe704fd707/image.png)

풀어내긴 했지만 솔직히 어려운 풀이다. 직관적으로 이해하기 힘들다.
처음 문제를 봤을 때 생각했던 직전 보다 높이가 낮아지면 그 차이만큼 물의 양을 계산하는 방식이 차라리 좀 더 이해하기 쉽다. 이 풀이의 문제는 가장 높은 곳을 기억하고 있어야 한다는 점이었고, 점점 낮아지는 모양을 만나면 업데이트가 어렵웠다. 그렇다면 전체 중 가장 높은 곳을 중심으로 양쪽에서 다가오는 방식을 생각할 수 있다. 두번째 풀이인 투 포인터 방식이다.



### 투 포인터 이용하기

처음에는 height가 정렬되어 있지 않아서 투 포인터를 쓸 수 없을 것이라고 생각했으나 투 포인터도 사용할 수 있었다. 좌우 포인터가 만나는 pivot을 종료 조건으로 설계해두면 된다. 본 문제에서는 height가 가장 큰 임의의 지점을 종료 지점으로 설정했다. 만약 모든 height의 높이가 비슷해 하나의 단일 종료지점이 없을 수도 있기 때문에, 양쪽이 같은 경우 왼쪽 포인터를 이동한다는 조건도 달아준다. 양쪽 포인터가 만나면 알고리즘이 종료된다.

* 포인터는 왼쪽과 오른쪽 끝 인덱스에서 시작한다. (left=0, right=len(height)-1)
* 왼쪽과 오른쪽에서 각각 left_max, right_max 값을 저장해준다.
* left_max, right_max 값을 비교해서 left_max이 더 커질 때까지 left를 1씩 늘려 오른쪽으로 이동시킨다.
* left_max가 right_max보다 더 커지면 left의 이동을 멈추고 right을 -1씩 줄여 왼쪽으로 이동시킨다.
* 포인터 이동 과정에서 left가 left_max보다 작으면 그 차이를 volume에 채워주고, right가 right_max보다 작아도 그 차이를 volume에 채워준다.
* left가 right를 만날 때까지 이 과정을 반복한다.


![](https://images.velog.io/images/bae12/post/34ba75ac-17a6-4e8a-975a-95cadce7de0e/image.png)

위의 그림은 투 포인터 left와 right가 7에서 만나 멈춘 상황이다. 초록색으로 채워진 구간이 volume이며 채워진 순서를 숫자로 명시해두었다.

포인터가 이동하면서 이전값보다 값이 작아지는 `부분 변곡점 inflection point` 를 만나면 그 차이를 volume으로 채운다. 알고리즘상 왼쪽 포인터가 먼저 종료 pivot에 도달하게 되어있다. (if left_max <= right_max)

```
class Solution:
    def trap(self, height: List[int]) -> int:
        
        # handle exception
        if not height:
            return 0
        
        # initailize variables
        volume = 0
        left, right = 0, len(height)-1
        left_max, right_max = height[left], height[right]
        
         
        # set end-condition
        while left < right:
            left_max, right_max = max(height[left], left_max), max(height[right], right_max)
            
            # move two-pointer to the heightest
            if left_max <= right_max:
                volume += left_max - height[left]
                left += 1
            else:
                volume += right_max - height[right]
                right -=1
        
        return volume
```
![](https://images.velog.io/images/bae12/post/7a0d6d59-2cb5-4d7c-9dd3-45b2dd85acbd/image.png)

