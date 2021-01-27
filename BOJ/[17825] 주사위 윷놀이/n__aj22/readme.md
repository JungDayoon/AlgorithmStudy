# 백준 17825번 : 주사위 윷놀이

## Algorithm

Backtracking

## Description
1. `def set_board()` : 윷놀이 판을 만드는 함수, 제일 처음에 한번 호출해준다.
    + 만들어진 윷놀이 판은 board 에 2차원 배열로 저장한다.
    + 이 후에 board_type 변수를 사용하는데, board_type = n 이면 board[n] 판을 사용한다.
    ![image](https://user-images.githubusercontent.com/33089715/105950588-fb81d580-60b1-11eb-87ee-0bbc12768ac8.png)


2. `def horse_next_move(board_type, now_point, move)` : 말을 이동시키는 함수, 이동시킨 후 나온 board_type과 point(점수)를 return 한다.

    + 현재 board_type에 해당하는 윷놀이 판에서 이동시켜준다.(now_board)
    + 현재 위치의 point 를 이용해서 윷놀이 판에서 index 를 찾는다.(index_of_now_point)
    + 찾아낸 index 에서 움직여하는 크기 만큼 말을 이동시켜준다. (index_of_next_point)

    + 인덱스가 현재 board 의 크기를 벗어나거나, 현재 위치가 -1 이면 도착했단 말이므로 -1, 0을 return
        ```python
        if(index_of_next_point >= len(now_board)):
            return -1, 0 #끝에 도달 
        if(now_board[index_of_next_point]==-1):
            return -1, 0
        ```
    + 현재 board_type 이 0 인데, 10, 20, 30에 해당하는 위치에 도달했다면 다른 board 로 이동시켜줘야 함으로 board_type 을 변경시켜준다.

        ```python
        if(board_type == 0 and point%10 == 0):
            board_type = point//10
        ```
    + 현재 점수가 40점이면 board_type 을 변경시켜줘야 하므로 board_type = 4로 바꾼다.
        ```python
        elif(point == 40):
            board_type = 4
        ```
    + 현재 board_type 이 1, 2, 3이면서 point 가 25점이거나 30이상이면 board_type = 5로 바꾼다.
        ```python
        elif((board_type == 1 or board_type == 2 or board_type == 3) and (point == 25 or point>=30)):
            board_type = 5
        ```
3. `def backtracking(now, score, position)` : backtracking으로 주사위 10번에 해당하는 말을 선택한다. 10번 째 말을 선택했을 때 점수 중 최댓값을 찾는다.

    + 도착위치에 도달한 말은 선택하지 않는다.
    + 이동하려 하는 위치에 이미 말이 존재한다면 선택하지 않는다.
    
## Review

예전에 삼성 코테 대비할 때 코테 전날 풀다가 못풀었던 문젠데

다시 새로운 방식으로 푸니까 나름 빨리 풀렸다..

다만 예외 상황 고려해주는거에서 실수를 많이해서 그 부분들 수정하는데 오래걸렸다 ㅠㅠ 

예외 상황으로는 25-30-35-40 에 해당하는 위치이다. 윷놀이 판을 어떻게 만드냐에 따라 저 위치가 다르게 인식될 수 있는데 다르게 인식된다면 말을 이동시켰을 때 다른 말이 이 위치에 있는지 여부를 확인할 수 없기 때문에 정답과 다르게 나온다. 이 점만 잘 고려해주면 된다..!
