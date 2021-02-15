# [BOJ]/[1405] 미친 로봇

## *- Backtracking -*

**Main**

* 각 방향으로 갈 확률을 `double[] DirProb`에 저장한다.

  :star: 입력은 **동, 서, 남, 북** 순서로 들어온다!! 주어지는대로 하기 !!

* `boolean[][] visited = new boolean[29][29]`

  * 방문여부를 담을 배열. `N`은 `1`이상 `14`이하인 자연수 이므로 map의 최대 크기는 29 X 29 

**Func**

* `void Solution(int n, int x, int y, double prob)`
  * `int n` : 남은 이동 개수
  * `int x, y` : 로봇의 현재 위치
  * `double prob` : 이때까지 이동의 확률
  * 동서남북 모든 방향으로 이동하는 경우를 각각 처리한다.
    * 이동한 위치의 `visited`를 `true`로 바꾼 후 함수를 호출하고, 그 후에 다시 `false`로 되돌려준다
    * `prob`는 각 이동방향에 맞게 갱신시킨다
  * `n == 0`이면 그 때의 `prob`값을 최종 확률 값인 `res`에 더해주고 `return`

