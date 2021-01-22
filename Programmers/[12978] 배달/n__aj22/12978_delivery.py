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
            distance = weight + now_distance
            if(distances[adjacent]>distance):
                distances[adjacent] = distance
                heapq.heappush(queue, [distance, adjacent])
    return distances

def make_road(N, road):
    arr = {}

    for i in range(N+1):
        arr[i] = {}
    
    for i in road:
        if(i[1] in arr[i[0]]):
            if(arr[i[0]][i[1]]<i[2]):
                continue
        arr[i[0]][i[1]] = i[2]
        arr[i[1]][i[0]] = i[2]
    
    return arr

 
def solution(N, road, K):
    answer = 0

    arr = make_road(N, road)
    di = dijkstra(arr, 1)

    for i in di:
        if(di[i]<=K):
            answer+=1

    return answer


if __name__ == "__main__":
    N = 6
    road = [[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]]
    K = 4
    solution(N, road, K)