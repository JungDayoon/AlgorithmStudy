# 백준 17140번 : 이차원 배열과 연산

## Algorithm

Simulation

## Description

1. def check_list_num(nowarr) : 한개의 열 혹은 행을 가져와서 연산을 적용한 리스트를 반환해주는 함수
  + dictionary 사용
  
2. main

  A. 종료조건
   + r, c 가 R, C보다 작거나 같고, 해당 위치가 k 이면 이 때의 시간을 출력하고 종료
    
   + 시간이 100 초이면 -1 출력하고 종료
    
  B. R>=C 일 경우 : R 연산
   + 한 개의 열 씩 check_list_num 호출해서 연산을 적용한 열로 바꾼다.
    
   + 이 때 한 줄씩 확인하면서 최대 행길이를 찾아서 max_C에 저장 한다.
     + 연산이 모두 끝나면, 다시 새로 한 개의 열 씩 확인하면서 max_C 보다 작은 리스트는 0 을 append 해서 동일한 길이를 같도록 만들어준다.
      
  C. R<C 일 경우 : C 연산
  
   + 한 개의 행 씩 check_list_num 을 호출해서 연산을 적용한 열로 바꾼다.
    
   + 이 때 한 줄씩 확인하면서 최대 열길이를 찾아서 max_R에 저장 한다.
     + 연산이 모두 끝나면, 기존의 arr 와 비교해 max_R 이 커졌으면 커진 만큼 row를 추가, 줄어들었으면 줄어든 만큼 row를 pop 한다.
    
   + 새로운 리스트를 기존 리스트에 복사해준다.
   
 ## Review
 
 딕셔너리 선언 
 
 ``` python 
 
 new_dic = {} 
 
 ```
 
 딕셔너리 값 추가
 ``` python
 
 new_dic[키]=value
 
 ```
