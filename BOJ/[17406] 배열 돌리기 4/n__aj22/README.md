# 백준 17406번 : 배열 돌리기 4

## Algorithm

구현, Bruteforce

## Description

1. `orderlist` : 연산 순서를 permutation으로 정한다.

+ 연산 순서가 정해졌다면 start 함수를 호출한 값들 중 최소 값을 찾는다.

2. `start(choose_order, temp)` : 연산 순서와 arr 배열을 복사한 temp를 파라미터로 가져가 순서대로 연산을 수행했을 때, 배열의 값을 return 한다.

+ r, c, s라면 s번 rotate를 호출한다.
+ 호출한 후, 배열의 합 중 최소 값을 return 한다.

3. `rotate(starty, startx, time, temp)` : 시작 위치(starty, startx)부터 한 변의 길이(time) 만큼 회전한다.(회전 순서 우 -> 하 -> 좌 -> 상)

## Review
쉽다