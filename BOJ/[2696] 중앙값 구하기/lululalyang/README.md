# [BOJ]/[2696] 중앙값 구하기

## *- PriorityQueue -*

* `ArrayList<Integer> list` : 수열

* `int M ` : 수열의 크기

  => 중앙값의 개수는 `M/2 + 1`이 된다.

### solution

`ArrayList<Integer> solution(int M, ArrayList<Integer> list)`

: 중앙값 수열에 해당하는 리스트를 리턴

* `ArrayList<Integer> minlist` : 리턴 리스트
* `PriorityQueue<Integer> pq` : 우선순위 큐를 사용

* `0`번부터 `M-1`번 인덱스의 원소를 스캔하면서,

  * 우선 `pq`에 원소를 추가한다.

  * 그리고 홀수번째 원소라면,

    * 만약 첫 번째 원소라면, 

      그냥 `minlist`에 추가. (원소 하나 중 중앙값은 자기 자신)

    * 그렇지 않다면,

      1. `i/2`개만큼 `pq`에서 뽑아낸다. => 뽑아낸 원소는 따로 리스트에 저장

      2. 뽑아낸 후 `pq`의 top에 있는 원소가 현재의 중앙값. => `minlist`에 추가한다.

      3. 뽑아낸 원소를 다시 `pq`에 추가해준다.

* `minlist`리턴

