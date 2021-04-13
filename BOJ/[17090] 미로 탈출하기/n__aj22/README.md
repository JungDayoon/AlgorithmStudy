# 백준 17090번 : 미로 탈출하기

## Algorithm

DP

## Description

1. 모든 위치에서 시작한다.

2. DP를 이용하기 때문에 한 번 거쳐간 곳은 가능 여부를 cache에 메모이제이션 한다.

+ cache[y][x] == 1 : 탈출 가능
+ cache[y][x] == 0 : 탈출 불가능
+ cache[y][x] == -1 : 아직 방문 전


## Review

쉬웠지만 처음에 파이썬으로 했다가 시간초과가 나고, recursion error가 나는 바람에 여러번 틀렸다.

cpp로 바꾸자마자 통과했다..!!