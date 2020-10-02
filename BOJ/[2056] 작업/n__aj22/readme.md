# 백준 2056번 : 작업

## Algorithm

DP

## Description

1. t : 현재 작업을 수행하는데 걸리는 시간

2. time_list : 작업 시간을 담고있는 리스트 각 작업마다 [작업 시작시간, 작업 끝나는 시간] 을 담아줬다.
    + 작업 번호와 인덱스를 일치시키기 위해 [0,0] 을 먼저 담아줬다.

3. prev_num : 선행되는 작업의 개수

    + 선행되는 작업이 없다면 작업을 처음부터 시작해도 되므로 time_list.append([0, t])
    
    + 작업이 있다면 prev_list 에 선행되는 작업의 번호를 담아준다.
      + time_list 중 prev_list에 해당하는 작업을 찾아가며, 해당 작업 중 가장 늦게 끝나는 작업 시간을 찾아준다.
        + 예) 5번을 수행하기위해 선행되는 작업이 2, 4 번이고 2번에 6시간째에, 3번이 11시간 째에 작업을 끝낸다면 5번이 작업을 시작할 수 있는 시간은 11시간째 이다.
      + time_list에 [선행 작업 중 가장 늦게 끝난 작업시간, 선행 작업 중 가장 늦게 끝난 작업시간 + t] 를 append 
          
    ``` python
    if(prev_num==0):
        time_list.append([0,t])
    else:
        prev_list = input_list[2:]
        check_list =[]
        max_time = 0
        for j in range(1, len(time_list)):
            if(j in prev_list):
                max_time = max(max_time, time_list[j][1])
        time_list.append([max_time, max_time+t])
    
    ```

4. max_answer : 정답에 해당하는 변수로, 끝나는 시간 중 가장 늦게 끝나는 시간을 찾아주면 된다.


## Review

최근에 DP 문제를 많이 풀어봐서 DP 로 풀었다.!

python3 로는 시간 초과가 나지만 pypy3 를 쓰면 통과 된다.. 
