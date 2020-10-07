# [SWEA 2105] 디저트 카페 - Python

### :computer: Algorithm

> 시뮬레이션, 백트래킹



### :computer: Logic

##### :bulb: **idea** 

사각형 모양으로 움직일 수 있기 때문에, :arrow_lower_right: 이 방향으로 움직일 횟수(count_1)와 :arrow_lower_left: 이 방향으로 움직일 횟수(count_2)를 정하여 각각의 경우를 모두 백트래킹으로 찾는다. :arrow_lower_right:,  :arrow_lower_left: 횟수를 정했으면 :arrow_upper_left: 방향으로 count_1만큼, :arrow_upper_right: 방향으로 count_2만큼 움직인다. -> 사각형 모양으로 움직일 수 있음



`cafeTour(y, x, count_1, count_2, idx)`

각 방향으로 이동하는 새 좌표가 `cafeMap`을 벗어나거나, `dessertList`에 이미 담긴 원소를 추가하려고 하면 그 경우를 빠져나온다. 이동할 때마다(즉 `cafeTour`를 재귀로 다시 부를 때) 그 전의 `dessertList`를 저장해두고 다시 원상태로 돌려놓는다.

`idx` == 3인 경우는 가장 마지막 경우이므로, 이 때 `start` 좌표를 만난다면 사각형으로 움직이는 것을 종료하면 되므로, 그 때의 `dessertList` 개수로 `maxDessert`를 갱신한다.



`main`

`cafeMap`을 순회하면서 각 좌표를 `start`로 하는 백트래킹 함수를 실행한다

### :computer: Review

> 걸린 시간: 1시간 20분

사각형 모양으로 움직이는 거는 좀 새로운 유형이어따

처음에는 bfs, dfs로 해야되나 뭐 어떻게 해야되나 감이 안왔는데 생각해보니 각 좌표에서 모든 방향으로 탐색해줄 필요가 없었다 -> 이러면 겹치는 경우가 많이 생김 (7->8->9->4, 8->9->4->7 은 같은 경우)

그래서 항상 ↘ ↙ ↖ ↗ 이 방향으로 움직이면서 사각형을 그리도록 했다