# [programmers]/[43105] 정수 삼각형

## 분류
DP

## 접근법
- 아래로 내려가면서 각각의 max값을 저장
ex) i=2,j=1
triangle[i][j] = max(triangle[i][j]+triangle[i-1][j-1],triangle[i][j]+triangle[i-1][j])
- 그리고 triangle의 마지막 행의 max값을 return

## 후기
쉽