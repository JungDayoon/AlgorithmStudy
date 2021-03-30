# [BOJ]/[1916] 최소비용 구하기

## *- Dijkstra -*

* `ArrayList<Node>[] adj` : 인접리스트

  > :star: 입력은 `출발도시 도착도시 비용` 순으로 입력받아지므로, `adj[입력도시]`에만 `<도착도시, 비용>`을 `add()`해주면 된다.

### solution

* `int Dijkstra(int start, int end, int N)`
  * `int[] d = new int[N+1]` : 각 도시까지의 거리 배열
    * 출발지점은 `0`으로, 나머지는 최대값(`Integer.MAX_VALUE`)으로 초기화한다.
  * PriorityQueue를 이용해 다익스트라를 구현한다.
    1. `pq`에서 `poll()`한 값이 `d[]`값보다 크다면 다음 과정을 처리해주지 않고 넘어간다.
    2. 인접한 노드까지의 거리 `d[next.v]`보다 현재 노드 `now.v`를 거쳐서 가는 비용이 더 작다면 그 값(`d[now.v] + next.w`)으로 인접노드까지의 거리(`d[next.v]`)를 갱신한다.
    3. `pq`가 빌 때까지 반복
  * `d[end]`값이 도착 도시까지 필요한 최소 비용이 된다.

</br>

## :speaking_head:

문제 잘 읽자 !!