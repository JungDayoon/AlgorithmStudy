# 백준 17143번 : 낚시왕

## Algorithm 

Simulation

## 코드설명

+ shark : 상어의 정보를 담고있는 이차원 리스트
    + shark[r][c] = [ s, d, z ] , 상어가 없으면 0
    
1. find_shark(index) : 낚시왕이 위치한 열 index를 파라미터로 받아와서 해당 열의 상어를 찾아준다.
  + 상어가 존재하면 해당 상어의 행 index 를 return
  
  + 상어가 존재하지 않으면 0 을 return
  
2. change_loc(r,c,s,d,z): (다윤 코드 참고)
  + s 가 0 보다 큰 경우 인덱스를 줄여가며 방향을 바꿔준다.
  
  + 바뀐 위치와 방향을 포함해서 r,c,s,d,z 를 return 해준다.
  
3. main

  + 낚시왕이 인덱스 1 부터 R 에 위치할 때 까지 반복해준다.
  
    + 낚시왕이 위치한 열에 상어가 존재한다면 catch_size에 그 상어 크기 만큼 더해주고, 해당 상어 위치는 0
  
    + 상어를 모두 찾아 shark_list 에 저장해 주고 해당 상어 위치는 0
  
      + shark_list 를 하나씩 확인하며 change_loc 을 호출해 상어 위치를 바꿔준다.
        + 바뀐 상어 위치에 새로운 값을 저장해준다. 이때 위치에 이미 상어가 존재하고 있으면 그 상어 크기와 비교해 더 큰 상어의 정보를 저장해준다.
        

## Review

상어 위치를 이동시키는데 시간이 너무 오래걸렸다 ㅠㅠ 

다윤이가 말해준게 도움이 됐다!! 땡스투 다윤

  
