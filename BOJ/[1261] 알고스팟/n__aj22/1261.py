import collections
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def isin(y, x):
    if(0<=y<M and 0<=x<N):
        return True
    return False

def dijkstra(graph):
    visited = [[float('inf')]*N for _ in range(M)]
    visited[0][0] = 0
    queue = collections.deque()
    queue.append([0, 0])
    while(queue):
        y, x = queue.popleft()
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx)):
                new_weight = visited[y][x]+int(graph[nexty][nextx])
                if(visited[nexty][nextx]>new_weight):
                    visited[nexty][nextx] = new_weight
                    if(graph[nexty][nextx] == '1'):
                        queue.append([nexty, nextx])
                    else:
                        queue.appendleft([nexty, nextx])
    return visited
if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    for i in range(M):
        arr.append(list(input()))
    print(dijkstra(arr)[M-1][N-1])