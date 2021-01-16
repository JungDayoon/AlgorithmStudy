# [49993] 스킬트리
## 분류💁

시뮬레이션

</br>

## 접근법

> 선행 스킬 순서 skill과 유저들이 만든 스킬트리1를 담은 배열 skill_trees가 매개변수로 주어질 때, 가능한 스킬트리 개수를 return 하는 solution 함수를 작성
- skill_trees[i]의 한 스킬을 s라고 한다면, 
- s가 skill_tree에 존재하고, 자기 차례이면 `가능`
- s가 skill_tree에 존재하고, 자기차례 아니면 `불가능`
- s가 skill_tree에 존재하면, `가능`
- 여기에 skill이나 skill_tree가 끝나는 경우면 `가능` 한 경우도 추가했다.

</br>

## 후기💡

- skill_tree에 skill에 아에 없는 알파벳인 경우도 고려해야한다.!
