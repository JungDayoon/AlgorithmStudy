def init(N):
    graph = {}
    for i in range(1, N+1):
        graph[i] = {}
    return graph
def bfs(start, graph, k):
    visited = [-1]*(N+1)
    visited[start] = 0
    answer = 0
    queue = []
    queue.append([start,float('inf')])

    while(queue):
        vertex, num = queue.pop(0)
        for next_vertex, weight in graph[vertex].items():
            min_num = min(num, weight)
            if(visited[next_vertex]==-1):
                if(min_num>=k):
                    answer+=1
                visited[next_vertex] = min_num
                queue.append([next_vertex, min_num])
    print(visited)
    return answer

if __name__ == "__main__":
    N, Q = map(int, input().split())
    graph = init(N)
    for i in range(N-1):
        p, q, r = map(int, input().split())
        graph[p][q] = r
        graph[q][p] = r
    
    print(graph)

    for i in range(Q):
        k, v = map(int, input().split())
        print(bfs(v, graph, k))
