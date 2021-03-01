# 백준 1068 : 트리

## Algorithm

BFS, tree

## Description

1. 트리를 dictionary로 표현하고, root를 찾는다.

    예를 들어 다음 입력이 들어왔다고 하자.
    ```
    5
    -1 0 0 1 1
    2
    ```
    다음과 같이 dictionary 형태로 바꾼다.
    ```
    root = 0
    tree_dic = {0: [1, 2], 1: [3, 4], 2: [], 3: [], 4: []}
    ```

2. 만약 지우려는 노드가 root 노드와 동일하면 0을 출력한다.(모두 삭제되기 때문)

3. 만약 지우려는 노드와 root 노드가 다르면 노드를 삭제하는 delete 함수를 호출한다.

+ `delete(node)` : 삭제하려는 node를 파라미터로 가져와 BFS를 사용해서 tree_dic에서 삭제해준다.

+ 호출 후 tree_dic에 남은 요소 중 value를 []로 갖는 key가 있다면 해당 노드가 리프 노드이다. 따라서 리프 노드의 개수를 확인한 후 출력한다.

## Review
