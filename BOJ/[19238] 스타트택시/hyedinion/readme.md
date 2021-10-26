# 스타트 택시

## 구현
- BFS

##
BFS는 while문과 queue를 사용

bfs를 할 때 depth도 queue에 같이 넣어주어야 한다.

### deque
```python
from collections import deque
deq = deque()
# Add element to the start
deq.appendleft(10)
# Add element to the end
deq.append(0)
# Pop element from the start
deq.popleft()
# Pop element from the end
deq.pop()
```
>- 앞, 뒤 양쪽 방향에서 엘리먼트(element)를 추가하거나 제거할 수 있다.
>- 일반적인 리스트(list)가 이러한 연산에 O(n)이 소요되는 데 반해, 데크(deque)는 O(1)로 접근 가능하다.

### 2차원배열 정렬
- 2차원 배열에서 sort의 기본값
```python
lst = [[2, 1], [3, 4], [1, 2], [1, 3], [3, 2]]
lst.sort()
print(lst)
# [[1, 2], [1, 3], [2, 1], [3, 2], [3, 4]]
```
- 0번째 인덱스에 대해서 오름차순으로 정렬한 뒤, 동일한 값의 경우 내림차순으로 재정렬
```python
lst = [[2, 1], [3, 4], [1, 2], [1, 3], [3, 2]]
lst.sort(key=lambda x: (x[0], -x[1]))
print(lst)
# [[1, 3], [1, 2], [2, 1], [3, 4], [3, 2]]
```
- 1번째 인덱스에 대해서 오름차순으로 정렬한 뒤, 동일한 값의 경우 0번째 인덱스 오름차순정렬
```python
lst = [[2, 1], [3, 4], [1, 2], [1, 3], [3, 2]]
lst.sort(key=lambda x: (x[1], x[0]))
print(lst)
# [[2, 1], [1, 2], [3, 2], [1, 3], [3, 4]]
```
출처 https://haesoo9410.tistory.com/193

### heapq
힙은 이진트리로 만들어져 있어 삽입, 삭제를 하는데 O(log n)이 걸린다.

일일이 n개의 데이터를 삽입한다고 했을 때 각 삽입마다 O(log n)의 시간이 걸리므로 총 O(nlog n)의 시간이 걸림
하지만 heapify를 이용할 경우 O(n)의 시간으로 배열을 heap으로 바꿔줄 수 있다.

```python
    heapq.heapify(lst)
    heapq.heappush(lst, value)
    heapq.heappop(lst)
```

- heapq 배열에서 sort의 기본값
```python
import heapq
lst = [[2, 1], [3, 4], [1, 2], [1, 3], [3, 2]]
heapq.heapify(lst)
for i in range(len(lst)):
    print(heapq.heappop(lst))
# [[1, 2], [1, 3], [2, 1], [3, 2], [3, 4]]
```

### 시간초과
1. heapq 사용
> list.append() -> O(1)*N, list.sort() -> O(NlogN) 시간복잡도 N+NlogN
> heapq.heappush() -> )(logN)*N, heapq.heappop()-> O(1) 시간복잡도 NlogN
> 코테 돌렸을 땐 heapq가 조금더 느렸음

2. map 사용
> dic(str(i)+" "+str(j)) : str() -> O(logN)*M*2, 인덱싱 -> O(1)
> map[] :  초기화 -> O(N**2) , 인덱싱 -> O(1)
> 확실히 map을 쓴게 훨씬 빨랐다. map을 써야겠다.

3. 연료
> bfs도중 연료가 move보다 적으면 바로 종료한다.
> bfs가 끝나고 나서 연료계산을 하지 않는다.

4. 승객
> 같은 거리의 승객을 탐색할 때 승객 수 만큼만 탐색하면 된다. -> 승객이 줄어들수록 시간이 줄어듦

5. 해결방법 ****중요
```python
if 0<=id<N and 0<=jd<N and wall_map[id][jd]!=1 and check_list[id][jd]==0:
    check_list[id][jd] = 1
```
> 여기서 check_list를 방문하기전에 queue에 들어갔다는 의미로 1를 미리 지정해 주어야함.
> 나는 일단 큐에 다 담고 큐에서 뺐을 때 check_list에서 이미 방문했던 길이면 continue를 하게 했는데
> 이렇게 하면 중복되는 인덱스들이 큐에 가득 담기기 때문에 이 큐를 방문하는데만 해도 시간이 엄청나게 걸림.
> queue 자체를 줄여야 된다 이말.. queue에 담을 때 신중을 가하자.