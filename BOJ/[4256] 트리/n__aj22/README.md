# 백준 4256번 : 트리

## Algorithm

Tree, 분할 정복, 재귀

## Description

**`find_postorder(pre_s, pre_e, in_s, in_e)`** : 

+ 파라미터
    
    + (pre_s, pre_e) : 확인하려고 하는 preorder 의 시작 인덱스, 끝 인덱스
    + (in_s, in_e) : 확인하려고 하는 inorder 의 시작 인덱스, 끝 인덱스

+ 현재 트리에서 root는 `preorder[pre_s]`이다.

    + preorder의 경우 root를 먼저 출력하기 때문에 preorder 중 첫번 째 값이 현재 트리의 root
    + postorder에서는 이 root를 가장 마지막에 출력한다.

+ 현재 트리의 왼쪽 자식 노드들은 inorder에서 root 전까지의 노드들이다.

    + 따라서 in_s 부터 in_e 까지 값을 확인하면서 root와 같으면 그 때의 i-in_s값이 left_length에 저장한다. left_length는 root기준 왼쪽의 서브 트리의 개수를 의미한다.

+ postorder은 왼쪽서브트리, 오른쪽서브트리, 루트 순으로 출력한다.

    + 왼쪽 서브트리를 호출한다.

        + preorder에서 왼쪽 트리 : pre_s+1, pre_s+left_length
        + inorder에서 왼쪽 트리 : in_s, in_s+left_length-1

        ```python
        find_postorder(pre_s+1, pre_s+left_length, in_s, in_s+left_length-1)
        ```

    + 오른쪽 서브트리를 호출한다.

        + preorder에서 오른쪽 트리 : pre_s+left_length+1, pre_e
        + inorder에서 오른쪽 트리 : in_s+left_length+1, in_e
        ```python
        find_postorder(pre_s+left_length+1, pre_e, in_s+left_length+1, in_e)
        ```
    + root값 출력한다.

+ 재귀 종료 조건
    ```python
    if(pre_s>pre_e):
            return
    ```
  
## Review

