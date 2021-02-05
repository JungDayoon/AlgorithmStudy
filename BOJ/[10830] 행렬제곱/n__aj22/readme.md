# 백준 11000 강의실 배정

## Algorithm 

분할정복

분할정복을 이용한 거듭제곱

## Description

**logic**

***빠른 거듭제곱 알고리즘 사용 - O(logN)***

1. N이 홀수이면 A<sup>n</sup>을 A * A<sup>n-1</sup>  로 바꾼 후 A를 빼내서 곱해준다.
2. N이 짝수이면 A<sup>n</sup>을 (A<sup>2</sup>)<sup>1/2</sup>로 바꾼다.
3. N이 0이면 종료


예를 들어 2<sup>5</sup>을 구해보자
ans = 1
1. 2<sup>5</sup> = 2 * 2<sup>4</sup> , ans = 1 * 2 = 2
2. 2<sup>4</sup> = (2<sup>2</sup>)<sup>2</sup> = 4<sup>2</sup>
3. 4<sup>2</sup> = 16<sup>1</sup>
4. 16<sup>1</sup> = 16 * 16<sup>0</sup> , ans = 2 * 16 = 32


## Review

어렵다!! ㅜㅜ
