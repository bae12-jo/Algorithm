import sys
si = sys.stdin.readline
N = int(si())
a = si().strip()
ans = 0
# 왼쪽으로 탈출할 수 있는 개수
for x in a:
    if x == '<':
        ans += 1
    else:
        break
# 오른쪽으로 탈출할 수 있는 개수
for x in reversed(a):
    if x == '>':
        ans += 1
    else:
        break
print(ans)
