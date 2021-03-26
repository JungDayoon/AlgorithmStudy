# Programmers 64064번 : 불량 사용자

## Algorithm

String

## Description
**`check_expression(uid, exp)`** : 숨겨진 아이디 exp에 uid가 해당하는지 여부를 확인해 boolean return
1. 문자열 길이가 다르면 False
2. 문자열 길이가 같으면
+ 문자열 길이만큼 확인하면서 같은 index에 다른 문자열이 존재한다면 False
3. 위와 같은 경우가 아니면 True

**`make_dictionary(banned_id, user_id)`** : banned_id에 해당하는 user_id를 dictionary로 표현해 return

1. dictionary의 key는 banned_id
2. dictionary의 value는 banned_id에 해당하는 user_id 리스트

+ 예를들어 user_id와 banned_id가 다음과 같다고 하자
    ```python
    user_id = ["frodo", "fradi", "crodo", "abc123", "frodoc"]
    banned_id = ["*rodo", "*rodo", "******"]
    ```
+ 위의 경우를 dictionary로 만들면 다음과 같다.
    ```python
    dic = {'*rodo': ['frodo', 'crodo'], '******': ['abc123', 'frodoc']}
    ```
**`backtracking(index, banned_id, dic, choose_list)`** : banned_id에 해당하는 id를 choose_list에 중복되지 않게 담아준 뒤, 중복되지 않은 조합인 경우 answer에 담아주는 함수

1. backtracking을 이용한다.
2. 종료 조건은 index가 banned_id길이와 동일한 경우, 즉 banned_id 개수 만큼 choose_list에 id가 담겼을 경우이다.
+ choose_list에 담긴 id를 sort한 뒤, answer에 담긴 조합인지 확인한다.
+ 없으면 answer에 추가한다.
    ```python
    if(index == len(banned_id)):
        temp = copy.deepcopy(choose_list)
        temp.sort()
        if temp not in answer:
            answer.append(temp)
        return
    ```
3. 종료 조건에 해당하지 않는다면 dictionary에 담긴 banned_id[index]값들을 모두 돌면서 choose_list에 없는 id인 경우, choose_list에 담은 후 backtracking을 재호출 한다.
    ```python
    for uid in dic[banned_id[index]]:
        if(uid not in choose_list):
            choose_list.append(uid)
            backtracking(index+1, banned_id, dic, choose_list)
            choose_list.pop(-1)
    ```
**`solution(user_id, banned_id)`** : 모든 과정을 수행해준 후 return 값은 answer의 길이이다.
## Review

쉽다
