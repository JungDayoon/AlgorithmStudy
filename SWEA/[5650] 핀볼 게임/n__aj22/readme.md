# SWEA 5650번 : 핀볼 게임

## Algorithm

Simulation

## Description
### 각 함수 설명
+ `def wormhole(arr)` : 전체 arr 를 대상으로 웜홀의 위치를 dict로 저장해서 return 해준다.

+ `def find_other_wormhole(wormhole_info, num, x, y)` : 웜홀의 정보를 가진 wormhole_info 를 바탕으로, 현재 위치 (x, y)에 해당하는 번호의 다른 웜홀을 찾아서 return 한다.

+ `def change_dir(direction, block_num)` : 현재 방향 direction과, block에 적힌 숫자를 바탕으로 방향 전환을 해준다.

+ `def choose(arr, wormhole_info)` : 전체 arr 에서 핀볼을 놓을 수 있는 곳을 정하는 함수이다. 위치를 정하면 위, 아래, 오른쪽, 왼쪽 4방향으로 start, 혹은 start2 를 호출한다.

+ `def start(arr, nowx, nowy, startx, starty, count, direction, wormhole_info, startflag)` :

+ `def start2(arr, nowx, nowy, startx, starty, count, direction, wormhole_info, startflag)` : 위 함수와 로직 동일, 반복문으로 구현



## Review

재귀로 풀었는데 테키 49/50 맞고, 

똑같은 로직을 반복문으로 풀었을 때 pass 했다.

무조건 재귀가 답은 아니다.! 크기가 크면 생각해서 풀자


