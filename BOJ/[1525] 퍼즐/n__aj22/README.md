# 백준 1525번 : 퍼즐

## Algorithm

BFS

## Description
### 함수설명

1. 퍼즐을 입력받을 때 arr 형태를 string 으로 입력받는다.

    예를들어 예제1과 같은 경우엔 "103425786" 으로 저장한다.

2. BFS를 하는데, 이 때 queue에 저장되는 값은 string과 해당 단계에 이르기 까지의 이동횟수이다.

3.  visited 방문 표시는 dictionary를 사용했다. key는 string, value는 이동횟수이다.

4. 현재 pop한 string이 목표로 하는 target_string("123456780")인 경우 : 이동 횟수를 return 하며 종료

5. target_string이 아닌경우 : string을 list로 변환해 0의 인덱스와 3*3 위치에서의 0의 (i, j)를 찾는다. 이 값을 이용해 위, 아래, 오른쪽, 왼쪽으로 각각 이동시킨 결과가 visited의 key에 존재하지 않는다면 queue에 담아준다.

6. 위의 단계 후에 target_string 을 찾지 못하면 -1을 return 한다.

## Review

아이디어를 떠올리기 어려웠다.