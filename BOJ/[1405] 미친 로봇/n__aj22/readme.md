# 백준 1405 : 미친 로봇

## Algorithm

백트래킹, 브루트포스

## Description
**로직설명**
```
로직은 간단하다.

백트래킹을 이용해 로봇을 이동시켜 주는데, 이동될 길이 이미 로봇이 거쳐간 곳이면 가지 않는다. 

N 번 이동을 성공했으면 그 길은 단순한 길이기 때문에 이 때까지 오는데 걸린 확률을 더해준다.
```

**함수설명**
+ `visited` : 로봇이 이동한 곳의 방문 여부를 표시해준다.

+ `def backtracking(nowx, nowy, now_percentage, choose_num)` :

    로봇을 백트래킹을 이용해 이동시킨다.

    이동된 곳이 이미 방문했던 곳이라면 가지 않는다.

    방문할 수 있는 곳이면 backtracking 함수를 호출한다.

    + 이 때 방향으로 갈 확률을 현재까지의 확률에 곱해서 호출 해준다.

**주의**
+ 원래 처음에는 로봇의 경로를 리스트에 추가해주고, 리스트에 존재하는지 여부를 방문 여부로 사용했다.
    ```python
    robot.append([nexty, nextx])
    #backtracking 호출
    robot.pop(-1)
    ```
    이 방법도 결과는 같아 보였으나 시간 초과가 났다.
+ 다음 시도한 방법은 visited 배열을 두고 방문 표시를 해주었다.
    ```python
    visited[nexty][nextx] = True
    #backtracking 호출
    visited[nexty][nextx] = False
    ```
    시간초과가 나지 않았다!
## Review

간단한 백트래킹 문제
