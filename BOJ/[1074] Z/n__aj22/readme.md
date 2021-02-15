# 백준 1074 : Z

## Algorithm

분할정복, 재귀

## Description
**함수설명**
+ `find(nown, startnum, nowr, nowc)` : 
    + 현재 확인할 공간의 가로, 세로 길이인 nown을 기준으로, 4등분 한다. 

    **half_n = nown/2**
    <img src="https://user-images.githubusercontent.com/33089715/107924051-b3b9e400-6fb5-11eb-9bd9-5f3721797d83.png" width="350">

    1. 1번 공간인 경우
        ```python
        if(0<=nowr<half_n and 0<=nowc<half_n):
            ##1번
            return find(half_n, startnum, nowr, nowc)
        ```
    2. 2번 공간인 경우
        ```python
        elif(0<=nowr<half_n and half_n<=nowc<nown):
            ##2번
            return find(half_n, startnum+half_n*half_n, nowr, nowc-half_n)
        ```
    3. 3번 공간인 경우
        ```python
        elif(half_n<=nowr<nown and 0<=nowc<half_n):
            ##3번
            return find(half_n, startnum+half_n*half_n*2, nowr-half_n, nowc)
        ```
    4. 4번 공간인 경우
        ```python
        else:
            ##4번 
            return find(half_n, startnum+half_n*half_n*3, nowr-half_n, nowc-half_n)
        ```
    + 함수를 호출할 때는, 새로운 공간 nown*nown 에서의 위치로 변환하기 위해 half_n 만큼 빼주는 경우도 있다.


## Review

빨리 풀어냈다!! 

