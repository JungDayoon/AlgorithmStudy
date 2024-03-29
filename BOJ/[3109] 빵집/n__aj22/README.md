# 백준 3109번 : 빵집

## Algorithm

DFS

## Description

1. 모든 y(0<=y<R) 부터 DFS를 시작한다.
    
    단, 0부터 증가하면서 시작하며 `dfs 함수` 호출 시 시작점 (y, 0)과 방문 여부를 확인하는 visited, cache(이후 설명)를 파라미터로 가져간다.

2. DFS 진행시 파이프라인 설치 순서를 1. 오른쪽 위 대각선, 2. 오른쪽, 3. 오른쪽 아래 대각선 차례로 확인한다.(파이프라인 설치시 최대한 윗쪽으로 설치하도록 해야지, 다음 파이프 라인을 설치할 때 최대한 많은 공간의 여유를 줄 수 있다.)

+ 다음 위치를 방문할 수 있으면, visited를 True로 변경한 후 다시 dfs 함수를 호출한다.
+ 현재 위치에서 다음 세 방향 중 갈 수 있는 곳이 아무 곳도 없다면 False를 return 한다.
+ 만약 x가 C-1인 위치까지 도달했다면, 파이프라인을 설치했다는 의미이므로 True를 return 한다.
+ dfs 호출 결과가 True인 경우에는 visited를 False로 바꾸지 않고 True를 return 한다.(파이프 라인을 설치 했음을 의미)
+ dfs 호출 결과가 False인 경우에는 visited를 False로 다시 바꾼 후 다음 방향을 확인한다.

**시간초과 해결**
+ 첫 시도때는 visited 배열만 사용했는데, 이렇게 하니까 시간초과가 났다.
+ 따라서 별도의 cache 리스트를 사용했다.
    + cache 리스트는 모두 True로 초기화 되어 있다.
    + `cache[y][x]`가 False이면, (y, x)를 방문한 적 있으며 (y, x)에서 뻣어나갈 수 있는 방향이 없다는 의미이다. 즉, 이 위치에 도달하면 이후에 파이프라인이 설치 불가능 함을 미리 확인해 저장해뒀다.(dp의 메모이제이션과 동일)
    + 따라서 다음 위치 확인시, `cache[nexty][nextx]`가 False인지 여부를 미리 확인하고 False이면 해당 위치로 가지 않는다. 
## Review

금방 풀었다.