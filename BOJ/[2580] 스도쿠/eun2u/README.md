# [2580] 스도쿠 - python

## 분류👩‍💻
백트래킹          
       
           


## 접근법

- 스도쿠의 종료조건 ; 빈칸의 개수만큼 다 채우면 출력하고 종료
- 가로 탐색, 세로 탐색, 3x3 탐색 중 중복되는 수가 있으면 바로 false return   
     
           
스도쿠 수를 놓을 수 없는 조건
1. 놓음으로써 중복된 값이 생기면 false
2. 1~9까지 모두 놓을 수 있으면 true



## 시퀀스 
1. 1부터 9까지 숫자를 탐색한다
2. 숫자를 그 자리에 놓을 수 있는지 검사한다.
3. 놓을 수 있으면 sudoku(picked+1)을 재귀 호출 한다.
3. 재귀 호출 후에는 그 자리의 값을 0으로 바꾼다.


## 코드

```python
def sudoku(picked):

    if(picked==n):
        printBoard()
    
    loc=space[picked]

    for num in range(1,10):
        if(board[loc[0]][loc[1]]== 0):
            if(canPut(loc[0],loc[1],num)==True):
                board[loc[0]][loc[1]]=num
                sudoku(picked+1)
                board[loc[0]][loc[1]]=0
```                
### 시간초과 해결하기

- 파이썬은 c/c++보다 메모리도 크고, 시간도 오래걸린다. 그래서 input().split() 대신에 아래 함수를 이용하였다.
```python
sys.stdin.readline().rsplit())
```
   
- 처음에는 중복된 값을 찾기 위해 boolean 배열을 사용하였다. 하지만 시간초과를 해결하기 위해 저장하지 않고, 중복된 값을 찾으면 바로 return 하였다.

## 후기🧝‍♀️
- 리스트를 탐색하며 숫자로 재귀를 들어갈지, 숫자를 탐색하며 리스트로 재귀를 들어갈지 엄청 고민했다..
- 역시나 백트래킹은 너무 부족하다.
- 파이썬.. 헤븐이네..

