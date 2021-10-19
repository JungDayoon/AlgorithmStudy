# SWEA 2105번 : 디저트카페

## Algorithm

백트래킹, DFS

## Description

1. dfs(N,I,J,i,j,space,direct_num,Move,disert_list) : 
+ I,J : 출발 index
+ i,j : 현재 index
+ direct_num : 움직이는 방향 D = [[1,1],[1,-1],[-1,-1],[-1,1]]
+ Move : 각각의 방향으로 움직인 횟수
+ disert_list : 먹은 디저트 list 

  + 종료 조건 : direct_num이 2가되면 두번 꺾었으므로 3,4번 방향으로 1,2번 만큼 이동 후 출발 index와 같아지면 종료
    + 같아지면 종료
  ``` python
    if i_forward==I and j_forward==J:
      return len(disert_list)
 
  + 방문한 적 없는 디저트인 경우
    + visited 리스트에 현재 디저트 번호를 저장해줌

2. 핵심
+1,2번 방향으로 (우하,좌하) 진행한 후에 3,4번 방향은 1,2번과 같은 길이로 움직여야 하므로 direct_num을 나눠서 dfs를 작성하였다.

## Review
드디어 앙숙 디저트 먹었다. 상쾌하다. dfs 마스터 한 것 같다.
근데 answer을 global로 두지않고 인자로 넘겨주는 방법이 있는지 궁금하다.

        
