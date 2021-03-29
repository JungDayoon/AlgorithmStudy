import heapq
road = {}
def init():
    global road
    for i in range(N):
        road[i+1] = {}

def dijkstra(start):
    distances = {node : float('inf') for node in road}
    distances[start] = 0
    queue = []
    heapq.heappush(queue, [distances[start], start])

    while(queue):
        now_distance, now_node = heapq.heappop(queue)

        if(distances[now_node]<now_distance):
            continue
        for node, weight in road[now_node].items():
            check_weight = weight+now_distance
            if(check_weight<distances[node]):
                distances[node] = check_weight
                heapq.heappush(queue, [distances[node], node])
    return distances
    
if __name__ == "__main__":
    N, M, X = map(int, input().split())
    total_distance = [0 for _ in range(N)]
    init()
    for i in range(M):
        u, v, cost = map(int, input().split())
        if v in road[u]:
            road[u][v] = max(road[u][v], cost)
        else:
            road[u][v] = cost
    back_home = dijkstra(X)
    for node, distance in back_home.items():
        total_distance[node-1] = distance
    for i in range(N):
        if i+1 != X:
            go_party = dijkstra(i+1)
            total_distance[i]+=go_party[X]
    print(max(total_distance))    
