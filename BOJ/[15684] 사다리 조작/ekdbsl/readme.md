# [BOJ 15684] 사다리 조작 - Python

### :computer: Algorithm

> 시뮬레이션, 브루트포스, 백트래킹



### :computer: Logic

`LadderInfo`: 사다리가 이어져있는 부분의 정보를 저장함. 가로선 두개 사이에 하나의 선이 생기기 때문에 가로길이는 N-1, 세로길이는 H이다.

![image](https://user-images.githubusercontent.com/45536712/95961852-9a0a6380-0e40-11eb-8afd-1f17323b7ba1.png)

이러한 사다리가 있다면, `LadderInfo`에는

1000

0010

0100

0000

1001

0000

이렇게 저장된다.

`cnt`를 올려가며, 백트래킹으로 사다리를 만들어준다. cnt는 추가로 설치할 사다리의 개수이다.

`makeLadder(now, goal, prev_y, prev_x)`

`now`: 현재까지 만든 사다리 개수

`goal`: 최종적으로 설치할 사다리 개수

`prev_y`: 그 전까지 본 사다리의 y값, `prev_x`: 그 전까지 본 사다리의 x값

사다리를 설치할 수 있는 경우라면(사다리는 연속할 수 없으므로 양 옆에 사다리가 설치되어 있지 않아야함), 그 자리에 사다리를 설치하고 다음 단계를 재귀로 다시 진행한다. 백트래킹을 위해 다시 `LadderInfo`를 원래대로 돌려준다.



`checkPossibility()`: 게임의 결과가 원하는 대로 잘 나오는 지 확인하는 함수. 즉 i번 세로선의 결과가 i번이 나오는 지 확인함



### :computer: Review

> 걸린 시간: 1시간 50분

처음에 시간 초과 떠서 해결하느라 좀 시간이 오래 걸렸다..

처음 짠 코드에서 시간 초과를 해결하려니 고치는 과정에서 실수도 생기고.. 역시 한번만에 완벽히 풀어야 빨리 푸는데 ㅜㅜ

백트래킹할 때 그 전에 봤던 y좌표와 x좌표를 기억해서 그 부분부터 다시 봐야 시간초과를 해결할 수 있다.

너무 당연한 문젠데 이걸 왜 ..

1시간 반안에 풀어야되는데 ~~~ 멘탈 개복치가 되어간다 흑

