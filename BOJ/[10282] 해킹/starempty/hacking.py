import collections
import heapq
import sys


T = int(input())
for tc in range(T):
    n, d, c = map(int, sys.stdin.readline().split())
    graph = collections.defaultdict(list)
    for i in range(d):
        a, b, s = map(int, sys.stdin.readline().split())
        graph[b].append((a, s))

    dp = collections.defaultdict(int)
    q = [(0,c)]
    while q:
        time, cur = heapq.heappop(q)
        if cur not in dp:
            dp[cur] = time
            for value, weight in graph[cur]:
                nw = time+weight
                heapq.heappush(q, (nw, value))
    print(dp)
    print(len(dp), max(dp.values()), end=' ')
    print()