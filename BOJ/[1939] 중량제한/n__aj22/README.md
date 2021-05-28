# 백준 1939번 : 중량제한

## Algorithm

이분 탐색

## Description

1. 이분탐색을 하는 기준은 물품의 중량이다.
+ min_w = 0
+ max_w = 다리의 중량제한 중 가장 큰 값
2. mid_w가 정해졌을 때, start에서 end까지 갈 수 있는지 여부를 `move_possibility(w, index, end_point, visited, bridge)` 함수 호출을 통해 확인한다.
+ 시작 위치부터 모든 길로 가면서 mid_w 무게인 물품이 end 까지 갈 수 있는 길이 있는지 확인한다.
+ 가능햐면 min_w = mid_w + 1
+ 가능하지 않으면 max_w = mid_w - 1
3. while 문이 끝난 후 max_w 출력
## Review

기본 다익스트라 문제!
금방 풀었다~~
