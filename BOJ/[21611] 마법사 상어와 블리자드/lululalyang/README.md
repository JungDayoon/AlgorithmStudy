# [BOJ]/[21611] 마법사 상어와 블리자드

## *- Simulation -*

**전역 변수**

```java
int[] bomb; // bomb[i]: 폭발한 i번 구슬의 개수
int[] dx, dy; // 문제에 제시된 방향 순서대로 인덱스 설정
int[][] map; // 각 위치의 구슬 정보
```

### solution

```java
void solution(int M, int[][] info){}
```

* `int[][] info` : 블리자드 마법의 방향과 거리 정보

다음 과정을 `M`번 반복한다.

1. ```java
   void Attack(int d, int s)
   ```

   `d`방향으로 `s`만큼의 구슬을 파괴한다. (해당 위치의 `map`값을 `0`으로)

2. ```java
   void remainTolist(ArrayList<Integer> remain)
   ```

   남은 구슬을 리스트 `remain`으로 옮긴다. 이 과정을 통해 구슬 사이 빈공간이 제거된다.

   `(N/2, N/2)`부터 :arrow_left:, :arrow_down:, :arrow_right:, :arrow_up: 순으로 1, 1, 2, 2, 3, 3, ..., (N-1), (N-1), (N-1) 칸씩 이동하면서 `0`이 아닌 구슬을 `remain`에 추가한다.

   * 이때, 만약 `0`이 두 번이상 나온다면, 이제 남은 구슬이 없는 상태이므로 과정을 종료한다.
   * 이동 칸 수 `size`는 `1`부터 `(N-1)`까지 2번씩 반복해주었고, `(N-1)`칸만큼 추가로 더 이동시켜주었다. (같은 칸수 각 2번은 `boolean flag`로 구분)

3. ```java
   void Explosion(ArrayList<Integer> remain)
   ```

   같은 구슬 4개 이상 연속되어있다면 폭발되므로 제거한다.

   * 이때, 폭발시키는 구슬의 숫자의 `bomb`배열에  폭발된 개수를 더해준다.
   * 폭발될 구슬이 없을 때까지 반복한다.

4. ```java
   void Grouping(ArrayList<Integer> remain)
   ```

   같은 숫자의 구슬 그룹을 두개의 구슬로 바꾼다. 3번 과정과 비슷하게 같은 숫자의 구슬의 개수를 계산하고, 원래 있던 구슬을 제거한 후에 그 위치에 구슬의 숫자와 개수를 추가한다. 

   * (개수, 번호) 순이므로 번호부터 추가해준다.

5. ```java
   void remainToMap(ArrayList<Integer> remain)
   ```

   남은 구슬을 다시 `map`으로 옮겨준다.

   2번과정과 같이 이동하면서 리스트 `remain`의 값을 `map`으로 옮겨준다.

   * 이동 중 `remain`의 구슬이 모두 옮겨졌다면, 나머지 자리는 `0`으로 채워준다. (빈자리)

위 1 ~ 5 과정을 `M`번 끝낸 후, `bomb[1] + bomb[2]*2 + bomb[3]*3`을 출력한다.

</br>

## :speaking_head:

삼성 코테 2번문제였는데 시험 당일에는 풀지 못했다 ㅠ 매 과정마다 2번처럼 이동하면서 처리해주는 방법으로 해결하려고 해서 어려웠던 것 같다.

남은 구슬을 리스트에 넣고 리스트로 과정을 처리해주니까 훨씬 쉬웠다.