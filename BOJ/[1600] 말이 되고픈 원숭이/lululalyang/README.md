# [BOJ]/[1600] 말이 되고픈 원숭이

## - BFS -

**Func**

`void findMinMove(int K, int[][] map, int W, int H)`

: BFS를 통해 원숭이의 최소 동작 수를 구한다.

* `Queue<int[]> q = new LinkedList<>()`
  * `q`에는 {`x`좌표, `y`좌표, 남은 `K`값, 이때까지의 동작 수 `moveCnt`}를 담는다.
* 남은 K값 `remainK`가 `0`보다 크면 말의 이동처럼 동작한다. 
  * `q`에는 {`x`, `y`, `remainK-1`, `moveCnt+1`}를 `add()`

* 그 후,  `remainK`값에 상관없이 상하좌우로도 이동시킨다.

  * `q`에는 {`x`, `y`, `remainK`, `moveCnt+1`}을 `add()` -> `remainK`값은 변하지 않음

* 매 경우마다, 이동한 위치가 `map`내에 존재하는지, 

  이동 동작 수 `moveCnt`가 이때까지의 목적지까지의 최소동작 수 `minMove`보다 작은지,

  방문하지 않았던 곳인지(`visited[rx][ry][remainK]==false`) 확인해준다.

  => 방문 여부는 `x`, `y`좌표와 `K`수로 구분하여 저장한다.

## :speaking_head:

* 처음에 방문여부를 체크하지 않아서 시간초과가 났다ㅠ 신경써서 풀자