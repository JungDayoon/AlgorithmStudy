# [BOJ]/[17825] 주사위 윷놀이

## *- backtracking -*

**Main**

* `Horse[] h = new Horse[4]` 말 4개의 위치 초기화 (`0`, `0`)으로

  > **:star: Java**
  >
  > Object를 clone 가능하게 하기 위해서 `Cloneable`을 `implements`해준 후, `clone()` 메소드를 오버라이드해줘야한다.
  >
  > 또, 이 객체의 `clone()`을 사용하는 메소드에서는 `throws CloneNotSupportedException`로 예외처리를 해주어야 한다.
  >
  > ```java
  > class Horse implements Cloneable{
  > 	...
  > 		
  > 	@Override
  > 	protected Object clone() throws CloneNotSupportedException {
  > 		return super.clone();
  > 	}
  > }
  > ```

* `initializeMap()`을 통해 윷놀이 판을 초기화
* `CalculScore(h, 0, 0)`을 호출하여 최대 score를 구한다.

**Func**

* `void initializeMap(ArrayList[] map)` : 윷놀이 판을 초기화한다.
  * 총 5개의 길로 나눈다.
  * `map[0]`은 `2->4->6->8->10->12->...->18->20->22->...->28->30->32->...->40`
  * `map[1]`은 `10->13->16->19`
  * `map[2]`는 `20->22->24`
  * `map[3]`은 `30->28->27->26`
  * `map[4]`는 `25->30->25`

</br>

* `void CalculScore(Horse[] h, int score, int turn) throws CloneNotSupportedException`

  : 백트래킹을 통해 모든 말을 각각 `trun`의 주사위 값만큼 이동시켜 최대 `Score`를 구한다.

  * 4개의 말 각각을 해당하는 `trun`에 대한 주사위 값만큼 `moveHorse()`를 통해 이동시켜주고 그에 맞게 `score`를 갱신한다. 

    * 도착한 말은 선택하지 않는다
    * 이동이 가능할 경우에만 `score`값 갱신.
    * 도착했을 때에도 갱신하지 않음

    > `moveHorse()`를 호출하기 전에 이전 `trun`까지의 말의 위치 정보인 `h`를 `clone()`한 `tmph`를 만들어 호출한다
    >
    > (`h`를 그대로 인자로 호출하게되면, `h`값이 계속 바뀌어서 이전 말의 이동이 저장되기 때문에 각 말마다 옮겨주는 처리를 하지 못한다. )

  * 그 후, 갱신된 `score`값과 `trun+1`을 이용해 다시 본 메소드를 호출한다.

  * 호출 후에는 갱신된 `score`값을 다시 원래대로 되돌려놓는다.

  * `trun==10`이라면 그 때의 `score`값과 `MaxScore`값 중 큰 값으로 `MaxScore`값을 갱신한다

</br>

* `boolean moveHorse(Horse[] h, int now, int diceNum)`

  : 현재 움직이는 말(`now`)을 주사위 수 `diceNum`만큼 이동시킨다.

  * 이동할 위치에 다른 말이 있다면 이동할 수 없기 때문에 `h`값을 바로 변경하지 않고, 이동할 위치를 담을 임의의 배열 `chk[2]`를 사용한다.

  * `map[]`의 `index`에 따라서 따로 처리해주었다

    > :star: ​이동된 `index`처리 잘 해주기 !!!! 
    >
    > 같은 위치이지만 `index`처리를 잘 못해주면 중복위치를 확인하지 못해 오류가 생긴다.

  * 옮긴 후에 도착하지 않았다면 `checkDup()`을 이용해서 다른 말이 그 위치에 존재하는지 확인한다.

    * 존재한다면 `false`를 return

  * 옮길 수 있다면, `chk`의 값을 `h[now]`로 옮겨주고 `true`를 return

</br>

* `boolean checkDup(Horse[] h, int now, int[] chk)`
  * `now`가 아닌 말을 탐색하면서 `chk`의 값과 같은 위치에 있는 `h[i]`가 있다면 `false`를 return한다. 그렇지 않으면 `true`를 return

</br>

## :speaking_head:

* 처음에는 조합으로 풀려고 했었는데, 너무 복잡해져서 백트래킹방식으로 풀었다
* 초반에 `map`의 `1`, `2`, `3`, `4`에 도착지점까지 초기화했는데, 그렇게 하니까 빠트리는 처리할 부분이 많아져서 같은 위치인데도 같은 위치로 잡아내지 못해서 틀렸었다. 
* 확 도착지점 빼고 초기화하고, `moveHorse()`도 그에 맞춰서 바꿔주니까 맞았다! 너무 경우가 많은(?) 데이터라 디버깅하기 힘들어서 어려웠다 .. 처음부터 잘 생각해서 풀기 !!!

