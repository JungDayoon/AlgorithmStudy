# [BOJ]/[1516] 게임개발

## 분류
DFS

## 접근법
정답 구하는 법<br>
먼저 지어야 하는 건물이 없으면 자기자신<br>
먼저 지어야 하는 건물이 있으면 max(그건물들을 짓는데 걸린 시간) 더하기 자기자신<br>

## main
```python
tower = [[] for _ in range(N)]
check = [False for _ in range(N)]
tower_stack = [[] for _ in range(N)]
answer = [0 for _ in range(N)]
```
`tower` : 건물 저장후 시간순으로 sort<br>
`check` : 지어진 건물인지 확인<br>
`tower_stack` : 먼저지어져야 하는 번호가 pre면 tower_stack[pre]에 자기자신을 넣어준다<br>
`answer` : 건물을 짓는데 걸린 시간<br>

`check_pre()` : 먼저 지어질 건물들이 지어졌는지 확인, 확인하면서 지어진 건물 중 가장 오래걸린 시간을 return<br>
`build_tower()` : `check_pre()`이면 `check[I]=True`, `answer[I]=time+t[0]`해주고 자신의 tower_stack에 있는 tower들을 다시 `build_tower()`한다<br>


## 후기
헉.. 한번에 맞아서 상쾌<br>
처음에는 current time을 두고 자기자신을 더해주게 했는데 이 current_time을 더하기로만 하려다 보니 문제가 많이 생겼다. 조심조심