# 백준 1655 : 가운데를 말해요

## Algorithm

우선순위 큐

## Description

1. queue를 두개 사용한다. 

+ leftq : 왼쪽 queue는 max_heap으로 구현한다.

    + 중앙값은 leftq의 첫번째 값이다. leftq[0]

+ rightq : 오른쪽 queue는 min_heap으로 구현한다.

2. leftq와 rightq의 크기가 같으면 leftq에 새로운 값을 넣는다. / 그렇지 않으면 rightq에 새로운 값을 넣는다.

3. 만약 rightq의 root 보다 leftq의 root 값이 더 크면 오른쪽 root와 왼쪽 root를 모두 pop 하고 반대 queue로 push 한다.(root 원소값 바꾼다.)

4. leftq의 첫번 째 값을 출력한다.



## Review

처음에 구현한 방법은 중앙값은 root에 존재하고, 중앙값 기준으로 뒤에 값은 계속 저장되면서 앞에 값들은 상황에 맞게 pop 되도록 구현했는데, 그렇게 하니까 중앙값 보다 작은 값이 계속해서 들어왔을 때 중앙값이 작은 값으로 바뀌는 상황이 있는데 이 때 이미 작은 값들이 pop되서 없는 상황이라 맞지 않았다.
너~무 아이디어가 생각이 안나서 참고했다..
