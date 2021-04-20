# [BOJ]/[20057] 마법사 상어와 토네이도

## *- Simulation -*

**전역 변수**

`int[] dx, dy` : 어느 한 점의 주변 좌표를 저장. 각 인덱스 당 위치는 그림과 같이 지정하였다. 

<img src="https://user-images.githubusercontent.com/33208360/115341562-ce8e1c00-a1e3-11eb-9f71-fa34f030c057.png" alt="image" style="zoom:67%;" />

 `Set<Integer> perc = new HashSet<>(Arrays.asList(7, 10, 5, 2, 1))` : 모래 이동에 쓰이는 비율을 set에 저장해두었다.

`Map<Integer, ArrayList<Integer>>[] percLoc = new HashMap[4]` : 각 방향에 따른 각 비율의 위치 인덱스를 리스트로 저장.

* `0` : :arrow_left: , `1`: :arrow_down:, `2` : :arrow_right:, `3`: :arrow_up:

* `<이동시킬 모래의 비율, 그 모래를 이동시킬 위치 인덱스 리스트>`

  => `void Initialize()`를 통해 초기화한다.

  ex) :arrow_left:방향의 7%만큼 이동시키는 위치라면, 

  ​	`percLoc[0].put(7, new ArrayList<>(Arrays.asList(3, 1)));`

`Loc now` : 토네이도의 현재 위치 (`class Loc`은 위치좌표 `(x, y)`를 가짐)

* `(x, y) = (N/2, N/2)`로 초기화

## solution

* `int Out = 0` : 격자 밖으로 나간 모래의 양

### HOW

1. 우선, 토네이도는 :arrow_left:, :arrow_down:, :arrow_right:, :arrow_up: 이 순서대로 회전한다. => `dir`을 `0, 1, 2, 3`으로 바꿔주면서 회전시킨다. (`dx, dy`배열의 인덱스와 동일)

2. 각 방향마다 이동하는 칸의 수는 `1, 1, 2, 2, 3, 3, 4, 4, 5 ,5, ...`와 같이 같은 칸 수로 2번씩 반복되고, `(N-1)`개의 칸 수까지 반복된다.

   마지막에 `(N-1)`번은 3번 반복되므로 2번 반복해준뒤 한 번 더 진행해준다.

3. 이동하면서 모래를 이동시키고, 이동시키면서 격자 밖으로 나가는 모래의 양을 계산한다.

   `int Move(int dir, int movecnt, int N)`

   : `dir`방향으로, `movecnt`칸만큼 토네이도를 이동시킨다.

   각 방향에 따른 각 비율별 위치를 `percLoc`에 저장해뒀으므로, 그에 해당되는 위치를 확인한다.

   * 그 위치가 범위 내라면, 그 위치에 해당하는 비율의 양을 더해주고
   * 범위 밖이라면, 밖으로 나간 모래의 양에 더해준다.

   마지막에 알파위치의 모래 양인 남는 모래를 구하기 위해 빼주는 모래의 양을 계산한다.

   현재 칸의 모래는 `0`으로 바꿔준다.

</br>

## :speaking_head:

> **:timer_clock: *1시간 12분***

방향별 비율마다 각 위치를 `Map`을 이용해서 저장해주고 이것을 이용해서 문제를 해결하였는데, 너무 비효율적인 것같다. 일반화할 수 있도록 다시 풀어봐야겠다!!!