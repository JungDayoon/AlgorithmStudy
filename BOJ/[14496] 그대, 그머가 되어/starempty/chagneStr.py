import collections
import heapq
import sys

a, b = map(int, input().split())
n, m = map(int, input().split())
graph = collections.defaultdict(list)

for _ in range(m):
    x, y = map(int, sys.stdin.readline().split())
    graph[x].append((y, 1))
    graph[y].append((x, 1))

dp = collections.defaultdict(int)
q = [(0, a)]
while q:
    time, cur = heapq.heappop(q)
    if cur not in dp:
        dp[cur] = time
        for value, weight in graph[cur]:
            nw = time+weight
            heapq.heappush(q, (nw, value))
    
if b not in dp:
    print(-1)
else:
    print(dp[b])

