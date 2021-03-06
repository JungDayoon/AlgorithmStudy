# [2206] 벽 부수고 이동하기
## 분류💁

BFS

</br>

## 접근법
> (1,1) 에서 (N,M)까지 가는데 최단거리 구하자. 벽은 한개까지 부술 수 있다.
- BFS 를 이용해서 최단거리를 구하는데, 3차원 배열을 이용해서 벽을 부수면서 갈 수 있는 최단거리와 하나도 부수지 않고 갈 수 있는 최단거리를 따로 구한다.

## 로직
- 방문되지 않은 
    * 이동하려는 곳의 `wcnt`(부순 벽 개수)가 0이면 
        * `dist[ny][nx][wcnt] = dist[here.y][here.x][wcnt]+1`
    * 이동하려는 곳의 `wcnt`가 1이면
        * `dist[ny][nx][wcnt+1]=dist[here.y][here.x][wcnt]+1`
- 최종 도착 지점에서
    * 둘 다 0 이상이면 방문되었다는 뜻이기 때문에 min값을 구하고,
    * 둘 중 0이 존재하면 갈 수 있는 곳을 구하고
    * 둘다 0인 경우는 갈 수 없으므로 -1이다.

</br>

## 후기💡
- `memset` 인자 순서 바꿔서 하고 있어서 틀렸다.. 이런 실수는 절대 하지말자..