# [BOJ]/[1967] 트리의 지름

## *- DP, Greedy -*

#### 트리의 지름

트리 내에서 가장 먼 두 정점 사이의 거리.

* **`far[v]` : 정점 `v`와 `v`에서 가장 먼 정점 사이의 거리** (모든 정점에 대해)

  => 트리의 지름 = `MAX(far[v])`

#### 1. DP

* 시간 복잡도 : `O(V)` (`V`: 노드 개수)

예) `far[2]`를 구하려고 할 때 => `2` 를 기준으로 두 가지로 분류한다.

​	(`2`의 서브트리인 노드 - 파란색) / (그렇지 않은 노드 - 주황색)

​                       ![image](https://user-images.githubusercontent.com/33208360/110060522-65745580-7da9-11eb-9b39-0e57eaab9699.png)            ![image](https://user-images.githubusercontent.com/33208360/110060696-a8cec400-7da9-11eb-8da8-b1acba901b87.png)

* `dist[v]` : 정점 `v`와 `v`의 **서브트리 "내"**의 정점 중 가장 먼 정점 사이의 거리 

 * `parent_far[v]` : 정점 `v`와 `v`의 **서브트리 "외"**의 정점 중 가장 먼 정점 사이의 거리 

   => `far[v] = MAX(dist[v], parent_far[v])` 가 된다.

1. `dist[v] = MAX(dist[자식노드] + w)` (`w`: Edge의 가중치) 

   => DP를 이용해 구할 수 있다

   ```java
   void dist_DFS(int now, boolean[] visited){
       visited[now] = true;
       dist[now] = 0;
       for(Edge next : g[now]){
           if(!visited[next]){ // 아직 확인하지 않은 노드라면
               dist_DFS(next.v, visited); // 그 노드의 dist를 구해준다
               dist[now] = Math.max(dist[now], dist[next.v] + next.w); // 자식노드 들 중 최대값으로 갱신
           }
       }
   }
   ```

2. `parent_far[v]`

   * 이를 구하기 위해 서브트리 외 정점을 2가지 그룹으로 나누어야 한다.

     (`v`의 부모 정점의 서브트리에 있는 노드) / (그렇지 않은 노드)

     예) `parent_far[4]`를 구하려고 할 때 => `4`의 부모노드는 `2`이므로 다음과 같이 나뉘게 된다.

     ![image](https://user-images.githubusercontent.com/33208360/110061803-8ccc2200-7dab-11eb-9fbd-2ccfece56aef.png)

     1. 부모 정점의 서브트리에 **없는** 경우 (주황색)

        `parent_far[v] = parent_far[부모정점] + w` (`w`: `v`와 부모정점 사이 Edge의 가중치)

        (예를 들어 주황색노드까지의 경우, `4`의 `parent_far`은 부모노드인 `2`를 거친 후 `parent_far[2]`만큼 가는 경로가 최장 거리이기 때문)

     2. 부모 정점의 서브트리에 **있는** 경우 (파란색)

        `parent_far[v] = dist[부모정점] + w` (`w`: `v`와 부모정점 사이 Edge의 가중치)

        (예를 들어 파란색 노드까지의 경우, `4`의 `parent_far`은 부모 노드인 `2`를 거친 후 `dist[2]`만큼 가는 경로가 최장 거리이기 때문)

        **BUT**, `dist[부모노드]`에서 선택된 부모노드에서 가장 먼 정점(파란 정점 중)이 현재 정점인 `v`의 서브트리에 있을 경우에는 `dist[부모정점] + w`가 답이 아니다.

        예를 들어, 아래의 트리에서 `parent_far[3]`을 구하려고 할 때,

        ​													 <img src="https://user-images.githubusercontent.com/33208360/110062609-eda82a00-7dac-11eb-9442-4c3b3adf57db.png" alt="image"  />

        `parent_far[3] = 2` 여야하지만 (3->1->2), `3`의 서브트리에 속해있는 정점 `4`가 `3`의 부모노드인 `1`의 가장 먼 정점이 되는데, 이는 위의 2번째 경우에 속하므로 `parent_far[3] = dist[1] + 1 = 2 + 1 = 3`으로 오답이 도출된다. 

        * 이 경우를 위해, `dist[부모노드]`값을 구할 때 `dist[v]+w`을 제외한 결과 필요하다.

          ( 부모노드의 `v`외의 다른 자식노드 트리의 가장 먼 거리!)

          이를 위해 `dist2[v] = (dist[자식노드] + w) 중 2번째로 큰 값` 을 저장하면, 위의 경우 2일때 `dist[]`가 아닌 `dist2[]`값으로 답을 구할 수있다.

#### 2. Greedy

1. 트리에서 임의의 정점 `v1`에서 가장 먼 정점 `v2`를 찾는다. => DP이용
2. `v2`와 `v2`에서 가장 먼 정점 `v3` 사이의 거리를 찾는다.
3. 이 `v2`와 `v3` 사이의 거리를 구하면 그게 트리의 지름이 된다.

* `dist[v]` : 정점 `v`와 `v`에서 가장 먼 서브트리 내 정점 사이 거리

  `dist[v] = MAX(dist[자식노드] + w)`

* `v1` 에서 가장 먼 거리는?!

  => `v1`을 루트라고 생각

  => `dist_DFS(v1)`을 호출

  => `dist[v1]`이 `v1`에서 가장 먼 거리

* `dist_DFS(v)`가 목적지 노드를 리턴하도록 => `v`에서 가장 먼 정점이 어디인지 리턴받는다.

  ```java
  int dist_DFS(int now, boolean[] visited){
      visited[now] = true;
      int farNode = now; // now에서 now의 서브트리 중 가장 먼 정점 farNode
      dist[now] = 0;
      for(Edge next : g[now]){
          if(!visited[next.v]){
              int farNode_next = dist_DFS(next.v, visited);
              // 자식노드 next에서 next의 서브트리 중 가장 먼 정점: farNode_next
              if(dist[next.v]+next.w > dist[now]){
                  dist[now] = dist[next.v] + next.w;
                  farNode = farNode_next; 
              }
          }
      }
      return farNode; 
  }
  ```

  

