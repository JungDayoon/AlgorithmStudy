# [BOJ]/[11779] 최소비용 구하기 2

## *- Dijkstra -*

* `ArrayList<Edge>[] adj` : 각 도시로의 버스 비용을 인접리스트로 표현

### solution

* `int[] d` : 출발지에서 각 도시까지 가는 데에 최소 비용
  * 최대값으로 초기화
* `int[] p` : 최소비용으로 도달할 때에 이전 도시번호 (부모노드)
  * 자기 자신으로 초기화

* 우선순위 큐를 사용하여 다익스트라 구현
* `pq`에 출발지와 비용 `0`을 추가하고 `q`가 빌 때까지 아래의 과정을 반복한다.
  1. `pq`에서 `poll()` => 도시번호 `v`, 이때까지 쓴 비용 `w`
  2. 만약, `d[v]`보다 `w`가 크다면 아래과정 생략
  3. `v`에서 갈 수 있는 도시(`next`)를 확인한다.
     * 만약 `d[next.v]`보다 `v`를 거쳐서 `next`로 가는 것이 더 비용이 적게 든다면(`(w + next.w)`가 더 작다면), `d`값을 갱신하고, `p`값을 현재도시(`v`)로 바꿔준다. 
     * 그리고 그 `d`값과 도시 번호를 `pq`에 `add()`

* 도착지까지의 최소비용 = `d[arrive]` (`arrive` : 도착도시의 번호)
* 배열 `p`에 dfs를 이용해 최소비용인 경로를 구한다.
  * `ArrayList<Integer> ans` : 경로 정보
  * `void dfs(int v, int[] p, ArrayList<Integer> ans)`
    * `p[v] == v`라면(출발지), `v`를 `ans`에 추가해주고 `return`
    * 그렇지 않으면 `dfs(p[v], p, ans)` 호출한 후 `ans`에 `v`를 추가해준다.
* 최소비용의 경로에 포함되어있는 도시의 개수 = `ans`의 크기
* 최소 비용을 갖는 경로를 방문하는 도시 순서 = `ans`의 원소 순서