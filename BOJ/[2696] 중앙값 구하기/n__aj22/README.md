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

1655번 풀고 푸니까 쉽게 풀렸다! 주의해야 할 점은 입력이 10개 단위로 된다는 점