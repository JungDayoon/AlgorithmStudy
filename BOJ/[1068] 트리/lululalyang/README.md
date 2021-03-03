# [BOJ]/[1068] 트리

## *- DFS -*

#### Main

* `ArrayList<Integer>[] g = new ArrayList[N]`

  : 트리 저장

#### Func

* `int CountLeaf(int N, int Root, int Delete)`

  : `Delete`노드를 지웠을 때 리프 노드의 개수를 return한다.

  * 만약, 루트노드 `Root`와 `Delete`가 같다면 `return 0`

    * 존재하는 노드 없음

  * dfs를 이용하여 지워야 하는 노드를 찾는다.

    ` void ChkDeleteNode(int now, int Dnode, boolean delete, boolean[] deleteNode)`

    1. `delete`가 `false`라면 `Dnode`를 찾는 과정

       : `g[now]`의 자식노드(`next`)를 스캔한다.

       * `Dnode`라면 `g[now]`에서 `Dnode`를 지우고, `deleteNode[next] = true` 처리해준 후, 현재 자식노드로 dfs를 진행한다. (이때, `delete=true`)
       * `Dnode`가 아니라면 아무 처리없이 `Dnode`로 dfs를 진행한다. (`Dnode`를 찾는 과정)

    2. `delete`가 `true`라면 자식 노드를 지우는 과정

       : `g[now]`의 자식노드(`next`)를 스캔한다.

       * `deleteNode[next] = true`처리해준 후, 현재 자식노드로 dfs를 진행한다. (지워야 하는 노드의 모든 자식노드는 다 삭제되기 때문이다.)

  * dfs를 끝낸 후에 `deleteNode`값이 `true`라면 그 노드의 자식노드는 모두 삭제한다. (`g[i].clear()`)
  * 마지막으로, 지워진 노드가 아니면서(`deleteNode[i] == false`) 자식노드가 없다면(`g[i].size()==0`) 리프노드의 개수애 포함시킨다.

## :speaking_head:

* 처음에 런타임에러가 나서 확인해보니까 부모노드를 입력받으면서 `g[i]`를 초기화 해줘서 `ArrayList`가 할당되지 않은 인덱스에 자식노드를 `add()`할 때 에러가 났었다. 실수하지말좌 !!!