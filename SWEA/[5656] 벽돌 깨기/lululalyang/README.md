# [SWEA]/[5656] 벽돌깨기

## - Simulation -

* `void recursion(int marble, int[][] arr)` 

  : 구슬 개수만큼 재귀적으로 돌면서 벽돌배열을 처리해준다.

  * `void arrAtoB(int[][] a, int[][] b)`

    : 파라미터로 받은 `arr`에 열 하나씩 구슬을 떨어뜨려주는데, 이때 넘겨받은 `arr`는 다른 열에서의 처리를 위해 남겨두고 새로운 배열인 `tmpArr`에 `arr`정보를 복사해 각 열에서의 처리를 진행한다. 

  * 구슬개수만큼 처리 후 `remainBrick(arr)`를 통해 남은 벽돌의 개수를 계산한다.

    그리고 그 수와 최소 벽돌 개수인 `Mincnt`를 비교하여 최소값을 갱신한다.

</br>

* `void crashBrick(int idx, int[][] arr)`

  : idx번째 열에 구슬이 떨어졌을 때 벽돌배열의 변화를 처리한다.

  1. 해당 열의 가장 위쪽 행부터 `0`값이 아닌 index를 찾는다.
  2. 그 위치의 좌표값 `x`, `y`, 그리고 그 벽돌 값을 `q`에 저장한다.
  3. `q`에서 하나씩 `poll()` 하면서 처리한다.
     1. 우선 이 벽돌은 깨진다(벽돌값 상관없이) -> `arr[x][y] = 0`
     2. 깨진 벽돌값이 1보다 크면 주변 벽돌도 깨진다 ( [벽돌값-1] 만큼 상하좌우 )
        * 가로, 세로 따로 처리해준다.
  4. `q`가 빌 때까지 위 과정을 반복한다.

  * 위 과정을 끝낸 후, 벽돌을 빈공간으로 떨어뜨려 정리해준다. -> `cleanBrick()`
    * `int check` : 만약 해당 열에 아무런 벽돌이 없을 때는 위의 1 ~ 4과정을 해주지 않으므로 `cleanBrick()`을 해주지 않아도 됨 => 따라서 체크해주자.

</br>

* `void cleanBrick(int[][] arr)`

  : 벽돌을 빈공간으로 떨어뜨리는 것을 처리한다. 

  * 떨어뜨려야 할 위치를 `int now`에 저장한다.

</br>

## :speaking_head:

* 오랜만에 풀어서 그런지 너무 오래 걸렸다 .. 감을 잃었는지 재귀적인 방법을 생각못해서 한참 고민했다 ㅜㅜ 멍청잉 ㅜ

  그래도 벽돌처리부터 구현해두고하니까 방법 되찾아서 풀었다 ..! 다시 열심히 문제를 풀도록 해야겠다ㅜ

