import collections
import sys
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
def endline(y, x, N, M):
    if(y==M-1 or y == 0 or x == N-1 or x == 0):
        return True
    return False
def isin(y, x, N, M):
    if(0<=x<N and 0<=y<M):
        return True
    return False
def fire(N, M, arr, fire_index):
    check = [[-1]*N for _ in range(M)]
    queue = collections.deque()
    for fire in fire_index:
        y, x = fire
        queue.append([y, x])
        check[y][x] = 0
    while(queue):
        y, x = queue.popleft()
        time = check[y][x]
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx, N, M)):
                
                if(arr[nexty][nextx] == "*" and check[nexty][nextx] == -1):
                    check[nexty][nextx] = 0
                    queue.append([nexty, nextx])
                elif(arr[nexty][nextx] == "." and check[nexty][nextx] == -1):
                    check[nexty][nextx] = time+1
                    queue.append([nexty, nextx])
    return check

def escape(start_position, N, M, arr, check):
    queue = collections.deque()
    queue.append(start_position)
    visited = [[-1]*N for _ in range(M)]
    visited[start_position[0]][start_position[1]] = 0
    while(queue):
        y, x = queue.popleft()

        count = visited[y][x]
        for i in range(4):
            nexty = y + dy[i]
            nextx = x + dx[i]
            if(isin(nexty, nextx, N, M)):
                if(visited[nexty][nextx] == -1 and arr[nexty][nextx] == '.' and (check[nexty][nextx] == -1 or  count+1<check[nexty][nextx])):
                    visited[nexty][nextx] = count+1
                    queue.append([nexty, nextx])
            else:
                return count+1
    return 'INPOSSIBLE'

if __name__ == "__main__":
    T = int(input())
    answer = []
    for t in range(T):
        N, M = map(int,sys.stdin.readline().split())
        arr = []
        start_position = [0, 0] #y, x
        empty = 0
        fire_index = []
        for i in range(M):
            new_arr = list(str(sys.stdin.readline()))
            for j in range(N):
                if(new_arr[j] == '@'):
                    start_position = [i, j]
                    new_arr[j] = '.'
                elif(new_arr[j] == '*'):
                    fire_index.append([i, j])
            arr.append(new_arr)
        check = fire(N, M, arr, fire_index)
        answer.append(escape(start_position, N, M, arr, check))
    for an in answer:
        print(an)
