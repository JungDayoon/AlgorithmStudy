import sys
sys.setrecursionlimit(10**5) 
def find_common_parent(u, v):
    #깊이 맞춰주기
    if(depth[u]!=depth[v]):
        count = 0
        diff_mod = 1
        if(depth[u]>depth[v]):
            depth_diff = depth[u]-depth[v]
            while(True):
                if(depth_diff == 1):
                    u = parent[u][count]
                    break
                diff_mod = depth_diff%2
                depth_diff = depth_diff//2
                if(diff_mod == 1):
                    u = parent[u][count]
                count+=1
                
        else:
            depth_diff = depth[v]-depth[u]
            while(True):
                if(depth_diff == 1):
                    v = parent[v][count]
                    break
                diff_mod = depth_diff%2
                depth_diff = depth_diff//2
                if(diff_mod == 1):
                    v = parent[v][count]
                count+=1
    ##u, v의 LCA 찾기 
    if(u!=v):
        for i in range(17, -1, -1):
            if(parent[u][i] != -1 and parent[u][i]!= parent[v][i]):
                u = parent[u][i]
                v = parent[v][i]
        u = parent[u][i]

    return u
def make_tree(count, index, parent_index):
    
    for node in graph[index]:
        if(parent[node][0] == -1 and node != 1):
            parent[node][0] = index
            depth[node] = count+1
            make_tree(count+1, node, index)
    return
def fill_parent():
    #parent[u][k+1] = parent[parent[u][k]][k]
    k = 0
    while(True):
        changeflag = False
        for i in range(1, N+1):
            if(parent[i][k] != -1):
                parent[i][k+1] = parent[parent[i][k]][k]
                changeflag = True
        if(changeflag == False):
            break
        k+=1

if __name__ == "__main__":
    N = int(input())
    parent = [[-1]*18 for _ in range(N+1)]
    depth = [-1]*(N+1)
    graph = {}
    for i in range(1, N+1):
        graph[i] = []
    
    for i in range(N-1):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)
    depth[1] = 0
    make_tree(0, 1, -1)

    #parent 채우기 
    fill_parent()
    
    ##공통 조상 찾기 시작 
    M = int(input())
    for i in range(M):
        u, v = map(int, input().split())
        print(find_common_parent(u, v))
    
