# 백준 11052번 : 카드구매하기

## Algorithm

Dilkstra

## Description

+ 주요코드

``` python
for i in range(1, N+1):
        for j in range(i, N+1):
            dp[j] = max(dp[j], dp[j-i]+arr[i])
```

## Review

