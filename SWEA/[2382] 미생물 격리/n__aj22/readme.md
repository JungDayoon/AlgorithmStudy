# SWEA 2382번 : 미생물 격리

## Algorithm

Simulation

## Description

1. table : 미생물 정보를 담고 있는 리스트
  + [세로위치, 가로위치, 미생물 수, 이동방향] 를 입력받는 그대로 table 리스트에 저장해준다.
  
  
  
2. M 번 반복하며 table 을 돌면서 미생물을 이동시켜준다.



3. word_cnt : table을 한번 돌고 나서 위치가 겹치는 미생물이 있는지 확인하기 위해 사용되는 딕셔너리
  + key는 (세로위치 가로위치) 이고 value는 해당 key 값을 count 해준다.
      ``` python
          key = str(j[0])+" "+ str(j[1]) #j[0] : 세로위치 j[1] : 가로위치
          if key not in word_cnt.keys(): #word_cnt 안에 해당 키값으로 된 것이 없다면 새로운 요소를 추가해준다.
                word_cnt[key] = 1 
          else: # 있다면 그 값의 카운트를 증가시켜준다.
                word_cnt[key] += 1
      ```
  + 미생물의 한번 이동이 끝나면 word_cnt를 돌면서 1 보다 큰 값을 갖는 key와 value를 찾아 따로 저장해준다.
  
  
  
4. check_list : 미생물의 위치가 겹치는 곳 들의 [세로위치, 가로위치] 위치를 저장해둔 리스트
  + 리스트를 모두 돌면서, table 안에 해당 위치와 겹치는 미생물들이 있다면, 그 미생물들 숫자를 모두 더해주고, 같은 위치 중 가장 큰 미생물들의 숫자와 방향을 저장해 가며 가장 큰 미생물의 방향을 찾아준다.
    + 또한 숫자를 더해주고 나면 해당 리스트는 더이상 필요 없기 때문에 제거해준다.
  + 한 위치가 끝나면 [세로위치, 가로위치, 미생물의 전체 갯수 합, 가장 큰 미생물의 방향] 을 다시 append 해준다.
  
  + 예) 첫번째 예시의 첫번째 이동 후에 check_list = [[3,3],[2,5]] 
      ``` python
          for j in check_list:
            max_num = 0
            max_dir=0
            total = 0
            for k in table[:]:
                if(j[0]== k[0] and j[1] == k[1]):# 찾으려는 위치인 경우
                    if(max_num<k[2]): #이전 까지의 최댓값 보다 크다면
                        max_num = k[2] #새로운 최대값으로 변경
                        max_dir = k[3] #최대값의 방향을 저장
                    total+=k[2]
                    table.remove(k) #더이상 필요없기 때문에 제거해줌
            table.append([j[0], j[1], total, max_dir])
      ```
 

## Review

위치가 겹치는 미생물을 처리하려다 보니 반복문이 많아졌다.

리스트를 for 문 도는 중에 요소를 제거 해준다면, for i in 리스트 이름[:]: 를 사용해주면 된다.
  
  
