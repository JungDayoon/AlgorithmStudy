# [Programmers]/[42579] 베스트앨범

## *- Hash(Map) -*

```java
Map<String, Integer> playSum; // 각 장르별 재생횟수 합
Map<String, PriorityQueue<Element>> playlist; // 장르 내의 노래 순서 (문제조건에 따라)
```

* `class Element`는 노래의 고유번호 (`int num`)과 그 노래의 재생횟수 (`int plays`)를 가진다.
  * 또, 재생횟수로 내림차순, 고유번호로 오름차순으로 정렬되도록 설정하였다.

1. 배열`genres`과 `plays`를 스캔하면서 `playSum`과 `playlist`를 완성시킨다.

2. `playSum`을 재생횟수 합에 따라 내림차순 정렬한다.

   > **:star: Java - Map Value기준 정렬**
   >
   > * `Map.Entry`를 리스트로 가져와 value값으로 정렬
   >
   >   ```java
   >   List<Map.Entry<String, Integer>> entries = new LinkedList<>(playSum.entrySet());
   >   Collections.sort(entries, (o1, o2) -> o2.getValue() - o1.getValue()); // value로 내림차순 정렬
   >   ```

3. `playSum`을 정렬한 리스트 `entries`를 스캔하면서 각 장르의 노래 중 많이 재생된 노래 2개씩의 고유 번호를 결과 리스트에 추가한다.

   * `entries`는 장르별 재생횟수 합으로 내림차순 정렬되어 있으므로 그 순서가 많이 재생된 장르순서이다.
   * `playlist`의 value는 `PriorityQueue`로 장르 내 많이 재생된 노래순으로, 재생횟수가 같다면 고유번호 오름차순으로 정렬되어 있으므로 2개씩 `poll()`하여 결과 리스트에 추가한다.
   * 장르별 노래가 1개 있는 장르도 있기 때문에 `pq`의 크기를 확인해주어야한다.

4. 리스트를 배열로 변경한 후 리턴

</br>

## :speaking_head:

`Map`은 활용할 수 있는 메소드가 너무 많은 것 같다 .. 아직 모르는게 많으니까 하나씩 익혀두도록 하자 

