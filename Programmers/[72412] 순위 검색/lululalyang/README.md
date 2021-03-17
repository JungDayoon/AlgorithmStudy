# [Programmers]/[72412]  순위 검색

## *- Binary Search, Map -*

#### solution

* `Map<String, ArrayList<Integer>> map = new HashMap<>()`
  * `String` : 지원자의 정보
  * `ArrayList<Integer>` : 위 정보에 해당되는 여러 지원자의 코테 점수 리스트

1. 지원자의 코테 점수를 제외한 나머지 정보를 하나의 문자열로 만든 후, 그 문자열을 key로, 코테 점수를 value로 가지는 데이터를 `map`에 저장한다.

   * 예를 들어, `"java backend junior pizza 150"`라면 

     <key, value> = <`javabackendjuniorpizza`, `150`> => 1가지

   * 그리고, 위의 정보를 포함하는 모든 경우, 즉 `"-"`를 포함하는 모든 경우를 하나의 문자열로 만들어 그 문자열과 코테 점수로 <key, value>쌍을 만들어 `map`에 저장한다.

     * 예를 들어 위의 지원자라면,

       `"-backendjuniorpizza"`, `"java-junior-"`, `"---pizza"`, `"----"` 등

       => 15가지

   * 한 지원자로 만들 수 있는 정보는 총 16가지

     * 하지만, 이미 `map`에 존재하는 정보라면 그 <key, value>쌍에서 `value`에 해당하는 list에 지원자의 코테점수를 추가한다.

2. `map`에 존재하는 모든 key에 해당하는 value값(list)을 정렬한다. 

   * 이후 이분탐색을 처리하기 위함

     > **:star: Java** - `List` 정렬
     >
     > ```java
     > Collections.sort(List); // Collections.sort() 메소드 사용
     > ```
     >
     > :heavy_plus_sign: 만약, list에 직접 생성한 객체를 추가하는 경우라면,
     >
     > `Comparable`인터페이스에 정의된 `compareTo()`메소드를 오버라이드 해준다.
     >
     > 메소드 안에는 정렬하고자 하는 조건으로 정의해준다.
     >
     > ```java
     > class Data implements Comparable {
     > 	@Override
     >     public int compareTo(Data d){
     >         return this.attr.compareTo(d.attr);
     >     }
     > }
     > ```
     >
     > 

3. 쿼리문을 스캔하면서 `map`에 존재하는 조건이라면 그 조건에 해당하는 코테 점수들로 이분탐색을 진행해 `X`점 이상인 점수의 개수를 찾는다.

   * 이때, 쿼리문도 `map`에 저장한 key처럼 하나의 문자열로 만들어 준 후 `map`에 있는지 확인한다.

   * ***이분탐색*** : list에는 중복되는 점수가 있을 수 있으므로, 값의 중복이 허용되는 리스트에서 해당 성분 중 가장 앞 위치를 찾는 이분탐색 방법을 사용한다.

     >**[중복이 있는 배열에서 성분 중 가장 앞의 위치를 찾는 법]**
     >
     >1. target이 모든 원소에 대해 더 작은 경우 => `0`
     >2. target이 모든 원소에 대해 더 큰 경우 => `list.size()`
     >3. target이 리스트 안에 없는 경우 => target보다 큰 가장 가까운 원소의 인덱스
     >4. target값이 있는데 그 값이 중복되어 있을 경우 => 같은 값 중 가장 작은 인덱스
     >
     >```java
     >int BinarySearch_dup(int[] list, int target){
     >    int left = 0;
     >    int right = list.length() - 1;
     >    int mid;
     >    while(left <= right){
     >        mid = (left + right) / 2;
     >        if(list[mid] < target){
     >            left = mid + 1;
     >        }else { // list[mid] >= target
     >            right = mid - 1;
     >        }
     >    }
     >    return left;
     >}
     >```

     * 위 방법으로 얻은 `X`점 이상인 점수 중 가장 앞 위치를 `list`의 크기에서 뺀 값이 이 조건에 해당하는 지원자 수.

</br>

## :speaking_head:

* 너무 어렵다 ..
  * 처음에는, 각 조건의 종류별로 ArrayList배열(`ArrayList[] lang, job, prev, food`)을 생성해 그 조건에 해당하는 지원자의 index를 추가해준 후, 이분탐색을 통해 몇명인지 찾아주었다.
    * 정확성은 다 맞았지만, 효율성은 하나도 뚫지 못했다 ..
  * 결국 방법을 참고하였다 !!
    * 위의 1번 과정을 전혀 생각하지 못했다 ㅠ 1번과 2번이 가장 중요한 과정인 것 같다.
      * 만약 쿼리마다 그 key에 해당하는 value list를 정렬해준다면 또 시간초과가 날 것이다

