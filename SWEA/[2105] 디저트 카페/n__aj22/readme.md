# SWEA 2105번 : 디저트카페

## Algorithm

백트래킹, DFS

## Description

1. dfs(cafe, direction, starty,startx, nowy, nowx, visited, num, N, turn) : 
+ cafe : 카페 정보를 담은 이차원 리스트
+ direction : 현재 진행중인 direction 숫자
+ starty, startx : 시작 y, x, 종료조건으로 사용됨
+ nowy, nowx : 현재 y, x
+ visited : 방문한 디저트 번호를 담아줌
+ num : 디저트 개수 
+ N : N
+ turn : 방향전환을 한 횟수를 저장해줌, starty, startx 와 함께 종료조건으로 사용됨

  + 종료 조건 : 현재 위치와 처음 시작한 위치가 동일하고 turn 횟수가 4인 경우
    + 현재 디저트 개수와 저장된 최대 디저트 개수를 비교해 최대값 저장해줌
  ``` python
    if(startx == nowx and starty == nowy and turn == 4)
  ```
  + 방문한 적 있는 디저트인 경우 : return
  ``` python
    if(cafe[nowy][nowx] in visited)
  ```
  
  + 방문한 적 없는 디저트인 경우
    + visited 리스트에 현재 디저트 번호를 저장해줌
  ``` python
    visited.append(cafe[nowy][nowx])
  ```
    + 현재 진행중인 방향으로 nextx, nexty를 찾아준다.
      + nextx, nexty 가 카페 범위에 벗어나지 않으면 해당 위치 정보를 저장한 dfs 를 다시 호출해준다.
  ``` python
    dfs(cafe, direction, starty,startx,  nexty, nextx, visited, num+1, N, turn) 
  ```
    + 위의 dfs 가 종료되면, 다음은 direction 을 전환해주고 전환된 방향으로 nextx, nexty 를 찾아준다.
      + nextx, nexty 가 카페 범위에 벗어나지 않으면 해당 위치 정보를 저장한 dfs 를 다시 호출해준다.
      + 이 때 주의할 점
        + direction 은 (direction+1)%4 (방향 전환을 했기 때문)
        + turn = turn+1 (방향 전환을 했기 때문)
  ``` python
    dfs(cafe, (direction+1)%4, starty,startx,  nexty, nextx, visited, num+1, N, turn+1)
  ```
    + 위의 두 dfs 가 모두 종료되면 현재 위치를 방문 목록에서 제거해준다.
  ``` python
    visited.remove(cafe[nowy][nowx]) 
  ```
  
2. main
+ 카페 모든 인덱스를 돌면서 dfs 호출

## Review

테케 49개 맞아서 넘 힘들었다..

틀린 이유는 , 기존에 내 생각으로는 만약에 진행중인 방향으로 가다가 하나의 사각형을 완성하면 flag를 True 로 바꿔주고, dfs 호출 후 flag 가 True 이면 바로 return 을 불러서 종료되게 했는데 더 진행해야 하는 경우도 있었나보다.

방향은 우하, 좌하, 좌상, 우상 방향을 차례대로 돌게 하니까 호출하기 편했다!

        
