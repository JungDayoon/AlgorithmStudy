parent = {}
rank = {}
def init():
    for i in range(V):
        parent[i+1] = i+1
        rank[i+1] = 0
def find(v):
    if(parent[v]== v):
        return v
    else:
        return find(parent[v])
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
def kruskal(graph):
    total_weight = 0

    for edge in graph:
        weight, u, v = edge
        if(find(u)!= find(v)):
            union(u, v)
            total_weight+=weight
    return total_weight
if __name__ == "__main__":
    V, E = map(int, input().split())
    init()
    graph = []
    for i in range(E):
        start, end, weight = map(int, input().split())
        graph.append((weight, start, end))
    graph.sort()
    print(kruskal(graph))

