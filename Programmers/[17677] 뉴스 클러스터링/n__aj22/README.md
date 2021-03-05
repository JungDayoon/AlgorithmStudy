# Programmers 17677번 : 뉴스 클러스터링

## Algorithm

string

## Description

**`find_dic(check_str)`** : 문자열 `check_str`을 두 글자씩 끊은 값을 키로, 키의 개수를 value로 갖는 dictionary 를 return
+ 알파벳이 아닌 특수문자가 포함되어 있다면 무시
+ 예를 들어 check_str = "FRANCE"라면
    
    dic = {'fr': 1, 'ra': 1, 'an': 1, 'nc': 1, 'ce': 1}
+ 예를 들어 check_str = "aa1+aa2"라면

    dic = {'aa': 2} 

**`Jacquard(dic1, dic2)`** : find_dic 함수로 찾은 문자열의 딕셔너리를 이용해 자카드 유사도를 계산하는 함수

+ `inser_num = 0` (교집합 개수), `union_num = 0` (합집합 개수)
+ 키가 더 많은 dic1의 key 값을 돌면서 dic2에도 동일한 key값이 있는지 확인한다.
    + 동일한 key가 있다면, 둘 중 더 작은 value를 inser_num에 더한다.(교집합은 더 작은 값을 가지기 때문)
    + 더해준 값을 각 `dic[key]`에서 뺀다.
    + 동시에 `union_num`에도 더해준다.
+ 위의 과정을 마친 후, key에 남은 value를 모두 `union_num`에 더한다.

+ 문제에서 요구하는 조건에 맞게 값을 return 해준다.

## Review

번거롭지만 쉬운 문제

