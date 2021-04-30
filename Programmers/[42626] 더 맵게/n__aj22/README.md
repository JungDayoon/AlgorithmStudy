# Programmers 42626번 : 더 맵게

## Algorithm

min heap

## Description

+ `scoville`를 heapq로 만들어 준다.

    ```python
    heapq.heapify(scoville)
    ```

+ 다음 과정을 반복한다.
    1. 큐에 들어있는 원소 중 가장 작은 것, 즉 `scoville[0]`의 값이 K 이상이면, 모든 수가 K 이상이므로 종료한다.

    2. 1번이 아니고, 만약 `scoville`의 크기가 2보다 작으면 더 이상 비교할 수 없으므로 -1를 return 한다.

    3. 2번이 아니면, scoville를 두 번 pop하고, 두 수를 문제에서 요구하는 조건에 맞게 계산한 후 append 한다.

    4. answer 증가

## Review



