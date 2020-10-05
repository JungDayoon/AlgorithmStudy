# [SWEA 2117] 홈 방범 서비스 - Python

### :computer: Algorithm

> 시뮬레이션



### :computer: Logic

`makeRhombus(serviceArea, K)`: 현재 K에 해당하는 마름모 만드는 함수

 `MapToNewMap(newMap, Map, K)`: Map을 벗어나는 곳에 마름모가 위치할 수도 있기 때문에 그에 맞게 Map의 크기를 키워서 newMap에 저장한다.

`findCost(newMap, serviceArea, serviceLen)`: 현재의 마름모(`serviceArea`)를 `newMap`에 놓고 한칸씩 계속 옮겨가면서 그 두가지의 값이 모두 1이라면 `count`를 올린다. -> 서비스 가능 지역에 집이 있다는 의미

그렇게 서비스 가능지역의 집 개수를 구하고 그 때의 이익을 계산한다. 이익이 0보다 크거나 같다면, 즉 손해보지 않는 경우에는 `maxHouse`와 `count`의 값을 비교하여 `count`가 더 크다면 `maxHouse`를 변경한다.



### :computer: Review

> 걸린 시간: 2시간

풀면서도 뭔가.. 읭..? 했는데 얼떨결에 통과됐다.

옛날에 카카오 문제 풀면서 이런 비슷한 넉김으로 풀었던게 기억나서 그대로 했는데 완전 비효율적인듯 ㅎ

풀고나서도 세상 찝찝.. 친구들 풀이 보니깐 내가 세상 어렵게 풀었드라.. 내일이나 시간날 때 다시 풀어보자 ㅠ ㅠ

마름모 영역에 들어오는 지 알 수 있는 방법

```python
if(abs(a-i)+abs(b-j)<K):
```

