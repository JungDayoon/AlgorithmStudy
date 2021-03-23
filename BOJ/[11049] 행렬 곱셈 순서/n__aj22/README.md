# 백준 11049 행렬 곱셈 순서

## Algorithm 

dp

## Description

1. 기저사례 : 
+ `start == end` : 시작과 끝이 같을 경우 0을 return 한다.
+ `start+1 == end` : 시작+1 이 끝인 경우 행렬 2개의 곱을 의미하므로, 두 개 곱의 연산 값을 return 한다.

2. cache[start][end]에 find_min(start ,k)+find_min(k+1, end)+행열 연산값의 min 값을 저장한다.

## Review

비슷하게 구현했는데 마지막 마무리가 어려워서 살짝 참고했다 ㅠㅠ