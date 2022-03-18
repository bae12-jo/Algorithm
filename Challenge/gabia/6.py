# 3 1
# 3 4 5
# 2 3 4
# 1 2 3
# 5 2
# 2 1 1 0 1
# 1 2 0 3 0
# 0 1 5 1 2
# 0 0 1 3 1
# 1 2 0 1 1
import sys
si = sys.stdin.readline
N, K = map(int, si().split())
a = [[0 for _ in range(0)]]
for _ in range(N):
    a.append([0] + list(map(int, si().split())))
S = [[0 for _ in range(N + 1)] for __ in range(N + 1)]
for i in range(1, N + 1):
    for j in range(1, N + 1):
        S[i][j] = a[i][j] + S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1]
def get_sum(x1, y1, x2, y2):
    return S[x2][y2] - S[x1 - 1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1 - 1]
        
dyA = [[0 for _ in range(N + 2)] for __ in range(N + 2)]
dyB = [[0 for _ in range(N + 2)] for __ in range(N + 2)]
dyC = [[0 for _ in range(N + 2)] for __ in range(N + 2)]
dyD = [[0 for _ in range(N + 2)] for __ in range(N + 2)]
for i in range(K, N + 1):
    for j in range(K, N + 1):
        dyA[i][j] = max(
            dyA[i - 1][j],
            dyA[i][j - 1],
            get_sum(i - K + 1, j - K + 1, i, j)
        )
for i in range(K, N + 1):
    for j in range(N - K + 1, 0, -1):
        dyB[i][j] = max(
            dyB[i - 1][j],
            dyB[i][j + 1],
            get_sum(i - K + 1, j, i, j + K - 1)
        )
for i in range(N - K + 1, 0, -1):
    for j in range(K, N + 1):
        dyC[i][j] = max(
            dyC[i + 1][j],
            dyC[i][j - 1],
            get_sum(i, j - K + 1, i + K - 1, j)
        )
for i in range(N - K + 1, 0, -1):
    for j in range(N - K + 1, 0, -1):
        dyD[i][j] = max(
            dyD[i + 1][j],
            dyD[i][j + 1],
            get_sum(i, j, i + K - 1, j + K - 1)
        )
ans = 0
for r in range(1, N + 1):
    for c in range(1, N + 1):
        A = dyA[r][c]
        B = dyB[r][c + 1]
        C = dyC[r + 1][c]
        D = dyD[r + 1][c + 1]
        arr = [A, B, C, D]
        arr.sort()
        ans = max(ans, arr[-1] + arr[-2])
        if ans == 16:
            ans = ans
print(ans)
