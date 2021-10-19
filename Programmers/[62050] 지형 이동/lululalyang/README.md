# [BOJ]/[62050] 지형 이동

## *- bfs, kruskal(MST) -*

1. 사다리없이 이동할 수 있는 구역, 즉 높이 차이가 `height`이하인 칸끼리 구역을 나눈다.

   * bfs 사용

   ```java
   void bfs(int x, int y, int[][] land, int[][] sep, int height, int sidx)
   ```

   * `sep[i][j]` : 위치별 구역 번호
     * 해당 배열으로 방문여부를 검사한다.
     * 방문했다는 의미로 `sidx`를 저장.

   * `int sidx` : 주어질 구역 번호

2. 전체를 방문하기 위해서는 나눈 구역 사이에 사다리를 놓아야한다. `sep`배열을 확인하며 다른 구역끼리 인접해있다면, 

   두 구역을 두 노드로, 그 사이의 높이 차이를 노드 사이 Edge의 비용으로 해 우선순위 큐에 추가한다. (=> 이후 kruskal을 위한 처리)

   ```java
   void GetEdge(int[][] map, PriorityQueue<Edge> pq, int[][] land)
   ```

   * 같은 엣지는 추가하지 않도록 각 위치의 :arrow_right:, :arrow_down: 위치의 칸만 확인한다.
   * 만약 다른 구역이라면, `pq`에 각 구역 번호와 그 사이 높이 차이를 추가한다.
     * `class Edge`는 `(u, v, w)`를 가지고(`u`, `v`: 구역번호, `w`: 사이 비용), `w`값을 기준으로 오름차순 정렬된다.

3. kruskal 알고리즘을 적용해 사다리 설치비용 최솟값을 구한다. (MST)

   ```java
   int Kruskal(PriorityQueue<Edge> pq, int N)
   ```

   1. **make-set**

      자기 자신만을 가지는 트리를 만든다. 

      => `p[i] = i`

   2. `pq`가 빌 때까지, **union-find**를 진행한다.

      * `u`와 `v` 각각이 속한 트리의 루트노드를 비교

        `find(v, p)`

      * 같다면, 해당 Edge는 사이클을 이루는 Edge로 패스하고 다음 Edge를 확인

      * 다르다면, 두 트리를 합친다. (union)

        한 노드의 루트노드를 다른 노드의 루트노드의 자식노드로 설정

      * 해당 Edge의 비용을 결과값 `cost`에 더해준다.

## :speaking_head:

문제만 읽었을 때는 어떻게 접근해야할 지 감을 못잡았는데, 문제에서 주어진 예제 테케 풀이를 보고 똑같은 방법으로 곰방 해결할 수 있었당