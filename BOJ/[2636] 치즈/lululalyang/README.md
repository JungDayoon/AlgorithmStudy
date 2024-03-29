# [BOJ]/[2636] 치즈

## *- BFS -*

**전역변수**

`int[][] cheese` : 치즈의 상태

## solution

**변수**

`int time = 0` : 치즈가 다 녹는데에 걸린 시간

`int cnt = 0` : 치즈가 다 녹기 한시간 전 남아있는 치즈칸의 개수

**로직**

1. 치즈가 판에 남아있는지 확인한다.

   `boolean ChkRemain(int N, int M)` : 치즈있으면 `true`, 없으면 `false`리턴

   **치즈가 없다면 그 때의 `time`과 `cnt`를 리턴한다.**

2. 치즈가 남아있다면, 공기와 접촉하는 치즈를 확인한다.

   `int ChkMelt(int N, int M, boolean[][] melt)`

   * BFS 사용
   * `melt`로 방문여부를 체크한다.
   * `(0, 0)`부터 `q`에 추가
   * 방문하지 않은 곳이라면 방문 여부를 `true`로 체크해준다.
     * 그 공간이 빈 공간이라면,  그 위치의 좌표를 `q`에 추가
     * 그 공간이 치즈라면, 녹는 치즈(공기와 접촉되는 치즈)의 개수를 `1`더해준다.

   * 공기와 접촉되는 치즈의 개수를 리턴.

     이 리턴값을 `cnt`로

3. `melt`가 `true`이고, 치즈인 공간이라면, 치즈를 녹여준다. (`cheese[i][j] = 0`)

4. 치즈가 남아있지 않을 때가지 위 과정 반복.



