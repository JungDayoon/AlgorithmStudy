# [BOJ]/[19238] 스타트 택시

## *- Simulation, BFS -*

**전역 변수**

```java
int fuel; // 택시에 남은 연료 양
int[][] map; // 지도의 정보
Passenger[] pass; // 승객의 출발지, 목적지 정보
```

* `map` : 벽은 `-1`로 저장한다
* `pass` : 승객 정보를 인덱스 `1`번부터 입력받아 저장하면서, 승객의 **출발 지점**에 해당하는 `map`좌표에 승객 인덱스를 저장한다.

## solution

**변수**

`int tx, ty` : 택시의 현재 위치

**로직**

아래 과정을 `M`번 반복한다.

1. 택시에서 가장 가까운 승객 찾기 - `int FindNearestPass(int tx, int ty, int N)`

   * 우선순위 큐를 이용한 BFS

     ```java
     PriorityQueue<Element> q;
     ```

     `class Element`는 거리순으로 오름차순, 거리가 같다면 행 번호로 오름차순, 행 번호가 같다면 열 번호로 오름차순 정렬된다.

   * 가장 가까운 승객으로 이동하는 데에 연료를 다 쓰거나, 갈 수 있는 승객이 없다면 `-1`를 리턴
   * 갈 수 있는 승객이 있다면 이동한 거리만큼 연료 `fuel`에서 빼고 그 승객의 인덱스를 리턴

2. 1번 과정에서 `-1`을 리턴받았다면 `M`명의 승객 전체를 데려다 줄 수 없는 경우로 `-1`를 리턴하며 반복 종료.

   그렇지 않다면, 리턴받은 번호의 승객의 위치로 택시를 이동시키고, `map`상에서 승객을 없앤다.

3. 현재 택시의 위치에서 태운 승객의 도착지까지 데려갈 수 있는지 확인한다. - 

   `boolean CanGoToDest(int tx, int ty, int ex, int ey, int N)`

   * Queue를 이용한 BFS
   * 목적지까지 갈 수 있다면 이동한만큼의 거리의 2배만큼 연료가 충전되므로 `fuel`에 이동거리만큼 더해준다. `true`리턴
   * 목적지 까지 갈 수 없다면 `false`리턴

4. 3번 과정에서 `false`를 리턴받았다면, `M`명의 승객 전체를 데려다 줄 수 없는 경우로 `-1`을 리턴하며 반복 종료.

   그렇지 않다면, 택시를 데려다 준 승객의 도착지로 이동시키고 다음 승객을 이동시키기 위해 다시 1번과정을 진행한다.

위 반복을 끝낸 후, `M`명의 승객 전체를 데려다 줬다면 그 때의 남은 연료 `fuel`을 리턴. 그렇지 않으면 `-1`을 리턴.

</br>

## :speaking_head:

작년에 알고리즘 스터디 할 때 도전했던 문제

* 15번의 *틀렸습니다* 와 5번의 *시간초과* 로 풀 지 못 한 문제 이 다 ㅜㅜ

그래도 이번에 다시 풀었을 땐 금방 풀 수 있었다 !! 성장했다 !!



