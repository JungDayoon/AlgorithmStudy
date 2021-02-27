# 백준 16985 : Maaaaaaaaaze

## Algorithm

Bruteforce, bfs, 구현

## Description
**함수설명**

**`main`** : 

1. `board_list` : 판 5개의 순서를 저장하고 있는 리스트, 순열 사용(0, 1, 2, 3, 4를 나열)
    -> 5!

2. `option_list` : 판 5개를 각각 회전을 어떻게 할지 회전 수를 저장하는 리스트, 중복 순열 사용(3번 까지 회전 가능하기 때문에 0, 1, 2, 3을 중복 허용해서 5개 선택) -> 4^5

3. board_list를 돌면서 판 5개의 순서를 정하고, 5개가 정해질 때 마다 option_list를 돌면서 5개의 판의 회전 수를 정해준다.

+ 각 판에대해서 회전을 한 결과를 check_board에 append 해줌으로써 5개를 합친 maze 완성

    ```python
    #5개의 판에 대해서 각 판을 option_list에 해당하는 것 만큼 회전해주는 함수 rotate를 호출
    #호출한 결과를 check_board에 append 한다.
    for k in range(5):
        check_board.append(rotate(base_board[i[k]], j[k]))
    ```

4. 정해진 보드 check_board가 완성이 되면, 시작[0,0,0]과 끝[4,4,4]이 1이면 미로 탈출 횟수를 확인하는 `escape_maze(check_board)`를 호출한다.

5. 최적화 하기 위해 maze 탈출 횟수의 최솟값인 12가 되면 바로 종료하게 했다.

**`rotate(arr, N)`**

+ 2차원 배열 arr를 N번 회전한 결과를 return

**`escape_maze(arr)`**

+ arr 미로를 탈출한 결과 횟수를 return하는 함수, bfs 사용

+ 탈출 불가이면 -1 return
## Review

복잡해 보였지만 차근차근 하니까 한 번에 통과했다.



