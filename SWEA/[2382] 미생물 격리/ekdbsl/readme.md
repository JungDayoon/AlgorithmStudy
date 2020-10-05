# [SWEA 2382] 미생물 격리 - Python

### :computer: Algorithm

> 시뮬레이션



### :computer: Logic

`Map` : NxN 크기의 2차원 deque 리스트. 각 좌표의 deque에 차례대로 군집이 들어가게 된다.

`AliveThing` : 현재 Map에 미생물이 하나 이상 존재하는 좌표를 저장하는 List. `Map`의 위치가 변경될 때 원래 Map에 있었던 군집을 알기 위해서 필요한 변수

`moveThing(AliveThing, Map)` : `AliveThing`을 순회하며 Map의 정보를 수정한다. `Map`의 처음 item이 기존에 있던 군집이므로 `popleft()`로 그 군집을 꺼내고, 다시 `append()`로 새로운 좌표에 넣어준다.

`Map`의 상태를 모두 변경하였으면, 이제 약품이 칠해진 부분에 위치한 군집에 대한 처리를 해야한다. -> `isInDanger(y,x)`를 통해 약품에 칠해진 부분에 있는 지 확인

한 좌표에 여러 군집이 있다면, 전체 군집 수와 가장 큰 군집의 방향을 리스트로 가지는 item을 새로운 deque에 넣어준다.



### :computer: Review

> 걸린 시간: 1시간 40분

한 칸에 미생물 군집이 여러 개인 경우, 그 칸의 미생물 합과 가장 큰 미생물 군집을 구하기 위해서 lambda를 사용했는데.. 아직 잘 쓸 줄 몰라서 이 부분이 틀렸었다.. 하필 첫 test case가 잘 돌아가는 바람에 찾느라 눈알 빠질뻔 했다.. lambda 사용법이랑 개념을 다시 한번 공부해야겠다.. 아직 구글링 없이는 어려운 점이 많다.. Python 부족함을 많이 느낀다 열심히 해야겠다 

```python
# 각 미생물 군집의 sum을 구함
total = sum(map(lambda x: int(x[0]), tmpList))
# 가장 큰 미생물 군집의 idx를 구함
value = max(tmpList, key = lambda item: item[0])
idx = tmpList.index(value)
```

```python
# 기존에 사용했던 방법
idx, value= max(enumerate(tmpList), key = operator.itemgetter(0)) 
```

-> 이 방법은 tmpList가 dictionary의 형태일 때 되는 거 같다.. 아직 왜 max값을 제대로 가져오지 못하는 지 정확하게는 모르겠지만 대부분의 경우에는 dictionary 형태일 때 다음과 같은 방법을 사용하는 것 같다.

```python
# dictionary에서 사용법
dic = { 'a': 7, 'bc': 5, 'ba': 5 }  
# value값으로 오름차순 정렬

sdic1 =  sorted(dic.items(), key = operator.itemgetter(1))  # 1번째 인덱스 기준으로 정렬 

>>> [('bc': 5), ('ba': 5), ('a': 7)]
# 1번째 인덱스 기준으로 정렬후, 같은 값이 나온다면 0번째 인덱스 기준

sdict2 = sorted(dic.items(), key = operator.itemgetter(1, 0))

>>> [('ba': 5), ('bc': 5), ('a': 7)]
```