# [Programmers]/[72413] 합승 택시 요금

## *- Dijkstra -*

#### solution

1. ` ArrayList<Node>[] adj` : 지점사이의 요금 정보인 `fares`를 인접리스트로 변환하여 저장
   * 양방향이므로 양 노드에 모두 추가해준다.

2. `int[] StoAllDist` : 출발지점 `s`에서 나머지 지점까지의 거리를 저장한다.

   * Dijkstra알고리즘을 이용

   * `int[] Dijkstra(ArrayList<Node>[] adj, int s, int n)` : `s`에서 나머지 노드까지의 최단 거리를 구해 그 배열을 리턴한다.

     1. `int[] dist = new int[n+1]` : 최단거리를 담을 배열 생성

        * 출발지의 최단거리는 `0`으로, 나머지 노드까지의 최단거리는 최대값(`Integer.MAX_VALUE`)으로 초기화한다.

     2. PriorityQueue를 사용해서 다익스트라를 구현한다.

        * `pq`에는 노드 인덱스와 그 노드까지의 거리를 저장. (=> `class Node`)

        * 처음에는 `pq`에 시작 노드와 그 노드의 최단거리인 `0`을 add()한다.

        * `pq`가 빌 때까지 아래 과정을 반복한다.

          1. `pq.poll()`한 노드(`now`)의 거리와 `dist[now.v]`를 비교한다.
             * 만약 `dist[now.v]`가 더 작다면 다음과정을 무시하고 다음 `pq.poll()`한다.

          2. `dist[now.v]`가 크거나 같다면 아래 과정을 수행한다.

             * `now.v`와 인접한 노드(`next`)를 모두 확인하는데,

               기존 최단거리인 `dist[next.v]`보다 `now.v`를 거쳐가는 거리가 더 짧다면 이 값으로 dist값을 갱신시킨다.

          ```java
          if(dist[next.v] > (dist[now.v] + next.w){
              dist[next.v] = dist[now.v] + next.w;
          }
          ```

          ​		갱신시켰다면 그 값을 다시 `pq`에 add()해준다.

     3. `dist`배열을 리턴

3. 이제, 출발지점을 제외한 나머지 지점을 출발지(`i`)로 지정하고, 다른 지점까지의 최단거리(`dist`)를 구한다.

   * 출발지점 `s`에서부터 `i`까지는 합승하고, 그 뒤로 따로 타고간 경우의 요금(`ans`)을 구한다.

     `ans = StoAllDist[i] + dist[a] + dist[b]`

   * 위에서 구한 `ans`값과 환승을 하지 않았을 때의 요금을 비교해 최솟값을 리턴한다.

     * 환승을 하지 않았을 때의 요금 = `StoAllDist[a] + StoAllDist[b]`

</br>

## :speaking_head:

* 다익스트라 예전에 공부했던 알고리즘인데 안하니까 문제봐도 감도 안오고 전혀 기억이 안났다 ..
  * 지혜의 풀이법을 참고했다.
  * 잊지않도록 공부해두자