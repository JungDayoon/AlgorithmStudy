# 백준 1261 : 알고스팟

## Algorithm

dijkstra, 0-1 BFS

## Description

### **0-1 BFS 알고리즘**
**해결할 문제**
1. 그래프 G에 V개의 정점과 E개의 간선이 있다.
2. 이 그래프는 가중치 그래프지만, 가중치는 0또는 1이다.
3. 이 그래프에서 최단 경로를 찾는 효율적인 코드를 구한다.

**로직**
    
    BFS와 전체 로직은 동일하지만 다른 점은 다익스트라처럼 큐에는 오직 이전 정점을 통해 최단 거리가 줄어든 정점만 큐에 삽입한다. 
    동시에 큐는 항상 시작점으로부터의 거리에 대해 정렬된 상태이다.

`dijkstra(graph)` : 

1. visited : 전체가 무한대 값으로 초기화 된 M*N 배열이다.

+ 처음에 visited[0][0] = 0 으로 초기화한다.(시작점)

2. queue : 앞, 뒤 모두 append가 가능한 덱(deque)을 사용한다.
+ 처음에 [0, 0]를 append한다.

3. queue가 존재할 동안 다음 과정을 반복한다.(BFS)
+ 첫번째 vertex를 pop한다. y, x
+ pop 된 y, x를 기준으로 상, 하, 좌, 우 네 방향을 확인한다.
+ 각 방향으로 이동할 수 있고, `visited[y][x]+graph[nexty][nextx]` 값이 원래의 `graph[nexty][nextx]`보다 작다면 `visited[nexty][nextx]` 값을 relax 한다.(dijkstra)

    + `graph[nexty][nextx]` 가 1이었다면 : queue의 끝에 [nexty, nextx]를 추가한다.

    + `graph[nexty][nextx]` 가 0이었다면 : queue의 앞에 [nexty, nextx]를 추가한다.

    + 이유? 큐는 항상 시작점으로부터의 거리에 대해 정렬된 상태여야 하기 때문에 0인 경우를 앞으로 둬야한다.

4. 모든 과정을 완료하고 나면 visited에는 [0, 0]에서 각 위치 까지 최단 거리를 저장하고 있다.

+ 따라서 답은 visited[M-1][N-1] 값을 출력하면 된다.


## Review

0-1 BFS 알고리즘에 대해 배울 수 있었다.

