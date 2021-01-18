import heapq
def dijkstra(graph, start):
    distances = {node : float('inf') for node in graph}
    distances[start] = 0
    queue = []
    heapq.heappush(queue, [0, start])

    while(queue):
        now_distance, now_edge = heapq.heappop(queue)
        if(now_distance>distances[now_edge]):
            continue
        for adjacent, weight in graph[now_edge].items():
            distance=weight+now_distance
            if(distances[adjacent]>distance):
                distances[adjacent] = distance
                heapq.heappush(queue, [distance, adjacent])
    return distances
if __name__=="__main__":
    start, end = map(int, input().split())
    N, M = map(int, input().split())
    mygraph = {}
    for i in range(N):
        mygraph[i+1] = {}
    for i in range(M):
        s, e = map(int, input().split())
        mygraph[s][e] = 1
        mygraph[e][s] = 1
    result = dijkstra(mygraph, start)
    if(result[end] == float('inf')):
        print(-1)
    else:
        print(result[end])

