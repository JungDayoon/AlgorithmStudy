# 백준 2800번 : 괄호 제거

## Algorithm

Stack, DFS

## Description

1. ` right(target,now,delete_list, prev) ` : 문자열 리스트를 오른쪽 부터 확인하면서 목표로 하는 개수인 target 만큼 ')' 를 선택해 그 위치를 delete_list에 담아준다.

  + 종료조건 : 목표 개수만큼 삭제할 ')'를 선택했을 때 / 모든 ')' 조합을 찾았을 때
    + left(delete_list) 를 호출한다.
    
  + 오른쪽 부터 확인하면서 ')' 가 있으면 그 ')' 위치를 delete_list 에 넣어주고 right를 다시 호출한다.
  
2. `left(delete_list):` : 선택된 ')'에 해당하는 '(' 선택해주는 함수 / stack 사용

  + new_delete_list : 삭제될 ')' 인덱스를 담고 있는 delete_list 와 헷갈리지 않도록 삭제될 '(' 인덱스를 담아주는 리스트

  + 스택에 ')'를 만나기 전까지 모든 문자를 담아준다.
  
  + ')'를 만나면 이전 '(' 까지 모든 문자를 pop 해준다.
    + 이 때, ')'가 삭제되기로 선택된 문자라면(delete_list에 있는 ')'라면)
      + new_delete_list에 현재 stack의 길이를 append 해준다 -> 왜? 현재 stack 의 길이가 곧 '(' 위치 인덱스와 동일하기 때문에
    + 내 코드는 stack 의 길이를 곧 인덱스로 쓰기 때문에 인덱스 위치가 변하면 안되므로 pop 된 횟수를 저장하고 있다가 위의 동작이 끝나면 pop 된 횟수 만큼 0을 append 해줬다.(인덱스만 유지시켜주면 돼서 아무 괄호를 제외한 아무 문자나 넣어주면 됨)
    
  + 문자열 전체 탐색이 끝났으면, delete_list 와 new_delete_list 를 합친다.
  
  + 위의 delete_list 에 있는 인덱스 제거한 문자열을 만들어서 new_string_list에 담아준다.
  
 3. ` main ` 
 
  + 쌍이 맞지 않는 문자열일 수 있으므로, ')' 괄호와 '(' 괄호의 횟수를 비교해서 더 작은 괄호 개수 check_num을 찾아준다.
  
  + 1부터 check_num 까지 숫자를 target 으로 하는 right 를 호출한다.
  
  + 위의 과정이 모두 끝나고 new_string_list 에 담겨있는 리스트를 중복 제거하고, sort 해서 출력한다.
  
## Review

리스트를 string 으로 반환하기

``` python
''.join(list)
```

