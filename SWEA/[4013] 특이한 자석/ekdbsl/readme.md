# [SWEA 4013] 특이한 자석

### :computer: Algorithm

시뮬레이션

### :computer:Logic

1. wheel 리스트에 톱니바퀴의 자성을 저장한다.

2. 회전을 할 톱니바퀴를 기준으로 while문을 돌면서 left, right 각각을 확인한다.

   접한 부분이 자성이 다르면 rotateInfo 리스트에 넣어주고, 자성이 같아지는 순간 다음 부분은 볼 필요가 없으므로 break 한다.

    ```python
   if(wheel[left][2] != wheel[left_prev][6]):
       left_dir *= -1
       rotateInfo.append([left_dir, left])
       left -= 1
       left_prev -= 1
   else:
      break
    ```

3. rotateInfo에 들어가있는 톱니바퀴를 회전시켜준다. -> rotate()함수 내에서
4. 주어진 모든 톱니바퀴에 대해서 위의 과정을 수행한 뒤, 정답을 계산한다. 톱니바퀴의 번호와 자성에 따라서 점수가 다 다르기 때문에 코드를 최적화하기 위해서 score 리스트에 미리 값을 넣어두었다.

### :computer:Review

- 톱니바퀴의 회전은 모든 톱니바퀴에 대해서 회전할 것인지 말 것인지를 결정한 뒤에 해야 한다는 것을 유의하고 풀면 빨리 풀 수 있는 문제이다. 

- 톱니바퀴 기준으로 left, right를 보는 부분을 따로 구현했는데 이를 한번에 할 수 있으면 코드를 더 짧게 짤 수 있을 것 같다. 다른 사람의 코드도 참고해봐야겠다.