import sys
sys.setrecursionlimit(10**4)
def find_cycle(now_p, before_p):
    if(visited[now_p] == 1): return now_p
    visited[now_p] = 1

    for i in graph[now_p]:
        if(i == before_p): continue
        result = find_cycle(i, now_p)
        if(result > 0):
            visited[now_p] = 2
            if now_p == result:
                return -1
            else:
                return result
    return -1

def find_dist():
    queue = []
    for i in range(1, N+1):
        if visited[i] == 2:
            dist[i] = 0
            queue.append(i)
    count = 1
    while(queue):
        temp = []
        
        for q in queue:
            for v in graph[q]:
                if dist[v] == -1:
                    dist[v] = count
                    temp.append(v)
        
        count+=1
        queue = temp
    


def init(N):
    graph = {}
    for i in range(1, N+1):
        graph[i] = []
    return graph
if __name__ == "__main__":
    N = int(input())
    graph = init(N)
    
    visited = [0]*(N+1)
    dist = [-1]*(N+1)

    for i in range(N):
        a, b = map(int, input().split())
        graph[a].append(b)
        graph[b].append(a)

    find_cycle(1, -1)
    find_dist()
    
    for i in range(1, N+1):
        print(dist[i], end=" ")