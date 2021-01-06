# [Programmers]/[12978] 배달

## *- Dijkstra -*

* `ArrayList<Edge>[] adj` : 마을과 사이 길을 나타내는 인접리스트
  * 양방향이므로 양쪽노드에 edge를 추가한다
* `int[] D` : 마을 1에서 각 마을까지의 걸리는 최소 시간
  * `D[1]`은 `0`으로, 나머지는 `Integer.MAX_VALUE`로 초기화
* `boolean[] visited` : 각 노드 방문여부
* 우선순위 큐를 사용해 다익스트라를 구현
* `D` 값을 확인하면서 `K`값 이하라면 `answer++`을 해준다.

