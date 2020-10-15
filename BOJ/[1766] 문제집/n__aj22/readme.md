# 백준 1766번 : 문제집

## Algorithm

Topological Sort, 우선순위 큐

## Description

` arr ` : 그래프 연결 정보를 저장한 딕셔너리

` in_degree ` : 자신으로 들어오는 간선의 개수를 저장해준 리스트

1. 처음에 in_degree 를 확인하며 0인 요소를 queue 에 담아준다.

2. queue 를 돌면서 
  + queue 중 가장 작은 숫자를 pop -> 출력
  
  + pop 한 숫자에서 가는 모든 간선을 확인하며 in_degree 를 줄여줌 -> 0 이면 queue 에 append
  
## Review

위상정렬 공부하고 푸니까 금방 풀렸다! 10분 컷!
  
