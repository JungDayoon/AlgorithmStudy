# [BOJ]/[1520] 내리막길

## - DP, DFS -

**Main**

* `route[x][y]`: (`x`, `y`)까지 가는 경로 수를 의미
* 한 번 갔던 길은 경로 수를 반복해서 계산하지 않기 위해 방문 여부를 저장한다.
  * `route[x][y] = -1` : 아직 방문하지 않은 곳
  * `route[x][y] >= 0` : 방문한 곳, 그 값은 경로 수
* 제일 오른쪽 아래 지점으로 가는 경로 수이므로 `findRoute(M-1, N-1)`을 호출한다.
  * Top-down 방식의 DP사용.

------

**Func**

* `int findRoute(int x, int y)`

  : (`x`, `y`) 까지의 경로 수를 `return` 한다.

  * `route[x][y] != -1` 이면 이미 방문한 지점 

    => 해당 위치의 경로 수를 리턴한다. (반복해서 계산하지 않음)

  * `x==0 && y==0` 이면 출발점 

    => 해당 경로가 추가되므로 `1`을 리턴

  * `route[x][x] == -1`이면 방문하지 않은 지점이므로 해당 위치까지의 경로수를 계산해준다.

    * 우선, 방문여부를 체크하기 위해 값을 `0`으로 변경한다.

    * 현재 위치(`x`, `y`)와 상하좌우의 `map`값을 비교하여 갈 수 있는 길이라면 현재 위치의 경로 수에 갈 수 있는 위치(`rx`, `ry`)의 경로수를 더해준다.

      => `route[x][y] += findRoute(rx, ry)`

    * 상하좌우의 경로수를 다 더해준 후 현재 위치의 경로수 (`route[x][y]`)를 리턴한다.

</br>

## :speaking_head:

* DP문제 역시 너무 어렵다 ,, 지혜풀이랑 다른 사람 풀이를 참고해서 풀었다. top-down방식은 더더욱 어려운 것 같다ㅜ 관련문제를 많이 풀어보자 !!!