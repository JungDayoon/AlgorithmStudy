# Programmers 72413번 : 합승 택시 요금

## Algorithm

다익스트라

## Description

### 로직

    각자 집으로 가는 방법, 함께 어떤 위치까지 같이 간 후 어떤 위치에서 각자 집으로 가는 방법 중 최소 값을 찾는다.

### 함수

1. **`make_road(fares)`** : fares 정보를 이용해 map 타입의 road에 요금 정보를 저장하는 함수

+ fares 입력이 다음과 같다고 하자.
    ```python
    fares = [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]
    ```

+ road에는 다음과 같이 저장된다.
    ```python
    {
        1: {4: 10, 3: 41, 5: 24, 6: 25}, 
        2: {4: 66, 3: 22}, 
        3: {5: 24, 1: 41, 2: 22}, 
        4: {1: 10, 6: 50, 2: 66}, 
        5: {3: 24, 6: 2, 1: 24}, 
        6: {5: 2, 4: 50, 1: 25}
    }
    ```
2. **`dijkstra(start)`** : start 부터 모든 지점 까지의 최소 거리를 dijkstra를 이용해 구한다.

+ 모든 지점까지의 최소 거리는 distances에 저장되며, distances 값을 return 한다.

3. **`solution(n, s, a, b, fares)`** : 정답을 answer에 저장해 return 하는 함수

+ 처음에 answer에는 각자 택시를 타고 집에갈 경우 요금을 저장한다.

    + s(시작지점)에서 모든 위치까지의 최소 거리를 `dijkstra(s)`를 호출해 shortest_paths에 저장한다.
    + s에서 a 까지의 최소 거리인 shortest_paths[a]와, b까지의 최소 거리인 shortest_paths[b]를 더한다.

+ [s에서 k까지의 최소거리 + k에서 a까지 최소거리 + k에서 b까지 최소거리]를 k=1인 경우 부터 k=n 인 경우의 최소 값을 찾는다.
## Review

