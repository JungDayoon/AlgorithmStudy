# [11052] 카드 구매하기
## 분류💁

DP

</br>

## 접근법
- 문제는 N개 카드를 구매하기 위해 지불해야하는 금액을 최대로 구하는 것이다.

- 본래 카드 가격을 `cards`, 최대를 가지는 카드 가격을 `P`라고 했을 떄,
- 카드 4개를 구매하기 위한 카드들이 {1,5,6,7}이라고 했을 때 카드 4개를 구매하는 최대는 max(P1+P3, P2+P2, card[4])가 된다.
- 즉, P2 + P2인 10이 정답이 된다.


</br>

## 후기💡

- 간단한 DP 문제이다.