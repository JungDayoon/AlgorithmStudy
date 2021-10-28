from collections import deque

def bfs(queue,graph):
    global answer
    while queue:
        n = queue.popleft()
        if n in graph.keys():
            for i in graph[n]:
                queue.append(i)
        else:
            answer+=1

def remove(graph,n):
    if n in graph.keys():
        for i in graph[n]:
            remove(graph,i)
        del graph[n]
    graph[graph_list[n]].remove(n)

N = int(input())
graph_list = list(map(int,input().split()))
graph = {}
root = -1
answer = 0
for i,pnode in enumerate(graph_list):
    if pnode==-1:
        root = i
        continue
    if pnode in graph.keys():
        graph[pnode].append(i)
    else:
        graph[pnode] = [i]

remove_node = int(input())
if remove_node == root:
    print(0)
else:
    remove(graph,remove_node)
    if not graph[graph_list[remove_node]]:
        del graph[graph_list[remove_node]]
    queue = deque()
    if graph:
        queue.append(root)
        bfs(queue,graph)
    print(answer)

    '''
12
4 2 6 0 9 6 -1 2 2 7 6 7 
1

4
-1 0 1 2
2
'''