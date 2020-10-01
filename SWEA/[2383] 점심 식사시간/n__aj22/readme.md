# SWEA 2383번 : 점심 식사시간

## Algorithm

Simulation

## Description

1. check_time(person1, person2) : 시간을 계산해서 리턴해준다.
  + person1 , person2 : 각각 1번, 2번 계단으로 갈 사람 리스트
  
  + first_stair_length, second_stair_length : 계단 길이를 음수로 만든 것, 예) 계단 길이가 3이면 -3, 사람이 계단에서 빠져나가는 조건을 만족시키기 위해 음수로 만들어줌
  
  + num1 = 3, num2 = 3 : 각 계단에 가능한 사람 수 , 만약 3명이 계단에 있다면 0이 됨
  
  + 처음에 person1,2 리스트를 확인해주면서 계단 위치와의 거리를 리스트에 추가해준다.
    + 계단에서 1초 대기했다가 내려가야 하므로 대기 여부를 확인해주는 False 또한 추가해준다.
    
      ``` python
        for i in range(len(person1)):
            length = abs(person1[i][0]-stair[0][0])+abs(person1[i][1]-stair[0][1])
            person1[i].append(length)
            person1[i].append(False)
      ```
  + while 문을 돌면서 계단을 내려가는 시간을 계산한다. (한번 반복할 때마다 1초)
    
    1. 각 사람들이 계단을 향해서 가고 있다면 거리를 하나씩 줄여준다. 예) 사람 1 번이 1번 계단과의 거리가 3이라면 1초에 한칸씩 줄어들 기 때문에 3->2->1->... 이렇게 줄어든다.
    
    2. 사람과 계단의 거리가 0이 될 때가 계단에 도착했을 때이다.
    
    3. 계단을 내려가고 있다면 다시 -1 -> -2 ... 이렇게 줄어든다. 만약 계단의 길이가 3이면, first_stair_length 가 -3 이므로 이 사람의 거리 요소가 -3 이 됐을 때 person1 리스트에서 탈출한다.
    
    + person1, person2 리스트가 모두 길이가 0이면 (모든 사람이 탈출 하면) 시간을 return 해주며 while 문 종료
    
    + person1, person2 리스트를 2번씩 확인한다.
      + 처음 확인할 때는 person1 중 현재 계단을 내려가고 있는 사람을 먼저 확인해준다. (즉, 거리요소가 음수인 사람)
      
        + 계단을 내려가기 시작했으면 원래 1초 기다려 주고 내려가야 하기 때문에 False 를 True 로 바꿔준다.
        
        + True 라면, 계단을 한칸 내려간다 (-1)
        
        + 내려 가고 나서, 1층에 도착 했다면 그 리스트 요소를 remove 하고 num1+=1 해준다.
        
       ``` python
        for i in person1[:]: 
            if(i[2]<0):
                if(i[3] == False):
                    i[3]=True
                else:
                    i[2]-=1
            if(i[2]==first_stair_length):
                num1+=1
                person1.remove(i)
       ```
      + 두번째 확인할 때는 계단에서 대기하고 있는 사람과 계단을 향해 가고 있는 사람을 확인한다.
      
        + 계단에서 대기하고 있는 사람은(거리 요소가 0인 사람) num1, num2 > 0 이면 계단에 3명보다 적게 있단 말이기 때문에 num1, num2 를 -1 해주고 거리요소 0 또한 -1 해준다.(계단을 내려가기 시작)
        
        + 계단으로 가고 있는 사람은 거리요소를 -1 해준다.
       
       ``` python
        for i in person1[:]: 
            if(i[2]==0):
                if(num1>0):
                    i[2]-=1
                    num1-=1
            elif(i[2]>0):
                i[2]-=1
       ```
       
2. main
  + person : 사람의 위치를 담은 리스트 예) [[1,2], [2,3]] 는 2명의 사람이 (1,2) 와 (2,3) 에 위치해있다.
  + stair : 두 계단의 위치를 담은 리스트
  
  + 1 ~ person 의 숫자 // 2 만큼 반복하며, 리스트를 2개로 나누는 모든 combination 을 찾는다.
    + 모든 combination에 대해 check_time 을 호출해서 최소 시간을 찾는다.
    
    

## Review 

별거 아닌 실수였는데 테케 43/50 개 까지 맞아서 찾느라 오래 걸렸다..!

눈 크게 뜨고 찾기!
        
