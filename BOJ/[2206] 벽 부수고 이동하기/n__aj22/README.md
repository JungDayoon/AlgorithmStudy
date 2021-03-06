# 백준 2206 : 벽 부수고 이동하기

## Algorithm

BFS 

## Description
**로직설명**
>
    BFS로직 대로 하는데, 벽을 한개 부수고 BFS하는 것과 벽을 부수지 않고 BFS하는 것을 따로 저장해준다.


**코드설명**

`*main*` :
    
+ road_map : 길을 저장해주는 배열

+ answer : visited를 체크할 수 있고, (1,1)에서 이 위치 까지의 거리를 저장해주는 3차원 배열 

    + 벽 한개를 부수고, 안부수고 여부를 저장해줘야 하기 때문에 기존 visited 에 한 차원을 더 늘려서 3차원으로 만들어준다.

    + 처음에는 -1로 초기화 되어있으나 BFS를 진행하면서 거리를 저장해준다.

    + `answer[y][x][0]` 일 때는 벽을 부수지 않은 상태에서 거리를 저장

    + `answer[y][x][1]` 일 때는 벽을 부순 상태에서의 거리를 저장

+ `findroad()`함수 호출 후 `answer[N-1][M-1][0]`의 값과 `answer[N-1][M-1][1]` 값을 비교해 최소값을 출력해주거나 혹은 -1이 아닌 값을 출력해준다. (둘 다 -1이라면 -1 출력)

`*findroad()*` : BFS를 하면서 거리를 answer에 저장해주는 함수

+ queue에는 `[y, x, is_break]`를 저장해준다.

    + `is_break`는 벽을 부순지 여부

    + `is_break == 0`이라면 벽을 부수지 않은 상태

    + `is_Break == 1`이라면 벽을 부순 상태

+ BFS 두가지 조건

    + 이동할 위치에 현재 is_break상태로 방문한적이 없고, 이동할 위치가 '0'일 때(길일 때)
        
        + `answer의 이동할 위치, is_break`에 `answer의 현재 위치, is_break` 값의 + 1한 값을 넣어준다.

            ```python
            if(answer[nexty][nextx][is_break] == -1 and road_map[nexty][nextx] == '0'):
                    answer[nexty][nextx][is_break] = answer[nowy][nowx][is_break]+1
                    queue.append([nextx, nexty, is_break])
            ```
        
    + 이동할 위치가 '1'이고(벽이고), 아직 벽을 부순적이 없고, 이동할 위치에 is_break+1(1)인 상태로 방문한 적이 없을 때 

        + `answer의 이동할 위치, is_break+1`에 `현재위치, is_break 값`의 + 1 한 값을 넣어준다.

            ```python
            elif(is_break == 0 and answer[nexty][nextx][is_break+1] == -1 and road_map[nexty][nextx] == '1'):
                    answer[nexty][nextx][is_break+1] = answer[nowy][nowx][is_break]+1
                    queue.append([nextx, nexty, is_break+1])
            ```

**예시**
+ 다음과 같은 길이 있다고 하자 

    <img src="https://user-images.githubusercontent.com/33089715/108348773-8d9a7b00-7225-11eb-8fb6-eace0fce1ab3.png" width="350">

+ answer[y][x][0]

    <img src="https://user-images.githubusercontent.com/33089715/108348972-c5a1be00-7225-11eb-80bd-e603f4e2c225.png" width="350">

+ answer[y][x][1]

    <img src="https://user-images.githubusercontent.com/33089715/108348977-c76b8180-7225-11eb-9b61-bac864dad33b.png" width="350">

+ 답은 18과 14중 작은 값인 14
## Review

BFS라 쉬울줄 알았는데 어려웠다..ㅎㅎ
