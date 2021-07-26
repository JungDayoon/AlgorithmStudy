# 백준 18428번 : 감시 피하기

## Algorithm

Bruteforce

## Description
1. 장애물을 둘 수 있는 공간을 따로 저장하고 combination을 이용해 3가지를 고르는 방법을 comb에 저장
2. 가능한 combination 마다 장애물을 두고 감시 가능 여부를 확인하는 `check_possibility(arr, teachers, N)` 호출
3. `check_possibility(arr, teachers, N)` : 현재 arr 상태에서 선생님의 위치에서 마주치는 학생이 있는지 확인, Boolean 값 return 

## Review
