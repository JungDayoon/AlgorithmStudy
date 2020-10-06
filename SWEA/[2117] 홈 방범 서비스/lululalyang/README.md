# [SWEA]/[2117] 홈 방범 서비스

## -Simulation-

* `int housecnt`: 해당 위치를 홈방범 서비스 범위의 중앙이라 했을 때, 서비스를 받을 수 있는 집의 개수 

* `int houseMaxcnt`: 보안회사가 손해를 보지 않고 서비스 가능한 최대 집 수

  ```java
  for(int K=1; K<=N+1; K++) {
  	int cost = K*K + (K-1)*(K-1); //운영 비용
  				
  	for(int i=0; i<N; i++) {
  		for(int j=0; j<N; j++) {
  			housecnt = check(K, i, j, house);
  			if((housecnt*M - cost) >= 0) { //손해가 아니라면
  				houseMaxcnt = Math.max(houseMaxcnt, housecnt);
  			}
  		}	
      }
  }
  ```

  :heavy_plus_sign:) 서비스 영역의 크기 `K`는 1부터 ***N+1***까지 

  예를 들어, `N=4`이고 `K=4`이면 범위를 아무리 움직여도 모든 `map`을 덮을 수 없다.  <img src="C:\Users\dmlfi\AppData\Roaming\Typora\typora-user-images\image-20201006152744577.png" alt="image-20201006152744577" style="zoom: 50%;" />

* `static int check(int K, int i, int j, ArrayList<int[]> house){}`

  :  (i, j)가 서비스 영역의 중앙이라 할 때, 그 영역에 속하는 집 개수 `return`

  ​	해당 좌표와 각 집의 좌표의 거리를 구해 영역에 속하는지 확인.

  ​	`if((Math.abs(i-h[0]) + Math.abs(j-h[1])) < K) `



## :speaking_head:

* 마름모 영역 안에 집이 속하는 지 확인하는 방법을 아무리 생각해도 모르겠어서 친구들 readme를 참고했다

  이번에 이렇게 풀었으니까 다음부턴 비슷한 문제가 있으면 적용해서 풀 수 있도록 해야겠다! 