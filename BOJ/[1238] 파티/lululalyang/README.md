# [BOJ]/[1238] 파티

## - Dijkstra -

* `int[][] dist`

  `dist[i][j]` : `i`마을에서 `j`마을까지 걸리는 시간의 최소값

### solution

* 우선순위 큐를 이용하여 Dijkstra를 구현.

  * `N`개의 각 마을을 출발지로 한 후, 나머지 마을까지 걸리는 시간의 최솟값을 구한다 => `dist`

* `i`마을에 사는 학생이 `X`마을로 갔다가 다시 돌아오는 최단 시간

  = `dist[i][X] + dist[X][i]`

  * 이 값의 최대값이 결과값