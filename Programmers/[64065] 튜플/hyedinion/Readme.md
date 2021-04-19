# [Programmers]/[64065] 튜플 - python

## 분류
시뮬레이션

## 접근법
- 첫번 째 숫자는 모두 나오니깐 N번 발현한 숫자가 첫번 째 숫자임을 알 수 있다.
- 위 규칙을 사용해 `N - (해당 숫자의 발현 횟수)` 가 해당 숫자의 인덱스 임을 알 수 있음

## 제약조건
- 문제 그대로

## 코드설명
```python
lis = re.findall('\d+',s)
```
- re.findall은 string에서 숫자를 찾아 list에 담고 return 해준다.
- '\d+' == 한자리 이상의 숫자
- '\d' == 한자리 숫자 찾기
- s = 숫자를 찾고 싶은 string

## 후기
- string으로 input이 들어와서 당황했다.
- 역시 python은 만능