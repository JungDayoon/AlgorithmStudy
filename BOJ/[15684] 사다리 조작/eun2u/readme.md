## 분류💁

백트래킹, 브루트포스


## 접근법

사다리 가로길이가 N, 세로길이가 H 이면, 


`map[i][j]`는 가로선의 존재 유무를 0 또는 1로 저장함. 그래서 가로길이 N-1, 세로길이 H로 저장하게 된다.

->  사다리를 타고 내려가려면, map[i][j]==1 이면 오른쪽으로 내려가고 map[i][j-1]==1이면 왼쪽으로 내려간다.

## 시퀀스

1. 가로선 1개 놓을 수 있는지 확인. 안되면 2개. 안되면 3개. 안되면 -1 출력

2. 놓으려는 가로선이 연속되어서는 안된다. 





## 후기💡

- 시간 초과 이유: 끝났던 지점을 저장해주고, 그 지점부터 탐색을 해야함..

- 틀렸습니다 이유: 3개 초과하는 경우 말고도 불가능한 경우에도 -1을 출력해야하는데, 그 조건 처리 해주지 못했다

- 문제를 꼼꼼히 읽으라는 말은 아무리 강조해도 지나치지 않다.! 한번에 시간초과가 안나게 조심하장 
