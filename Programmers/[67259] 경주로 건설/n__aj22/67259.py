import heapq
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
total_cost = float('inf')
def isin(y, x, N):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def calculate_cost(dir1, dir2):
    if(dir1 == -1):
        return 100
    elif(dir1 == dir2):
        return 100
    else:
        return 600
    
def dijkstra(N, board):
    distances = [[float('inf')]*N for _ in range(N)]
    distances[0][0] = 0
    queue = []
    heapq.heappush(queue, [0, 0, distances[0][0], -1])
    while(queue):
        y, x, now_distance, dir1 = heapq.heappop(queue)
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx, N)):
                if(board[nexty][nextx] == 1):
                    continue
                roadcost = calculate_cost(dir1, i)
                nextdistance = roadcost+now_distance
                if(nextdistance<=distances[nexty][nextx]):
                    distances[nexty][nextx] = nextdistance
                    heapq.heappush(queue, [ nexty, nextx, distances[nexty][nextx],i])
    return distances
def backtracking(N, board, visited, cost, before, now):
    global total_cost
    if(now == [N-1, N-1]):
        total_cost = min(total_cost, cost)
        return
    
    for i in range(4):
        nexty, nextx = now[0]+dy[i], now[1]+dx[i]
        if(isin(nexty, nextx, N)):
            if(not visited[nexty][nextx] and board[nexty][nextx] == 0):
                now_cost = calculate_cost(before, i)
                if(cost+now_cost>total_cost):
                    continue
                visited[nexty][nextx] = True
                backtracking(N, board, visited, cost+now_cost, i, [nexty, nextx])
                visited[nexty][nextx] = False
    return

def solution(board):
    answer = 0
    N = len(board)
    visited = [[False]*N for _ in range(N)]
    visited[0][0] = True
    distances = dijkstra(N, board)
    for i in distances:
        print(i)
    answer = distances[N-1][N-1]
    return answer

if __name__ == "__main__":
    boards = [ [[0, 0, 1, 0, 1, 1, 0, 0, 0, 0],
[0, 0, 0, 0, 1, 0, 1, 1, 0, 1],
[1, 0, 0, 0, 0, 1, 1, 0, 1, 0],
[0, 0, 0, 0, 0, 0, 1, 0, 0, 0],
[0, 0, 0, 0, 1, 0, 1, 0, 1, 1],
[0, 0, 1, 0, 1, 1, 0, 1, 0, 1],
[0, 1, 0, 0, 1, 0, 0, 0, 1, 0],
[1, 0, 0, 1, 0, 0, 0, 0, 0, 0],
[0, 0, 0, 0, 0, 1, 0, 1, 0, 0],
[1, 0, 0, 0, 0, 0, 0, 0, 1, 0]],
    [[0,0,0],[0,0,0],[0,0,0]],
    [[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,1],[0,0,1,0,0,0,1,0],[0,1,0,0,0,1,0,0],[1,0,0,0,0,0,0,0]],
    [[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]]

    for board in boards:
        print(solution(board))