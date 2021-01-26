# 백준 2643번 : 색종이 올려 놓기

## Algorithm
DP

## Description
1. `def is_possible(paper1, paper2)` : paper1 위에 paper2 를 올리는 것이 가능한지 Boolean 을 return 한다.

2. `def find(i)` : i번째 종이에 올라갈 수 있는 종이의 최대 개수를 return 해준다.

3. `main` : 색종이를 입력받을 때, 색종이의 모양을 일치시키기 위해 변을 오름치순으로 sort 해서 color_paper 에 append 한다. 이후에 색종이의 모양을 고려하지 않아도 된다. 
    + n 크기의 cnt에는 i 번째 종이를 제일 먼저 쌓기 시작했을 때 쌓을 수 있는 최대 종이 개수를 저장하고 있다. 각 종이 마다 find 를 호출한다.

    + cnt에 저장된 값 중 가장 큰 값이 정답이다.

## Review

다시 풀어볼 문제!
