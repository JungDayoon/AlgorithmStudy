# 백준 13334 : 철로

## Algorithm

우선순위 큐

## Description

1. train_line에 모든 사람들의 집, 사무실의 시작, 끝 위치를 넣어준다.
+ 이 때 이후 sort 하기 편하도록 [end, start] 순으로 넣어주었다.
+ 모두 넣은 후, [end, start]기준 오름차순 sort를 한다.
    ```python
    #첫 번째 예시에서 train_line은 다음과 같다.
    train_line = [[20, 10], [25, 10], [30, 25], [35, 25], [40, 5], [50, 30], [60, 50], [100, 80]]
    ```
2. sort된 train_line을 순서대로 확인한다.
**모든 위치 마다 end 기준으로 앞으로 철로를 설치한다고 가정한다.**
    ```python
    #예를 들어 train_line의 첫 번째 [끝, 시작]인 [20, 10]에서는 20 위치에서 앞으로 길이 30인 철로를 설치한다.
    #그렇게 되면 철로의 시작 위치는 -10에서 20까지 설치되는 것이다.
    ```
+ end-start 길이가 설치할 철로의 길이보다 길다면 continue (어짜피 집과 사무실의 위치가 모두 L에 포함되는 사람들에 포함할 수 없기 때문에)
+ 그렇지 않은 경우 queue에 [start, end](우선순위 큐)를 넣어준다.

+ **queue에서는 시작위치 기준으로 min_heap**을 구성하고 있기 때문에 root는 시작위치가 가장 작은 값이다.
    + 만약 root의 start 값이, 이번 철로 설치 시작 위치보다 작다면 이 위치는 이번 철로에 포함되지 않기 때문에 pop한다.(이후에도 더 이상 포함되지 않음)
    + 더 이상 작은 root를 만나지 않을 때 까지 pop하다가 더 큰 root를 만나면 break 한다.
+ 집과 사무실의 위치가 모두 L에 포함되는 사람들의 수는 현재 queue의 길이와 같다.

## Review
어렵다 ㅠㅠ 그래서 참고했다.. 
