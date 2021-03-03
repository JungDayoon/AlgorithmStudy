import collections
dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]
parent = {}
rank = {}
def init(v):
    parent[v] = v
    rank[v] = 0
def isin(y, x):
    if(0<=y<N and 0<=x<M):
        return True
    return False
def make_bridge(direction, start, y, x, length):
    
    if(island[y][x]!=start and island[y][x]!=0):
        return length-1, island[y][x]
    nexty = y + dy[direction]
    nextx = x + dx[direction]
    if(isin(nexty, nextx)==False):
        return -1, -1

    if(island[nexty][nextx]==start):
        return -1, -1
    return make_bridge(direction, start, nexty, nextx, length+1)
def union(u, v):
    root1 = find(u)
    root2 = find(v)
    if(root1 != root2):
        if(rank[root1]>rank[root2]):
            parent[root2] = root1
        else:
            parent[root1] = root2
            if(rank[root1] == rank[root2]):
                rank[root2]+=1
    return
def find(v):
    if(parent[v] == v):
        return v
    else:
        return find(parent[v])
def kruskal(graph):
    total_weight = 0
    edge_num = 0
    for edge in graph:
        weight, u, v = edge
        if(find(u)!=find(v)):
            edge_num+=1
            union(u, v)
            total_weight+=weight
    return total_weight, edge_num

def check_island(y, x, index):
    island[y][x] = index
    q = collections.deque()
    q.append([y, x])
    while(q):
        nowy, nowx = q.popleft()
        for i in range(4):
            nexty = nowy + dy[i]
            nextx = nowx + dx[i]
            if(isin(nexty, nextx)):
                if(island[nexty][nextx] == 0 and arr[nexty][nextx] == 1):
                    island[nexty][nextx] = index
                    q.append([nexty, nextx])
    return
if __name__ == "__main__":
    N, M = map(int, input().split())
    arr = []
    for i in range(N):
        arr.append(list(map(int, input().split())))
    island = [[0]*M for _ in range(N)]
    island_num = 0
    for i in range(N):
        for j in range(M):
            if(island[i][j] == 0 and arr[i][j] == 1):
                island_num+=1
                check_island(i, j, island_num)

    for i in range(1, island_num+1):
        init(i)
    graph = []
    for i in range(N):
        for j in range(M):
            if(island[i][j]!=0):
                start_island = island[i][j]
                for k in range(4):
                    nexti = i + dy[k]
                    nextj = j + dx[k]
                    if(isin(nexti, nextj)):
                        if(island[nexti][nextj] == 0):
                            bridge_length, target_island = make_bridge(k, start_island, i, j, 0)
                            if(bridge_length>=2):
                                graph.append((bridge_length, start_island, target_island))
   
    graph.sort()
    weight, edge_num = kruskal(graph)
    if(edge_num != island_num-1):
        print(-1)
    else:
        print(weight)