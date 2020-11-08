# [BOJ 9465] 스티커 - Python

### :computer: Algorithm

> DP



### :computer: Logic

스티커를 고르는 경우는 i번째 위치에서 위에꺼를 고르느냐 아래꺼를 고르느냐 고르지 않고 그냥 지나가냐 이렇게 세가지로 줄일 수 있다.

위에꺼를 고를 때는 그 전의 위치에서도 위에꺼를 골랐다면 고를 수 없다.

아래꺼를 고를 때는 그 전의 위치에서도 아래꺼를 골랐다면 고를 수 없다.

이를 유의하면서 코드를 작성하면 된다.

- 핵심코드

  ```python
  for i in range(1, n+1):
      answer[0][i] = max(answer[1][i-1] + sticker[0][i], answer[2][i-1] + sticker[0][i])
      answer[1][i] = max(answer[0][i-1] + sticker[1][i], answer[2][i-1] + sticker[1][i])
      answer[2][i] = max(answer[0][i-1], answer[1][i-1], answer[2][i-1])
  
  ```

  

### :computer: Review

DP 문제는 생각해내기까지는 어렵지만 막상 구현은 정말 쉬운 것 같다

시뮬레이션은 막 사람 실수 유발하게 하고 복잡하게 해서 어려웠는데 이거는 메모이제이션을 어떻게 해야할지 생각해내는게 어려운것 같다...

이 문제도 스티커를 위에꺼 선택/아래꺼 선택/선택x 이렇게 세가지 경우로 나눠서 푸니까 쉽게 풀렸다.

이렇게 풀어야겠다고 생각하는 게 어려웠따!!