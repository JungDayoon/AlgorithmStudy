# [BOJ]/[2800] 괄호제거

## 분류
combination

## 접근법
1. ( 가 인덱스 i일 때 나오면 i를 stack에 넣어줌<br>
2. ) 가 인덱스 j일 때 나오면 stack.pop()해서 [i,j]를 bracket에 넣어줌<br>
3. bracket에서 가능한 모든 조합을 combinations로 구함<br>
4. combination에 들어있는 인덱스를 제외한 str을 answer에 append한다.
5. answer.sort()후 출력

## combination 리스트 변환
```python
temp = []
    for c in com:
        temp+=c
```
- combination에 들어있는 com은 ([1,2],[3,4]) 이런식으로 들어있기 때문에 [1,2,3,4] 이렇게 변환이 필요
- com을 돌면서 com[i]를 temp에 더해준다.


## 놓친것
((((1))))2<br>
일 때<br>
(1)2가 여러번 나올 수 있음.<br>
중복되는 부분을 체크해 줘야함.<br>


## python 순열, 조합
```python
items = ['1', '2', '3', '4', '5'] 
from itertools import permutations 
list(permutations(items, 2)) 
# [('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('2', '1'), ('2', '3'), ('2', '4'), ('2', '5'), ('3', '1'), ('3', '2'), ('3', '4'), ('3', '5'), ('4', '1'), ('4', '2'), ('4', '3'), ('4', '5'), ('5', '1'), ('5', '2'), ('5', '3'), ('5', '4')] 
from itertools import combinations 
list(combinations(items, 2))
# [('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('2', '3'), ('2', '4'), ('2', '5'), ('3', '4'), ('3', '5'), ('4', '5')]
```
출처: https://ourcstory.tistory.com/414 [불로]


## 후기
순열,조합 공부완료