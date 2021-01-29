import heapq

inf = 999999


def solution(N, road, K):
    adj = [[] for _ in range(N)]
    dist = [inf for _ in range(N)]
    pq = []

    for path in road:
        adj[path[0] - 1].append([path[1] - 1, path[2]])
        adj[path[1] - 1].append([path[0] - 1, path[2]])

    dist[0] = 0
    heapq.heappush(pq, (0, 0))  # dist, node num

    while pq:
        curr = heapq.heappop(pq)
        if curr[0] > dist[curr[1]]:
            continue
        for next in adj[curr[1]]:
            if curr[0] + next[1] < dist[next[0]]:
                dist[next[0]] = curr[0] + next[1]
                heapq.heappush(pq, (dist[next[0]], next[0]))

    return list(map(lambda t: t <= K, dist)).count(True)


print(solution(5, [[1, 2, 1], [2, 3, 3], [5, 2, 2], [1, 4, 2], [5, 3, 1], [5, 4, 2]], 3))
print(solution(6, [[1, 2, 1], [1, 3, 2], [2, 3, 2], [3, 4, 3], [3, 5, 2], [3, 5, 3], [5, 6, 1]], 4))
