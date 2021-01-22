# 백준 2225번 : 합분해

## Algorithm
DP

## Description

규칙만 잘 찾으면 반복문으로 간단하게 해결가능하다.

```
dp[i][j] = dp[i][j-1]+dp[i-1][j]
```


## Review
