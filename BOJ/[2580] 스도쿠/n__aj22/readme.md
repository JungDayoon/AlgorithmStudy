# 백준 2580번 : 스도쿠

## Algorithm

Backtracking

## Description

1. promising(line) :

+ line : 가로, 세로, 정사각형 값을 담은 3차원 리스트를 파라미터로 가져온다.

+ flag : 가로, 세로, 정사각형에 긴 값을 for 문으로 돌면서 체크해준다. 

  + 예를들어 가로에 4라는 숫자가 존재하면 flag[0][4] 값을 1로 만들어준다.
  + 예를들어 세로에 8라는 숫자가 존재하면 flag[1][8] 값을 1로 만들어준다.
  + 예를들어 정사각형에 3 숫자가 존재하면 flag[2][3] 값을 1로 만들어준다.
  
+ flag 를 확인하는 for문을 다 돌고 나서 3개의 리스트 모두가 0인 숫자가 존재한다면 이 숫자들을 number 리스트에 담아 return 해준다. (이 공간에 들어갈 수 있는 숫자라고 판단)

  + 만약 이런 조건을 만족하는 숫자가 없다면, []를 return 해준다.
  
2. make_sudoku(check_sudoku, zero_num):

+ check_sudoku : 현재 확인하려고 하는 스도쿠판

+ zero_num : 현재 스도쿠 판에 남은 0의 숫자

  + 종료조건 : 만약 zero_num 이 0이된다면 현재 스도쿠 판에 0이 없기 때문에 출력해주고 종료한다.
  
+ 스도쿠 판 전체를 돌면서 0을 만나면, line에 확인하려는 현재 위치의 값이 담기는 가로, 세로, 정사각형을 담아서 promising을 호출해준다.

  + 만약 promising 의 결과 값으로 받은 number 에 아무 숫자도 존재하지 않는다면 (len(number) == 0) 이 공간에 담을 수 있는 숫자가 없다는 의미이기 때문에 return
  
  + number 에 값이 존재 한다면 number 을 반복문을 돌며, 현재 위치에 number을 넣은 sudoku를 이용해 다시 make_sudoku를 호출한다.
  
   ``` python
      for k in number:
            check_sudoku[i][j]=k
            make_sudoku(check_sudoku, zero_num-1)
            check_sudoku[i][j]=0
   ```
    + 호출이 끝난 후에는 반드시 다시 0으로 만들어 줘야 한다.

    + number 반복문이 끝난 후에도 return 을 해줘야 한다.
    
    
## Review 

성공했지만 python3 으로는 시간초과가 나고, pypy3 로는 성공했다.

혹시 파이썬으로 초과가 된다면 pypy3를 사용해보시길...
