# [BOJ]/[17144] 미세먼지 안녕!

## *- Simulation -*

**전역 변수**

`int[][] dust` : 방의 정보.

`int[] cleaner` : 공기 청정기 위치의 행 번호 2개

## solution

1. 미세먼지 확산

   `void SpreadDust(int R, int C)`

   * `int[][] plus` : 각 위치에 확산되어서 더해줘야하는 먼지의 양을 저장한다

   * `dust`를 스캔하면서 먼지가 있다면,

     그 위치의 상하좌우의 `plus`값에 `dust / 5`값을 더해준다.

     확산된 칸의 개수만큼의 `dust/5`를 `dust`에서 빼준다.

   * `dust`에 `plus`값을 더한다.

2. 공기 청정기 작동

   * 위쪽 청정기 -> `cleaner[0]`의 위치로 `void UpCleaner(int cloc, int R, int C)`

     원래 바람 방향의 반대인 :arrow_down:, :arrow_left:, :arrow_up:, :arrow_right: 순으로 각 먼지를 이동시킨다.

     청정기 바로 옆자리는 `0`으로 바꿔준다. (청정기 바람은 미세먼지가 없는 바람)

   * 아래쪽 청정기 -> `cleaner[1]`의 위치로 `void DownCleaner(int cloc, int R, int C)`

     원래 바람 방향의 반대인 :arrow_up:, :arrow_left:, :arrow_down:, :arrow_right: 순으로 각 먼지를 이동시킨다.

     청정기 바로 옆자리는  `0`으로 바꿔준다.

위 과정을 `T`번 반복한다.

3. 그 후`dust`에 **남아있는 먼지의 양**을 구한다.

</br>

## :speaking_head:

>  :timer_clock: 16:30 ~ 17:19 (50분)

이전에 *배열 돌리기 4* 를 풀어서그런지 회전시키기가 수월했다.