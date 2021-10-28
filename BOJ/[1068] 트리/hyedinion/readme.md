# [BOJ]/[1068] 트리

## 분류
tree, bfs

## 그래프 만들기
```python
graph = {}
root = -1
for i,pnode in enumerate(graph_list):
    if pnode==-1:
        root = i
        continue
    if pnode in graph.keys():
        graph[pnode].append(i)
    else:
        graph[pnode] = [i]
```
> 1. 부모 노드가 주어졌을 때 graph[pnode]가 이미 존재하면 append해주고 없으면 graph[pnode]=[i]로 설정해준다.
> 2. root 노드가 0번노드가 아닐 수 도 있기 때문에 조심!
> - 그래프 만들기 연습을 많이 할것.

## 접근법
1. 그래프를 그리고 지울 node가 root 노드면 0을 return
2. root노드가 아니면 dfs로 연결된 graph를 삭제. 근데 삭제 node의 부모가 빈 배열이 될 수 있으므로(자식 node가 1개) 빈배열이면 삭제 해 주어야함
3. bfs로 자식이 없는 node가 있으면 answer+1해준다
## 후기
생각보다 너무 어렵게 풀었다.<br>
유나코드 참조해서 공부하기<br>
예외처리 잘하기<br>