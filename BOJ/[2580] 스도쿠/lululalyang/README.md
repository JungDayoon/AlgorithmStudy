# [BOJ]/[2580] 스도쿠

## -Backtracking-

* `LinkedList<Integer> check(int[][] map, int x, int y)` 

  : 해당 좌표(x, y)의 가로, 세로, 작은 네모칸에 들어갈 수 있는 숫자를 확인해 `LinkedList`로 `return `.

  * `boolean[] c`: 1 ~ 9에서 이 자리`(x,y)`에 들어갈 수 있는 수는 `false`, 

    ​																		들어갈 수 없는 수는 `true` (존재하는 수)

    :arrow_right: `false`인 `index`만 `LinkedList<Integer> possN `에 `add`해주고 이를 `return`.

    (`+` 작은 네모칸은 `int modx=x%3`, `int mody=y%3`으로 나눠서 확인)

* `void sudoku(int[][] map, int zeroN)`

  : `for`문 돌면서 `map[i][j] = 0`이면 `check(map, i, j)`호출

    :arrow_right: 그 자리에 들어갈 수 있는 수 담긴 `LinkedList<Integer> tmp` 받아

    :arrow_right: `Iterator`로 돌면서 `map[i][j]`에 값 하나씩 넣어주고 

    :arrow_right: `sudoku(map, zeroN-1)`호출 

  ​			* `zeroN`: 남은 0 개수 (위에서는 zero을 하나 줄여주고 호출한다.) 

  * `sudoku(map, zeroN-1)` 호출하고 나서 `map[i][j]=0`해줘야 함

    : 이전에 넣어준 값이 유효하지 않으니까

  * `while(iter.hasNext())`뒤에도 `return`해줘야 함

    : 이 반복문이 끝났다는 건 해당 자리에 들어갈 수 있는 값을 다 넣어도 `zeroN!=0`이라는 것

      :arrow_right: 이전 `0`자리에 잘못 넣어준 것이니까 이번 재귀함수는 `return`

  * `zeorN == 0`이면 현재 `map`을 출력해주고 종료. (`System.exit(0)`)

    +) `System.exit(0) `: 정상 종료

    +) `System.exit(1)`: 비정상 종료 (0 이외의 다른 값)



------

:speaking_head: 백트래킹 문제인 걸 알고 N-Queen문제 다시 풀어보면서 먼저 공부하고 풀었다

​	원래 시도했던 방법으로는 모든 칸이 0일 때 풀리지 않았다 ..

​	결국에는 지혜가 전체적으로 힌트 알려줘서 풀었다! 마지막까지 지혜 readme참고하긴 했지만ㅠ

​	이번 주에 코테 치고 느꼈는데,, 알고리즘 공부 더 열심히 해야겠다!!!!!!!!!!!!!!!!!!!!