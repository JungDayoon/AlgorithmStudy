# [BOJ]/[17472] 다리 만들기 2

## *- BFS, MST -*

1. `map`정보를 입력받을 때 만약 바다라면(`map[i][j] == 1`), 그 위치의 `map`값을 `-1`로 저장한다.

2. `int DivideIsland(int[][] map)`

   : 섬을 `1` 이상의 정수로 구분한다.

   * BFS 이용
   * `q`를 이용하여서 `map`값이 `-1`인 부분을 찾으면 `IslandCnt`값으로 저장한다.
   * 매 섬마다 `IslandCnt`값을 `+1`해준다.

3. `void MakeBridge(int IslandCnt, PriorityQueue<Edge> pq, int[][] map)`

   : 각 섬을 잇는 다리를 만들어서 `pq`에 저장한다.

   * BFS 이용
   * 각 섬에서 다른 섬까지의 거리를 `q`를 이용하여서 구한다.
     * `q` -> `{x좌표, y좌표, 이때까지의 다리 길이, 방향}`
     * 이때, 다리의 방향은 변하지 않으므로 `q`에 `dir`값도 함께 저장한다.
     * `boolean[] chkIsland` : 해당 섬까지 다리를 만들었는지의 여부
       * 만약 다리를 이전에 만들었다면, 그 다리의 길이가 최솟갑이므로 현재 다리는 무시한다.

4. `int Kruskal(int IslandCnt, PriorityQueue<Edge> pq)`

   * Kruskal 알고리즘을 이용하여 MST의 가중치를 구한다.

   * 먼저, 각 정점별로 자기 자신만을 가지는 트리를 만든다. -> `make-set`

   * `pq`에서 Edge 하나씩 `poll()`하면서 두 노드의 루트노드가 같은지 확인한다. -> `find()`

     * 같으면 이 Edge는 사이클을 생성하므로 무시.
     * 다르다면 두 트리를 하나로 합친다. -> `union`

   * 모든 Edge를 다 확인한 후에, 각 노드의 루트노드 정보인 `p`를 확인한다.

     * 모든 정점의 루트노드가 같아야 하나로 연결된 것

     * 만약 루트노드가 다르다면 이는 모든 섬을 연결할 수 없는것 => `return -1`

     * 루트노드가 다 같다면 모든 섬을 연결한 것 => `return res` 

       (`res` : MST의 가중치 값)

       