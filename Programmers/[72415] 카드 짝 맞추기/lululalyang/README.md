# [Programmers]/[72415] 카드 짝 맞추기

## *- Permutation, Backtracking, BFS -*

#### solution

1. `board`를 확인하면서 <u>카드의 종류</u>와 각 <u>카드의 위치</u>를 저장한다.

   * `Card[] cardInfo` : 각 카드의 위치
   * `ArrayList<Integer> cardList` : 카드 종류

2. 카드의 개수 (`cardList.size()`)로 순열 집합을 모두 구한다.

   * `ArrayList<ArrayList<Integer>> perm` : 순열 집합

3. 순열로 만든 각 <u>뒤집으려는 카드 인덱스의 순서 집합</u> 으로 조작 횟수 최솟값을 구한다.

   `void ComputeMoveCnt(ArrayList<Integer> order, int now, int N, int moveCnt, int r, int c, int[][] board)`

   * 모든 카드를 뒤집을 때까지 재귀를 호출해 아래의 과정을 반복한다.

     * `int findNum` : 현재 뒤집으려는 카드 번호

     * `findNum`에 해당하는 두 카드 중 첫번째카드를 먼저 찾는 경우와, 두번째 카드를 먼저 찾는 경우, 두 가지 모두를 처리해준다.

       * **백트래킹**을 이용.

       * 재귀 호출 전, 현재 카드를 뒤집으려고 할 때 조작횟수를 구한 후, 그 값으로 호출해준다.

         `int SameCardDiffOrder(int flag, int findNum, int r, int c, int[][] board)`

   * 모든 카드를 뒤집었다면, 그 때의 `moveCnt`와 최종 return값 `MinCnt` 중 작은 값으로 `MinCnt`를 갱신해준 후 `return`한다.

4. 모든 순열 집합을 처리했을 때의 `MinCnt`값이 조작 횟수 최솟값으로 이 값을 `return`한다.

#### Func

* `int SameCardDiffOrder(int flag, int findNum, int r, int c, int[][] board)`

  : `flag`의 값에 따라 찾아야하는 카드 `findNum`의 두 카드를 찾는 순서를 바꿔 조작횟수를 구해 그 값을 리턴한다.

  * `flag`가 `0`이면 `0`번째 카드 먼저 찾은 후, `1`번째 카드를 찾는 조작 횟수를, `flag `가 `1`이면 그 반대로 처리해준 횟수를 `return`
  * 조작횟수는 위의 flag에 맞는 경우에 맞춰 출발 좌표와 찾는 좌표로 `findCard()`함수를 호출하여 구한다.

* `int findCard(int r, int c, int findr, int findc, int[][] board)`

  : `board`의 상황에 맞춰 현 위치 좌표 `(r, c)`에서 찾아야하는 좌표`(findr, findc)`까지 필요한 조작 횟수를 구한다.

  * **BFS** 사용

  * 상하좌우 방향키만 썼을 경우와, <ctrl + 방향키>를 썼을 경우 두 가지 모두를 `q`에 넣어준다.

</br>

## :speaking_head:

* 완전 한참 푼 문제이다ㅜ

  * 처음에는 순열로 모든 경우를 처리해주지 않고, 그 때그때의 최소 조작횟수로 움직이면서 처리해주었는데, 오답투성이였다ㅜ 잡아주지 못한 반례가 있었나보다.

    또, 문제도 잘못 이해해서 `ctrl+방향키`는 그 방향으로 움직이다가 카드를 만나면 멈춰야하는데, 끝까지 갈 수 있도록 했다. 집중하고 문제 조건 잘 생각해서 풀도록 하자

  * 지혜의 풀이방식을 참고해서 순열을 이용하였다.