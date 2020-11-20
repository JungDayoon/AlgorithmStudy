import heapq

inf = 999999

V, E, P = map(int, input().split())
adj = [[] for _ in range(V)]
dist = [inf for _ in range(V)]
pq = []

for _ in range(E):
    a, b, c = map(int, input().split())
    adj[a - 1].append([b - 1, c])
    adj[b - 1].append([a - 1, c])

dist[0] = 0
heapq.heappush(pq, (0, 0))  # distance, node num

while pq:
    curr = heapq.heappop(pq)
    if curr[0] > dist[curr[1]]:
        continue
    for next in adj[curr[1]]:
        if curr[0] + next[1] < dist[next[0]]:
            dist[next[0]] = curr[0] + next[1]
            heapq.heappush(pq, (dist[next[0]], next[0]))

pq = []
dist2 = [inf for _ in range(V)]
dist2[P - 1] = 0
heapq.heappush(pq, (0, P - 1))

while pq:
    curr = heapq.heappop(pq)
    if curr[0] > dist2[curr[1]]:
        continue
    for next in adj[curr[1]]:
        if curr[0] + next[1] < dist2[next[0]]:
            dist2[next[0]] = curr[0] + next[1]
            heapq.heappush(pq, (dist2[next[0]], next[0]))

# print(dist)
# print(dist2)
if dist[P - 1] + dist2[V - 1] <= dist[V - 1]:
    print("SAVE HIM")
else:
    print("GOOD BYE")
