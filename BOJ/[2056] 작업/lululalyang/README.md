# [BOJ]/[2056] 작업

## -Topological Sort-

* `int[] into = new int[N+1]`: 각 node의 진입 차수
* `int[] work = new int[N+1]`: 각 작업 수행시간
* `int[] time = new int[N+1]`: 선행 관계를 따졌을 시 각 작업수행이 끝나는 시간



* 진입차수가 0인 노드를 queue에 넣고, 노드 하나씩 빼면서

  그 작업보다 선행되어야 하는 작업의 수행시간과 해당 작업의 수행시간을 더한 것이 원래의 수행시간보다 크다면 해당 작업의 수행완료 시간을 바꿔준다.

  ```java
  while(q.size() != 0) {
  	int node = q.poll();
  	visited[node] = true;
  			
  	for(int i : adj[node]) {
  		if(time[i] < time[node] + work[i]) {
  			time[i] = time[node] + work[i];
  		}
  		into[i]--;
  		if(into[i] == 0)	q.add(i);
  	}
  }
  ```

* 작업 수행완료(`time[]`)시간 중 가장 큰 값이 모든 작업을 수행했을 시의 작업완료 시간

  => `int max`

---



> **:heavy_plus_sign:) Topological sort - Queue 사용**
>
>  * 각 정점에 '진입하는 노드의 수' 구해놓고 시작
>
>  * 진입 차수가 0인 정점을 Queue에 넣는다.
>
>  * Queue에서 node꺼내 그 node와 연결된 모든 edge 삭제
>
>  * edge 삭제 후, 진입차수가 0인 node를 Queue에 넣는다.
>
>    => Queue가 빌 때 까지
>
>    ​	1) 모든 원소를 방문하기 전에 Queue가 비면 Cycle 존재.
>
>    ​	2) 모든 원소 방문하면 -> Queue에서 꺼낸 순서가 위상정렬 결과.
>

## :speaking_head:

* Topological sort 알고리즘이 익숙하지 않아 '[BOJ]/[2252]줄세우기'를 먼저 풀어본 후 풀었다.

  한번 풀고 풀어도 풀기 어려웠다 .. 관련 문제를 많이 풀어봐야겠다..!