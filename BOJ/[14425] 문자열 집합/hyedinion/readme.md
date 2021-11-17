# [BOJ]/[14425] 문자열 집합

## 분류
시뮬레이션

## 접근법
문제그대로

## 시간복잡도
dict.keys() = O(1)<br>
List와 달리 Dictionary는 내부적으로 hash를 통해서 자료들을 저장하기 때문에 시간복잡도가 O(1)<br>
-> O(M)만큼으로 탐색가능<Br>
set -> k in set으로 key에 있는지 확인 가능
dict-> k in dict.keys()로 key에 있는지 확인 가능

## dict vs set vs tuple
collection type == list, dict, set, tuple<br>

### dict 딕셔너리
- key,value로 구성
- dict() 나 {}로 만들 수 있다.
- key 중복 불가, 순서 없음

### set 집합
- key 값만 존재
- set()이나 {}로 만들 수 있다.
- key 중복 불가, 순서없음

### tuple 튜플
- tuple()이나 ()로 만들 수 있다.
- 리스트와 비슷하지만 항목값을 변화할 수 없다. (프로그램이 실행되는 동안 값이 바뀌지 않길 원하는 경우 사용)
- del 안됨 -> 항목값을 변화 시킬 수 없기 때문
- 1개의 요소만을 가질 때는 요소 뒤에 콤마(1,)를 반드시 붙여야 한다
- 중복가능, 순서있음

## 후기
쉽