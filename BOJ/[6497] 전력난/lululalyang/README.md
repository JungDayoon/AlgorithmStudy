# [BOJ]/[6497] 전력난

## *- MST -*

* ***Kruskal 알고리즘*** 을 사용하여 모든 두 집 쌍이 불이 켜진 길로 연결될 수 있도록 하는 최소한의 전기세 값을 구한다.
  * 이때, 각 간선의 정보를 입력받을 때 모든 전기세(= 거리)를 더하여 저장해둔다.
  * **(모든 거리의 전기세 값 - 최소한의 전기세 값)**이 절약할 수 있는 최대 비용이 된다.