# [BOJ]/[16234] 인구 이동

## *- Simulation, BFS -*

### Solution

* `int[][] visited` : 마을 방문 여부. 같은 연합인 마을은 같은 값을 갖는다.
* `Map<Integer, Integer> change` : <연합 번호, 연합 내 각 마을의 인구수>
  * 연합 내 각 마을의 인구수 : 바뀌어야하는 인구수를 의미한다

1. 방문하지 않은 마을이라면, ***BFS*** 를 이용해 주변 마을 중 연합을 맺을 수 있는 마을을 확인한다.

   `int ChkAdj(int N, int[][] A, int[][] visited, int r, int c, int united, int L, int R)`

   * 이때, 연합되는 마을의 **총 인구 수**와 **연합되는 마을 수**를 계산해, 그 값으로 연합 내 각 마을의 인구수를 구한다.

   * 연합맺은 마을은 해당하는 연합 번호를 `visited`에 저장하여 방문여부를 확인한다.
   * 연합 마을을 모두 구했다면, 연합 번호와 각 마을의 인구수를 `change`에 `put()`한다.

   * 연합되는 마을이 없다면 `change`에 `put()`하지 않는다.

2. 만약 `change`가 비었다면, 즉 연합을 맺은 마을이 하나도 없다면 종료한다.

3. `change`가 비어있지 않다면, 이동 수를 늘려준다.

   * `move++`

4. 인구 이동 후 인구 수를 조정한다.

   `void MovePop(int N, int[][] A, Map<Integer, Integer> change, int[][] visited)`

   * `visited`를 스캔하면서, `visited`값에 해당하는 Key값이 `change`에 존재한다면, Key에 해당되는 Value값으로 `A`값(마을 인구수)을 바꿔준다.
   * 하루가 지났다면 `change`비워주기
     * `change.clear()`

