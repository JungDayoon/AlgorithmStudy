# Programmers 72415번 : 카드 짝 맞추기

## Algorithm

BFS, Permutation

## Description

1. board 전체를 확인하면서, 카드 위치 정보를 card_map에 저장한다.

+ card_map : map형식으로 key는 카드 번호, value는 해당 번호 카드의 2개의 위치 [[y1, x1], [y2, x2]]를 가진다.

2. card_map 에 있는 카드 key들의 순열을 card_order에 저장한다.

+ 예를 들어 1, 2, 3번 카드가 있다면 [1,2,3],[1,3,2],[2,1,3].. 등의 6가지 순서가 card_order에 저장된다.

+ 이 후에 card_order 순서대로 카드를 뒤집어 보고 최소 이동 값을 찾는다.

3. `backtracking(board, r, c, moved_distance, index, order_list)`: card_order중 하나인 order_list 순서대로 카드를 뒤집는데, 해당 번호 카드의 첫번째 카드를 먼저 뒤집을 수도, 두번째 카드를 먼저 뒤집을 수도 있기 때문에 이를 bactracking으로 해준다.

+ 현재 선택 할 카드 index에 해당하는 첫 번째 카드를 먼저 뒤집는다. find_card 함수를 호출해 이동 거리, 이동 후 r, c 위치를 확인한다. 두 번째 카드를 find_card 함수를 호출해 이동 거리, 이동 후 r, c 위치를 확인한다.

+ board 에서의 두 카드의 위치를 모두 0으로 만들고 backtracking 호출

+ 호출이 끝났다면 다시 원래 숫자로 바꿔준 후 위와 같은 방법으로 두번째카드, 첫번째 카드 순서대로 뒤집어 backtracking 호출

+ 종료 조건은 모든 카드를 뒤집었을 경우, 즉 index가 카드의 개수와 같아졌을 때이다.
    + 현재 까지의 moved_distance를 min_move 와 비교해 최소값으로 update 한다.

4. `find_card(r, c, target_card, board)`: 현재 위치(r, c)에서 target_card까지의 이동 거리를 BFS를 이용해 찾는 함수

+ 일반 BFS와 거의 동일하나, 현재 위치에서 다음 위치로 이동하는 경우 그냥 한 칸 이동하는 것과 ctrl을 누르며 이동하는 경우 두 가지를 queue에 넣어준다.

+ 이동 후 (r, c)와 이동 거리를 return 한다.

## Review

어려웡~