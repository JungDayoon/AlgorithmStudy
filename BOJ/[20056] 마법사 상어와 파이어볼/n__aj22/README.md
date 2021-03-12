# 백준 20056번 : 마법사 상어와 파이어볼

## Algorithm

Simulation, 구현

## Description

1. 처음 파이어볼의 정보는 fireball_info에 들어가있다.

    + {r, c, m, s, d}를 한 번에 저장하는 구조체 사용

2. K 시간 동안 다음 과정을 반복한다.

+ fireball_info 에 있는 파이어볼을 각 볼의 방향, 속도에 따라 이동시켜준다.

+ 이동시켜준 결과를 vector 타입의 2차원 배열에 저장한다. 
    
    + 이동된 위치가 (r, c) 라면 
        ```cpp
        arr[r][c].push_back(fireball)
        ```

+ 다시 arr를 처음부터 끝까지 확인한다.(fireball_info는 clear된 상태)

    + 해당 위치의 파이어 볼이 1개라면 fireball_info에 담는다.

    + 해당 위치의 파이어 볼이 2개 이상이라면 문제에서 주어진 규칙에 따라 생성된 4개의 볼이 fireball_info에 담긴다.
    
## Review

어이없는 곳에서 실수해서 10번정도 실패했다.

**<C, C++에서 a mod m 을 구하는 방법 >**

[a가 양수이면]

+ a % m을 하면 된다.

[a가 음수이면]

+ a % m + m 을 하면 된다.