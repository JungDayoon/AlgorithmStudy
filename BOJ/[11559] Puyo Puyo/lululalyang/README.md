# [BOJ]/[11559] PuyoPuyo

## - BFS -

* ***in `Main`***

  `while`문을 돌면서 `PuyoPuyo()`를 호출하고 그 결과에 따라 뿌요가 터졌으면 연쇄를 늘려주고(`turn++`), 뿌요가 터지지 않았으면 반복문을 종료한다. 

  <br>

* `int PuyoPuyo(String[][] puyo, boolean[][] visited)` 

  : `(11, 0)`좌표부터 시작해서 뿌요가 있으면 해당하는 좌표(`x,y`)와 색상정보(`s`)를 `q`에 넣고 `Pop()`을 호출한다.

  그 결과에 따라 뿌요가 터졌다면 `Gravity()`를 호출해 중력으로 인한 처리를 해주고 `return 1`

  터지지 않았다면 `return 0`

  </br>

* `int Pop(Queue<String[]> q, String[][] puyo, boolean[][] visited)`

  : `q`가 빌 때까지 `q`에 담긴 좌표의 상/하/좌/우에 같은 색상의 뿌요가 있다면 `q.add()`해준다.

  같은 색상 뿌요의 좌표값을 저장해주기위해 `q`에서 꺼낼 때마다 `ArrayList<int[]> bomb`에 좌표를 저장한다.

  `q`가 비면 `bomb`의 크기를 확인하는데 `4`이상이라면 그 자리의 뿌요들을 터트리고("."으로 만듦), `return 1`

  `bomb`의 크기가 4보다 작다면 `return 0`

  </br>

* `void Gravity(String[][] puyo)`

  : 각 열마다 문자가 있다면 아래쪽부터 순서대로 `ArrayList<String> S`에 담은 후 그 자리의 뿌요는 없애준다.

  그 다음 다시 그 열의 맨 아래쪽부터 `S`에 담긴 문자를 하나씩 넣어준다.

  

