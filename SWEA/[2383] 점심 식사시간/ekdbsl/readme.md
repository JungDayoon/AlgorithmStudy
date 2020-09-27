# [SWEA 2383] 점심 식사시간 - Python

### :computer: Algorithm

> 시뮬레이션, 조합



### :computer: Logic

`solution(people1, people2)`: `people1`은 계단1을 이용하는 사람들이고, `people2`는 계단2를 이용하는 사람들이다. 각 사람들의 위치에서 부터 계단의 위치까지의 거리를 계산하여 `distance` 리스트에 저장하여 `calculTime(distance)`을 호출하는 함수이다.

`calculTime(distance)`: 모든 사람이 계단을 내려오는 시간을 계산하는 함수

- `stairLine`: 계단 두 개를 사용중인 사람의 시간을 저장하는 리스트
- 다음을 종료조건(모든 사람들이 계단을 빠져나오는, 즉 `finish` = 사람 수)이 될 때까지 `time`을 하나씩 증가시키면서 수행한다
  1. 각 계단에 있는 사람들의 시간을 1씩 줄인다. 단, 3번째 이후의 사람들의 시간은 줄이면 안된다. 동시에 계단을 이용할 수 있는 사람은 최대 3명이기 때문이다. 따라서 4번째 사람부터는 대기한다.
  2. 계단에 있는 사람들 중 시간이 0이 된 사람이 있다면, 계단 이용이 끝난 것이므로 `stairLine`에서 pop해준다.
  3. `distance` 리스트에서 거리가 0이 된 사람이 있다면, 이는 계단에 도착해있다는 뜻이므로 `stairLine`에 추가해준다. `distance`를 순회하며 계속 거리를 1씩 빼준다.
- 종료조건을 만족하면, 그 때의 `time`을 return 한다.

In main,

사람들을 2개의 집합, people1과 people2로 분할하고 각 집합을 stair1과 stair2를 이용하는 경우와 stair2와 stair1을 이용하는 경우로 나누어 풀어야한다.

따라서 조합을 수행하는데, N//2+1 만큼만 해주면 된다.

N = 5라면 전체에서 조합이 0인 경우를 빼면 조합이 5인 경우가 되고, 전체에서 1인 경우를 빼면 조합이 4인 경우가 되고, .. 따라서 N//2+1까지만 해주어도 모든 경우를 계산할 수 있게 된다.

```python
for i in range(N//2+1):
	comb = list(combinations(person, i))
	for comb_item in comb:
            other = [x for x in person if x not in comb_item]
            minTime.append(solution(comb_item, other)) #집합1이 stair1, 집합2이 stair2를 사용하는 경우
            minTime.append(solution(other, comb_item)) #집합1이 stair2, 집합2이 stair1을 사용하는 경우
```



### :computer: Review

> 걸린 시간: 1시간 40분

계단을 이용하고 있는 사람과 대기 중인 사람을 어떻게 분리할 것인지 고민이 됐다. 처음에는 대기 중인 사람은 리스트를 따로 만들어서 보관할까 하다가 많이 복잡해질 것 같아서 좀 더 간단히 표현할 방법을 생각하는데 시간을 좀 썼다. 그래도 어떻게 풀 지 꼼꼼히 생각하고 하니까 구현도 금방 되고 한번만에 통과했다 ! 