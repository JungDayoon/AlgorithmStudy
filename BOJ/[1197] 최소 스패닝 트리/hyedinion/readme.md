# [BOJ]/[1197] 최소스패닝트리

## 분류
최소신장트리 (MST, Minimum Spanning Tree)
1. Kruskal
>- 그래프 내에 적은 숫자의 간선만을 가지는 ‘희소 그래프(Sparse Graph)’의 경우 Kruskal 알고리즘이 적합
2. Prim
>- 그래프에 간선이 많이 존재하는 ‘밀집 그래프(Dense Graph)’ 의 경우는 Prim 알고리즘이 적합하다.

https://gmlwjd9405.github.io/2018/08/29/algorithm-kruskal-mst.html

## Kruskal
1. edge를 가중치 순으로 정렬한다.
2. 작은 weight순으로 union_find()을 하여 union가능하면 weight++ 한다.

## Union_find()
### make_set(V)
```python
for i in range(V):
    parent[i+1]=i+1
    rank[i+1]=0
```
- 처음에 자기자신의 부모집합은 자신이기때문에 parent[]를 자기 자신으로 초기화 한다.
- rank는 모두 0으로 초기화 한다.

### find(x)
```python
if parent[x]==x:
    return x
else:
    return find(parent[x])
```
- find함수는 부모집합이 누구인지 알려준다.(즉, 부모집합이란 집합내에서 rank가 가장높은 node이다.)

### union(x,y)
- 만약 그냥 parent[x] = y 라고 하면 find(x)함수로 부모를 찾는데 최악의 경우 O(N)만큼 걸린다.
>- 최악의 경우 == 비대칭 트리, 연결리스트 상태

- 해결법
>1. Union by Rank
>- 각 집합의 ‘rank’를, 더 큰 집합일수록 더 큰 rank를 가지도록 매긴다. 그리고 union 연산에서 언제나 더 작은 집합을 더 큰 집합에 합친다.
>2. Path Compression
>- Find(x)는 x에서 트리의 루트까지의 정점들을 차례대로 방문하는 형태로 구현한다. 경로 상의 모든 정점들을 모두 루트 정점을 바로 가리키게 한다.

### Union by Rank(x,y)
```python
x = find(x)
y = find(y)
```
- 부모집합을 찾아 부모집합으로 비교를한다.
```python
if x==y:
    return False
```
- 같은 집합이면 union할 수 없음을 반환

```python
if rank[x]>rank[y] : #x,y순서는 상관없음
    parent[y]=x
else:
    parent[x]=y
    if rank[x]==rank[y]:
        rank[x]+=1
return True
```
- rank가 높은곳에 낮은 node를 union한다.
- 만약 rank가 같다면 한쪽에 더해주고 더해준 쪽 rank를 +1해준다.

### 주저리
- 근데 그럼 1-2 3-4에서 union(1,3)을 하면<br>
rank[1]=2<br>
rank[2]=0<br>
rank[3]=1<br>
rank[4]=0<Br>
이 되는데 이 때 union(5,4)를 하면<br>
parent[4]=3<br>
parnat[3]=1 이 되니깐<br>
parent[5]=1 이되네<br>

- 근데 rank를 안쓰면 안되는건가?<br>
그냥 parent node에 append해주면 안되남
> - 1-2 3-4-5 일 때 1에 3-4-5를 붙히면 find함수가 거의 O(N)까지 가기 때문에 효율성 떨어짐
>- rank를 사용하면 부모집합이 3이 되기 때문에 안전

## 후기
약간 union이 이해될랑말랑한다.<br>
특히 find함수를 써야하는 이유과 rank가 최적인지에 대한 의문이 있음<br>
만약 내가 아무지식없이 풀었다면 배열에 모든 부모집합을 저장해 두었을 듯.<br>
>- 이 경우 나의 부모집합의 부모집합이 바뀌었다면 dfs로 모두 수정해 주어야하는 복잡함이 있을듯