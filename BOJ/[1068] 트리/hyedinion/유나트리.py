import sys
from collections import deque
input = sys.stdin.readline

def bfs(root, erasenode, graph):
    global leafcount
    queue=deque([root])
    while queue:
        node=queue.popleft()
        if node!=erasenode:
            if node not in graph.keys():
                leafcount+=1
            elif len(graph[node])==1 and erasenode in graph[node]:
                leafcount+=1
            else:
                for child in graph[node]:
                    queue.append(child)

graph=dict()
n=int(input())
for node, parent in enumerate(map(int, input().split())):
    if parent==-1:
        if node not in graph.keys(): graph[node]=[] 
        root=node
    else:
        if parent in graph.keys():
            graph[parent].append(node)
        else:
            graph[parent]=[node]

erasenode=int(input())
leafcount=0

bfs(root,erasenode, graph)
print(leafcount)