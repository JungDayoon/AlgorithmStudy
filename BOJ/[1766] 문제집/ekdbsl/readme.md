# [BOJ 1766] 문제집 - Python

### :computer: Algorithm

> 위상정렬, 우선순위 큐



### :computer: Logic

- **주어진 입력값을 그래프로 나타내기** -> 위상정렬을 사용하기 위해서 adj리스트와 노드로부터 들어오는 간선의 개수를 담은 indegree 리스트가 필요하다.
- indegree가 0인 노드를 queue에 담고, 그 노드로부터 연결된 노드의 indegree를 줄여주는 작업을 반복한다.
  - 이 때, 큐에서 노드를 꺼낼 때 번호가 작은 순으로 꺼내야 하므로 꺼내기 전에 sort해주었다. priority queue 라이브러리가 있긴 하던데 외부 라이브러리를 못 쓰게 하는 경우도 있고, 매우 간단한 우선순위 큐라서 직접 구현했다.



### :computer: Review

> 걸린 시간: 10분

의량이한테 우선순위 큐 사용하면 된다는 말 듣고 풀어서 엄청 빨리 풀었당