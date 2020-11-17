# [BOJ]/[10282] 해킹

## *- Dijkstra -*

* `int[] result = new int[N+1]` : 해킹된 컴퓨터로부터 걸리는 시간을 담을 배열

  * 해킹당한 컴퓨터에 해당하는 index는 `0`으로, 나머지는 `Integer.MAX_VALUE`로 초기화.

* `ArrayList<Integer> com` : 해킹된 컴퓨터 번호를 담을 ArrayList

* *우선순위 큐*(`PriorityQueue`)를 사용해 다익스트라를 구현

  * 우선순위 큐에 담을 객체 `Edge`를 만들어 줄 때, `Comparable<Edge>`를 `implements`한다.

    (**어떤 기준으로 우선순위를 결정할지 설정**해준다)

    ```java
    class Edge implements Comparable<Edge> { //Comparable!!!
    	
        ...
    
    	@Override
    	public int compareTo(Edge o) {
    		return Integer.compare(this.w, o.w);
    }
    ```

    > `Integer.compare(a, b)` : a가 크면 1, b가 크면 -1, 같으면 0을 return한다.

  * `pq`에 해킹당한 컴퓨터의 번호를 먼저 `add`

    :arrow_right: 그 후, `pq`가 빌 때까지 아래 과정 반복

    * `pq`에서 요소 하나 꺼냄

      -> 해킹당한 컴퓨터에서 해당 요소를 거쳐서 가는 시간이 기존의 `result`값보다 작다면 그 값으로 `result`값을 갱신한다.

      -> 갱신된 컴퓨터는 해킹된 컴퓨터로 `com`에 `.add()`해준다.

      -> 해당 요소와 인접한 노드를 모두 탐색한 후에 해당 요소의 방문여부를 `true`로 바꿔준다.

* `com.size()`가 *총 감염된 컴퓨터 수*

* *마지막 컴퓨터가 감염되기까지 걸리는 시간* 은 `com`을 `result`값을 기준으로 내림차순으로 정렬한 후 0번째 `com`의 `result`값으로 출력해주었다. (`com`에 속하는 컴퓨터가 해킹되는데 걸리는 시간 중 가장 큰 값이니까!)

  >**:star:*​사용자 설정 기준으로 리스트 정렬***
  >
  >```java
  >Collections.sort(com, new Comparator<Integer>() {
  >
  >	@Override
  >	public int compare(Integer o1, Integer o2) {
  >		if(result[o1] < result[o2])
  >			return 1;
  >		else if(result[o1] > result[o2])
  >			return -1;
  >		else
  >			return 0;
  >	}		
  >});
  >```
  >
  >* `com`은 `Integer`형의 값을 가지고 있는 ArrayList
  >* 이를 시간 값을 담고 있는 `result[]`의 값을 기준으로 정렬하려 한다
  >* 지금은 내림차순 정렬이므로 `o1<o2`면 return 1, `o1>o2`면 return -1, `o1==o2`면 return 0
  >* 만약 오름차순 정렬이라면 앞 쪽의 객체가 크면 1, 뒤 쪽 객체가 크면 -1, 같으면 0을 return
