# [4014] 활주로 건설 - python

## 분류

Divide & Conquer

<br>
## 접근법

1. 절벽지대(이하 space)의 가로,세로에 놓을 수 있는 활주로를 구해야함
2. space의 각 line을 쪼개어 함수 하나에 집어넣으면 반복으로 쉽게 답을 구할 수 있음.
3. space의 각 line을 for문으로 집어 넣어 활주로를 건설 할 수 있으면 1, 없으면 0을 return하는 check_line()함수를 구현
4. return 값을 answer에 더해 출력

<br>
## 제약조건

경사로를 놓을 수 없는 경우
- 칸의 높이차가 1을 넘을 때
- 이미 설치된 경사로에 또 경사로를 설치할 때
- 경사로의 길이가 범위를 벗어날 때

<br>
## 코드설명
<br>
### 내장함수
```python
space = list(map(list,zip(*space)))
```

1. zip 함수를 사용하면 각 변수의 index가 같은 요소값끼리 묶어서 ()로 return

```python
space = [[1,1,1],
         [2,2,2],
         [3,3,3]]
print(list(zip(*space)))
```
2. *space에서 *연산자는 space를 iterator로 사용하여 각 열의 첫번째 인자들을 묶어서 return해줌
결과 -> [(1, 2, 3), (1, 2, 3), (1, 2, 3)]

```python
s = space[:]
```
3. s = space라고 하면 주소값도 복사하여 값을 수정하게 되면 space의 값도 바뀌게됨.
s 값만 수정하고 싶으면 space의 각 인자를 복사하는 [:]를 써야한다.
<br>
### check_line() 함수

s = space의 각 line을 넘겨준 값을 복사 <br>
a = prev<br>
b = current<br>
i = index<br>

1. 처음에 함수에 들오면 첫번째로 line의 첫번 째 값을 a에 저장.
2. i를 증가하며 line[i]의 값이 a와 다르면 조건탐색 시작
3. line[i]의 값을 b에 저장
```python
if abs(a-b)>1:
    return 0
```
4. (a-b)의 절댓값이 경사로의 높이인 1보다 작으면 0을 return 
5. b의 값이 a보다 작을 경우 오른쪽에 경사로를 설치해야함
   b의 값이 a보다 작을 경우 오른쪽에 경사로를 설치해야함
```python
#오른쪽
if i+x>n:
    return 0
#왼쪽
if i-x<0:
    return 0
```
6. 범위를 넘어가면 0을 return
``` python
for j in range(x):
    if s[i+j]!=b:
        return 0
    s[i+j]=-1
i = i+x
a = b
continue
```
7. 오른쪽 경사로를 탐색할 때 같은 숫자가 경사로의 길이만큼 반복되면 경사로를 설치했다고 -1로 표시하고 경삿로 길이보다 작으면 0을 return한다.
```python
for j in range(1,x+1):
    if s[i-j]!=a:
        return 0
```
8. 왼쪽도 마찬가지

<br>
## 후기
- 제약조건을 많이 놓쳤다
- 다음엔 더 꼼꼼히 문제를 읽어야겠다.


