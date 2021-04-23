# 백준 19236번 : 청소년 상어

## Algorithm

구현, 시뮬레이션, 백트래킹

## Description

**`backtracking(arr, info, total_eat, shark)`** :
1. 상어를 이동시킨다(move_fish 호출)
2. 현재 상어 위치에서 먹을 수 있는 물고기를 확인한다(shark_possible 호출)
    
    2-1. 먹을 수 있는 물고기가 없으면 현재까지 먹은 양과 기존에 저장된 answer 의 값과 비교해 max 값 저장
    
    2-2. 먹을 수 있는 물고기가 있으면 먹은 후(상어 이동, 물고기 삭제) 다시 backtracking 호출, 호출 끝난 후에는 다시 원래 상태로 돌려준다.


## Review
Time ⏱ : 1시간 40분
