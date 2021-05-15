# 백준 16929 : Two Dots

## Algorithm

DFS

## Description

**`DFS(visited, startingpoint, y, x, arr, alpha, count)`** : 사이클이 존재하는지 여부를 Boolean 으로 return 하는 함수
+ startingpoint : 사이클이 존재하는지 여부는 탐색 중 startingpoint와 같으면 사이클이 존재한다.
    + 따라서 DFS 탐색 중, count가 4이상이고, startingpoint와 같은 점을 만나면 사이클이 존재한다고 판단하여 True를 return 한다.
    + 현재 점 기준 4방향 모두 방문이 불가능하면 False를 return 한다.


## Review
