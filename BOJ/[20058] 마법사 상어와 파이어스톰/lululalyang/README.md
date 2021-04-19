# [BOJ]/[20058] 마법사 상어와 파이어스톰

## *- Simulation -*

**전역 변수**

`int[][] map` : 주어지는 얼음판의 정보

`int[] L` : 각 q번째 시전 때의 단계 L (0번째부터 (Q-1)번째까지)

## solution1 - 파이어스톰 Q번 시전

* `int n = (int)Math.pow(2, L[q])` : 부분격자의 한 변의 길이

1. 각 부분 격자마다 시계방향으로 90도 회전시킨다.

`void RotateOnePart(int i, int j, int n)`

<img src="https://user-images.githubusercontent.com/33208360/115197163-a7741380-a12b-11eb-853a-f2e5a082c87e.png" alt="image" style="zoom: 67%;" /> 파란색 부분이 회전시키려는 부분격자라고 할 때, 빨간색 부분을 시작점으로 해서 시계방향으로 회전시킨다

* ` void RotateOneLine(int x, int y, int n)`

  위의 하나의 시작점(`(x, y)`)을 기준으로 한변이 `n`인 사각형의 둘레를 회전시킨다.

  * `int[][] tmp = new int[4][n]` : 기존의 값을 그림과 같이 화살표의 순서에 따라 옮겨둔다. <img src="https://user-images.githubusercontent.com/33208360/115198137-b14a4680-a12c-11eb-84ce-5ce171d775f8.png" alt="image" style="zoom:67%;" />

  그 후, 다시 `tmp`값을 그림과 같이 `map`으로 옮긴다. <img src="https://user-images.githubusercontent.com/33208360/115198552-23229000-a12d-11eb-830c-353aa53f5ca3.png" alt="image" style="zoom:67%;" />

2. 각 얼음칸이 얼음칸 3개이상과 연결되어 있는지 확인

   그렇지 않다면 얼음을 `1`녹인다.

   `void ChkAdjThree(int N)` : `Boolean`배열을 따로 두고 얼음칸 3개 이상과 연결되어 있지 않은 칸은 체크해둔 다음, 배열을 참고해 다시 `map`값을 변경시킨다.

위 과정을 `Q`번 반복한다.

## solution2 : 남아있는 얼음의 합, 가장 큰 덩어리의 칸의 개수

1. 남아있는 얼음의 합

   `int Sum(int N)` : 남아있는 것 다 더한다.

2. 가장 큰 덩어리의 칸의 개수 => BFS를 사용한다.

   `int solution2(int N)`

   : 방문여부를 체크할 배열을 하나 두고, bfs를 이용해 연결되어 있는 얼음칸을 구한다. 그 칸의 개수를 구해 칸의 최대값을 구한다.

</br>

## :speaking_head:

> :timer_clock: ***1시간 11분***

이렇게 칸을 쪼개고 쪼개서 처리하는 문제는 딱 집중해서 풀자 !

처음에 마지막 테케가 틀렸었는데, 얼음을 녹일 때 만약 얼음이 없는 칸이면 즉, `map`값이 `0`이면 아무 처리도 해주지 않아야하는데 그 칸도 `-1`해줘서 틀렸었다. 이런 부분 놓치지않도록 주의하자