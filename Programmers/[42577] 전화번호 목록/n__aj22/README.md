# Programmers 42577번 : 전화번호 목록

## Algorithm

## Description

1. phone_book 의 문자열을 sort 한다.

2. phone_book 의 개수 - 1 만큼 for 문을 확인하며 phone_book[i+1]의 접두어가 phone_book[i]인지 확인한다.
    + phone_book[i+2]가 phone_book[i]로 시작한다면, phone_book[i+1]또한 당연히 phone_book[i]로 시작할 것이기 때문에(sort 되있으므로) 굳이 확인할 필요가 없다.
    + 접해있는 두 문자열만 확인하면 된다.
    
## Review

처음에는 그냥 2중 for문 사용해서 했는데 효율성 부분에서 2개가 시간초과가 났다.

이 부분을 해결하는 부분을 참고했다.



