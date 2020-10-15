# [BOJ]/[1516] 게임 개발

## -Topological Sort, DP-

* `static int[] doneTimes`: 먼저 지어야하는 건물의 완성을 생각해줬을 때의 각 건물의 완성시간, `BuildingT[]`의 값으로 초기화한다.
* `int[] BuildingT`: 각 건물 하나를 건축하는데 걸리는 시간
* `int[] into`: 진입차수
* ***in `Main`***
  * 진입차수(`into[]`)가 `0`인 `node`를 `Queue<Integer> q`에 넣어주고, `q`에 넣어준 `node`는 `into[i]=-1`로 설정해 표시해준다.
  * 그 후, `topologicalSort()` 호출.

* `void topologicalSort(int[] BuildingT, int[] into, ArrayList<Integer> q, Queue<Integer> q)`

  * `q.size()`가 `0`일 때까지, 아래 과정 반복한다.

    * `q`에서 원소(`poll`) 꺼낸 후,

    * `poll`을 선행으로 하는 건물의 완성시간(`doneTimes[]`)을 확인

      그 시간이 `poll`의 완성시간(`doneTimes[]`)에 현재 건물의 건축시간을 더한 값이 더 크면 이 값으로 현재 건물의 완성시간(`doneTimes[]`)를 바꿔준다.

    * 바꿔주면서 `into[]`를 하나씩 줄여준다. (뒤에서 `poll`의 `edge`를 모두 제거하니까)

    * `poll`의 `edge`를 모두 제거한다.

    * 진입차수가 `0`인 `node`를 `q`에 넣어준다.

  ```java
  while(q.size() != 0) {
  	int poll = q.poll();
  			
  	for(Integer node : g[poll]) {
  		if(doneTimes[node] < doneTimes[poll] + BuildingT[node]) {
  			doneTimes[node] = doneTimes[poll] + BuildingT[node];
  		}
  		into[node]--;
  	}
  			
  	g[poll].clear();
  	checkIntoZero(into, q); //진입차수가 0인 node확인 후, q에 넣어주는 메소드
  }
  ```

  > 이때, `doneTimes[node]`의 값이 바뀌지않더라도 진입차수를 `-1`해줘야 한다.
  >
  > 뒤에서 `poll`과 연결된 `edge`를 모두 제거하니까

</br>

## :speaking_head:

* *'[BOJ]/[2056]작업'* 과 비슷한 유형 (위상정렬과 DP가 함께 쓰인다)

* `into[]`값을 바꿔주는 코드의 위치를 잘못둬서 *틀렸습니다* 가 나왔다

  조건문과 값변경 위치 꼼꼼히 봐주기!

