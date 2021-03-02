# 백준 9372번 : 상근이의 여행

## Algorithm

Tree(Kruskal)

## Description

1. (travel2.py) Kruskal을 이용해서 해결

    입력받는 비행 스케줄이 (a, b)라면 (1, a, b)을 모두 저장해준 후 kruskal을 돌리면 된다.


2. (travel.py) 주어지는 비행 스케줄은 항상 연결 그래프를 이룬다는 조건이 있기 때문에 사실 정답은 N-1 과 동일하다.

    따라서 입력받는 비행 스케줄에 관계없이 N-1을 출력하면 정답이었다.!

## Review
