import heapq

T = int(input())
inf = 99999999
for t_c in range(T):
    n, d, c = map(int, input().split())
    adj = [[] for _ in range(n)]
    pq = []
    distance = [inf for _ in range(n)]
    for _ in range(d):
        a, b, s = map(int, input().split())
        adj[b-1].append([a-1, s])
    distance[c-1] = 0
    heapq.heappush(pq, (0, [c-1, 0]))

    while pq:
        curr = heapq.heappop(pq)[1]
        if curr[1] > distance[curr[0]]:
            continue
        for next in adj[curr[0]]:
            if distance[curr[0]] + next[1] < distance[next[0]]:
                distance[next[0]] = distance[curr[0]] + next[1]
                heapq.heappush(pq, (distance[next[0]], [next[0], distance[next[0]]]))

    res = []
    for d in distance:
        if d != inf:
            res.append(d)
    print("{} {}".format(len(res), max(res)))