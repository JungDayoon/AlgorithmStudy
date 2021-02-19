parent = {}
rank = {}

def make_set(v):
    parent[v] = v
    rank[v] = 0

def find(v):
    if(parent[v]==v):
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
            if(rank[root1] == rank[root2]):
                rank[root2]+=1
def kruskal(graph):
    mst = []
    total_weight = 0

    for edge in graph:
        weight, u, v = edge
        if(find(u)!=find(v)):
            union(u, v)
            mst.append(edge)
            total_weight+=weight
    total_weight-=mst[-1][0]
    return total_weight

if __name__ == "__main__":
    N, M = map(int, input().split())
    for i in range(N):
        make_set(i+1)
    
    graph = []
    for i in range(M):
        u, v, weight = map(int,input().split())
        graph.append((weight,u,v))
    graph.sort()
    
    print(kruskal(graph))
