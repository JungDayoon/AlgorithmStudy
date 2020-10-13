# [BOJ 11559] Puyo Puyo - Python

### :computer: Algorithm

> 시뮬레이션, BFS



### :computer: Logic

`puyoMap`에서 같은색깔이 네 개 이상있는 경우가 단 하나라도 있을 때까지 다음을 반복한다.

- `puyoMap`을 순회하면서 방문한적 없고, 뿌요가 있다면 `bfs`를 수행한다. -> 인접한 같은 색깔을 가진 뿌요들의 개수를 반환
- 뿌요의 개수가 4개 이상이라면, 뿌요가 터지기 때문에 이 뿌요들의 좌표를 `removeList`에 담아준다.
- `puyoMap`의 순회가 끝나면 터뜨릴 뿌요의 좌표가 들어있는 `removeList`를 순회하며 뿌요들을 없애준다.
- 뿌요를 없애고 나면, `puyoMap`에 빈칸이 생기기 때문에 이 빈칸들을 `removeEmpty()` 함수에서 없애준다.



### :computer: Review

> 걸린 시간: 50분

끝까지 로직을 먼저 생각한 다음에 구현하니 실수가 없네요.. 너무 기분이 좋습니다.. 흐엉

:bookmark: 이번 문제를 풀면서 얻은 개꿀팁

```python
removeList = sum(removeList, [])
```

-> **2중 리스트를 flatten하게** 만드는 방법

**또 다른 방법**

```python
from itertools import chain
removeList = list(chain(*removeList))
removeList = list(chain.from_iterable(removeList))
```

-> 이 방법이 `sum()` 함수보다 성능이 더 좋음