# [BOJ]/[1446] 지름길

## *- Dijsktra -*

* `TreeMap<Integer, ArrayList<Edge>> adj`

  : 인접리스트로 목적지까지의 위치를 노드로 다 만들 수 없어서 맵을 사용하였다. 

  <지름길 노드번호, 그에 연결된 Edge의 ArrayList>의 쌍

  또, 지름길 노드번호로 오름차순 정렬해야해서 `TreeMap`을 사용하였다.

  > `TreeMap`은 key의 값대로 정렬되어 저장된다.

* 만약 `adj`에 출발지인 `0`을 key값으로 갖는 노드가 없다면 추가해주었다.

* 지름길로 가지 않고도 이동할 수 있으니까 지름길 노드 사이에 edge를 하나씩 넣어주었다.

  * weight는 두 노드사이의 거리 차

* 그리고, `Edge`를 담는 `PriorityQueue`를 사용해 *다익스트라* 를 구현하였다.

  * 이때, 각 지름길node와 edge는 `TreeMap`으로 저장되어있기 때문에 index로 접근 어렵다

    -> *방문여부* 와 *해당 노드까지의 최소거리* 도 각각의 key를 이용해 `TreeMap`으로 만들어주었다.

    ```java
    TreeMap<Integer, Boolean> visited = new TreeMap<>(); // 노드 방문 여부
    for(Integer key : keys2) { 
    	visited.put(key, false); //다 fasle로 초기화
    }
    		
    TreeMap<Integer, Integer> result = new TreeMap<>(); // 0에서의 최소 거리값
    for(Integer key : keys2) {
    	result.put(key, Integer.MAX_VALUE); //모두 최대값으로 초기화
    }
    result.put(0, 0); //출발지는 0으로 초기화
    ```

* 출발지 0부터 나머지 모든 노드까지의 최단거리를 구한 후, 고속도로의 길이를 key값으로 갖는 노드가 있다면 그 노드의 최단거리를 출력.

  그렇지 않다면, 고속도로의 길이보다 작은 노드  중 가장 가까운 노드를 탐색해 ( 해당 노드의 최단거리 + 해당 노드에서 목적지까지의 거리 ) 를 출력.



## :speaking_head:

adjacency list의 index가 노드의 번호인 경우의 다익스트라 문제밖에 풀어본 적이 없어서 처음에 목적지까지의 노드를 다 만들어야하나 했는데 다윤이의 :star2:힌트:star2: 로 `TreeMap`을 써서 풀었다!

다양한 object가 쓰여서 좀 힘들었당 ..

