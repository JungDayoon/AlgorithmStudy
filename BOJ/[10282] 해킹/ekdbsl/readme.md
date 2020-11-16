# [BOJ 10282] 해킹 - Python

### :computer: Algorithm

> 다익스트라 알고리즘



### :computer: Logic

기본적인 다익스트라 알고리즘을 Priority Queue를 사용하여 구현했다.

adj 리스트를 잘 만들어준 다음, 다익스트라의 핵심인 greedy한 방식으로 현재까지의 dist와 그 전 노드의 dist + 현재 노드까지의 거리를 비교하여 갱신하는 방식으로 풀이하였다.

```python
if distance[curr[0]] + next[1] < distance[next[0]]:
                distance[next[0]] = distance[curr[0]] + next[1]
                heapq.heappush(pq, (distance[next[0]], [next[0], distance[next[0]]]))
```



