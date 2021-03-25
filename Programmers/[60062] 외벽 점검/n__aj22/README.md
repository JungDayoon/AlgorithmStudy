# Programmers 60062번 : 외벽 점검

## Algorithm

Simulation, Permutation

## Description

### 로직

    각자 집으로 가는 방법, 함께 어떤 위치까지 같이 간 후 어떤 위치에서 각자 집으로 가는 방법 중 최소 값을 찾는다.

### 함수

1. dist를 내림차순으로 정렬한 뒤 1명부터 [dist의 길이]명 까지 선택한다.

+ 누가 선택된지는 중요하지 않기 때문에 선택할 인원수가 정해졌다면 가장 긴 거리를 이동할 수 있는 것을 먼저 선택하면 된다.

+ dist가 정해졌다면 어떤 거리를 먼저 사용할 것인지 결정해주는 permutation을 수행한다.

+ 모든 permutation에 대해 점검을 할 수 있는지 여부를 확인하는 go_fix 함수를 호출한다.

2. **`go_fix(choose_friend, weak, n)`** : 현재 선택된 dist(choose_friend)로 모든 weak를 확인할 수 있는지 여부를 boolean 으로 return 하는 함수

+ weak의 모든 점을 시작 index로 확인한다.

    + visited를 weak의 길이만큼 만들어준다.

    + 시작 인덱스가 정해졌다면, 해당 인덱스부터 choose_friend 를 돌면서 방문할 수 있는 지점을 True로 바꿔준다.

    + choose_friend의 전체를 돌고난 후 visited에 False가 없다면 모든 지점을 방문했다는 의미이므로 True를 return 한다.

    + 그렇지 않은 경우는 나머지 경우를 확인한다.

## Review

