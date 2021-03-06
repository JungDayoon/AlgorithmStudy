# [SWEA]/[2105] 디저트 카페

## *- Backtracking -*

* `int dx[], dy[]`: index 0부터 3까지 오른쪽 아래,  왼쪽 아래, 왼쪽 위, 오른쪽 위 방향으로 이동 시의 `x`와 `y`좌표의 증감.

* `int sx, sy`: 사각형을 그리는 시작점의 `x`, `y`좌표 (마지막에 돌아오는 것을 확인하기 위함)

* `ArrayList<Integer> d`: 사각형을 그리며 먹을 수 있는 디저트 수를 담은 리스트 

* ***in `Main`***

  * `map`의 `row`는 `0`에서 `N-3`까지, `col`은 `1`에서 `N-2`까지를 사각형의 시작점으로 한다.

    (`map`의 꼭짓점은 시작점이 될 수 없고, 최소길이인 모서리`1`의 사각형을 그리려면 `row`의 최대값은 `N-3`이다. (`map`의 index는 `0`~`N-1`))

  * 시작점 저장해놓고 `calculationCnt()` 호출.

  * `maxDessert`가 `0`이면 먹을 수 있는 디저트가 없으므로 `-1` 출력

    </br>

* `void calculationCnt(int x, int y, int dir, int edge1, int edge2, int[][] map)`

  * `int x, y`: 현재 위치의 `x`, `y`좌표

  * `int dir`: 현재 향하고 있는 방향 (`0`: 오른쪽 아래/`1`: 왼쪽 아래/`2`: 왼쪽 위/`3`: 오른쪽 위)

  * `int edge1`: 사각형에서 `\`모양 모서리의 길이

  * `int edge2`: 사각형에서 `/`모양 모서리의 길이

  * `dir`에 따라, 따로 처리해준다

    * `dir == 0, 1`일 때

      방향에 따라 다음 위치 찾아(`rx`, `ry`) 그 위치가 `map`을 벗어나지 않고,  `map`값이 이전에 `d`에 담은 디저트 수에 존재하지 않는 값이면 `d.add()`한다. 

      각 방향에 맞는 `edge`길이 늘려주면서 `calculationCnt()`호출해주고, 위 조건을 만족하지 못하면 `backtracking`후, 다음 방향으로 바꿔준다. (`0`은 `1`로(`edge2`길이 1늘려주면서), `1`은 `2`로)

      </br>

    * `dir == 2`일 때

      `edge1`만큼 `x`, `y`좌표를 방향에 맞춰 이동시켜주면서 그 위치가 `map`벗어나지 않고 `d`에 존재하지 않는 값이면 `d.add()`해준다. 그 후, `dir=3`으로 바꿔서 `calculation()`호출

      위치가 위 조건 만족하지 않으면 `return`해서 `backtracking`

    * `dir == 3`일 때

      `edge2`만큼 방향 맞춰 이동시켜주면서 위 조건 만족하지 않으면 `return`후 `backtracking`.

      `edge2`만큼 이동 후, 시작점에 다시 도착했다면, 그 때의 사각형의 길이 즉, `d.size()`와 기존의 `maxDessert`중 최대값을 저장한다.

      

      </br>

      

  ## :speaking_head:

  백트래킹 문제는 몇 번 풀어도 풀어도 어려운 것 같다 .. 더 많이 풀어보면서 여기저기 적용할 수 있도록 익혀야겠다!

  이 문제에서는 백트래킹할 때 `d`를 이전에 추가했던 요소만 `remove`해주면 안된다. 뒤쪽에서 `dir`이 `2`, `3`일 때는 계속 함수를 호출하는게 아니라 `for`문을 `edge`길이 만큼 돌면서 처리해주므로 이전 `d`를 다른 리스트`tmp`에 저장해놓고 백트래킹으로 되돌아왔다면 `tmp`값으로 `d`값을 설정해주어야한다!

  이번 문제도 다윤이의 readme, 소스코드를 많이 참고해서 풀었다 .. 그래도 이렇게 하나하나 풀어가다보면 나중에 노하우가 생기지 않을 까..!!!! 

  

  

  

  

  