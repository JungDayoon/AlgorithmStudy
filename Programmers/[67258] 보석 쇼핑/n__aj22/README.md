# Programmers 67258번 : 보석 쇼핑

## Algorithm

Sliding Window

## Description
```
슬라이딩 윈도우 알고리즘을 사용한다.
```
**`gem_dic`** : 윈도우 안에 속하는 보석을 [key:보석 이름, value : 보석 개수]로 가지고 있는 딕셔너리
**`gem_dic`** : 윈도우 안에 속하는 보석의 set (중복 없음)
1. 슬라이딩 윈도우의 시작 인덱스를 의미하는 lo는 0부터 gems의 길이 만큼 반복한다.

2. 정해진 lo 부터 시작하는 윈도우의 끝 인덱스 hi는 hi_start 부터 gems의 길이만큼 반복한다.(hi_start 초기값 0)

3. 만약 gems[hi] 값이 gem_dic에 존재한다면, 값을 하나 증가시킨다.
+ gem_dic에 존재하지 않는다면 value 1로 추가한다.
+ gem_set에 gems[hi] 추가한다.

4. set 에 추가 후, gems_value(gems의 set)과 gem_set의 길이가 같다면(모든 보석이 포함되었다면), hi_start(다음 hi 시작 인덱스)값을 hi로 바꾸고 기존에 찾은 구간 보다 더 작은 구간이면 update 해준 후 break.

5. gem_dic과 gem_set에서 윈도우의 첫 번째 보석인 gems[lo](다음 윈도우는 lo+1 부터 시작하기 때문)와 마지막 보석인 gems[hi_start](다음 hi에 포함되기 때문)를 상황에 맞게 삭제한다.



## Review

너무너무 어렵다.. 아이디어만 참고했다.. 