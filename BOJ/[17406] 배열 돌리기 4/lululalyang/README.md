# [BOJ]/[17406] 배열 돌리기 4

## *- Simulation -*

**전역변수**

`int Min = Integer.MAX_VALUE` : 최종 결과값. 회전 연산을 모두 끝낸 후의 배열 A값의 최솟값.

`int[][] map` : 문제에서 주어지는 배열 A

`Rotate[] R` : 회전 연산 배열

* `class Rotate` : 회전 시켜야 하는 범위의 왼쪽 맨위 좌표와, 오른쪽 맨 아래 좌표를 저장.
  * `int sx, sy, ex, ey`
* 회전연산 `(r, c, s)`를 입력받으면 `r-s, c-s, r+s, c+s`값으로 `R`배열에 저장

`int N, M` : 배열 A의 세로, 가로 길이

## solution

1. 순열을 이용해, 가능한 연산 순서를 구한다.

   `void Perm(int K, int r, boolean[] visited, ArrayList<Integer> tmp)`

   * 가능한 순서를 구할 때마다 다음 과정을 진행.

2. 전달받은 연산 순서로 배열 A를 회전시킨다.

   `void RotateByOrder(ArrayList<Integer> order)`

   * `int[][] maptmp` : 배열A를 회전시키는 경우가 여러가지이기 때문에 똑같은 값을 가지는 배열을 만들어 사용한다.

   * 하나의 연산 내의 회전은

     문제에서 주어진 예제라면, ![image](https://user-images.githubusercontent.com/33208360/114886685-b6647880-9e42-11eb-890d-f6fdbc118ebe.png)

     

     이런 식으로, 바깥 둘레의 사각형부터 안쪽 둘레의 사각형으로 들어오면서 회전시킨다.

     따라서, 초록색으로 색칠된 부분과 같이 사각형의 **왼쪽 위 꼭짓점의 좌표**와 **그 때의 사각형의 가로, 세로 길이**를 통해 각 사각형을 회전시킨다.

     `void RotateMap(int i, int j, int n, int m, int[][] maptmp)`

     * `int i, j` : 위에서 말한 왼쪽 위 꼭짓점의 좌표

     * `int n, m`: 그 때의 사각형의 가로, 세로 길이

     * :arrow_right:, :arrow_down:, :arrow_left:, :arrow_up: 순서에 맞춰 각각 회전시킨다.

       이 때, 꼭짓점 값들을 잘 회전시킬 수 있도록 각 단계별로 따로 저장해두고 뒷 단계에서 사용한다.

3. 회전시킨 배열의 값을 구한다.

   `void ComputeArrValue(int[][] maptmp)`

   : 각 행의 합의 최솟값을 구해 최종 결과 값인 `Min`을 갱신시킨다.

4. 모든 연산 순서로 진행한 후의 `Min`값이 배열 A값의 최소값이다.

<br>

## :speaking_head:

> :timer_clock: 1시간 37분

* 회전을 어떻게 시켜야 할 지 고민을 오래 했다ㅜ

* 그래도 로직을 짜두고 구현하니까 구현은 금방했다 !!

  구현할 때 인덱스 확인 잘하자 

