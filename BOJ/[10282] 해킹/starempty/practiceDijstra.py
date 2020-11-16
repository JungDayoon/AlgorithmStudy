import sys
from collections import deque

n, m, k, x = map(int, input().split())
info = ['dummy']+[[] for _ in range(n)]  # []*n 하면 인덱스 가지고 추가 불가능


for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    info[a].append(b)


distance = ['dummy']+[-1]*n
distance[x] = 0
q = deque([x])

while q:
    now = q.popleft()
    for next in info[now]:
        if distance[next] == -1:
            distance[next] = distance[now]+1
            q.append(next)
check = False
for i in range(1, n+1):
    if distance[i] == k:
        print(i)
        check = True
if not check:
    print(-1)