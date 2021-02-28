# 백준 13459번 : 구슬 탈출

## Algorithm

DFS, Simulation

## Description
**함수설명**

`changeposition(ry, rx, by, bx, direction, arr)` : 

+ 빨간 구슬의 위치(ry, rx)와 파란 구슬의 위치(by, bx)를 direction으로 이동시킨 결과를 return 해주는 함수

    + return 값은 이동시키고 난 후의 ry, rx, by, bx, arr이다.
+ 이 때 주의해야 할 점은, 이동시키려는 방향에 먼저 있는 구슬을 먼저 이동시켜줘야 한다. 예를 들어 왼쪽으로 이동시키는 경우에는 빨간 구슬과 파란 구슬 중 더 왼쪽에 있는 구슬을 먼저 왼쪽으로 이동 시킨 후 다음 구슬을 왼쪽으로 이동시킨다.

`move(ry, rx, by, bx, arr, count)` : 

+ 이동 방향을 dfs로 정해주는 함수
+ 만약 11번 이상 호출되었거나, 파란 공이 구멍에 들어갔을 경우 return
+ 이동 후 빨간 공이 구멍으로 들어갔을 경우 성공했기 때문에 ispossible을 True로 바꾸고 return 

## Review

3시간 풀었다..!!

문제 자체는 그렇게 어렵지 않은데 구현할 때 실수하기 쉬운 문제라 좀 귀찮았던 문제