# 백준 14889번 : 스타트와 링크

## Algorithm

BruteForce, Backtracking

## Description

+ div_team_member(nownum, member_list, prev) : 백트래킹을 하는 주 함수

#### 파라미터
  + nownum : 현재 선택된 사람 숫자
  
  + member_list : 현재 선택된 사람 번호를 담고있는 리스트
  
  + prev : 이전에 선택된 번호 다음 번 번호부터 탐색하기 위해서 이전에 선택한 멤버 번호 + 1 을 담고 있음
  
## 설명

  + 종료조건 : 현재 선택된 사람 숫자가 총 인원의 반이 될 때
    1. 현재 선택된 member_list 의 합
      + member_list 를 2명씩 combination 을 한다.
      + combination 한 결과 리스트를 돌면서 arr 에 저장된 스코어를 더해준다.
    
    2. member_list 에 없는 사람들의 합
      + member_list 에 없는 사람들을 new_member_list 리스트로 만들어 준다.
      + new_member_list 를 2명씩 combination 한다.
      + combination 한 결과 리스트를 돌면서 스코어를 더해준다.
      
    3. 위의 두 개의 합의 차와 현재 최솟값과 비교를 해 더 작은 수를 저장한다.
    
  + (prev, N) 까지 돌면서 현재까지 선택된 선수가 아니라면 새로운 선수를 선택한다.
    + member_list 에 append 해주고 div_team_member 호출
    + 호출 후에는 반드시 member_list 에서 빼줘야 한다.
    
 
 ## Review
 
 처음에 한참을 잘못 생각해서 엄청 복잡하게 풀었는데,, 절때 복잡할 필요가 없는 문제였다.! 
 
 문제 제대로 읽자
 
