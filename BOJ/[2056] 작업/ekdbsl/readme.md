# [BOJ 2056] 작업 - Python

### :computer: Algorithm

> Topological Sort



### :computer: Logic

어떤 일이 다른 일보다 반드시 선행되어야 한다는 조건이 주어지는 경우는 Topological sort로 해결한다.

주어진 input으로 `adj`와 `indegree` 리스트를 완성한다.

`adj`는 현재 노드에서 뻗어나가는 다음 노드의 번호를 저장한다.

`indegree`는 현재 노드로 들어오는 간선의 노드 개수를 저장한다.

`queue`에 들어오는 간선의 개수가 0인 노드들을 모두 넣어주고, `queue`가 빌 때까지 다음을 반복한다.

1. `queue`의 첫번째 원소를 pop하고, 그 노드에서 나가는 간선들을 다 제거한다. 즉 그 노드에서 뻗어나가는 간선의 노드들의 `indegree` 값을 하나씩 줄여준다.
2. `Next` 노드들의 작업이 끝나는 시간과 (현재 노드의 작업이 끝난 시간 + `Next` 노드의 수행시간)을 비교하여 더 큰 것을 저장한다.
3. 들어오는 간선의 개수가 0이라면, `queue`에 `Next`를 append한다.



### :computer: Review

> 걸린 시간: 타이머 누르는 걸 깜빡했다

위상정렬 문제는 아직 조금 낯설다. 다른 문제들로 더 연습해봐야겠다 ~!