import heapq

def dijkstra(graph, start):
    distances = {node : float('inf') for node in graph}
    distances[start] = 0
    queue = []
    heapq.heappush(queue,[0, start])
    while(queue):
        now_distance, now_edge = heapq.heappop(queue)
        if(now_distance>distances[now_edge]):
            continue
        for adjacent, weight in graph[now_edge].items():
            distance = weight+now_distance
            if(distances[adjacent]>distance):
                distances[adjacent] = distance
                heapq.heappush(queue, [distance, adjacent])

    return distances
def check_com(graph):
    num = 0
    max_time = 0
    for i in graph.items():
        if(i[1] != float('inf')):
            num+=1
            max_time = max(max_time, i[1])

    return num, max_time
if __name__ == "__main__":
    test_case = int(input())
    for t in range(test_case):
        n, d, c = map(int, input().split())
        mygraph = {}
        for i in range(n):
            mygraph[i+1] = {}
        for i in range(d):
            a, b, s = map(int, input().split())
            mygraph[b][a] = s
        result = dijkstra(mygraph, c)
        num, time = check_com(result)
        print(num, time)


