# 백준 1799번 : 비숍

## Algorithm

backtracking

## Description

1. 아래와 같은 체스판이 있을 때, 흰 칸과 검은 칸을 따로 생각해도 된다.(대각선이 다르기 때문에 겹칠 일이 없기 때문)
+ 따라서 answer = [0, 0]으로 두고 answer[0]은 [0, 0]에서 시작했을 때, answer[1]은 [0, 1]에서 시작했을 때 각각의 최대 체스 개수를 저장하고 합을 정답으로 한다.
    <img class="fit-picture"
     src="https://user-images.githubusercontent.com/33089715/119817366-dbabd280-bf28-11eb-8b9c-87f64021ac2a.png"
     width = 300>
2. `backtracking(index, c, count)` : 현재 위치를 의미하는 index, 시작 위치를 의미하는 c, 체스 개수를 의미하는 count 를 파라미터로 갖는다.
+ index는 현재 위치로, 좌표가 (i, j)일 때, i*N+j이다.
+ c는 시작위치로 (0, 0)에서 시작하면 0, (0, 1)에서 시작하면 1을 갖는다.
+ **로직**
    + 현재 상황에서 남은 위치에 모두 체스를 두었을 때의 개수가 현재 answer에 저장된 값 보다 작거나, 현재 위치가 체스 판을 넘어가면 더 이상 확인할 필요 없으므로 종료한다.(return)
        ```python
        if N*N-index+1+count<=answer[c] or index >= N*N:
            return 
        ```
    + 현재의 y, x값을 index를 통해 구한다.
    + for 문을 돌리기 위한 (i, j)값 중, j 값의 시작은 x 부터 시작한다.
    + 현재 위치에서 남은 위치를 반복문으로 확인하면서, 해당 위치에 체스를 둘 수 있으면 visited[위치] = True로 두고 backtracking 을 호출한다.
    + 주의 해야 할 점은, 현재 i에 해당하는 j가 모두 끝나면 다음 j는 이번 줄의 시작 j 와 엇갈리게 둬야한다.
        + 예를 들어 현재 줄에서 0번째 부터 확인했으면 다음 줄은 1번째 부터 확인해야 한다.
    + (0, 0)번째 부터와, (0, 1)번째 부터 모두 확인하고 answer 값의 합을 출력
## Review
+ 너무 어려워서 참고했다 ㅠㅠ
