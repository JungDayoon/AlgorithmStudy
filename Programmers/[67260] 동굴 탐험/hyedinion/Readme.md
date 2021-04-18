# [Programmers]/[67260] 동굴탐험 - python

## 분류
시뮬레이션, graph(adjlist)

## 접근법
- 주어진 path로 그래프를 그린다. (adjlist -> dictionary로 구현. 시간제한 때문)
- checkList를 통해 탐험 가능한지 check한다. 탐험 후 True를 저장.

## 제약조건
- order가 있어서 먼저 방문 해야하는 조건이 있다.
- 시간제한있음 (효율성 테스트)

## Graph
### 그래프란?
node와 그 node를 연결하는 edge를 하나로 모아놓은 자료구조 (tree도 graph의 한 종류)<br>
### 트리와의 차이점
rootnode, 부모 자식 개념이 없음. cycle 가능 (tree와의 차이점이다.)
### 구현방법
1. 인접list (adjlist)
- edge를 탐색하며 해당 node : n 과 연결된 node : n2 를 list[n] = [n2]에 저장.
- 무방향 graph일 경우 해당 edge는 두번 저장됨.
2. 인접행렬 (adjacency matrix)
- matrix[i][j]가 true라면 i -> j로의 간선이 있다는 뜻이다.
### 탐색방법
- dfs : 깊이 우선탐색. 재귀함수, stack으로 구현가능
- bfs : 너비 우선탐색. queue로 구현가능



## 코드설명

### 변수
>- solution() : 카카오 코테는 solution함수를 구현 후 return값으로 채점함<br>
>- pathDict{} : path를 adjlist로 구현한 dictionary.
>```python
>#path adj dictionary만들기
>    for p in path:
>        if p[0] not in pathDict.keys():
>            pathDict[p[0]] = [p[1]]
>        else:
>            pathDict[p[0]].append(p[1])
>        if p[1] not in pathDict.keys():
>            pathDict[p[1]] = [p[0]]
>        else:
>            pathDict[p[1]].append(p[0])
>```
>양방향이기 때문에 for문으로 path를 돌면서 p[0],p[1]모두 넣어준다.<br>
>dictionary에 키값이 없으면 새로운 list로 만들어 node를 저장해 주고, 존재하면 list에 append해준다.<br><br>
>
>- ordict{} : order를 저장한 dictionary ( ex)order = [8,5] -> 5를 방문하기 전 8을 방문해야함. ordict[5] = 8을 저장 )<br>
>- checkList{} : 방문 여부 check. 처음에 전부다 False로 저장<br>
>- queue[] : 방문해야할 node들을 저장.<br>
>- find_path() : queue에 있는 node를 하나씩 꺼내 방문. failList를 return<br>
>- failList[] : 도달 가능하지만 조건이 만족되지 않았을 경우 failList에 저장. 다시 탐색할 때 failList를 queue로 설정해서 find_path()함수를 다시 실행<br>
>- change : 전역변수. checkList의 변화가 있으면 True를 저장. 다시 탐색을 시작할 때 false를 저장
>```python
>if checkList[n]== False:
>            #조건이 만족됐는지 확인
>            #만족 안됐으면 failList에 담아줌
>            if n in ordict.keys():
>                if checkList[ordict[n]] == False:
>                    failList.append(n)
>                    continue
>            #조건이 만족 되었을 때
>            checkList[n]=True
>            change = True
>```
>find path함수를 다시 실행 할 때 failList에 있는 (다시 실행할 땐 queue로 바뀌어 있음) node들의 조건이 만족되었는지 확인하는 부분.<br>
>change가 True일 때 find_path()함수를 다시 실행하기 때문에 이 부분이 필요<br>

### 코드흐름
>1. 도달 가능하지만 (여기서 도달가능하다는 것은 부모 노드가 True라는 것) 조건이 만족되지 않으면 failList에 저장한다.
>2. change를 통해 checkList가 변경되었는지 확인한다. (checkList에 변화가 없다면 또 탐험 할 필요가 없음)
>3. change가 True일 때 failList의 node들이 조건을 만족했는지 확인하기 위해 failList만 다시 queue에 넣어 다시 방문한다. 


## 후기
- 처음에 그래프를 그릴 때 list에 있는 path를 다돌면서 tree를 그렸다. 그래프에 대해 공부를 덜해서 인접list를 까먹었기 때문... 여기서  부모node를 찾고 path를 remove하고 복사하고 반복문을 돌아서 시간이 낭비 되었다.
- 다른사람들의 코드를 보고나니 그럴 필요없이 그냥 쭉돌면서 모든 node를 넣어주면 되는것이였다.. 바보..
- 그래프는 나의 취약점.. cycle감지하는 코드는 구현해본적이 없다. 앞으로 공부할 예정! 누가 나를 보면 cycle 알려주기.