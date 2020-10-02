# SWEA 2117번 : 홈 방범 서비스

## Algorithm

Simulation

## Description

1. main
  + K 를 증가시키면서 while 반복문 
  + need_money : K 마다 운영 비용을 계산해준 값
  + need_home : 이익을 남기기 위해 필요한 집의 개수
  + while 문의 종료조건은 필요한 집의 개수 보다 현재 존재하는 집의 개수가 작을 때 (이익을 남겨야 하는 조건을 만족하지 못하기 때문)
  
  + K 마다 (0, 0) 부터 (N-1, N-1) 좌표를 돌면서 해당 범위에 포함되는 집의 개수를 return 해주는 find_home 함수 호출
  + 집의 최대 개수를 찾아준다.
  
2. find_home(i, j, K, N, home) : 좌표 i, j 기준 K 범위 안에 속하는 집의 개수를 return 해준다.
  + 집의 좌표가 마름모 안에 속하는지 확인하는 방법은 거리를 고려해주면 된다.
  
      ``` python
      if(abs(a-i)+abs(b-j)<K):#마름모 범위 안에 속함
      ```


## Review

좀 더 시간이 덜 걸리게 짜고 싶었는데 .. 

그래도 한번에 패스 해서 만족한다!
  
