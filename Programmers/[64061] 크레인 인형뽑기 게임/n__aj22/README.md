# Programmers 64061번 : 크레인 인형뽑기 게임

## Algorithm

Simulation

## Description

**`choose_friends(board, x, n)`** : board에 x열에 해당하는 인형 중 가장 위에 있는 인형 번호를 return 하는 함수

+ 가장 위에 있는 인형을 찾았다면 해당 위치 board 값을 0으로 만들고 번호를 return 한다.
+ 찾지 못했으면 -1 return

**`solution(board, moves)`** : 

1. choose_friends 함수를 호출해 뽑을 인형 번호를 찾는다.

2. 만약 basket에 담긴 인형이 없다면 뽑은 인형을 바로 담는다.

3. 그렇지 않고 마지막 인형이 뽑은 인형 번호와 같다면 answer에 2를 더하고 basket에 담긴 마지막 인형을 없앤다.

4. 그렇지 않고 마지막 인형이 뽑은 인형 번호와 같지 않다면 basket에 인형을 담는다.

## Review

