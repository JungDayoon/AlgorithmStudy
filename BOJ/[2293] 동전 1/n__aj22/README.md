# Programmers 2293번 : 동전 1

## Algorithm

DP

## Description

1. k+1 크기의 0으로 초기화된 dp배열을 만들어준다. 

+ dp[0] = 1

2. 다음 점화식을 이용한다.

    ```python
    dp[j]+=dp[j-c]
    #j는 목표 돈의 크기
    #c는 동전의 크기
    ```
## Review

어렵다 ㅠㅠ 실버맞나! 