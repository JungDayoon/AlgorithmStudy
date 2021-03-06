# [BOJ 18223] 민준이와 마산 그리고 건우

### :computer: Algorithm

> 다익스트라 



### :computer: Logic

이 문제도 기본 다익스트라와 동일하지만, 한가지 추가되는 제약 조건이 건우를 데리고 가는 최단거리도 구해주어야 한다는 것이다.

따라서, 시작점부터 각 노드까지의 최단거리를 모두 구해주고 (dist 리스트에 저장)

건우가 있는 노드부터 각 노드까지의 최단거리도 구해준다.(dist2 리스트에 저장)



건우(노드번호: P-1)를 데리고 마산(노드번호: V-1)까지 가는 거리는 결국 dist[P-1] + dist2[V-1]가 된다.

-> `시작점-건우까지의 최단거리` + `건우-종착점까지의 최단거리`

건우를 데리고가지 않고 마산까지 바로 가는 최단거리는 dist[V-1]가 된다.

이 두개의 값을 비교하여 건우를 데리고 가는 최단거리가 바로 가는 거리보다 길어지지 않는다면 건우를 도와줄 수 있다.