import collections
import sys
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def endline(y, x, N, M):
    if(y==M-1 or y == 0 or x == N-1 or x == 0):
        return True
    return False
def isin(y, x):
    if(0<=x<N and 0<=y<M):
        return True
    return False
def fire():
    firelen = len(fire_index)

    while(firelen):
        y, x = fire_index.popleft()
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx)):
                if(arr[nexty][nextx] == '.'):
                    arr[nexty][nextx] = "*"
                    fire_index.append([nexty, nextx])
        firelen-=1

def escape():
    fire()
    queue = collections.deque()
    queue.append(start_position)
    visited = [[0]*N for _ in range(M)]
    visited[start_position[0]][start_position[1]] = 1
    while(queue):
        qlen = len(queue)
        while(qlen):
            y, x = queue.popleft()
            for i in range(4):
                nexty = y + dy[i]
                nextx = x + dx[i]
                if(isin(nexty, nextx)):
                    if(visited[nexty][nextx] == 0 and arr[nexty][nextx] == '.'):
                        visited[nexty][nextx] = visited[y][x]+1
                        queue.append([nexty, nextx])
                else:
                    return visited[y][x]
            qlen-=1
        fire()
    return 'IMPOSSIBLE'

if __name__ == "__main__":
    T = int(input())
    for t in range(T):
        N, M = map(int,sys.stdin.readline().split())
        arr = []
        start_position = [0, 0] #y, x
        empty = 0
        fire_index = collections.deque()
        for i in range(M):
            new_arr = list(str(sys.stdin.readline()))
            for j in range(N):
                if(new_arr[j] == '@'):
                    start_position = [i, j]
                    new_arr[j] = '.'
                elif(new_arr[j] == '*'):
                    fire_index.append([i, j])
            arr.append(new_arr)
        print(escape())
