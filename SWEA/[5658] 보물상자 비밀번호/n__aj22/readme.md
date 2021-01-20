# SWEA 5658번 : 보물상자 비밀번호

## Algorithm

Simulation

## Description
### 각 함수 설명
+ `def turn_arr(arr)` : 전체 arr 오른쪽으로 한 칸 이동시켜주는 함수이다. 제일 마지막 숫자를 pop하고, arr의 제일 앞에 붙여준다.

+ `make_number_arr(arr, N)` : arr 을 한 칸 이동시켜준 후, 각 변에 해당하는 알파벳을 숫자로 변환해 arr 로 return 해주는 함수이다. N 은 한 변에 위치한 알파벳의 개수이다. 

+ `make_decimal(num_str)` : 16진수에 해당하는 num_str 을 10진수로 변환해 return 한다.

    16 to 10 : int(num_str, 16)

## Review

문제가 어렵지 않아서 금방 풀었다!
```
16진수를 10진수로 : int('16진수 str', 16)

문자열을 알파벳 list 로 변환하기 : list(str_arr)

내림차순으로 sort : sorted_arr = list(sorted(arr, reverse=True))
```


