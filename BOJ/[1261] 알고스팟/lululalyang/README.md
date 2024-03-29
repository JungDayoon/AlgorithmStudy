# [BOJ]/[1261] 알고스팟

## *- Dijkstra, BFS -*

* `int[][] map` : `0`이면 빈공간, `1`이면 벽
* `int[][] breakWall` : 해당 좌표까지 오는데 부숴야 하는 벽의 최소 개수
  * 모든 칸을 최대값 (`Integer.MAX_VALUE`)로 초기화한다.
* `class Wall` : (`x`, `y`) 현재 좌표, `breakCnt` 벽을 부신 개수

### solution

1. `q`에 첫 위치 `(0, 0)`과 벽을 부시지 않았으므로 `0`을 `add()`한다.

2. `q`가 빌 때까지 아래의 과정을 반복한다.
   1. `q`에서 원소하나를 `poll()` => `x`, `y`, `cnt`
      * 위치의 상하좌우를 확인
   2. 그 위치가 범위 내에 존재할 때,
      * 그 위치가 벽이라면, 벽을 부숴야 그 위치로 갈 수 있으므로 `cnt+1`
      * 빈공간이라면, 그대로 `cnt`
   3. 위에서 구한 현재 위치로 갈 때의 필요한 벽 부신 횟수가 `breakWall`의 값보다 작을 때에만 값을 갱신하고, 그 값과 위치를 `q`에 `add()`한다.
   4. `q`에서 꺼낸 위치가 도착점이라면, 그때의 `cnt`와 결과값 `res`를 비교해 작은 값으로 `res`를 갱신
   5. 만약, `q`에서 꺼낸 횟수가 `breakWall`값보다 크다면, 위 과정을 진행하지않고 다음 원소를 확인한다.
3. `res`가 도착점까지 가는데 부숴야 하는 벽의 최소 개수

