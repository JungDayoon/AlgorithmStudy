N, M = map(int, input().split())
S = set()
for _ in range(N):
    newStr = str(input())
    S.add(newStr)

count = 0
for _ in range(M):
    newStr = str(input())
    if newStr in S:
        count += 1

print(count)