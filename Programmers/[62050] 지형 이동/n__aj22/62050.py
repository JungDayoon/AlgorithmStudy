dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
parent, rank = {}, {}
def make_set(v): #initialization
    parent[v] = v
    rank[v] = 0
def find(v): # v 의 최상위 정점을 찾는다.
    if(parent[v]!=v):
        parent[v] = find(parent[v])
    return parent[v]

def union(v, u):#v, u 연결
    root1 = find(v)
    root2 = find(u)

    if(root1 != root2):
        #짧은 트리의 루트가 긴 트리의 루트를 가리키도록
        if(rank[root1] > rank[root2]):
            parent[root2] = root1
        else:
            parent[root1] = root2
            if rank[root1] == rank[root2]:
                rank[root2] +=1

def kruskal(edges):
    mst = []
    edges.sort()
    total_weight = 0
    for edge in edges:
        weight, v, u = edge
        if(find(v)!=find(u)):
            union(v, u)
            total_weight+=weight
            mst.append(edge)
    return total_weight

def isin(y, x, N):
    if(0<=y<N and 0<=x<N):
        return True
    return False
def bfs(visited, height, index, y, x, N, land):
    queue = []
    visited[y][x] = index
    queue.append([y, x])

    while(queue):
        y, x = queue.pop(0)
        for i in range(4):
            nexty, nextx = y+dy[i], x+dx[i]
            if(isin(nexty, nextx, N) and visited[nexty][nextx] == 0):
                if(abs(land[y][x]-land[nexty][nextx])<=height):
                    visited[nexty][nextx] = index
                    queue.append([nexty, nextx])
    
    return
def init(n):
    global parent, rank
    parent, rank = {}, {}
    for i in range(1, n):
        parent[i] = i
        rank[i] = 0
    return

def solution(land, height):
    answer = 0
    N = len(land)
    visited = [[0]*N for _ in range(N)]
    index = 1

    for i in range(N):
        for j in range(N):
            if(visited[i][j] == 0):
                bfs(visited, height, index, i, j, N, land)
                index+=1

    check_ladder = {}

    for i in range(N):
        for j in range(N):
            for k in range(1, 3):
                nexty, nextx = i+dy[k], j+dx[k]
                if(isin(nexty, nextx, N)):
                    if visited[i][j]!= visited[nexty][nextx]:
                        key = str(min(visited[i][j], visited[nexty][nextx]))+"-"+str(max(visited[i][j], visited[nexty][nextx]))
                        value = abs(land[i][j] - land[nexty][nextx])
                        if key in check_ladder.keys():
                            check_ladder[key] = min(check_ladder[key], value)
                        else:
                            check_ladder[key] = value
    edge = []
    for key, value in check_ladder.items():
        v, u = map(int, key.split("-"))
        edge.append([value, u, v])
        edge.append([value, v, u])
    
    init(index)

    answer = kruskal(edge)
    return answer

if __name__ == "__main__":
    land = [[[1, 4, 8, 10], [5, 5, 5, 5], [10, 10, 10, 10], [10, 10, 10, 20]],
    [[10, 11, 10, 11], [2, 21, 20, 10], [1, 20, 21, 11], [2, 1, 2, 1]]]
    height = [3, 1]

    for i in range(2):
        print(solution(land[i], height[i]))