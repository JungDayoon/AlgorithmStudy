# [Programmers 43105] 정수 삼각형 - Python

### :computer: Algorithm

> Dynamic Programming



### :computer: Logic

정수삼각형의 어떤 자리의 합은 그 전 라인의 왼쪽, 오른쪽 자리의 합 중 큰 값에 현재 자리 값을 더한 것으로 나타낼 수 있다.

```python
nowAnswer[ni] = max(prevAnswer[ni-1], prevAnswer[ni]) + nowLine[ni]
```

이를 반복해나가며 최종적으로 가장 큰 합을 구할 수 있다.