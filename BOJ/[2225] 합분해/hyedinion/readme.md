# [BOJ]/[2225] 합분해

## 분류
DP

## 접근법
A + ... + C + D = N 이라고 할 때<br>
A + ... + C : k-1 개<br>
D : K번 째 수<br>
A + ... + C = N-D 이다.<br>
DP배열을 k번 더해서 N이 되는 수 DP[k][n]이라고 할 때<br>
이것을 점화식으로 나타내면 DP[K][N] = DP[k-1][N-D] (DP[k-1][N-D]에 D를 더하면 되므로 갯수는 DP[k-1][N-D]와 같다)<br>
여기서 중요한 점은 D가 0부터 N까지 모든수가 될 수 있다는 것이다. (DP[K][N] = SUM(DP[k-1][N-D]) for D in range(N))<br>

## 첫번 째 방법 O(N**3)

1. 일단 1번 더해서 N이 되는 것은 자기자신 밖에 없으므로 DP[1][N]을 1로 초기화 해준다.<br>
```python
for i in range(N):
    DP[1][i] = 1
```
2. 2부터 K까지(i) 반복하면서 0~N까지(j) DP[i][j]배열을 업데이트 해준다.
```python
for i in range(2,K+1):
    for j in range(N+1):
        for D in range(j+1):# DP[i][j] += DP[k-1][j-D] for D in range(j)
            DP[i][j] += DP[i-1][j-D]
```

## 두번 째 방법 O(N**2)
- 첫번 째 방법에서는 모든 D를 j까지 다 돌면서 DP를 업데이트 해주었다. (DP[i][j] += DP[k-1][j-D] for D in range(j))
- 하지만 D를 j-1까지 더한 값은 DP[i][j-1]에 저장되어 있기 때문에 (DP[i][j-1] += DP[k-1][j-D] for D in range(j-1)))
- DP[k-1][j]값만 더해주면 된다.
- DP[i][j] = DP[i][j-1] + DP[i-1][j]
- 여기서 주의할 점은 range out of index 에러에 걸리지 않기 위해 j==0일 때 처리를 해주어야 한다.
    1. j==0일 때 경우의 수는 (K개를 더해서 0이되는 경우의 수)1이므로 DP[i][0]을 1로 초기화하고 for문을 1부터 N까지 돌려준다.
    2. 초기화 하기 싫다면 if j==0 : DP[i][j]=DP[i-1][j] 로하고 for 문을 0부터 N까지 돌려준다.

```python
for i in range(N):
    DP[1][i] = 1
    DP[i][0] = 1

for i in range(2,K+1):
    for j in range(1,N+1):
        DP[i][j] = DP[i-1][j]+DP[i][j-1]
```

## 후기
생각이 안난다.
너무 어렵다..🥲