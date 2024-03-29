# 백준 11062번 : 카드 게임

## Algorithm

Dynamic Programming

## Description

**`cache`** : N*N 크기의 캐시, 메모이제이션에 사용한다.

+ `cache[i][j]` : i는 카드의 시작 index, j는 끝 index를 의미한다.

**`start_game(turn, start, end)`** : top-down 방식으로 카드 뽑기를 하는 함수 
+ turn : 카드를 뽑는 순서
    
    + 홀수번은 근우, 짝수번은 명우를 의미한다.

+ start : card_list에서 다음 turn 으로 가져가는 카드의 시작 index

+ end : card_list에서 다음 turn 으로 가져가는 카드의 끝 index
    ```
    이 문제에서 중요한 것은 근우의 점수이다. 
    그렇다고 근우의 점수가 최댓값을 갖게 해주면 
    `근우와 명우는 서로 자신의 점수를 가장 높이기 위해 최선의 전략으로 게임에 임한다`라는 문제에 어긋난다.
    따라서 명우의 카드를 고를때는 근우가 최소의 점수를 갖게 하는 방향으로 카드를 뽑는다는 아이디어를 사용했다.
    ```
**근우의 점수만 고려해주면 된다.**

+ 종료조건 : **turn이 카드의 개수와 동일할 경우**, 만약 근우의 차례이면 해당 카드의 숫자를 return, 명우의 차례이면 0을 return 한다(근우가 얻는 점수가 아니기 때문).

+ cache에 값이 존재한다면 해당 값을 return 한다.

+ cache에 값이 존재하지 않을 경우

    + 근우의 차례일 때, 다음 두 값 중 **더 큰 값**을 return

        + 첫번째 카드를 고르는 경우 :
            start를 start+1로 바꿔 start_game을 호출한 후 반환받은 값에 첫번 째 카드 값을 더한다.


        + 마지막 카드를 고르는 경우 : 
            end를 end-1로 바꿔 start_game을 호출한 후 반환받은 값에 마지막 카드 값을 더한다.


    + 명우의 차례일 때, 다음 두 값 중 **더 작은 값**을 return

        + 첫번째 카드를 고르는 경우:
            start를 start+1로 바꿔 start_game을 호출한 후 반환받은 값에 첫번 째 카드 값을 더한다.

        + 마지막 카드를 고르는 경우 :
            end를 end-1로 바꿔 start_game을 호출한 후 반환받은 값에 마지막 카드 값을 더한다.

## Review

탑 다운 너무 어렵다 ㅠㅠ
