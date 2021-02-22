# 백준 1774번 : 우주신과의 교감

## Algorithm

MST(Kruskal)

## Description
**Logic**

1. 좌표를 입력받아 좌표를 잇는 모든 edge를 만들어 준다.

    + 모든 좌표 사이의 edge를 만들어야 하기 때문에 조합 사용

2. 이미 연결된 신들과의 통로를 입력 받아 union을 미리 해둔다. (이 후에 kruskal 했을 때 영향 받지 않기 위해)

3. kruskal 함수 호출 후 연결된 간선 사이의 가중치인 weight를 return 받는다. 

## Review
**주의할 점**

출력이 소수 둘째 자리 까지였는데, 처음엔 반올림 한 값인 round(answer, 2)을 해줬더니 틀렸다!

파이썬에서 둘째 자리까지 출력해줄 때 아래처럼 ..!
```python
print('%.2f' % answer)
```