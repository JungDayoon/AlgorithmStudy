# SWEA 5648번 : 원자 소멸 시뮬레이션

## Algorithm

Simulation

## Description

1. atom(원자 위치 정보와 방향 에너지 크기 정보를 가지고 있는 리스트) 의 길이가 0이 될 때 반복문 종료

2. 각 원자들을 이동시켜 준다. (이 때, 전체 범위인 -1000~1000를 넘어가면 원자들은 없어지기 때문에 delete_atom list 에 포함시켜준다.)
  
  + 원래는 1초에 1만큼 이동하지만, 가운데서 만날 때도 있기 때문에 0.5 씩 이동시켜줬다.
3. delete_atom 리스트 안에 있는 제거 대상을 atom 리스트에서 제거해준다.

4. atom 을 sort 한다.

5. 중복 원자 제거 : delete_atom 리스트에 atom 첫번째 요소의 x,y 좌표를 먼저 넣어준다.
  + 이 후 atom 을 돌면서 delete_atom 리스트 안에 있는 x,y 이면 flag = True 로 바꿔준다.
  + 만약에 리스트 안에 들어있지 않은 x,y인데 flag = False 이면, delete_atom 리스트의 마지막 요소는 중복되는 요소가 없었다는 말이므로 다시 pop 해서 없애준다.
    + 새로운 x,y 를 delete_atom에 추가해준 후 flag = False 로 초기화
    
  + 모든 atom 을 확인 했다면 delete_atom 안에 든 x,y 와 겹치는 원자를 atom 에서 제거한다.
    + 제거와 더불어 제거되는 원자의 에너지를 total_energy에 더해준다.
