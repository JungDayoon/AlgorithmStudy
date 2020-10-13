# [BOJ 14889] 스타트와 링크 - Python

### :computer: Algorithm

> 조합, 순열



### :computer: Logic

축구 팀을 두 팀으로 나눈 뒤 각 팀의 선수들의 순열을 만들어 능력치 합을 각각 구한다.

- 축구 팀을 두 팀으로 나누는 방법: 조합을 사용 -> N//2 크기로
- 각 팀의 능력치 합을 구하는 방법: 순열을 사용 -> 각 팀 arr에서 크기 2의 순열을 모두 구한다.



### :computer: Review

> 걸린 시간: 45분

순열이랑 조합 만들 때 자꼬 실수를 한다,,, 최근에 계속 파이썬 함수로 써서 그런가 ㅜ ㅜ

이 기회를 통해서 다시 한번 정리를 하자

- Permutation

  ```python
  def makePermutation(now, goal, set, list, visited, arr):
  	# now: 순열을 만들 때 현재 보는 자리수
  	# goal: 순열의 최종 개수. 즉 now가 goal과 같아진다면, 이때 순열이 완성된 것임
  	# set: 함수를 돌 때마다 계속 바뀌는 리스트. 현재 자리까지의 순열이 들어가있음
  	# list: 다 완성된 순열을 차례대로 저장함. list의 크기는 (len(arr))P(goal)
  	# visited: 순열을 만들 배열(arr) 크기만큼의 리스트. 이미 방문했던 원소는 다시 set에 넣을 수 없으므로 필요함 -> 중복 순열을 만들 때엔 필요 없음
  	# arr: 순열을 생성할 배열.
  	
  	if now == goal:	# 순열이 완성됨
  		list.append(set[:])
  		return
      if i in range(0, len(arr)):
          if not visited[i]:
              set[now] = arr[i]	# 만약 set에 append하는 형식으로 한다면, 재귀함수 부른 뒤 set.pop()이 필요함. 현재의 방법은 index에 직접 접근.
              visited[i] = True
              makePermutation(now+1, goal, set, list, visited, arr)
              visited[i] = False
  	
  ```

- Combination

  ```python
  def makeCombination(now, goal, set, list, prev, arr):
      # now: 조합을 만들 때 현재 보는 자리수
      # goal: 조합의 최종 개수. 즉 now가 goal과 같아진다면, 이 때 조합이 완성된 것임
      # set: 함수를 돌 때마다 계속 바뀌는 리스트. 현재 자리까지의 조합이 들어가있음
      # list: 다 완성된 조합을 차례대로 저장함. list의 크기는 (len(arr))C(goal)
      # prev: 순열을 만들 배열의 index를 어디서 부터 볼것인지를 저장하는 변수. 그 전 경우에 넣은 원소의 index 다음부터 보면 됨
      # arr: 조합을 생성할 배열
      
      if now == goal:
          list.append(set[:])
          return
      for i in range(prev+1, len(arr)):
          set.append(arr[i]) # 만약 set[now] = arr[i] 으로 쓴다면, pop하는 부분 없어도 됨
          makeCombination(now+1, goal, set, list, i+1, arr)
          set.pop(i)
  ```

  





