# [Programmers]/[42627] 디스크 컨트롤러

## *- Heap -* 

> 스케줄링 알고리즘 중 SJF(Shortest Job First)와 같이 Ready Queue와 Job Queue를 두어 구현한다.

```java
PriorityQueue<Ready> ready = new PriorityQueue<>(); // 요청시간 기준 오름차순 정렬
PriorityQueue<Job> job = new PriorityQueue<>(); // 소요시간 기준 오름차순 정렬

int now = 0; // 현재 시간
int ans = 0; // 각 작업의 요청부터 종료까지 걸린 시간의 총합
```

1. 배열 `jobs`의 작업을 모두 `ready` 큐에 추가한다.

2. `ready` 큐가 빌 때까지 아래 과정을 수행한다.

   1. `ready.peek()`의 요청시간이 현재 시간 `now`보다 클 때까지 `ready`의 작업을 `job`큐로 옮겨준다. (`now`시간 또는 그 전에 요청된 작업을 옮겨줌)

   2. `job`이 비었다면, `now`를 `+1`해주고 다시 위 과정을 진행한다.

   3. `job`이 비어있지 않다면, `job`에서 작업을 하나 꺼내(`job.poll()`) 현재 시간 `now`와 결과값 `ans`를 갱신한다.

      ```java
      Job tmp = job.poll();
      now += tmp.jobtime;
      ans += (now - tmp.request);
      ```

3. `ready`큐가 비었다면, `job`큐가 빌 때까지 2번의 3번과정을 진행한다. (`job`큐의 남은 작업을 처리)

4. 평균값인 `ans / cnt`를 리턴.

</br>

## :speaking_head:

`pq`하나만 사용하여서 요청 시간별로 리스트에 저장해둔 후에 현재 시간보다 적은 작업들만 `pq`에 넣고 작업을 처리하는 방식으로 풀었었는데, 계속 런타임에러가 났다 ㅠ

다른 사람의 풀이과정을 참고하였는데 `pq` 2개를  `ready`큐와 `job`큐로 사용해서 해결하였더니 쉽게 풀렸다. `pq` 두 개 쓸 생각 전혀 하지 못했다 ㅠ🙄

