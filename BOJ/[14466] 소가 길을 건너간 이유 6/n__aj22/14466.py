from collections import deque
dc = [0, 0, -1, 1]
dr = [-1, 1, 0, 0]
def isin(r, c, N):
    if(0<=r<N and 0<=c<N):
        return True
    return False
def notroad(load, nextposition, nowposition):
    if(nextposition in load[nowposition[0]][nowposition[1]]):
        return False
    return True
def bfs(cow, N, load, K, cowmatrix):
    total_count = 0
    
    for cowindex in range(K-1):
        count = 0
        queue = deque()
        queue.append(cow[cowindex])
        visited = [[False]*(N) for _ in range(N)]
        visited[cow[cowindex][0]][cow[cowindex][1]] = True

        while(queue):
            r, c = queue.popleft()
            for i in range(4):
                nextr, nextc = r+dr[i], c+dc[i]
                if(isin(nextr, nextc, N) and not visited[nextr][nextc]):
                    if notroad(load, [nextr, nextc], [r, c]):
                        visited[nextr][nextc] = True
                        queue.append([nextr, nextc])
                        if cowmatrix[nextr][nextc]!=0:
                            count+=1
        cowmatrix[cow[cowindex][0]][cow[cowindex][1]] = 0
        total_count+=(K-cowindex-1-count)
    return total_count

if __name__ == "__main__":
    N, K, R = map(int, input().split())
    loadlist = [[] for _ in range(N)]
    for i in range(N):
        loadlist[i] = [[] for _ in range(N)]

    for i in range(R):
        r1, c1, r2, c2 = map(int, input().split())
        loadlist[r1-1][c1-1].append([r2-1, c2-1])
        loadlist[r2-1][c2-1].append([r1-1, c1-1])

    cow = []
    cowmatrix = [[0]*N for _ in range(N)]
    for i in range(K):
        r, c = map(int, input().split())
        cow.append([r-1, c-1])
        cowmatrix[r-1][c-1] = i+1
    print(bfs(cow, N, loadlist, K, cowmatrix))