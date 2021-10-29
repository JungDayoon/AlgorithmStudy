parent = {}
rank = {}
def kruskal(edge):
    weightSum = 0
    for e in edge:
        w,a,b = e
        if union(a,b):
            weightSum+=w
    return weightSum

def make_set(V):
    for v in range(V):
        parent[v+1] = v+1
        rank[v+1] = 0
    return

def union(x,y):
    x = find(x)
    y = find(y)

    if x==y:
        return False
    if rank[y]>rank[x]:
        parent[x]=y
    else:
        parent[y]=x
        if rank[y]==rank[x]:
            rank[x]+=1
    return True

def find(x):
    if parent[x]==x:
        return x
    else:
        return find(parent[x])

V,E = map(int,input().split())
edge = []

for e in range(E):
    A,B,C = map(int,input().split())
    edge.append([C,A,B])

edge.sort()
make_set(V)
print(kruskal(edge))