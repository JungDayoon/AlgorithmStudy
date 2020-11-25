# [Programmers]/[49993] 스킬트리

## *- Simulation -*

* `LinkedList<Character> skillList`: 선행스킬순서에 있는 각 스킬을 연결리스트로 저장

* `skill_trees`에 있는 각 스킬트리를 확인한다.

  * 스킬트리의 한 스킬이 `skillList`에 속하는 스킬이라면

    * `skillList`의 복제본 인 `tmpList`의 첫번째 스킬과 동일한지 확인

      * 동일하다면 첫번째 스킬 삭제

      * 첫문자가 아니라면 -> 선행스킬이 없었으므로 불가능한 스킬트리이다

        -> `flag=1`로 하고 `break`

  * `skillList`에 존재하지 않는 기술이라면 -> 순서에 상관없는 기술이므로 그냥 넘어간다!

  * `flag`가 `0`이라면 `answer++`

* `answer`이 최종 답

</br>

## :speaking_head:

현재 노드를 가리키는 노드를 하나 만들어서 옮겨주면서 하려다가 이렇게 해도 될거같아서 그냥 복제본 만들고 가장 첫번째 노드를 삭제하는 방법으로 구현하였다 !