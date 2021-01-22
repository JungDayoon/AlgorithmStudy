# Programmers 49994번 : 방문 길이

## Algorithm

Simulation


## Description

#### 기본로직

1. 각 알파벳에 맞게 이동시켜 준다.

2. 원래의 위치(startx, starty)와, 이동된 위치(nextx, nexty)가 list_of_road 에 존재하는지 확인한다.

3. 확인 후 존재하면 (x1, y2, x2, y2)에 해당하는 키의 값을 하나 올려주고, 그렇지 않으면 추가해준다.

4. 처음가는 길의 개수는 list_of_road 에 있는 값의 개수이다.


#### 함수설명

1. `direction(character)` : character 에 해당하는 방향을 숫자로 return 한다.

2. `find(sx, sy, nx, ny)` : dictionary 에 들어갈 키 값을 일관성 있게 하기 위해서, 왼쪽, 위에 있는 (x, y)좌표~오른쪽, 아래에 있는(x, y)좌표 순으로 출력해준다.

3. `visit(arr)` : 입력받은 알파벳을 리스트로 저장한 arr 를 받아와서, 각 순서대로 함수를 수행한다.  


## Review

간단한 시뮬레이션 문제!

