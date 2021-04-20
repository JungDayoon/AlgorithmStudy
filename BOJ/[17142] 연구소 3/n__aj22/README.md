# 백준 17142번 : 연구소 3

## Algorithm

구현, 시뮬레이션, BFS

## Description

**`choose_virus(index, choose_list)`** : 백트래킹으로 virus를 선택해 M개 만큼 선택되면 spread_virus를 호출한다.
+ choose_list : 선택한 바이러스 인덱스를 담은 리스트

**`spread_virus(choose_list)`** : BFS로 choose_list를 시작으로 바이러스를 퍼트리는 함수
+ BFS임으로 시간이 오름차순으로 흐른다.
+ time을 계속해서 갱신해주면 되는데, 이 때 주의해야할 것은 arr 값이 0일 때만 time을 갱신한다.
+ 마지막으로 갱신된 time 값이 바이러스를 퍼트리는데 걸리는 시간
+ answer과 비교해서 더 작은 값을 저장한다.

## Review
Time ⏱ : 18분
