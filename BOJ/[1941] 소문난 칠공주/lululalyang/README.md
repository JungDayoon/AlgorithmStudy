# [BOJ]/[1941] 소문난 칠공주

## *- backtracking & BFS -*

**Func**

* `void Comb(int N, int r, int start, ArrayList<Integer> tmp)`

  : 조합을 이용해 25명의 학생 중 7명을 뽑는다 (백트래킹을 통해 조합을 구현)

  * `r` 을 하나씩 줄여가면서 `Comb()`를 호출하고, `r==0`이면 그 때의 조합집합 `tmp`를 `chkPersons()`의 인자로 전달한다.
  * `chkPersons()`를 호출한 후에  `tmp`에 추가한 숫자를 `remove()`해줘야 함 -> 다음 경우를 위해

</br>

* `void chkPersons(ArrayList<Integer> SevenPrincess)`

  : 임도연, 이다솜 각 파의 명수를 세는 함수.

  * `Iterator`를 이용해 전달받은 인자 `SevenPrincess`를 탐색하면서 임도연파 명 수인 `Ycnt`와 이다솜파 명 수인 `Scnt`를 계산한다.
  * `Ycnt`가 7명의 절반 이상 즉, `4`명 이상이라면 `return`한다.
  * 이다솜파가 많으면 즉, `Scnt>=4`이라면 `chkAdjacent(SevenPrincess)`를 호출한다.

</br>

* `void chkAdjacent(ArrayList<Integer> sp)`

  : 소문난 칠공주 7명이 인접해있는지 확인하는 함수.

  * `boolean[] visited`는 전달받은 `sp`리스트의 `index`를 통해 방문 여부를 확인한다.
  * `BFS`를 이용해 7명이 인접해있는지 확인한다
  * `map`의 범위 내에 존재하고, `sp`에 존재하는 학생이라면 `q`에 추가하고 계속해서 확인한다. 
  * `q`가 빌 때까지 확인하고, 그 후 `visited`가 모두 `true`라면 해당 경우의 수를 `+1`해주고, `false`가 하나라도 존재한다면 `return`한다.

  



