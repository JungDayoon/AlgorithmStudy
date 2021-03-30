import heapq
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def isin(y, x, n):
    if(0<=y<n and 0<=x<n):
        return True
    return False
def dijkstra(graph, n):
    distances = [[float('inf')]*n for _ in range(n)]
    distances[0][0] = graph[0][0]
    queue = []
    heapq.heappush(queue, [distances[0][0], 0, 0])

    while(queue):
        now_distance, y, x = heapq.heappop(queue)
        if(distances[y][x]<now_distance):
            continue
        for i in range(4):
            nexty = y+dy[i]
            nextx = x+dx[i]
            if(isin(nexty, nextx, n)):
                next_distance = now_distance + graph[nexty][nextx]
                if(next_distance<distances[nexty][nextx]):
                    distances[nexty][nextx] = next_distance
                    heapq.heappush(queue, [distances[nexty][nextx], nexty, nextx])
    return distances
if __name__ == "__main__":
    t = 1
    while(True):
        N = int(input())
        if(N == 0):
            break
        arr = []
        for i in range(N):
            arr.append(list(map(int, input().split())))
        
        print("Problem %d: %d"%(t, dijkstra(arr, N)[N-1][N-1]))
        t+=1