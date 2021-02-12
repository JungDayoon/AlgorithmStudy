# 백준 1062 : 가르침

## Algorithm

백트래킹, 문자열

## Description
**함수설명**
+ `init_alpha()` : visited와 total을 초기화해주는 함수

    backtracking 할 때 방문 가르친 알파벳 방문 표시를 해주기 위해서 사용하는 dictionary 형식의 visited 를 초기화 해준다. 모든 단어는 "anta" "tica"가 포함되어 있기 때문에 ["a","n","t","i","c"]에 해당하는 알파벳은 visited가 true로, 그 이외의 알파벳은 false 로 초기화해준다.

    total 은 알파벳의 인덱스를 확인하는 용으로 사용한다.

+ `find_max()` : K 개를 모두 가르쳤을 때 배울 수 있는 단어의 개수를 확인해 최대값을 찾아주는 함수

+ `backtracking(choose_num, index)` : 알파벳을 K개 만큼 backtracking 을 사용해 선택하고, K 개 만큼 선택되면 find_max()를 호출한다.

## Review

틀렸습니다 지옥에서 겨우 벗어났다.. ㅠㅠ
