# [BOJ]/[1766] 문제집

## - Topological Sort, 우선순위 큐 -

> < Queue사용 위상 정렬 >
>
> 1) 진입차수가 0인 node를 Queue에 넣는다.
>
> 2) Queue에서 node를 꺼내 그와 연결된 모든 edge 삭제
>
> 3) edge삭제 이후 진입차수가 0인 node를 Queue에 넣는다.
>
> 4) Queue가 빌 때까지 반복
>
> ​		모든 원소를 방문하기 전 Queue가 빈다면 Cycle 존재.
>
> ​		모든 원소를 방문했다면 Queue에서 꺼낸 순서가 위상정렬의 결과 !

* 먼저 풀어야 하는 문제 순서가 있으므로 위상정렬을 사용한다.

  하지만 **"*조건 3. 가능하면 쉬운 문제부터 풀어야 한다.* "** 가 있으므로 일반 큐가 아닌 우선순위 큐를 사용해야한다. 

> **우선순위 큐**: 우선순위의 개념을 큐에 도입한 자료구조.
>
> ​						데이터들이 우선순위를 가지고 있고, 우선순위가 높은 데이터가 먼저 나간다.

> :star: **JAVA 우선순위 큐**
>
> ```java
> PriorityQueue<Integer> pq = new PriorityQueue<>();
> ```
>
> 기본적으로 숫자가 낮은 것이 우선순위가 높은 것이다. 
>
> 일반 큐와 같이 `add()`, `peek()`, `poll()`등의 메소드를 사용할 수 있다.
>
> +) 우선순위 변경 
>
> ```java
> PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
> ```
>
>  ( `Integer`의 경우 `Collections.reverseOrder()`을 사용.
>
>  객체의 경우 `Compartor`클래스나 `Comparable`인터페이스를 이용해야한다. )

</br>

## :speaking_head:

Queue를 사용한 위상정렬 방법을 익혀두니 잘 적용할 수 있었다.

또, 이 문제를 통해서 우선순위 큐 `PriorityQueue`를 사용해보고 공부할 수 있었다 !