# [BOJ]/[17779] 게리맨더링2

## *- Brute forcing -*

* `int[][] A = new int[N+1][N+1]` : 각 구역의 인구수 (index `1` ~ `N`)

*  주어진 `x`, `y`의 범위는 조건에 따라 각각 `1 <= x <= N-2`, `1 <= y <= N-1`

  :arrow_forward: 이 범위내의 `x`와 `y`를 모두 확인해줌

  :arrow_forward: `d1`과 `d2`는 그 때 그때의 `x`값과 `y`값에 따라 범위가 바뀐다 ( 이 또한 문제에서 주어진 조건에 따라! --> `void Compute_d1d2(int x, int y)` )

* 결정된 `x` ,`y`, 그리고 `d1`, `d2`값에 따라 각 1 ~ 5번 선거구의 인구수를 구한다

  --> `void Compute_Min(int x, int y, int d1, int d2)`

  * 우선, 5번 선거구의 인구수부터 구한다.

  * 이때, 이후에 1~4번 선거구의 인구수를 구하기 위해 모든 구역 중 5번 선거구에 속하는 구역은 `boolean`값으로 체크해둔다.

  * 시작점 `(x, y)`에서 부터 확인

    *  행은 `x <= r <= x+d1+d2`가 범위. ***BUT*** 열은 각 행마다 범위가 다르다.

    * 5번 선거구의 가로 꼭짓점인 `(x+d1, y+d1)`, `(x+d2, y+d2)`까지는 열 범위를 늘려주다가, 이를 지나면 열 범위를 다시 줄여준다

      :arrow_forward: 이때는 `int front, end`를 이용해 범위를 계산

      :arrow_forward: 가로 꼭짓점을 지났는지 여부를 확인하기 위해 `int flag1, flag2`를 사용

  * 5번 선거구의 인구수와 속하는 구역을 확인한 후에, 1~4번의 선거구의 인구수를 구한다 

    --> `void Compute_Sum(int sx, int ex, int sy, int ey, boolean[][] visited)`

    * 문제에서 주어진 각 선거구의 범위에 해당하는 구역의 인구수를 더해 `return`
    * 이때, 5번 선거구에 포함되지 않는 (= `visited[][]`값이 `false`인!) 구역의 인구수만을 더한다

* 1번부터 5번까지의 인구수를 다 구했다면 최대값과 최소값의 차이와 `Min`값을 비교해 작은값을 `Min`값으로

  * `Arrays.sort(sum)`사용 

    > :star: **sort** vs **parallelSort**
    >
    > sort는 크기가 작은 Array일 때, parallelSort는 큰 Array일 때 성능이 좋다!

## :speaking_head:

5번 선거구가 대각선에 모서리 길이도 달라서 어떻게 처리해줘야될지 한참 생각했다 .. 그래도 이부분 해결하니까 통과했다!

