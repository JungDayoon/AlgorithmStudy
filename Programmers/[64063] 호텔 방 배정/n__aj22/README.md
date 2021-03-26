# Programmers 64063번 : 호텔 방 배정

## Algorithm

재귀

## Description

**`findemptyroom(emptyroom, num)`** : 빈 방 번호를 return 하는 함수

1. emptyroom : 빈 방이 아닌 방 번호를 key로 갖고 있는 dictonary

2. num : 찾으려고 하는 방 번호

3. 재귀를 이용해 빈 방 번호를 찾는다.

+ emptyroom의 키에 num이 없으면 해당 방 번호는 빈 방이라는 의미이다.
    + 이 방은 이제 배정될 예정이므로 emptyroom 에 추가해준다. 다음 방을 찾을 수 있도록 key는 num이고 value는 num+1를 갖도록 추가한다.
    + num 을 return 한다.

+ 그렇지 않으면 findemptyroom 을 이용해 다음 방인 emptyroom[num]을 찾는다. 
    + return 값이 빈 방이다. 따라서 이 빈 방에 배정될 예정이므로 현재 num 에 해당하는 dictionary 값을 empty+1로 바꿔준다.
    ```python
    empty = findemptyroom(emptyroom, emptyroom[num])
    emptyroom[num] = empty+1
    return empty
    ```

## Review

첫 번째 시도때는 다음 방 정보를 저장하는 리스트를 썼었는데, 방 번호 만큼 미리 리스트를 다 만들어 주기도 하고, 다음 방 번호를 찾는 while 문에서 시간초과가 났다. 
비슷한 알고리즘을 dictionary를 사용하고, 재귀로 구현하니 시간초과가 나지 않는다... 