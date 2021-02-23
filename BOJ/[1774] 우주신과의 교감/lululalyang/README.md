# [BOJ]/[1774] 우주신과의 교감

## *- MST _ Kruskal -*

#### Main

* `Vertex[] V = new Vertex[N+1];` : 각 정점의 좌표를 저장
* `boolean[][] Dist = new boolean[N+1][N+1];` : 기존에 존재하는 간선인지 아닌지 확인
* `M`개 만큼의 이미 연결된 통로를 입력받아 그 두 정점과 사이 거리를 `pq1`에 `add`한다.
  * 이때, `add()`되는 간선은 `Dist`를 `true`로 변환시켜준다
* `Dist`를 스캔하면서 `false`인 간선, 즉 아직 존재하지 않는 통로는 `pq2`에 `add`한다.

#### Func

* `double Kruskal(int N, Vertex[] V, PriorityQueue<Edge> pq1, PriorityQueue<Edge> pq2)` 

  : 추가로 연결해야 하는 통로의 최소 길이를 구해 return한다.

  * Kruskal 알고리즘 사용.
  * `pq1`으로 먼저 union-find 진행. 
    * 기존에 존재하는 통로부터 트리구조를 완성시켜준다.
    * 이때 통로의 길이는 신경쓰지 않아도 됨. (`flag = false`)
  * `pq1`을 끝내고 `pq2`로 union-find를 진행한다.
    * 이때에는 새로 만들어 줘야하는 통로이므로 길이를 계산해주어야 한다. (`flag = true`)

* ` double Loop(PriorityQueue<Edge> pq, int[] p, double res, boolean flag)` 

  : kruskal 알고리즘 내부에 존재하는 반복문

  * `pq1`과 `pq2` 두 pq 모두 루프를 반복해야하므로 따로 구현해주었다.
  * 이때, `pq1`은 거리를 계산하지 않아도 되고, `pq2`는 거리를 계산해야하므로 `flag`로 구분하여 처리하였다.

* `void make_set(int[] p)` : 정점 각각 자기 자신만 존재하는 트리를 만든다.

* `int find(int v, int[] p)` : 해당 정점 `v`의 루트노드를 return

* `void union(int root1, int root2, int[] p)` : `root1`이 속한 트리와 `root2`가 속한 트리를 하나의 트리로 합친다.

* `double ComputeDist(Vertex v1, Vertex v2)` : 두 정점 `v1`, `v2` 사이의 거리를 return

</br>

## :speaking_head:

* Prim 알고리즘을 사용하여 풀다가 이 알고리즘을 풀면 안된다는 것을 반례를 통해 알았다. 

  * Prim은 정점을 기반으로 하는 알고리즘으로 방문여부를 체크해주는데, 만약

    4 2
    
    0 0
    
    0 1
    
    0 2
    
    0 3
    
    1 4
    
    2 3

    이라는 입력이 들어왔다면 이미 존재하는 간선이 1-4, 2-3으로 모든 정점을 방문한 것으로 처리되어 추가로 더 통로를 만들지 않게 되고 이는 오답이 된다.

* 그래서 이미 존재하는 통로를 `boolean[][] Dist`를 이용해 구분하여서 `pq1`, `pq2`를 따로 만들어 주었고 이를 이용하여 kruskal 알고리즘을 적용하였더니 정답이었다.
