# 백준 19542번 : 전단지 돌리기

## Algorithm

트리

## Description

### 기본로직

1. 입력받은 간선 정보를 이용해 트리를 만든다.

2. 해당 문제는 노드가 트리의 끝에서 부터 어느 위치에 있는지가 중요하기 때문에 root 에서 부터의 깊이가 아니라, 끝에서 부터의 깊이를 확인해서 저장한다.

3. 저장된 깊이를 바탕으로 현민이가 이동해야 하는 거리를 확인한다.

### 함수설명

**`void make_tree(int n)`** : 시작노드 부터 DFS로 노드를 탐색하며 트리를 만들어 주는 함수

+ 현재 노드 n에 연결된 모든 노드를 확인하면서 방문한 적이 없는 노드를 만났다면 그 노드를 현재 노드 n의 자식 노드에 추가해주고 방문표시를 해준 후 자식 노드를 n으로 하는 make_tree 함수를 재호출 한다.

+ 다음과 같은 트리가 있다고 하자.

    <img src="https://user-images.githubusercontent.com/33089715/110231309-68fb0e80-7f5a-11eb-9820-861a9aa33a48.png" width="700">

+ make_tree를 호출한 후에 자식 노드를 저장하는 child_adj는 다음과 같다.

    <img src="https://user-images.githubusercontent.com/33089715/110231545-b2982900-7f5b-11eb-9905-9361c36ae1c0.png" width="600">

**`int check_depth(int n)`** : top-down 방식으로 트리의 단말 노드부터의 깊이를 찾아서 저장한다.

+ 트리의 단말 노드라면 (자식 노드가 없으면) : 끝 노드이기 때문에 depth는 0이고 0을 반환한다.

    ```cpp
    if(child_adj[n].size() == 0){//no child
        depth[n] = 0;
        return 0;
    }
    ```
+ 단말 노드가 아니라면 (자식 노드가 존재하면) : 자식 노드 중 더 최대 깊이 값에 1을 더한 값을 depth로 갖는다.
    ```cpp
    int n_depth = 0;
    for(int nextnode:child_adj[n]){
        n_depth = max(n_depth, check_depth(nextnode));
    }
    n_depth++;
    depth[n] = n_depth;
    return n_depth;
    ```
+ 위의 예시에서의 트리로 check_depth 호출 후에 결과는 다음과 같다.

    <img src="https://user-images.githubusercontent.com/33089715/110231816-7bc31280-7f5d-11eb-8640-c6a294132c1a.png" width="600">

**`int go(int n)`** : depth가 D보다 크거나 같은 노드를 탐색하기 위해 이동한 거리 + 1를 return

+ 답은 (go를 호출한 결과 - 1)*2한 결과이다.

## Review

어려웠다.