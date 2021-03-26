# [BOJ]/[4256] 트리

## *- Divide & Conquer -*

* *전위 순회 (PreOrder) : **root** -> Left -> Right*
* *중위 순회 (InOrder) : Left -> **root** -> Right*
* *후위 순회 (PostOrder) : Left -> Right -> **root***

------

* `static int[] preOrder` : 전위 순회를 입력받아 저장. 전역변수로 저장한다.

* `static int[] inOrder` : 중위 순회를 입력받아 저장. 전역변수로 저장한다.

#### solution

* `ArrayList<Integer> PostOrder(int prestart, int preend, int instart, int inend)`

  : 전달받은 전위순회와 중위 순회의 인덱스에 해당되는 서브트리의 postOrder를 리턴한다.

  * `int prestart, preend` : **preOrder[]** 에서 현재 확인해야하는 범위의 시작과 끝 인덱스
  * `int instart, inend` : **inOrder[]** 에서 현재 확인해야하는 범위의 시작과 끝 인덱스

  </br>

  1. **전위 순회**는 root부터 시작되므로 현재 전달받은 서브트리의 루트는 `preOrder[prestart]` 가 된다.

     * `int root = preOrde[prestart]`

  2. **중위 순회**는 left서브트리 다음 root이므로, root 이전까지가 left 서브트리가 된다. 따라서 root의 위치를 찾아준다.

     * `int rIdx = FindNumIninOrder(root)` 

       : `inOrder`배열에서 `root`의 인덱스를 찾아 `rIdx`에 저장한다.

  3. left서브트리와 right서브트리를 확인하여 각 List에 `add()`해준다.

     1. left 서브트리
        * left서브트리의 크기가 `1`이라면 그 하나의 노드만으로 이루어진 서브트리로 `ArrayList<Integer> left`에 노드에 해당하는 숫자를 `add()`해준다.
        * 크기가 `2`이상이라면 2개 이상의 노드로 이루어진 서브트리이므로, 이 서브트리로 다시 `postOrder()`를 재귀로 호출한후 그 리턴값을 `left`에 할당한다.
        * 크기가 `0`이라면 left 서브트리가 없는 것!
        * 크기계산은 인덱스로 처리해준다.
     2. right 서브트리
        * left 서브트리와 동일하게 처리해준다.
        * `ArrayList<Integer> right`

  4. **후위 순회**는 left 서브트리 다음, right 서브트리 다음, root 순이므로 앞 과정에서 구한 `left`, `right`리스트를 차례로 결과 리스트인 `res`에 `add()`해주고, 마지막에 `root`를 `add()`해준다. 

     * `res`리스트가 후위 순회된 순서로, 리턴값이 된다.

</br>

## :speaking_head:

* 각 순회의 순서를 잘 알아두자! 순서만 잘 안다면 쉬운 문제!