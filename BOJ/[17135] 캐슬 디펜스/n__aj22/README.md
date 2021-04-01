# 백준 17135번 : 캐슬 디펜스

## Algorithm

구현, 시뮬레이션, 브루트포스, BFS

## Description

**`choose_archer(num, archer, index, enemy)`** : 궁수 3명의 위치를 선택한다.

+ 3명의 궁수가 선택되었다면, start_game 함수를 호출해 선택된 위치의 궁수로 제거할 수 있는 적의 수를 return 받아 최대 값과 비교해 update 한다.

+ 선택된 궁수는 성이 있는 칸에 위치해 있으므로 [N, x위치]에 있다.
    + 이 함수에서 결정하는 것은 x위치이다. 따라서 궁수 위치를 결정했다면 archer리스트에 [N, i]를 append 한다.

**`start_game(archer, enemy)`** : 정해진 archer로 게임이 끝날 때 까지 궁수로 인해 제외된 적의 수를 return 한다.

+ temp 에 enemy를 저장해 temp가 빌 때 동안 턴을 진행한다.

1. **궁수 공격** : 각 궁수에 대해, 적 중에서 거리가 D보다 작은 적들을 queue에 담아준다. 
    + 이 때는, 거리가 작은 것을 기준으로, 작은 것이 여러개라면 왼쪽 적을 먼저 삭제해야 하기 때문에 heapq를 이용해 [distance, x, y] 로 저장한다.(distance, x, y 순으로 오름차순 정렬하기 위해서)

    + 모든 적들을 확인 했다면, queue에 담긴 적 중 가장 첫번 째 적의 위치를 delete_list에 담아준다.(한 턴에서 궁수는 동시에 공격하고, 한 명의 적이 여러 궁수에게 공격당하는 경우도 있기 때문에 바로 삭제하면 안되고 모든 궁수가 공격하는 적을 확인한 후 삭제해야한다.)

    + delete_list에 담긴 적의 위치가 이번 턴에 궁수에 의해 제거될 적이므로, delete_list의 길이를 deleted_num 에 더해준다.

    + 이 후, temp 에서 delete_list에 위치한 적들을 모두 삭제한다.

2. **적 이동** : 남은 temp의 적들을 한 칸씩 아래로 이동시켜준다.

    + 이 때, 이동시킨 위치가 N과 같다면 성에 도달한 것을 의미하므로 새로운 delete_list에 이 위치를 담아준다.

    + temp를 모두 확인한 후, delete_list에 담긴 적들을 temp 에서 삭제한다.

3. **return 값 - deleted_num**

## Review

이 zㅣ ~~