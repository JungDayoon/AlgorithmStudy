# 백준 5582번 : 공통 부분 문자열

## Algorithm

DP

## Description
**점화식**
    
    str1[i] == str2[j] 일 때,
        arr[i][j]  = arr[i-1][j-1] + 1
    
## Review

