# 백준 11779번 : 최소비용 구하기 2

## Algorithm

Dijkstra

## Description

`dijkstra(start)` : start 부터 dijkstra를 수행한 결과 distances를 return 한다.

+ 단, 이번 문제에서는 경로도 출력해야 하기 때문에 distances에는 key는 node로, value는 [최단 거리, 이전 node]를 저장한다.

+ 마지막에 경로를 출력할 때는 distances를 end 부터 start 까지 반복해서 돌면서 거꾸로 된 경로를 찾아주고, 이를 반대로 출력하면 된다.
## Review

아마 경로가 여러개라면 그 중 하나만 출력하면 되는 것 같다. 
나는 백준에서 주어진 예제를 입력했을 때 아래와 같은 결과가 나왔지만 통과했다.
```
4
3
1 4 5
```
