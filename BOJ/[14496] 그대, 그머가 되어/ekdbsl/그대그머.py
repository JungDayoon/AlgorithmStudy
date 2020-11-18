import heapq

inf = 9999999
a, b = map(int, input().split())
N, M = map(int, input().split())
adj = [[] for _ in range(N)]
dist = [inf for _ in range(N)]
pq = []

for _ in range(M):
    s, e = map(int, input().split())
    adj[s-1].append([e-1, 1])
    adj[e-1].append([s-1, 1])

dist[a-1] = 0
heapq.heappush(pq, (0, [a-1, 0]))

while pq:
    curr = heapq.heappop(pq)[1]

    if curr[1] > dist[curr[0]]:
        continue
    for next in adj[curr[0]]:
        if next[1] + curr[1] < dist[next[0]]:
            dist[next[0]] = next[1] + curr[1]
            heapq.heappush(pq, (dist[next[0]], [next[0], dist[next[0]]]))

print(dist[b-1] if dist[b-1] < inf else -1)