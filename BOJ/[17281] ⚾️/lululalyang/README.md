# [BOJ]/[17281] ⚾

## *- Simulation -*

* 모든 타순을 고려해주기 위해 **'순열'** 사용.

  (순서가 있는 조합이 순열이다 = 순서가 다르면 다른 것으로 취급. 

  *백트래킹*  사용.)

  ```java
  int perm(int[] output, boolean[] visited, int done, int n, int r){ // nPr, done은 이때까지 output배열에 넣은 수 개수
      if(done == r){
          //output배열에 순열
          return inning(output)
      }
      for(int i=1; i<n+1; i++){ // 1부터 8까지의 순열을 구하기 위해 i=1부터로 설정
          if(!visited[i]){
              visited[i] = true;
              output[done] = i;
              perm(output, visited, done+1, n, r);
              visited[i] = false;
          }
      }
      return 0;
  }
  ```

  <br/>

* `int inning(int[] output)`

  : 현재의 타순으로 이닝마다의 점수를 구해 최종 점수를 구하고, 이 최종 점수의 최대값을 구한다.

  * `int[] turn = new int[9]`

    : 1번선수(`player[0]`)는 매번 4번타자니까 전달받은 `output`배열에다가 1번선수를 4번타자로 끼워넣고 `turn`배열에 저장한다.

  * `int thisTurn = turn[turnidx]`

    : `turn`배열을 이용해 타자를 바꿔준다. `turnidx`는 `0`에서 `8`까지 바뀌고 `8`다음엔 다시 `0`으로 바뀐다.

  * `int[] base = new int[] {-1, -1, -1, -1}`

    : index`0`은 홈, `1`은 1루, `2`는 2루, `3`은 3루에 있는 주자를 저장한다.

  * 각 선수가 얻은 결과(`ball`)에 따라 `thisScore`를 계산.

    `ball = 0`이면 `out++`

    </br>

* `int Score(int ball, int[] base, int thisTurn)`

  : 선수가 얻은 결과(`ball`)와 주자들의 위치정보(`base`)를 이용해 이 결과로 얻을 수 있는 점수(`score`)를 구한 후 `return`

  현재 타자(`thisTurn`)의 위치도 이동시킨다.

</br>

## :speaking_head:

순열은 아직 익숙하지 않아서 참고했다 ㅎ

그래도 조합을 한두번 쓰면서 익숙해진 것처럼 순열도 이번에 했던 걸 적용하면서 익혀야겠다!