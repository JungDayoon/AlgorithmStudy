# [BOJ 10816] 숫자 카드 2 - Python

### :computer: Algorithm

> 해시



### :computer: Logic

파이썬에서는 `Hash` 자료구조로 `Dictionary`를 사용한다.

Dictionary를 선언할 때는 간단하게 `Dict = {}` 이렇게 해주면 된다.

- `key`: 카드 숫자
- `value`: 카드 숫자 빈도수 

카드들의 정보를 받아와서 이 카드의 숫자가 `Dict`에 있는지 확인하고, 없다면 새롭게 만들어준다.

-> `Dict[card] = 0`

그리고, 이 `key`의 `value`를 1만큼 증가시킨다.

비교할 카드들의 빈도수를 저장할 새로운 리스트를 하나 만든다. -> `result`

비교할 카드의 숫자가 `Dict`에 있다면 그 `value`를 `result`에 추가하고, 없다면 0을 추가한다.



### :computer: Review

> 걸린 시간: 30분

문제 자체는 간단한 Hash를 이용하는 문제였지만,, 

역시 파이썬을 이론부터 탄탄히 배우고 하는 게 아니라서 그런지 완전 잘못 생각한 부분이 있었다.

```python
### 잘못된 방법 ###
outStr = ""
for compare in Compare:
    if compare not in Dict:
        outStr += "0 "
    else:
        outStr += str(Dict[compare]) + " "
    print(id(outStr))

print(outStr)
```

처음에 풀이한 방법은 outStr이라는 빈 문자열을 하나 선언한 뒤, 이 문자열에 새로운 카드의 빈도수를 계속 이어붙여나가는 방식으로 구현했다.

하지만 문자열 뒤에 숫자가 새로 붙을 때마다 객체를 새로 생성하는 이런식의 풀이는 비교할 카드 수가 매우 많다면 성능이 현저히 떨어진다.



파이썬에서의 **Mutable vs Immutable** 개념부터 다시 공부하자

- **Mutable 객체**
  - 객체를 생성한 후, 객체의 값을 수정 가능. 변수는 값이 수정된 같은 객체를 가리킨다.
  - ex) list, set, dict
- **Immutable 객체**
  - 객체를 생성한 후, 객체의 값을 수정 불가능. 변수는 해당 값을 가진 다른 객체를 가리키게 됨
  - ex) int, float, complex, bool, string, tuple, frozen set

이렇게, **string은 immutable 객체에 해당한다**. 따라서 새로운 값을 더하여 문자열을 생성할 경우 기존의 것에서 더해지는 것이 아니라 새로운 객체가 생성되는 것이다.

