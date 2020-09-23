# [4013] 특이한 자석

## Algorithm

Simulation

## 코드설명
1. turn_right(magnet), turn_left(magnet)
  > 돌리려고 하는 톱니바퀴 리스트를 파라미터로 가져와 왼쪽, 오른쪽으로 돌린 결과를 return 한다.
  
2. main
  > 돌리는 톱니바퀴 정보를 읽어오면 자석의 번호, 회전방향을 magnet_num, rotation_dir에 저장
  
    magnet_rotation에 이번 차례에 돌려지는 톱리바퀴 번호와 방향을 저장한다.
    
    첫번째 톱니바퀴 기준 왼쪽과 오른쪽으로 돌아가는 톱니바퀴를 확인한다.
  
