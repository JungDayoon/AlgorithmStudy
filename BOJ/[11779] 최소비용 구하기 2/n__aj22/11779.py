import heapq
bus = {}
def init(n):
    global bus
    for i in range(n):
        bus[i+1] = {}
def dijkstra(start):
    distances = {node:[float('inf'), start] for node in bus}
    distances[start] = [0, start]
    queue = []
    heapq.heappush(queue, [distances[start][0], start])
    while(queue):
        now_distance, now_node = heapq.heappop(queue)
        if(now_distance>distances[now_node][0]):
            continue
        for node, weight in bus[now_node].items():
            next_distance = now_distance+weight
            if(next_distance<distances[node][0]):
                distances[node] = [next_distance, now_node]
                heapq.heappush(queue,[distances[node][0], node])
    return distances
if __name__ == "__main__":
    n = int(input())
    m = int(input())
    init(n)

    for i in range(m):
        u, v, cost = map(int, input().split())
        if v in bus[u].keys():
            bus[u][v] = min(bus[u][v], cost)
        else:
            bus[u][v] = cost
    start, end = map(int, input().split())
    distances = dijkstra(start)
    route = []
    node = end
    route.append(end)
    while(True):
        if(node == start):
            break
        nextnode = distances[node][1]
        route.insert(0, nextnode)
        node = nextnode
    print(distances[end][0])
    print(len(route))
    for i in route:
        print(i, end = " ")



