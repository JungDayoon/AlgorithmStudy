# Programmers 64062번 : 징검다리 건너기

## Algorithm

이분탐색

## Description

**`possibility(num, stones, k)`** : 징검다리를 건널 친구 수 num, 징검다리 stones, 디딤돌 최대 칸 수 k를 파라미터로 받았을 때, num 명의 친구가 징검다리를 건널 수 있는지 여부를 Boolean 으로 return

+ 돌에 적힌 숫자는 그 돌을 밟을 수 있는 친구의 숫자이다.

+ (돌에 적힌 숫자 - num)이 0보다 작은 경우는 num명의 친구 중 마지막 친구는 이 돌을 무조건 밟을 수 없다는 의미이다.

+ 따라서 (돌에 적힌 숫자 - num)이 0보다 작은 수가 연속으로 k개 있다면 num명의 친구들은 징검다리를 모두 건널 수 없으므로 False를 return 한다.

+ 위의 경우가 아니면 True를 return 한다.

**`solution(stones, k)`** : 이분 탐색으로 건너기 가능한 숫자를 찾는다.

+ start : 1(최소 한 명의 친구는 건널 수 있음)

+ end : max(stones) + 1(stone의 최대값이 친구 수의 최대 값이다. 여기에 1을 더하는 이유는 아직은 잘 모르겠다. 생각해보기!!)

+ 위의 (start, end)를 이용해 이분탐색으로 가능한 친구 수를 찾는다.

    + while 반복 종료 조건 : start == end-1 일 경우
        
        + mid 를 찾을 수 없으므로 answer = start 이고 종료
    + 그렇지 않을 경우 mid를 찾고, mid가 친구 수 일때 가능한지 possibility 함수를 호출해 확인한다.
        
        + mid로 가능하다면 start = mid

        + 가능하지 않다면 end = mid

## Review

효율성 문제라 고민 많이 했는데 생각보다 아이디어는 금방 떠올랐다. 다만 종료 조건을 설정하는데 있어서 실수를 해서 이 부분을 해결하는데 시간이 좀 걸림..
