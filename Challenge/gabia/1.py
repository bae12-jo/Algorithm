import sys
si = sys.stdin.readline
A, B, N = map(int, si().split())
def gcd(p, q):  # p와 q의 최대공약수
    return p if q == 0 else gcd(q, p % q)
G = gcd(A, B)
L = A * B // G
cntA = N // A  # A의 배수 개수
cntL = N // L  # L의 배수 개수
print(cntA - cntL)
