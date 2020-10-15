from collections import deque

N, M = map(int, input().split())
adj = [[] for _ in range(N+1)]
indegree = [0 for _ in range(N+1)]
out = ""
for i in range(M):
    a, b = map(int, input().split())
    adj[a].append(b)
    indegree[b] += 1

queue = deque()

for i in range(1, len(indegree)):
    if indegree[i] == 0:
        queue.append(i)

while queue:
    queue = sorted(queue)

    curr = queue.pop(0)
    out += str(curr) + " "
    for next in adj[curr]:
        indegree[next] -= 1
        if indegree[next] == 0:
            queue.append(next)

print(out)