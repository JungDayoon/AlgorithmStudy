# [SWEA 5658] 보물상자 비밀번호 - Python

### :computer: Algorithm

> 구현



### :computer: Logic

`rotate` 함수를 통해서 비밀번호를 한칸씩 돌린다. 이 비밀번호는 `TreasureStr`이라는 변수에 저장한다.

한 칸에 위치하는 비밀번호만큼 잘라서 저장해야하므로, 이를 처리한다.

```python
for i in range(0, N - (rotate_num - 1), rotate_num):
  	splitTreasure.append(TreasureStr[i:i + rotate_num])
```

내림차순으로 정렬한 후에 그 위치에 있는 수(16진수)를 10진수로 변환하여 출력한다.