# 백준 2688번 : 줄어들지 않아

## Algorithm

DP

## Description
**Top Down**

`main` : 

+ cache : 65*10 크기의 -1로 초기화된 배열 
    
    + 테스트케이스 전체에 공통으로 사용된다.
    + `cache[i][j]` : 시작이 j인 i 자리 수를 저장한다.
    + 초기화 : cache[1][j] 는 모두 1로, cache[2][j] 는 [10, 9, ... 1] 로 초기화 한다.

+ answer : 각 테스트 케이스 별 정답이 저장된다. 

    + start(0~9) 숫자로 시작하는 n 자리 수를 호출하는 find 함수를 호출한 결과를 모두 더한 값이 저장된다.

    ```python
    answer = 0 
    for i in range(10):
        answer+=find(n, i)
    ```
`find(index, start)` : index는 자릿수, start는 시작 숫자를 의미한다.

+ topdown 방식으로 구현했다.
+ 만약 cache[index][start]에 저장된 값이 -1이 아니면 그 값을 return 한다.
+ 그렇지 않으면 다음과 같이 한다.

    ```python
    num = 0
    for i in range(start, 10):
        num+=find(index-1, i)
    ```
    + 예를들어 find(3, 1)가 호출됐다고 생각하자, find(3, 1)은 3자리 수 중 1로 시작하는 숫자를 찾는 함수이다. 
    + 3자리 수 중 1로 시작하는 숫자는 (1로 시작하는 2자리 수)+(2로 시작하는 2자리 수)+ ... + (9로 시작하는 2자리 수)를 의미한다.(왜냐면 이 두자리 수들 앞에 1 붙이면 된다.)
    + 따라서 find(2, 1)~find(2, 9)를 호출한 결과가 find(3, 1)이 된다.

+ 호출한 결과를 cache에 저장하고 리턴 한다.
## Review

