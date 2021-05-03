# 백준 21611번 : 마법사 상어와 블리자드

## Algorithm

구현

## Description

### 로직

    M 번 다음 과정을 반복한다.
    1. break_marble(board, d, s) : d 방향으로 s 길이 만큼 얼음 파편을 던져 구슬 파괴
    2. rotate(board) : 구슬이 파괴되 생긴 빈 공간을 당겨준다.
    3. explosion(board) : 4개 이상 연속된 구슬을 폭발시킨다.
    4. change_marble(board) : (구슬 개수, 구슬 번호)로 구슬을 변경시킨다.

1. `break_marble(board, d, s)` : d 방향으로 s 길이 만큼 얼음 파편을 던져 구슬 파괴

+ N//2, N//2 를 시작점으로 d 방향으로 s 만큼의 구간을 0으로 바꿔준다.
+ 바뀐 arr 를 return 한다.

2. `rotate(board)` : 1번 과정 후 구슬 중간의 빈 공간을 없애준다.

    1. 0을 제외한 숫자를 newarr에 담기
    + (N//2, N//2)를 시작점으로 문제에서 요구하는 순서대로 돌면서 숫자를 확인한다.
    + 만약 숫자가 0이 아니라면 newarr에 append 한다.

        ```python
        if(arr[nexty][nextx]!=0):
            newarr.append(arr[nexty][nextx])
        ```
    + (0, 0)의 위치까지 확인한 후 newarr는 0이 제외된 나머지 숫자들의 연속이다.
    2. newarr에 담긴 숫자들을 다시 arr에 넣어주기
    + arr를 모든 숫자가 0인 N*N 크기의 배열로 초기화해준다.
    + 다시 (N//2, N//2)부터 시작하면서 순서대로 newarr의 숫자를 하나씩 담아준다.
    + newarr 의 길이만큼 담아줬다면 종료한다.
    3. 새롭게 바뀐 arr 를 return

3. `explosion(board)` : 
+ `delete_list` : (N//2, N//2)부터 (0, 0)까지 한번 스캔하면서 폭발할 구슬의 좌표가 담긴다. 새로 스캔을 시작할 경우 빈공간으로 초기화해야한다.
+ `new_arr`, `now_num` : 동일한 구슬의 개수를 파악하기 위해 사용, new_arr에는 now_num 번호의 연속된 구슬의 좌표가 담긴다. 따라서 새로운 번호를 만날 때 마다 새로운 번호와 좌표로 초기화 되어야 한다.(같은 번호를 만나면 계속 이어 붙이기)
+ delete_list의 길이가 0일 때 까지(폭발할 구슬이 없을 때 까지) 다음 과정을 반복한다.
    + (N//2, N//2)부터 시작하면서 숫자 확인
    + 현재 위치의 구슬의 숫자가 now_num과 동일한 숫자가 아닌 경우
        + new_arr에 담겨있는 좌표의 개수가 4이상인 경우 4개 이상의 연속한 구슬이 있다는 의미이므로 delete_list 에 new_arr를 담아준다.
        + 빈 공간으로 초기화한 new_arr에 현재 위치 좌표를 담아준다.
        + now_num을 현재 위치의 구슬 번호로 변경시킨다.
    + 현재 위치의 구슬의 숫자가 now_num 과 동일한 숫자인 경우
        + new_arr 에 현재 좌표를 담아준다.
   + 한 번 스캔이 끝나면 delete_list에 담긴 구슬의 위치를 0으로 만들어 준다.
        + delete_list는 2차원 배열인데 각 row는 같은 숫자의 좌표를 의미한다. 이 정보를 가지고 최종 점수를 answer 에 계산해 더해준다.
    + 0인 공간을 없애기 위해 rotate를 호출한다.
+ 바뀐 arr를 return
4. `change_marble(board)` : 3번 과정과 동일한 방법으로 (구슬 개수, 구슬 번호)의 형태로 바꿔준다.
+ 바뀐 arr를 return
## Review
생각보다 빨리 풀어냈다.
차근차근!