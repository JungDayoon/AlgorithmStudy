import heapq
bus = {}
def init():
    global bus
    for i in range(N):
        bus[i+1] = {}
    return
def dijkstra(start):
    global bus
    queue = []
    distances = {node:float('inf') for node in bus}
    distances[start] = 0
    heapq.heappush(queue, [distances[start], start])#거리, node 순

    while(queue):
        now_distance, now_node = heapq.heappop(queue)
        if(distances[now_node]<now_distance):
            continue
        for node, weight in bus[now_node].items():
            next_distance = now_distance+weight
            if(next_distance<distances[node]):
                distances[node] = next_distance
                heapq.heappush(queue, [distances[node], node])

    return distances


if __name__ == "__main__":
    N = int(input())
    M = int(input())
    init()
    for i in range(M):
        u, v, cost = map(int, input().split())
        if(v in bus[u].keys()):
            bus[u][v] = min(bus[u][v], cost)
        else:
            bus[u][v] = cost
    
    start_point, end_point = map(int, input().split())
    print(dijkstra(start_point)[end_point])
