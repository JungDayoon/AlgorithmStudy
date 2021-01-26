# [BOJ]/[2643] 색종이 올려 놓기

## *- DP -*

**Main**

* `int[][] paper = new int[N][2]` : 색종이의 가로, 세로 길이

**Func**

* `void findMax(int[][] paper, int N)`

  : 각 색종이마다 올려놓을 수 있는 색종이를 `onto`에 저장해준 후, 각 색종이마다 `recursion`을 호출하여 쌓을 수 있는 최대 색종이 수를 계산한다.

  * `ArrayList[] onto` : 해당 색종이에 올릴 수 있는 색종이를 리스트로 저장

  * `boolean CanPuton(int a1, int b1, int a2, int b2)` 함수 사용

    : (a1, b1)에 (a2, b2)가 올라갈 수 있는지 리턴

    * 그냥 올릴 수 있거나, 90도 회전해서 올릴 수 있으면 `true`
    * 그 외는 불가 `false`

* `void recursion(int idx, int cnt)`

  * `idx`에 해당하는 색종이에 올릴 수 있는 색종이를 탐색하면서, 또 그 색종이에 올릴 수 있는 색종이를 계속 해서 탐색한다. 

  * 하나의 색종이마다 `cnt+1`을 해주면서 올릴 수 있는 색종이 장 수를 계산한다.

  * 올릴 수 있는 색종이를 모두 탐색한 후 `cnt`값과 `maxCnt`값을 비교하여 큰 값으로 `maxCnt`값을 갱신한다.