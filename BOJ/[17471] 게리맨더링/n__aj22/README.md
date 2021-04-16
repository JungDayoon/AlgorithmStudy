# 백준 17471번 : 게리맨더링

## Algorithm

BFS, Bruteforce

## Description
**`check_bundle(index)`** : 선거구의 구역 묶음을 확인하는 함수. index와 연결된 모든 구역의 visited 를 True로 바꿔주게 된다.

+ main에서 visited가 false인 구역을 만나면 check_bundle을 호출하게 된다.
+ check_bundle의 리턴 값은 index와 연결된 묶음의 인구 수 합이다.
+ check_bundle이 호출되는 횟수가 선거구의 구역 묶음의 개수이다.

    + 묶음이 3개 이상인 경우 : 문제에서 요구하는 것은 선거구를 2개로 나누는 것인데 이미 3개로 나눠져있기 때문에 2개로 나누는 것은 불가능하다. 따라서 -1을 출력한다.
    + 묶음이 2개인 경우 : 2개의 묶음의 차를 출력하면 된다. 각 묶음의 인구수는 check_bundle에서 return 받아 population_list에 저장되어 있다.
    + 묶음이 1개인 경우 : `divid()` 함수를 호출해, 구역을 2개로 나눴을 때 차의 최소값을 return 받아 출력한다.

**`divid()`** : 선거구의 구역을 2개로 나누는 함수. 나눈 후 인구수의 차를 모두 확인하고 최소값을 return 한다.

**중복을 제거하기 위해 1번 선거구는 무조건 존재하기 때문에 1번 구역이 포함된 선거구, 1번 구역이 포함되지 않은 선거구 이렇게 두 개로 나눈다고 생각했다.**
+ 따라서 N개의 구역을 (1~N-1)개를 뽑는 combination을 수행하는데, 이 중 1번이 포함된 리스트만 확인한다.
+ combination중 1이 포함된 arr1과, [1...N]중 arr1에 포함되지 않는 수를 arr2로 정의한다.
    ```python
    col_list = list(itertools.combinations(temp, i))#i는 몇 개를 뽑을 것인지 for문으로 지정
    for col in col_list:
        if 1 in col:#1이 포함된 comb만 
            arr1 = list(col)
            arr2 = [n for n in range(1, N+1) if n not in arr1]#arr1에 포함되지 않은 수를 arr2로
    ```
+ arr1, arr2 두 리스트의 possible 호출 결과가 모두 True이면, 각 인구수의 합을 answer(최소값을 저장)와 비교해 최소값을 저장한다.

**`possible(temp)`** : 선거구역 temp가 연결 요소인지 확인하는 함수. 연결되어있으면 True, 그렇지 않으면 False
+ temp의 첫 번째 값을 기준으로 BFS한다.
+ dictionary type의 `visited`를 사용한다.
+ pop된 선거구에 연결된 선거구가 `temp`에 포함되어 있고, 방문한 적이 없으면 
    + `visited = True` //방문 여부 True로 변경
    + `queue.append()` //queue에 포함
+ BFS를 완료한 후, `visited` 값 중 `False`가 존재한다면, `temp`의 선거구가 연결되어 있지 않다는 의미이므로 `return False`
    + 그렇지 않으면 `return True`

## Review
Time ⏱ : 1시간 10분

시간 더 줄일 수 있었는데 더 효율적인 방법이 없을까 고민만 한 20분 한 것 같다.

삼성은 거의 다 해보는거기 때문에 딱히 효율적인 방법이 떠오르지 않으면 고민 말고 다 하자!