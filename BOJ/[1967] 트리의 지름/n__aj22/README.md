# 백준 1967번 : 트리의 지름 

## Algorithm

tree, BFS

## Description

**트리의 지름 구하는 공식**

1. 트리에서 임의의 한 점 x를 잡는다.

2. 정점 x에서 가장 먼 정점 y를 찾는다.

3. 정점 y에서 가장 먼 정점 z를 찾는다.

    *트리의 지름은 정점 y와 z를 연결하는 경로이다.*

**함수 설명**

`init()` : 각 정점에 대한 노드를 저장해 줄 tree 딕셔너리를 초기화해준다.

+ 노드가 N개라면 1~N까지의 키에 value는 [](빈 리스트)를 갖도록 초기화한다.

`find_max(start)` : start 정점 부터 BFS를 이용해 각 노드 까지의 거리를 구한다. 

+ visited : 방문 여부와, 이 정점에 방문했을 때 시작 점 start 부터 이 정점 까지의 거리를 저장해준다.

+ queue : BFS를 돌려줄 queue, 정점과 이 정점 까지의 거리를 저장한다.

+ max_vertex, max_weight : 최대 거리와 최대 거리일 때의 정점을 저장한다. BFS를 하면서 각 정점에 대한 visited 값이 갱신될 때의 거리 값과 max_weight 값을 비교해, max_weight 보다 크다면 max_weight 값과 max_vertex를 갱신한다.

    + max_vertex와 max_weight 값을 return 한다.

`main` : 

+ 입력받은 parent, child, weight에 대해 tree에 parent를 키 값으로 갖는 value에 [child, weight]를, child를 키 값으로 갖는 value에 [parent, weight]를 추가한다.

+ 임의의 정점 x에서 가장 먼 정점 y를 먼저 구한다.
    + x는 1로 정하고 1에서 부터 가장 먼 정점 y를 구한다.

+ 위의 단계에서 구한 y에 대해서 가장 먼 정점 z를 구한다.
    + 이 때의 weight 값이 **트리의 지름**이다.
## Review

트리의 지름에 대한 개념을 배울 수 있어서 좋았다.
  
