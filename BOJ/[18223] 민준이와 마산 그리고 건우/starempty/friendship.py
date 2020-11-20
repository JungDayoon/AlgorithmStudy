import collections
import heapq
import sys

v, e, p = map(int, sys.stdin.readline().split())
graph = collections.defaultdict(list)
for i in range(e):
    a, b, c = map(int, sys.stdin.readline().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

#print(graph)

q = [[0, 1]]
dp = collections.defaultdict(int)
while q:
    time, cur = heapq.heappop(q)
    if cur not in dp:
        dp[cur] = time
    for next, weight in graph[cur]:
        if next not in dp:
            nw = weight + time
            heapq.heappush(q, [nw, next])
    #print(dp)
#print(dp)
q2 = [[0, p]]
dp2 = collections.defaultdict(int)
while q2:
    time2, cur2 = heapq.heappop(q2)
    if cur2 not in dp2:
        dp2[cur2] = time2
    for next2, weight2 in graph[cur2]:
        if next2 not in dp2:
            nw2 = time2 + weight2
            heapq.heappush(q2, [nw2, next2])
#print(dp2)
print("SAVE HIM" if dp[v] >= dp[p]+dp2[v] else "GOOD BYE", end='')