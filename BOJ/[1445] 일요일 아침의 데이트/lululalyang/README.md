# [BOJ]/[1445] 일요일 아침의 데이트

## *- PriorityQueue, Dijkstra -*

* `String[][] map` : 숲 정보
  * 숲 정보를 입력받을 때, 출발지인 `"S"`의 좌표를 `int sx, sy`에, 도착지인 `"F"`의 좌표를 `int ex, ey`에 저장한다.

### solution

**변수**

* `int[][] trash` : 출발지에서 현 좌표까지 오는데에 지나야하는 **쓰레기칸 최소 개수**
* `int[][] trashAdj` : 출발지에서 현 좌표까지 오는데에 지나야하는 **쓰레기칸의 인접칸 최소 개수**
  * 두 배열은 모두 최대값으로 초기화한다.
  * 시작 위치의 좌표는 `0`으로 초기화한다.
* `PriorityQueue<Element> pq` : ***다익스트라*** 에 사용될 우선순위 큐
  * 출발지의 좌표와 지나간 쓰레기 칸 개수 `0`, 지나간 쓰레기 인접칸 개수 `0`을 추가한다.

**클래스**

* `class Element` : `x`, `y`좌표, 지나온 쓰레기 칸 개수 `trashCnt`, 지나온 쓰레기 인접칸 개수 `adjCnt`를 가진다.
  * 쓰레기 칸의 개수에 따라 오름차순으로, 만약 그 개수가 같다면 쓰레기 인접칸 개수에 따라 오름차순으로 정렬할 수 있도록 설정한다.

**로직**

* `pq`가 빌 때까지 아래의 과정 반복

  `Element now` : `pq`에서 뽑은 원소

1. `now`의 위치가 `"F"`라면, 도착점이므로 지나온 쓰레기 개수와 `trash`배열값을 비교
   * 더 적은 쓰레기를 지나왔다면, `now`의 값으로 `trash`와, `trashAdj`를 갱신한다.
   * 만약 지나온 쓰레기 개수가 같다면, `trashAdj`를 더 작은 값으로 갱신한다.
   * 다음 과정을 하지 않고, `continue`

2. `now`위치의 상하좌우를 확인한다. 그 위치가 `map`내에 위치한다면,
   * 그 위치가 `"g"`라면 쓰레기 개수를 `+1`
   * 그 위치가 `"."`, 즉 빈공간이라면 인접한 칸에 쓰레기가 있는지 확인하고 있다면 인접 쓰레기 개수를 `+`해준다.

3. 2번 과정에서 구한 현재 위치까지의 쓰레기 개수와 쓰레기 인접칸 개수를 `trash`와 `trashAdj`와 비교한다.

   * 구한 쓰레기 개수가 `trash`값보다 작다면, 

     구한 값으로 `trash`와 `trashAdj`를 갱신하고, 그 값을 `pq`에 추가한다.

   * 그 값이 같다면, 구한 쓰레기 인접칸과 `trashAdj`를 비교.

     인접칸이 적을 경우에만 `trashAdj`를 갱신하고, 그 값을 `pq`에 추가한다.

4. `pq`가 비었다면, 그 때 `"F"`위치의 `trash`값이 지나온 쓰레기칸의 최소값, `trashAdj`값이 지나온 쓰레기인접칸의 최소값이 된다. => 이를 리턴

</br>

## :speaking_head:

* 처음에 쓰레기 인접칸의 개수를 구해야하는지 알고 틀렸었다. 문제를 제대로 읽자 !!!!🤬🤬



