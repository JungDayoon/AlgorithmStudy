# 백준 17822번 : 원판 돌리기

## Algorithm

시뮬레이션, 구현

## Description

1. **`main`** : 원판을 문제에서 요구하는 조건에 맞게 돌리고 원판에 적힌 수의 합을 구하는 함수

+ 원판은 입력 그대로 사용한다.

    예를들어 예시에서 주어진 다음 원판을 입력받으면 아래 표 처럼 입력받는다. 표에서 row의 인덱스는 0부터 시작하지만 N은 1부터 시작하기 때문에 구현시에 i+1로 사용한다.
    <img src="https://user-images.githubusercontent.com/33089715/111255942-6637a200-865b-11eb-8868-9b91cdc9a5fc.png" width="500">
    <img src="https://user-images.githubusercontent.com/33089715/111256225-f37af680-865b-11eb-9e55-15f45f00fffd.png" width="500">

+ T 만큼 (x, d, k)를 입력 받고 조건에 맞는 원판을 `rotate` 함수를 통해 돌려준다.

+ 돌린 후 `delete_same_num`을 호출해 인접한 같은 숫자를 제거한다.

2. **`rotate(arr, num, direction)`** : 원판을 direction 방향으로 num 만큼 돌린다.

+ 역 방향인 경우에는 정방향으로 num을 바꿔준다.
    + 역방향으로 num만큼 돌리는 것은 M-num 만큼 정방향 하는 것과 같다.

+ 이동된 arr를 저장할 temp를 선언하고, num 만큼 이동시켜준다.

+ temp를 return 한다.

3. **`delete_same_num(arr)`** : 전체 원판에서 인접한 같은 숫자를 지워주는 함수

+ 원판이므로 j=0과, j=M-1은 이어져 있는 상태로 생각한다.

+ 가로, 세로로 확인해서 인접한 index에 같은 숫자가 존재하면 visited에 true로 표시한다.

+ 가로, 세로 모두 확인한 후에 visited가 true인 칸을 -1로 바꿔준다.
    
4. **`make_balance(arr, num, total)`** : 지워진 숫자가 없다면 make_balance 함수를 호출해 전체 평균보다 작은 값은 +1, 큰 값은 -1 해준다.
## Review


