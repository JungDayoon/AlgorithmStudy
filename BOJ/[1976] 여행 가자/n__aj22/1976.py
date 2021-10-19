def bfs(visited, index, graph, start):

    visited[start] = index
    queue = []
    queue.append(start)

    while(queue):
        vertex = queue.pop(0)
        for v in graph[vertex]:
            if(visited[v] == -1):
                visited[v] = index
                queue.append(v)
    return 

if __name__ == "__main__":
    N = int(input())
    M = int(input())

    graph = {i:[] for i in range(1, N+1)}

    for i in range(1, N+1):
        newlist = list(map(int, input().split()))
        for j in range(1, N+1):
            if(newlist[j-1] == 1):
                graph[i].append(j)
    plan = list(map(int, input().split()))
    plan = list(set(plan))
    visited = [-1]*(N+1)

    index = 0
    for i in range(1, N+1):
        if(visited[i] == -1):
            bfs(visited, index, graph, i)
            index+=1

    startindex = visited[plan[0]]

    flag = True
    for i in range(1, len(plan)):
        if(visited[plan[i]]!= startindex):
            flag = False
    if(flag):
        print("YES")
    else:
        print("NO")
