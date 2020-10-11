# [BOJ]/[17143] 낚시왕

## -*Simulation*-

* `Shark[] shark = new Shark[M]`: 상어의 정보를 저장할 클래스 Shark 생성 후 배열 생성

* `boolean[] caught = new boolean[M]`: 상어의 존재 유무 (잡혔으면 `true`, 안잡혔으면 `false`)

*  ***in `Main`***

  (1) 상어의 개수(`M`)가 `0` 이면 잡을 상어가 없으니까 `0`을 출력해주고 종료

  >  :star:**Java** 
  >
  > `System.exit(0)`: 정상종료	/	`System.exit(1)`: 비정상종료

  <br/>

  (2) `Arrays.sork(shark)`: 객체 배열 정렬

  > 객체로 이루어진 배열의 경우에는 객체 클래스가 Comparable 인터페이스의 `compareTo()` 메소드를 구현해야 정렬 가능
  >
  > ```java
  > class Shark implements Comparable<Shark>{
  >     
  >     ...
  >         
  >     @Override
  > 	public int compareTo(Shark s) {
  > 		if(this.z < s.z) {
  > 			return -1;
  > 		}else if(this.z == s.z) {
  > 			return 0;
  > 		}else {
  > 			return 1;
  > 		}
  > 	}
  > }
  > ```

  <br/>

  (3) 낚시왕의 위치를 1에서 C까지 바꿔주면서 `catchShark()`, `moveShark()`, `checkSharkLoc()`메소드 차례대로 호출.

  * `void catchShark(int king, Shark[] shark, boolean[] caught, int R)`

    : 낚시왕이 있는 열(`king`)에 있는 상어 중 땅과 가장 가까운 상어 잡는 메소드.

    낚시왕과 같은 열에 있는 잡히지 않은 상어 중 row index가 가장 작은 상어를 잡고(`caught[sharkidx] = true`), 해당 상어의 크기를 낚시왕이 잡은 크기에 더해준다(`sharksizeSum += shark[sharkidx].z`).

    <br/>

  * `void moveShark(Shark[] shark, boolean[] caught, int R, int C)`

    : 각 속력에 따라 상어 이동 시키는 메소드.

    - `Map`의 크기에 따라 상어가 기존의 자리, 방향으로 돌아오는 데에 걸리는 속력은 규칙적이다. (예: C가 2면 `2`, 3이면 `4`, 4면 `6`, 5면 `8`, 6이면 `10`, 7이면 `12` ...)

      ```java
      int divideN1 = 2 * (R-1);
      int divideN2 = 2 * (C-1);
      ```

      따라서, 이 크기로 속력을 `%`해준 그 값으로 상어를 이동시킨다.

      `speed = shark[i].s % divideN1` 또는 `speed = shark[i].s % divideN2`

    - `shark[i].d`가 `1`, `2` 일 때와, `3`, `4`일 때로 나누어서 `speed`를 구해주고 각각 `R`과 `C`범위를 벗어나지 않도록 처리해 이동시켜준다. (예: `shark[i].d`가 `2`일 때)

      ```java
      speed = shark[i].s % divideN1;
      if(shark[i].d == 2) {
      	if((shark[i].r+=speed) > R) {
      		shark[i].r = 2*R - shark[i].r;
      		if(shark[i].r < 1) {
      			shark[i].r = Math.abs(shark[i].r) + 2;
      		}else {
      			shark[i].d--;
      		}
      	}
      }
      ```

      <br/>

  * `void checkSharkLoc(Shark[] shark, boolean[] caught, int R, int C)`

    : 한 칸에 두 마리 이상 있을 경우 크기가 가장 큰 상어가 나머지 상어 모두 잡아먹는 메소드.

    * `int[][] map = new int[R+1][C+1]`: 상어의 위치를 표시해줄 배열

      -> 처음에 모두 `-1`로 초기화해준다. (`initial(map);`)

    * 잡히지 않은 상어들의 위치에 해당하는 `map`의 index에 상어 array의 index를 넣어주는데

      그 자리에 아무 상어도 없으면 그냥 그자리에 현재 상어의 index를 넣어주고,

      기존에 상어가 있다면 원래 있던 상어는 잡아먹히니까 없애주고 그 자리에 현재 상어의 index를 넣어준다. *(`Main`에서 상어의 크기별로 정렬해주니까 크기 비교 필요 없음!)*

## :speaking_head:

처음에 한 칸에 두 마리 이상 있는 지 확인할 때 `shark[]`를 이중 for문으로 돌면서 위치가 같으면 `caught[i] = true`로 해줬었는데 시간초과였다!! 상어 최대 개수가 10000개니까 이중으로 돌면 시간초과날만한 것 같당 .. 

그래서 은주가 말해준대로 `shark[]`를 크기별로 미리 오름차순으로 정렬해주고, 새로운 `map`배열을 만들어서 반복문 한 번만 돌 수 있도록 해서 했다! 

근데 돌아가긴 하는데 마지막에서 런타임에러 나서 보니까 `System.exit(status)`의 status가 0일 때가 정상종료였다 ... ! 헷갈리지 않 기 !!

