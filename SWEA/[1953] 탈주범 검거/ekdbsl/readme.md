# [SWEA 1953] 탈주범 검거 - Python

### :computer: Algorithm

> BFS



### :computer: Logic

`Tunnel`: 각 터널 구조물에서 움직일 수 있는 방향 정보를 가지는 List

`inOut`: 어떤 구조물로 이동할 때 그 다음 구조물로 연결되어야 하는 방향 정보를 가지는 List

`pos`: 0: 위 1: 아래 2: 왼쪽 3: 오른쪽

`queue`: 이동할 좌표를 가지는 큐



`bfs(hole_y, hole_x)`: 

- 맨홀뚜껑 위치를 `queue`에 넣고, `visited` 갱신 -> 초기설정

- `queue`가 빌 때까지 다음을 수행

  - `time`이 `L`과 같아지면 함수를 빠져나옴 - 종료조건

  - 현재 `queue`의 len만큼 움직일 수 있는 경우를 확인한다 -> `time`을 세기 위해서

  - 움직일 수 있는 경우에는 `queue`에 그 좌표를 추가하고 `visited` 갱신하고 `count` 올린다.

    ```python
    if isIn(ny, nx) and not visited[ny][nx] and inOut[tDir] in Tunnel[Map[ny][nx]]:
    ```

    좌표를 벗어나지 않으면서, 들리지 않은 경우이면서 터널 구조물이 이어지는 경우

    

### :computer: Review

> 걸린 시간: 35분

터널 이어지는 부분 구현하는 것만 잘 생각해주면 나머지는 그냥 기본 BFS 문제랑 별 다른거는 없는 거 같당

어른상어 풀다가 터진 멘탈 회복하고 갑니다...