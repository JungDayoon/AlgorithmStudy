# Programmers 60061번 : 기둥과 보 설치

## Algorithm

Simulation

## Description

1. **`make_column(answer, x, y)`** : 기둥 설치시 (x, y, 0, 1)

    ```
    기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    ```

    + 바닥 위에 있으면 True 반환
        ```python
        if(y == 0): #바닥 위 
            return True
        ```
    + 기둥 왼쪽에 보가 있으면 True 반환
        ```python
        if([x-1, y, 1] in answer): #왼쪽 보와 연결 
            return True
        ```
    + 기둥 오른쪽에 보가 있으면 True 반환
        ```python
        if([x, y, 1] in answer): #오른쪽 보와 연결
            return True
        ```
    + 아래에 기둥이 있으면 True 반환
        ```python
        if([x, y-1, 0] in answer):#다른 기둥 위 
            return True
        ```
2. **`make_beam(answer, x, y)`** : 보 설치시(x, y, 1, 1)

    ```
    보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    ```

    + 왼쪽 끝에 기둥이 있으면 True 반환
        ```python
        if([x, y-1, 0] in answer): #왼쪽이 기둥 위
            return True
        ```
    + 오른쪽 끝에 기둥이 있으면 True 반환
        ```python
        if([x+1, y-1, 0] in answer): #오른쪽이 기둥 위
            return True
        ```
    + 양 끝부분에 다른 보가 있으면 True 반환
        ```python
        if([x-1, y, 1] in answer and [x+1, y, 1] in answer):
            return True
        ```
3. 기둥, 보 삭제시(x, y, a, 0)

+ 해당 기둥 혹은 보를 삭제하고 설치된 구조물 전체에 대해 위 두 함수를 이용해 가능 여부를 확인한다.


## Review

번거로운 조건은 입력 자체에서 걸러줬기 때문에 구현하기 편했다.
