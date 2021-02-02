# [BOJ]/[11000] 강의실 배정

## *- Priority Queue -*

**Main**

* `int[][] ST = new int[N][2]`

  * index `0` : Si
  * index `1` : Ti

* `ST`를 시작 시간 `Si`를 기준으로 오름차순 정렬한다.

  * 만약 시작 시간이 같다면 끝나는 시간 `Ti`로 오름차순 정렬한다.

  > **:star: Java** : 2차원 배열 정렬하기 => `Comparator`인터페이스 사용
  >
  > ```java
  > Arrays.sort(arr, new Comparator<int[]>() {
  > 	@Override
  > 	public int compare(int[] o1, int[] o2) {
  > 		return o1[0] - o2[0];
  > 	}
  > }); // 0번째 index로 오름차순 정렬
  > ```
  >
  > :heavy_plus_sign: 해당 문제에 적용
  >
  > ```java
  > Arrays.sort(ST, new Comparator<int[]>() {
  > 	@Override
  > 	public int compare(int[] o1, int[] o2) {
  > 		if(o1[0] != o2[0])
  > 			return o1[0] - o2[0];
  > 		else
  > 			return o1[1] - o2[1];
  > 	}
  > }); // 0번째 index로 오름차순 정렬하고, 0번째 값이 같다면 1번째  index로 오름차순 정렬
  > ```

</br>

**Func**

* `int ComputeClassCnt(int[][] ST, int N)`

  * `PriorityQueue<Integer> pq`

    : 우선순위 큐. 사용하고 있는 교실 수업의 끝나는 시간을 저장한다.

  </br>

  1. `pq`에 `SP`의 `0`번째 수업의 끝나는 시간을 `add()`한다

     * `SP`를 정렬해뒀으므로 가장 먼저 시작하는 수업

  2. `1`번째 수업부터 스캔하면서 <u>해당 수업의 시작시간</u>이 <u>`pq`의 첫 번째 값</u>보다 

     * **작으면**

       => 현재 사용하고 있는 교실에서 수업할 수 없다는 것.

       => 교실을 추가한다 = `pq`에 해당 수업의 종료시간을 `add()`

     * **크거나 같으면**

       => 현재 사용하고 있는 교실에서 수업이 가능하다는 것.

       => `pq`의 첫 번째 값을 제거하고(`remove()`), 해당 수업의 종료시간을 `add()`

  * `pq`의 크기가 필요한 최소한의 강의실 개수 

    => `return pq.size()`

## :speaking_head:

* 우선순위 큐를 사용한다는 것을 참고하니까 나름 금방 풀었다 !