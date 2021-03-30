# 백준 4485번 : 녹색 옷 입은 애가 젤다지?

## Algorithm

Dijkstra

## Description

`dijkstra(graph, n)` : 2차원 배열에서 [0, 0] 에서 모든 위치까지의 최단거리인 다익스트라를 수행한 결과담은 distances 배열을 return 한다.

+ pop한 위치에서 상, 하, 좌, 우 4방향으로 이동했을 때 거리가 현재 distances에 저장된 거리보다 작으면 업데이트 해주고 queue에 push 한다.
## Review

쉽다!
