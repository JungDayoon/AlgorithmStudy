# SWEA 1949번 : 등산로 조성

## Algorithm

DFS

## Description

1. dfs(road, x, y, K, visited, num, N) : 시작점을 기준으로 가능한 등산로를 마다 num+1 해주며 dfs 

+ road : 산의 길 정보를 나타내는 이차원 리스트
+ x, y : 좌표
+ K : 깎을 수 있는 최대 높이
+ visited : 방문한 곳을 표시함
+ num : 방문 횟수를 저장하기 위함
+ N : N
    
  + 현재 위치의 visited를 1로 바꿔주고 현재 위치 기준 4군데 확인
    + 조건
      + 길의 범위를 넘어가지 않아야함
      + 방문한 적이 없어야함
      + 높이조건
      
        1) 현재 위치보다 다음 위치가 높지만, 높이 차이가 K 보다 작다면
          + 다음 지형을 현재 높이보다 하나 낮게 깎아준다.
          + 바뀐 road로 dfs 다시 호출, 단 K는 이미 썼으므로 0으로 바꿔준다.
          + dfs 호출 후에는 다시 지형을 원상복구
          ``` python
            if(road[nexty][nextx]>=road[y][x] and road[nexty][nextx]-road[y][x]<K):
                    temp = road[nexty][nextx]
                    road[nexty][nextx] = road[y][x]-1
                    dfs(road, nextx, nexty, 0, visited, num+1, N)
                    road[nexty][nextx] = temp
          ```
        2) 현재 위치보다 다음 위치가 낮은 경우
          ``` python
            elif(road[nexty][nextx]<road[y][x]):
                    dfs(road, nextx, nexty, K, visited, num+1, N)
          ```

  + 종료조건 : 현재 위치 기준 4군데 모두 확인이 끝났다면(더 이상 갈 수 없음을 의미) 그때의 num 값이 현재 길로 왔을 때 방문한 등산로의 개수이므로 max_height와 비교해서 저장
    + 다른 루트를 통해서 이 곳을 방문할 수도 있기 때문에 visited를 다시 0으로 바꿔준다.
2. main

+ 최대 높이 봉우리를 max_bong 에 저장해두고 max_bong 마다 bfs를 실행해준다.
+ 최대 개수 max_height 출력

## Review

조건이 까다로워 보였지만 잘 생각하니까 금방 아이디어가 생각나서 40분 정도 만에 푼 것 같다.! 


