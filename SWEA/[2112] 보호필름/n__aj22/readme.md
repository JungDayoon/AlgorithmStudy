# SWEA 2112번 : 보호필름

## Algorithm

백트래킹, DFS

## Description

1. change(now_row, index) : 선택된 row 를 now_row 파라미터로 받아와서 모두 index 값을 저장해주는 함수
  + index 는 A 약품 혹은 B 약품에 따라 0, 1
  
2. save(now_row,temp_row) : 선택된 row를 temp row에 저장해주는 함수

3. check_possibility(film, D, K) : 필름의 통과 여부를 확인해주는 함수
  + 각 열을 체크하면서 하나의 열이라도 연속된 숫자가 K 이상인 열이 없다면 return 0
  + 모두 통과할 경우 return 1

4. set_drug(drug_max, drug_now, film, D, K, choose_list, prev) : 백트래킹 하면서 drug_max 만큼 약물이 선택되면 필름의 통과 여부를 확인하는 함수

+ drug_max : 약물 최대 선택 횟수
+ drug_now : 현재 선택된 약물의 개수
+ film : 필름 정보를 담고있는 리스트
+ choose_list : 약물을 사용하기로 선택된 행 정보를 담고 있는 리스트
+ prev : 이전에 선택된 행을 담고 있는 변수, 반복문의 시작 조건으로 prev+1가 사용된다. (2,4를 선택 했다면 4,2 가 선택 되는 것을 방지하기 위함)
  + drug_max 와 drug_now 가 같으면 check_possibility 호출
    + check_possibility 가 1일 경우(통과 된 경우) flag = True 로 셋해주고 종료한다.
    
  + 그렇지 않은 경우에는 prev+1 부터 D 까지 돌면서 다음 선택될 행을 정한다.
    + choose_list 에 없는 행이라면, choose_list 에 append 해준다.
    + 선택된 행을 save 호출해서 저장해주고 change 호출해서 A 약물을 넣어준다.
    + 다시 set_drug 호출한다. 파라미터로 drug_now+1(행을 하나 더 선택했기 때문에), prev = i (현재 선택된 행 다음 행부터 확인해도 되므로)
    + 호출 후 flag 가 True 로 변경됐으면 검사를 통과했으므로 종료
    + 그렇지 않으면 B약물 처리 후 set_drug 호출
    + flag 를 다시 확인 해준 후 실패 하면 choose_list 에서 해당 행 삭제하고 원래 row 정보를 film 에 저장해준다.
5. main
+ 필름 Depth 만큼 돌면서 set_drug 호출해준다.

## Review
시간초과... 너무 힘들다..

deepcopy는 되도록이면 사용하지 말자.. 메모...
