1.
# 4578
# 1 4 99 35 50 1000
# 1999
# 2 11 20 100 200 600
import sys
si = sys.stdin.readline
W = int(si())
weights = [1, 5, 10, 50, 100, 500]
costs = list(map(int, si().split()))
dp = [[0 for _ in range(W + 1)] for __ in range(6)]
# 0번 보석만 사용했을 때를 가정한 초깃값
for i in range(W + 1):
    dp[0][i] = costs[0] * i
def dp_version():
    global W
    # 점화식
    for k in range(1, 6):
        for w in range(1, W + 1):
            dp[k][w] = dp[k - 1][w]
            if w - weights[k] >= 0:
                dp[k][w] = min(dp[k][w], dp[k][w - weights[k]] + costs[k])
    # 정답
    print(dp[5][W])
def greedy_version():
    global W
    jewels = list(zip(weights, costs))
    jewels.sort(key=lambda x: x[1]/x[0])
    ans = 0
    for w, c in jewels:
        cnt = W // w
        W %= w
        ans += cnt * c
    print(ans)
greedy_version()
