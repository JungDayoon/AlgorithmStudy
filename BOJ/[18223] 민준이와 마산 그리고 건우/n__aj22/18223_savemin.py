import heapq
def dijkstra(graph, start):
    distances = {node : float('inf') for node in graph}
    distances[start] = 0
    queue = []
    heapq.heappush(queue, [distances[start],start])

    while(queue):
        current_distance, current_node = heapq.heappop(queue)
        if distances[current_node]<current_distance:
            continue # 현재 거리보다 이미 작으면 계산할 필요가 없음
        for adjacent, weight in graph[current_node].items():
            distance = current_distance+weight
            if distance < distances[adjacent]:
                #계산한 거리가 기존 거리보다 작으면
                #업데이트
                distances[adjacent] = distance
                heapq.heappush(queue, [distance, adjacent])

    return distances


if __name__ == "__main__":
    V, E, P = map(int, input().split())
    mygraph = {}
    for i in range(V):
        mygraph[i+1] = {}
    for i in range(E):
        a,b,c = map(int, input().split())
        mygraph[a][b] = c
        mygraph[b][a] = c

    result = dijkstra(mygraph, 1)

    result2 = dijkstra(mygraph, P)


    if(result[V] == result[P]+result2[V]):
        print("SAVE HIM")
    else:
        print("GOOD BYE")

