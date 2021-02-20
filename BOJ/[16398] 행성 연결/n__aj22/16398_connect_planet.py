def getMinVertex():
    minVertex = 0
    minWeight = float('inf')

    for v in range(N):
        if(False == mst[v] and weights[v]<minWeight):
            minVertex = v
            minWeight = weights[v]
    return minVertex
def prim(start):
    cnt = 0
    mst[start] = True
    weights[start] = 0
    minVertex = start
    while(cnt<N-1):
        for v in range(N):
            if(mst[v] == False and graph[minVertex][v]!=float('inf')):
                if(graph[minVertex][v]<weights[v]):
                    weights[v] = graph[minVertex][v]
        minVertex = getMinVertex()
        if(weights[minVertex] == float('inf')):
            return
        mst[minVertex] = True
        cnt += 1
if __name__ == "__main__":
    N = int(input())
    graph = []
    mst = [False]*N
    weights = [float('inf')]*N 
    for i in range(N):
        num_list = list(map(int, input().split()))
        graph.append(num_list)
    for i in range(N):
        graph[i][i] = float('inf')
    prim(0)
    print(sum(weights))