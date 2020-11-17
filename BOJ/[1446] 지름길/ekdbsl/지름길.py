import heapq

N, D = map(int, input().split())
adj = {}
node = set()
inf = 9999999
for _ in range(N):
    s, e, d = map(int, input().split())
    node.add(s)
    node.add(e)
    if s not in adj:
        adj[s] = []
    adj[s].append([e, d])

if 0 not in adj:
    adj[0] = []
    adj[0].append([0, 0])
    node.add(0)

nodeList = sorted(list(node))
for i in range(len(nodeList)-1):
    for j in range(i+1, len(nodeList)):
        if nodeList[i] not in adj:
            adj[nodeList[i]] = []
        adj[nodeList[i]].append([nodeList[j], nodeList[j] - nodeList[i]])

# print(adj)

pq = []
dist = {}
for n in nodeList:
    dist[n] = inf
heapq.heappush(pq, (0, [0, 0]))
dist[0] = 0

while pq:
    curr = heapq.heappop(pq)[1]
    if curr[1] > dist[curr[0]]:
        continue
    if curr[0] in adj:
        for next in adj[curr[0]]:
            if dist[next[0]] > dist[curr[0]] + next[1]:
                dist[next[0]] = dist[curr[0]] + next[1]
                heapq.heappush(pq, (dist[next[0]], [next[0], dist[next[0]]]))

# print(dist)
for key, value in dist.items():
    if key > D:
        break
    prevKey = key
    prevValue = value

print(prevValue + (D-prevKey))
