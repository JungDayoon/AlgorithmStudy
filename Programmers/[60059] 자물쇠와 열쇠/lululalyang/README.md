# [Programmers]/[60059] 자물쇠와 열쇠

## *- Simulation -*

#### Solution

1. `lock`의 `(0, 0)`과 `(N-1, N-1)`을 걸쳐서 열쇠를 맞출 수 있도록 `lock`보다 더 큰 `Lock`배열을 만든다. 

   예) `N = 4`, `M = 3`

   <img src="https://user-images.githubusercontent.com/33208360/110497773-1c6e2980-813a-11eb-8623-a58de91786be.png" alt="image" style="zoom:80%;" />	노란색: `key` / 파란색: `lock` / 전체: `Lock`

   * `Lock`의 크기는 `N + 2*M - 2`

2. 위 그림의 위쪽 검정색 인덱스부터 아래쪽 검정색 인덱스까지 `key`를 놓아보며 자물쇠의 홈과 잘 맞는지 확인한다.

   ` boolean ChkOpen(int x, int y, int M, int N, int[][] key, int[][] Lock, int holeCnt`

   * `int x, y` : `Lock`에서 `key`를 놓아볼 좌표의 시작점

   * `int holeCnt` : 자물쇠의 홈의 개수

   * 시작점부터 `key`의 크기만큼 `Lock`을 스캔한다.

     * 만약 원래 `lock`이 있는 범위 내에 속하는 좌표라면 `Lock`과 `key`의 값을 비교한다.

       * 두 값이 같으면 안됨 => `return false`
         1. `lock=1`, `key=1`이라면 자물쇠의 돌기와 열쇠의 돌기가 만나는 경우로 일어나선 안된다.
         2. `lock=0`, `key=0`이라면 자물쇠의 홈인데 열쇠의 홈이 만나는 경우로 자물쇠를 열 수 없기 때문에 안됨.
       * `lock=0`, `key=1`인 경우가 자물쇠의 홈과 열쇠의 돌기가 잘 맞는 경우로 이 경우의 수를 저장한다. => `cnt++`

     * ***`cnt`값과 `holeCnt`값이 같아야 자물쇠의 모든 홈이 열쇠의 돌기와 만난 것!***

       ***=> 이 때에만 `true`를 리턴해준다 !!***

3. 만약 위 경우에 자물쇠가 열리지 않는다면 `key`를 시계방향으로 90도씩 회전시켜 다시 2번과정을 확인한다. 

   * `void rotateKey(int[][] key)` : `key`를 시계방향 90도
   * 4방향 모두 확인해줬는데도 안되면 `false`를, 어느 방향일 때 자물쇠가 풀리면 그 이후는 처리해주지 않고 바로 `true`를 리턴해준다.

</br>

## :speaking_head:

* 어후 ,, 인덱스 처리때문에 아주 혼났다
* 또 위에 볼드체 부분에서 생각이 짧았다ㅜ `lock=1`, `key=0`인 경우도 `true`를 리턴해주었었다 .. 이런거 하나빠트리니까 오류 찾기가 너무 어려웠다 ㅠㅠ 처음부터 설계잘하잣 ㅜ!!!

