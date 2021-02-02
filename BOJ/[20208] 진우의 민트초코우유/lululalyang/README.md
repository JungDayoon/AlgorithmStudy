# [BOJ]/[20208] 진우의 민트초코우유

## *- backtracking -*

**Main**

* 민초마을의 지도정보를 `map`에 입력받으면서 진우의 집 좌표는 `int[] house`에, 민트초코우유는 `ArrayList<int[]> milkLoc`에 저장한다.

</br>

**Func**

* `void ComputeMaxMilk(int x, int y, int h, boolean[] visited, int milkCnt)`

  : 백트래킹을 통해 민트초코우유의 최대 개수 `MaxMilCnt`를 계산하는 함수

  * `int x, y`: 현재 위치 좌표
  * `int h`: 현재 남은 체력
  * `boolean[] visited`: 이때까지의 각 민트초코 방문 여부
  * `int milkCnt`: 이때까지 먹은 민트초코 개수

  </br>

  * 이 때까지 먹은 민트초코우유 개수(`milkCnt`)가 최대 개수(`MaxMilkCnt`)보다 크고, 남은 체력으로 현재 위치에서 집까지 돌아갈 수 있다면 `MaxMilkCnt`값을 `milkCnt`로 바꿔준다.
  * 모든 민트초코를 스캔하면서
    * 방문하지 않은 민초인데, 현재 위치에서 그 방문하지 않은 민초까지의 거리가 남은 체력으로 갈 수 있는 거리일 때, 그 민초의 방문 여부를 `true`로 바꿔주고 `ComputeMaxMilk(현위치 x, 현위치 y, 남은 체력 - 이동한 체력 + H, 방문여부 visited[], milkCnt+1)`을 호출한다.
    * 호출한 후에는 방문여부를 `false`로 바꿔주고 다음 방문하지 않은 민초를 스캔한다.

</br>

## :speaking_head:

* 백트래킹을 어떻게 적용해야할 지 감이 안와서 지혜의 readme를 참고하여 풀었다. 
  * 어떤 조건을 만족하면 `return`해주는 백트래킹 문제만 풀어보다가 이런 패턴의 문제는 처음 풀어봐서 어려웠다. 여러 문제를 풀어보도록 해야겠다.