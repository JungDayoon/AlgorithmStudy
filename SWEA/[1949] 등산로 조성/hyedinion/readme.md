# [SWEA]/[쉬움] 등산로조성

## 분류
dfs

## 접근법
시간이 2초이상이면 전탐을 해도 되는 것 같다.<br>
가장 높은 봉우리를 hill_list에 담아주고 각 봉우리에서 dfs()를 한다.<br>

## dfs(I,J,depth,curve)
I,J = 현재 위치
depth = 등산로 길이
curve = 산은 깎았는지 check

```python
mountain[I][J]>mountain[newI][newJ]
```
만약 다음 봉우리로 갈 수 있으면 dfs 진행
갈 수 없으면 봉우리 깎고 갈 수 있으면 진행, 갈 수 없으면 answer 업데이트
answer 업데이트

## 놓친것
1. dfs하기전에 처음 i,j False만들기
2. 현재 봉우리 > 다음봉우리 부등호 방향
3. range(K+1)까지
4. 산은 딱 한번 깎을 수 있다 ** 문제 꼼꼼히 일기
6. 갈 수 없을 때만 산을 깎기 -> range(1,k+1)까지

## 후기
반복되는 코드를 줄일 수 있을 것 같은데 다시 해봐야 겠다.<br>
꼼꼼히 생각하기<br>