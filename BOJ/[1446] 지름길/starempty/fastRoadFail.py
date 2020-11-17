import collections
import heapq
import sys

n, d = map(int, input().split())
graph = collections.defaultdict(list)
for _ in range(n):
    a, b, c = map(int, sys.stdin.readline().split())
    if b > d:
        continue
    graph[a].append((b, c))

print(graph)


q = [0,0]
dp = collections.defaultdict(int)

while q:
    cur, length = heapq.heappop(q)
    if cur not in dp:
        dp[cur] = length
        for end, l in graph[cur]:
            nl = length+l
            heapq.heappush(q, [end, nl])
    #print(q)
print(dp)
m = max(dp)
print(dp[m]+(d-m))