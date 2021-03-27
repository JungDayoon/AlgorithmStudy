# 백준 2636번 : 치즈

## Algorithm

Simulation, BFS

## Description

**`choose_cheeze(arr)`** : arr에서 공기에 접촉한 치즈의 좌표 list를 return 한다.

1. 가장자리는 무조건 0이기 때문에 [0, 0]을 시작으로 공기를 BFS로 찾는다.

2. pop된 위치를 기준으로 4방향을 확인할 때, 

+ arr[nexty][nextx] == 1 이라면, 공기와 접촉하고 있는 치즈이다. 따라서 cheeze_list에 담아준다.(있는지 먼저 확인한 후 없으면)

+ arr[nexty][nextx] == 0 이라면, 공기이므로 방문여부 표시를 하고 queue에 담아준다.

## Review 

쉽다.

