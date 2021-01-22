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

dp 는 점화식 생각하는데 많은 시간만 쏟으면 구현은 금방하는 것 같다.. 근데 점화식 생각하는게 너무 어렵당 

