# 8
# 7 2 5 6 1 4 2 8
import sys
si = sys.stdin.readline
N = int(si())
a = [0] + list(map(int, si().split()))
dy = [[-1 for _ in range(4)] for __ in range(N + 1)]
dy[0][0] = 0
for i in range(1, N + 1):
    dy[i][0] = max(dy[i - 1][0], dy[i - 1][2])
    if i >= 2:
        dy[i][1] = max(dy[i - 1][1], dy[i - 1][0] - a[i])
    else:
        dy[i][1] = dy[i - 1][0] - a[i]
    if i >= 2:
         dy[i][2] = dy[i - 1][1] + a[i]
print(max(dy[N]))
