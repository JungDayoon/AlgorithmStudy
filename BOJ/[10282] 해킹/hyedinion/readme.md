# [BOJ]/[10282] 해킹

## 분류
shortest Path (Dijkstra)

## 접근법
1. 시작node에서 갈 수 있는 node의 distance를 갱신해준다.
2. 그 다음 비용이 작은 node로 움직인다.
3. 다음 node에서 갈 수 있는 distance를 갱신해준다.
4. 반복한다.

## 놓친것
1. 다음 node를 고를 때 비용이 작은 것을 골라야 한다. (deque가 아닌 heapq사용)<br>
heappush(queue,[비용, node])


## 후기
다익스트라 정복!<br>
위상정렬, 다익스트라, 크루스칼 등 그래프이론은 꼭 복습하자.<br>