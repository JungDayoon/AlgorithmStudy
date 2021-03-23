# 백준 1509 : 팰린드롬 분할

## Algorithm

dp

## Description
**함수설명**

1. **`int palindrome(int start, int end)`** : 문자열 S에서 start~end 해당하는 구간이 팰린드롬인지 아닌지 top-down 방식으로 찾는다.

+ 기저사례 : start가 end보다 같거나 커진 경우 팰린드롬이 맞기 때문에 1을 return

+ start 와 end의 문자가 다르면 팰린드롬이 아니므로 0을 return

2. **`int getminpal(void)`** : 최소 팰린드롬 분할 값을 찾아 minpal에 저장하는 함수

+ S의 첫번째 인덱스 값에는 아무것도 없으므로 0을 저장한다.

+ 1번 인덱스 부터 str.length()인덱스 까지 돌면서 end 지정(i)

    + 1번 인덱스부터 i번 인덱스 까지 돌면서 start 지정(j)
    + (j~i)에 해당하는 최소 팰린드롬 분할 값을 `minpal[i]`에 저장한다.
    + 만약 (j~i)에 해당하는 문자열이 팰린드롬이라면(palindrome 호출 한 결과) minpal[i] 값과 minpal[j-1]+1 값을 비교해 최소 값을 minpal[i]에 업데이트 한다.

+ minpal의 마지막 값, 즉 minpal[str.length()]을 return 한다.



## Review

너무 어렵다 ㅠㅠ 엉엉... ㅠㅠ