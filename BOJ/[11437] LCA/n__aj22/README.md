# 백준 11437번 : LCA

## Algorithm

Tree, LCA

## Description

**`depth`** : 트리의 각 정점마다의 깊이가 저장되어있다.

**`parent`** : `parent[i][k]` 에는 i번 정점의 2^k번째 부모가 저장되어 있다. (시간을 O(N)에서 O(logN)으로 줄이기 위해 사용)

**`make_tree(count, index, parent_index)`** : 처음에 트리를 만들어 주는 함수.
    
+ 시작 정점 1부터 DFS를 하며, 각 정점의 깊이와 정점의 부모를 저장한다. 2^0번째 부모이기 때문에 `parent[i][0]`에 모든 값을 저장해준다.

+ 예를 들어 다음과 같은 트리가 있다고 하자(예제)
<img src="https://user-images.githubusercontent.com/33089715/109943954-0c5ce100-7d19-11eb-8e0c-13b1e2566e82.png" width="500">

+ make_tree 호출 후 depth와 parent는 다음과 같다.

    <img src="https://user-images.githubusercontent.com/33089715/109944847-fef42680-7d19-11eb-9c53-22f27018f20f.png" width="650">

    *parent는 보기 편하게 col, row가 반대로 되어있습니다.*

**`fill_parent()`** : parent 배열을 채워주는 함수

+ `parent[u][k+1] = parent[parent[u][k]][k]`임을 이용해 bottomup 으로 parent 배열을 채운다.

+ 호출 후에 parent는 다음과 같다.

    <img src="https://user-images.githubusercontent.com/33089715/109945837-fea85b00-7d1a-11eb-8e39-105d9ab6a463.png" width="650">

    *parent는 보기 편하게 col, row가 반대로 되어있습니다.*

+ 예를들어 `parent[15][2]`는 정점 15의 2^2=4, 즉 4번째 조상 부모를 의미합니다.

**`find_common_parent(u, v)`** : 정점 u와 정점 v의 공통 부모를 찾는 함수, **LCA 알고리즘 적용**

1. u와 v의 깊이를 같게 만듭니다. 
    
+ 예를 들어 u와 v의 깊이 차가 11이라고 합니다. 11을 2진수로 변환하면 1011(2)입니다. 그렇다면 u의 2^0번째 부모 → 2^1번째 부모 → 2^3번째 부모로 u를 대체합니다. 

    ```python
    count = 0
    if(depth[u]>depth[v]):
        depth_diff = depth[u]-depth[v]
        while(True):
            if(depth_diff == 1):
                u = parent[u][count]
                break
            diff_mod = depth_diff%2
            depth_diff = depth_diff//2
            if(diff_mod == 1):
                u = parent[u][count]
            count+=1
    ```
+ 이 과정을 완료하면 u와 v의 깊이가 같아집니다.

2. 둘의 깊이를 똑같게 유지하며 정점들을 이동시켜준다.
- parent[u][k] ≠ parent[v][k] : 두 정점 u, v의 LCA 깊이는 둘로부터 2^k보다는 멀리 떨어져 있음이 확실하다.
- parent[u][k+1] == parent[u][k+1] : 2^k, 2^{k+1}사이의 어딘가에 둘의 LCA가 있다.
- 따라서 k를 큰 값부터 시도하면서 순회하여, parent[u][k] ≠ parent[v][k] 이면 u, v를 동시에 2^k만큼 위로 올려보낸다.

    ```python
    if(u!=v):
        for i in range(17, -1, -1):
            if(parent[u][i] != -1 and parent[u][i]!= parent[v][i]):
                u = parent[u][i]
                v = parent[v][i]
        u = parent[u][i]
    ```
3. u를 return 한다.

## Review

LCA 공식을 사용해서 시간을 최소한으로 했기 때문에 무조건 통과라고 생각했는데 recursive error가 떴다. 

똑같은 문제 11438번에도 통과될 거라고 생각했는데 메모리초과에 시간초과에... 내생각엔 똑같은 로직을 cpp로 하면 통과될 듯? 

파이썬 넘 힘들다.. ㅎㅎ