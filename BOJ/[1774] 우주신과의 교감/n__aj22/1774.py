from itertools import combinations
import math
parent = {}
rank = {}
def make_set(v):
    parent[v] = v
    rank[v] = 0
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
    total_weight = 0

    for edge in graph:
        weight, u, v = edge
        if(find(u)!=find(v)):
            union(u, v)
            total_weight+=weight
    return total_weight
def find(v):
    if(parent[v]==v):
        return v
    else:
        return find(parent[v])

def make_edge(edge):
    u = edge[0]
    v = edge[1]
    return math.sqrt(float((u[1]-v[1])**2 + (u[2]-v[2])**2)), u[0], v[0]
if __name__ == "__main__":
    N, M = map(int, input().split())
    god = []
    road = []
    for i in range(N):
        new_list = list(map(int, input().split()))
        new_list.insert(0, i+1)
        make_set(i+1)
        god.append(new_list)
    comb_list = list(combinations(god, 2))
    graph = []
    for i in comb_list:
        graph.append((make_edge(i)))
    
    graph.sort()

    for i in range(M):
        u, v = map(int, input().split())
        union(u, v)
    answer = kruskal(graph)
    print('%.2f' % answer)

    
