# [SWEA 5644] 무선충전 - Python

### Algorithm

시뮬레이션

### Logic

1. drawMap()함수를 사용하여 charger_map 리스트에 BC의 정보를 저장한다. 

2. personA_loc, personB_loc은 각 시간 별 A, B 사용자의 위치 값이다.

3. A, B가 있는 위치에서 충전 가능한 BC 정보를 charger_map에서 얻는다.

4. findSum()에서 충전 양의 최댓값을 chargeSum에 더해준다.

   A, B 사용자가 충전가능한 BC가 여러 개일 수도 있기 때문에 각각을 for문으로 모두 찾아서 max값만 chargeSum에 저장해야함

### Review

- 충전 양의 최댓값을 구하는 findSum()을 더 간단히 구현할 수 있을 것 같은데 코딩하다보니 좀 길어진 것 같다 ㅠ ㅠ

