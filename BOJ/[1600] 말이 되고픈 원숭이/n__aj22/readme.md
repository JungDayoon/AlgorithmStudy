# 백준 1600번 : 말이 되고픈 원숭이

## Algorithm

BFS

## Description

1. 전체 로직은 BFS 풀이법과 동일하다. 큐에는 [현재위치y, 현재위치x, 지금까지 남은 K, 현재까지 동작수]를 담는다.

2. 모든 위치는 동일한 K 횟수가 남은 상태에서는 한 번만 방문할 수 있다. 따라서 visited 변수를 사용한다. 각 위치마다 방문여부를 확인할 수 있는 Boolean 을 가지고 있다. 다만 K 는 0이상 30 이하의 수를 가질 수 있으므로 31개 만큼의 크기를 갖고 있다.

    ```python
    visited = [[[True]*31 for i in range(W)] for j in range(H)]
    ```

3. 만약 현재 위치가 도착 위치이면, 현재까지의 동작수와 현재의 최소값 min_visit을 비교해서 최소값을 저장한다.

4. 현재 위치에서 위, 오른쪽, 아래, 왼쪽의 위치로 갈 수 있는 지 확인하고 갈 수 있다면 queue 에 추가해준다.

    + 방문여부를 표시해준다.
        ``` python
        visited[nexty][nextx][nowN] = False
        ```
5. 현재 남은 K 인 nowN 이 0보다 크다면 말이 갈 수 있는 이동방향으로 갈 수 있는지 확인하고 갈 수 있다면 queue 에 추가해준다.

    + 방문여부를 표시해준다.
        ``` python
        visited[nexty][nextx][nowN-1] = False #nowN 을 하나 줄여줘야하기 때문에
        ```    

## Review

사소하게 고려해줘야할게 있어서 쪼금 시간이 걸렸다...
