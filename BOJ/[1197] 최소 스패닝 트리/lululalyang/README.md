# [BOJ]/[1197] 최소 스패닝 트리

## *- MST -*

#### :heavy_plus_sign:알고리즘 선택

* 최소 스패닝 트리(MST)를 구하는 알고리즘에는 'Kruskal'과 'Prim'이 있다.
  * 'Kruskal'은 간선 선택 기반 알고리즘으로, **O(eloge)**의 시간 복잡도
  * 'Prim'은 정점 선택 기반 알고리즘으로, **O(v^2)**의 시간 복잡도
  * 따라서, 해당 문자의 `V`, `E`의 범위에 따라 ***Kruskal 알고리즘*** 을 선택하였다.

#### **Main**

* Kruskal 알고리즘은 간선 선택 기반 알고리즘으로, 우선 간선을 가중치를 기준으로 오름차순으로 정렬해야한다.

  * `PriorityQueue` 사용

    => `PriorityQueue` 사용하기 위해 생성한 Class 객체에 `Comparable`을 상속(`implements`)한 후 `int compareTo(Object o)`를 오버라이드해준다.
    
    ```java
    class Edge implements Comparable<Edge>{
    	int w; // 가중치
        ...
            
        @Override
        public int compareTo(Edge e){
            return this.w >= e.w ? 1 : -1;
            // 현재 객체의 가중치가 전달받은 객체의 가중치보다 더 크면 1, 아니면 -1
            // '오름차순' 정렬시 조건 (-> 가장 작은 가중치의 edge를 골라야하므로)
        }
    }
    ```

#### Func

* `int Kruskal()` : Kruskal 알고리즘에 따라 MST를 구한 후, 그의 가중치를 return한다.

  * `void Make_set()` : 우선 `V`개의 루트노드를 생성하고 자기 자신만으로 초기화한다.
    * `p[i] = i`
  * `pq`로 저장된 edge 중 가중치가 가장 작은 edge, 즉 `pq.poll()`한 edge를 MST에 추가시킬지 확인한다. (모든 edge를 다 확인할 때 까지)
    * edge의 두 노드가 속한 트리의 루트 노드를 확인한다. (`int find(x)`)
      * 두 루트 노드가 같으면 이 edge는 현재까지의 MST에 추가되면 사이클을 만들게 된다는 의미 => 다음 edge를 확인한다.
      * 다르다면 두 노드가 속한 각 트리를 하나로 합친다. (`void union()`)

* `int find(x)`

  * `x`가 속한 트리의 루트노드를 return한다.

  * `p[x] == x` 라면 루트 노드이므로 `return x`

  * 그렇지 않다면, 재귀를 이용해 `x`의 루트노드를 찾는다.

    * 이때 루트 노드를 찾아 바로 `p[x]` 의 값을 바꿔주고, (시간 절약) 루트 노드를 return

      ```java
      p[x] = find(p[x]);
      return p[x];
      ```

* `void union(int root1, int root2)`

  * `root2`가 속한 트리를 `root1`이 속한 트리의 자손으로 합쳐준다.
  * `p[root2] = root1` : root2의 부모 노드를 root1로

