# 백준 9465번 : 스티커

## Algorithm

DP

## Description
```
두 가지 방법을 사용했다.
두 방법 모두 memorization 을 사용했는데, 값을 dp 리스트에 저장한다. dp[status][n번째 열] 로, status 는 0(n번째 열에서 스티커를 아무 것도 떼지 않았을 때 최댓값), 1(n번째 열에서 위에 줄의 스티커를 떼어냈을 때 최댓값), 2(n번째 열에서 아랫 줄의 스티커를 떼어냈을 때 최댓값)가 있다.
```

1. Bottom-up

+ `main` 에서 구현
+ 반복문


2. Top-down

+ `def choose(c, status, N)` : 
+ 재귀로 구현
  
## Review

dp 오랜만에 보니까 너무 어렵다.. 
그리고 Top-down 으로 먼저 풀었는데 예전에 C++로 똑같은 로직으로는 통과했는데 파이썬을 사용하니까 런타임 에러뜬다..!! 그래서 다시 풀었다... 