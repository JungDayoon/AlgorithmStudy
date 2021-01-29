# 백준 20208번 : 진우의 민트초코우유

## Algorithm

Backtracking

## Description
1. 첫 번째 시도 : 원래 n*n 에서 backtracking 하듯 풀었다. -> 시간초과!

2. 두 번째 시도 : 우유의 위치를 따로 milk_arr 에 저장해두고 milk_arr에서만 backtracking한다.

+ `def cal_dis(y1, x1, y2, x2)` : (y1, x1), (y2, x2)사이의 거리

+ `def backtracking(health, y, x, milk_num)` :

    + 현재 먹은 우유의 개수가 현재 최대 우유(max_milk)보다 크고, 집 까지의 거리가 현재 체력보다 작거나 같으면(현재 체력으로 집 까지 갈 수 있으면) max_milk 갱신

    + milk_arr를 돌면서 아직 먹지 않은 우유를 확인한다.
        + 먹지 않은 우유까지 가는데 거리가 현재 체력으로도 갈 수 있으면 visited 를 True 로 변경하고 backtracking 함수를 호출

        + health -> health-minus_health+H, milk_num -> milk_num+1

+ `main` : 집의 위치를 (home_y, home_x), 우유의 위치를 milk_arr 에 저장한 후 backtracking 호출

    
## Review

예전에 삼성 코테 대비할 때 코테 전날 풀다가 못풀었던 문젠데

다시 새로운 방식으로 푸니까 나름 빨리 풀렸다..

다만 예외 상황 고려해주는거에서 실수를 많이해서 그 부분들 수정하는데 오래걸렸다 ㅠㅠ 

예외 상황으로는 25-30-35-40 에 해당하는 위치이다. 윷놀이 판을 어떻게 만드냐에 따라 저 위치가 다르게 인식될 수 있는데 다르게 인식된다면 말을 이동시켰을 때 다른 말이 이 위치에 있는지 여부를 확인할 수 없기 때문에 정답과 다르게 나온다. 이 점만 잘 고려해주면 된다..!
