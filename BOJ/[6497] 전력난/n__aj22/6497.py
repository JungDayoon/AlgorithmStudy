parent = {}
rank = {}
answer = 0
def make_set(v):
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
            if(rank[root1] == rank[root2]):
                rank[root2]+=1
def kruskal(graph):
    for edge in graph:
        weight, u, v = edge
        if(find(v)!=find(u)):
            union(u, v)
            global answer
            answer-=weight
if __name__ == "__main__":
    while(True):
        parent = {}
        rank = {}
        m, n = map(int, input().split())
        if(m == 0 and n == 0):
            break
        for i in range(m):
            make_set(i)
        graph = []
        answer = 0
        for i in range(n):
            x, y, z = map(int, input().split())
            graph.append((z, x, y))
            answer+=z
        graph.sort()
        kruskal(graph)
        print(answer)
