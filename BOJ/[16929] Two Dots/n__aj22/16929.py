dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]

def isin(y, x):
    if 0<=y<N and 0<=x<M:
        return True
    return False
def DFS(visited, startingpoint, y, x, arr, alpha, count):

    for i in range(4):
        nexty, nextx = dy[i]+y, dx[i]+x
        if(isin(nexty, nextx)):
            if [nexty, nextx] == startingpoint and count>3:
                return True
            if not visited[nexty][nextx] and arr[nexty][nextx] == alpha:
                visited[nexty][nextx] = True
                if DFS(visited, startingpoint, nexty, nextx, arr, alpha, count+1):
                    return True
                visited[nexty][nextx] = False
    
    return False


def solution(arr, N, M):
    
    for i in range(N):
        for j in range(M):
            visited = [[False]*M for _ in range(N)]
            visited[i][j] = True
            if DFS(visited, [i, j], i, j, arr, arr[i][j], 1):
                return "Yes"

    return "No"
if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    for i in range(N):
        arr.append(list(input()))
    
    print(solution(arr, N, M))