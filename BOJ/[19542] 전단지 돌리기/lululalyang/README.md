# [BOJ]/[19542] 전단지 돌리기

## *- Tree -*

* `subDepth[v]` : 정점 `v` 아래의 최대 depth

1. `subDepth[v]` 구하기 => DP 이용

   `subDepth[v] = MAX(subDepth[자식노드] + 1)`

2. `int Dist_DFS(int now, int dist, boolean[] visited)`

   : 루트노드에서 내려가는 길이를 리턴해준다. 

   (마지막에 루트노드로 돌아와야하기 때문에 총 이동거리는 `리턴값*2`)

   * `dist` : 루트에서 `now`까지 내려온 거리

   * `res` : `now`에서 내려가야하는 서브트리의 노드까지의 내려간 거리

     * 자식노드를 확인할 때, 

       * 방문하지 않았고, `subDepth[v]`가 `D`보다 크거나 같은 경우에만 그 노드를 이용해 DFS를 호출해준다.

         (`D`보다 크거나 같은 경우, 현재 노드의 서브트리 노드 중 전단지를 돌릴 수 없는 노드가 존재하는 것)

   * (`dist` + `res`) 값을 리턴해준다.