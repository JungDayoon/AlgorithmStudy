# 백준 1497 : 기타콘서트

## Algorithm

백트래킹

## Description
**함수설명**
+ `song_num(choose_list, guitar):` : 선택된 기타 index를 가진 choose_list 와 이 때 기타의 수 guitar 를 파라미터로 호출해, 이 기타를 골랐을 때 연주할 수 있는 곡의 개수를 확인한다.

    + 만약 곡의 최대 개수인 max_num을 갱신한다면, 이 때의 기타 개수를 guitar_num 에 저장해준다.

    + 또한 곡의 최대 개수가 곡 전체 개수와 같다면, 이 때의 기타 개수가 정답이므로 is_M 플래그를 True 로 바꿔준다.

+ `backtracking(target_num, choose_list, index)` : 백트래킹을 이용해 기타를 선택하는 함수이다.

    + target_num은 목표로 하는 기타 개수이다. 선택된 기타의 개수가 목표로 하는 기타의 개수가 되었을 때 song_num 함수를 호출한다.

    + choose_list는 선택한 기타의 인덱스를 저장한다.

    + index 는 이전에 확인한 인덱스 다음 부터 확인하기 위해서 사용하는 값이다.

    + 반복문 중 만약 최대 값을 갖게 하는 기타의 개수를 찾았다면 break를 이용해 종료한다.
        ```python
        if(is_M == True):
            break
        ```

+ `main` : 
    + 각 기타마다 연주할 수 있는 곡의 정보를 song_info에 저장하는데, 모든 곡을 연주할 수 없다면 바로 -1 를 출력하고 종료한다.

    + 그렇지 않다면, 목표로 하는 target_num 을 하나씩 늘려주며 backtracking 을 호출한다.

## Review

가르침과 거의 유사한데 더 쉬워서 금방 풀었다

20분 소요!
