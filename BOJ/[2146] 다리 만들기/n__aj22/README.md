# 백준 2146 : 다리 만들기

## Algorithm

BFS 

## Description
**로직설명**
```
BFS를 두 번 해준다.

1. 섬의 번호를 표시해주는 BFS

2. 다리를 연결해주는 BFS
```

**함수설명**
`chcked_island(cnt, y, x)` : BFS 를 이용해 섬마다 번호를 붙여준다. 

+ 예를 들어 다음과 같은 섬이 있다고 하자 (예제 1)

    <img src="https://user-images.githubusercontent.com/33089715/107868912-c821b200-6ecb-11eb-8fe5-a8f5e2041e01.png" width="350">

+ check_island를 호출하고 난 후에는 다음 처럼 바뀐다.

    <img src="https://user-images.githubusercontent.com/33089715/107868931-028b4f00-6ecc-11eb-893d-43f0cd54a1b1.png" width="350">
    
    + 같은 섬 끼리 같은 index를 가지고, index의 최대 값이 섬의 개수이다.

`make_bridge(index)` : index 번호에 해당하는 섬에서 시작하는 다리를 BFS로 만들어 준다.

+ 처음에 queue에 index 에 해당하는 섬을 모두 담아준다. 이 때 방문표시를 해주고 queue에는 [y, x, 0]을 넣어준다. 0은 bridge의 길이를 표시해주기 위한 cnt 이다. 

+ queue를 돌면서 방문한 적이 없고, 0인 곳이면 다리를 놓을 수 있기 때문에 방문 표시를 해주고, queue에 [y, x, cnt+1]를 담아준다.(현재 위치까지의 다리 길이가 cnt이므로 다음 다리는 cnt+1)

+ 만약 현재 위치가 0이 아니고, 현재 위치와 다른 index 라면 다른 섬이므로 이 때의 cnt가 index번호에 해당하는 섬에서 다른 섬 까지 이은 최단 길이의 다리이다. 따라서 min_bridge 값과 비교해 작은 값을 저장하고 종료한다.

+ 예를 들어 위의 예시에서 make_bridge(1)을 호출한 결과는 다음과 같다. 

    <img src="https://user-images.githubusercontent.com/33089715/107869158-4bdc9e00-6ece-11eb-8247-16d4264a854b.png" width="350">

    + 1번 섬에서 시작해 (7, 4)위치에서 0이 아니고 1번이 아닌 섬과 만나므로 이 섬까지의 다리 길이는 3이다.

`main` : 
    
1. check_island 를 호출해 섬에 번호를 붙여준다.

2. 섬의 개수만큼 make_bridge 를 호출해 각 섬에서 갈 수 있는 최단 거리의 다리를 계산하고 최소 값을 min_bridge에 갱신한다.

3. min_bridge 출력 

## Review

문제가 재밌었다!! 
