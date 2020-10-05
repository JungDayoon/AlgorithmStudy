# [SWEA]/[2382] 미생물 격리

## - Simulation -

* `class Micro`: 미생물군집의 세로 위치, 가로 위치, 미생물 수, 이동방향을 저장하기 위함.

* `Micro[] com = new Micro[K]`:  K개의 미생물 군집

* *in `main`,*

  :one: 모든 군집을 이동방향(`com[i].dir`)에 따라 이동 시킨후,  약품이 칠해진 곳일때

  ​	` if(com[i].x==0 || com[i].x==(N-1) || com[i].y==0 || com[i].y==(N-1))`

  ​	1) 군집 내 미생물이 1개라면 해당 군집 없앰. 

  ​			(+ 존재하지 않는 군집은 x좌표를 -1로 지정함. => `com[i].x = -1`)

  ​	2) 2개 이상이라면 미생물 수를 반으로 줄이고, 이동방향을 반대로 바꿔준다.

  ```java
   if(com[i].dir%2 == 1)   com[i].dir++; //dir=1,3
   else   com[i].dir--; //dir=2,4
  ```

  :two:존재하는 군집만 다시 반복문으로 돌면서 같은 위치에 있는 군집의 `index`를 `ArrayList<Integer> tmp`에 추가하면서 그 군집들의 미생물 수를 모두 더해줌. (`int mCntSum`: 해당 군집들의 미생물 수의 합) 

  ​	같은 위치에 2개 이상의 군집이 있다면(`if(tmp.size() != 0)`)  그 군집끼리 미생물 수(`mCnt`)를 비교해 

  ​	가장 많은 미생물을 가진 군집의 미생물 수를 `mCntSum`으로 바꿔주고 

  ​	나머지 군집은 없앤다(`x`좌표를 `-1`로).

  ​	(:heavy_plus_sign:처음엔 이중 `for`문을 돌면서 두 군집만 비교해 가장 많은 미생물을 가진 군집을 찾았는데, 그렇게 되면 	가장 큰 미생물 수라도 앞에서 합쳐진 미생물 수보다 작다면 그 군집은 찾을 수 없다. => 같은 위치에 존재

  ​	하는 군집은 모두 서로 비교해주어야 함.)

  ```java
  if(tmp.size() != 0) {
      int maxmCnt = com[i].mCnt;
  	int maxidx = i;
  	for(int j : tmp) {
  		if(maxmCnt < com[j].mCnt) {
  			com[maxidx].x = -1;
  			maxmCnt = com[j].mCnt;
  			maxidx = j;
  		}else {
  			com[j].x = -1;
  		}
  	}
  	com[maxidx].mCnt = mCntSum;
  }
  ```

  :three: M시간 동안 :one:, :two:의 과정을 반복한 후, 존재하는 군집(`if(com[i].x != -1)`)의 미생물 수를 모두 더해 남아있는 미생물 수를 구한다.

## :speaking_head:

+) `ArrayList`: 데이터 검색에 유리. & 대량의 자료 추가/삭제 불리.

+) `LinkedList`: 데이터 추가/삭제 유리. & 데이터 검색에는 불리.

* 처음에 테스트케이스 10개만 정답이여서 한참 틀린부분 찾았는데 알고보니 테스트케이스 수 T가 아닌 10까지만 코드를 실행하게 ` for`문을 설정했다.. 다시 이런 실수 하지 않기ㅠ!!

* '시간 초과' 뜰 때는 `LinkedList`대신 `ArrayList`써보기.

  또 이중`for`문 돌 때, 필요하지 않은 `index`는 두 번째 `for`문으로 <u>들어가기 "전"에</u> 조건 걸어서 `continue`해주기!!!

  