parent = {}
rank = {}
def init(v):
    parent[v] = v
    rank[v] = 0
def find(v):
    if(parent[v] == v):
        return v
    else:
        return find(parent[v])
def union(u, v):
    root1 = find(u)
    root2 = find(v)
    if(root1!=root2):
        if(rank[root1]>rank[root2]):
            parent[root2] = root1
        else:
            parent[root1] = root2
            if(rank[root1]==rank[root2]):
                rank[root2]+=1
def kruskal(graph):
    total_weight = 0

    for node in graph:
        weight, x, y = node
        if(find(x)!= find(y)):
            union(x, y)
            total_weight+=weight
    return total_weight

if __name__ == "__main__":
    TC = int(input())
    for tc in range(TC):
        N, M = map(int, input().split())
        airplain = []
        for i in range(1, N+1):
            init(i)
        for i in range(M):
            a, b = map(int, input().split())
            airplain.append((1, a, b))
    
        print(kruskal(airplain))

        