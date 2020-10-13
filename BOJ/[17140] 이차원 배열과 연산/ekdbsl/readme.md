# [BOJ 17140] 이차원 배열과 연산  - Python

### :computer: Algorithm

> 구현, 시뮬레이션



### :computer: Logic

`rOperation(standard, change)`: r연산을 하는 함수. `standard`는 행, 즉 바뀌지 않는 기준을 의미하고, `change`는 크기가 달라질 열을 의미한다.

`numberCnt`: [숫자, 등장 횟수]를 저장하고 있는 리스트. 배열 `A`에 들어갈 수 있는 수는 100이하의 수라고 명시되어있기 때문에 `numberCnt`의 크기도 그를 포함하는 101로 지정했다.

`Map`에서 원소들을 순회하면서, `numberCnt`에 그 숫자의 횟수를 늘려준다.

횟수가 적은 순, 횟수가 같다면 숫자가 작은 순으로 정렬한다.

`numberCnt`를 보면서 `Map`을 갱신한다.

최대의 열 길이를 `maxC`로 한다.

`cOperation(standard, change)`: c연산을 하는 함수. 로직은 r연산과 동일



### :computer: Review

> 걸린 시간: 50분

단순 구현이라서 쉬웠는데 풀다가 딴짓해서 생각보다 오래걸렸당