# [12763] 지각하면 안돼

다익스트라
</br>

## 접근법

-  간단한 문제 소개
    > 건물과 건물 사이에 이동하는 데 걸리는 시간과 택시비, 현재 가지고 있는 돈을 알고 있을 때, 분 내에 얼마의 최소 지출로 도착할 수 있는지 출력하는 프로그램


- `cost`와 `times` 두개를 사용해서 각 정점에서의 돈과 시간의 최소값을 저장한다. 
- `cost[there]`의 값이 최소일때만 pq에 넣어줄 뿐만 아니라 `times[there]`의 값이 최소일때도 pq에 넣어줘야한다. 
</br>

## 시퀀스

1. `cost`와 `times`의 src값을 0으로 하고, pq 에도 {src,0,0}을 넣어준다.
2. pq 가 비지 않을때 까지 아래를 반복한다.
3. pq에서 pop()한 정점의 인접한 정점을 검사한다.(현재 정점은 here, 인접한 정점은 there)
    1. `nextC`(현재 돈에서 `there`의 돈을 더한 것)와, `nextT`(현재 시간에서 `there`의 시간을 더한 것)가 주어진 cost limit과 time limit에 넘어가는지 확인한다.
    2. `if(cost[there] > nextC)` 이면, `cost` ,`times` 배열을 갱신하고 pq에 push한다.
    3. 2번에 해당하지 않고 `if( times[there] > nextT)` 이면, `times` 배열을 갱신하고 pq에 push한다.

</br>

## 후기💡

- priority_queue를 구조체로 쓰기 위해서는 cmp 클래스를 생성해서 연산자를 만들어야한다.. 
```cpp
struct way{
    int v, t,m; //정점, 시간, 돈
};
struct cmp{
    bool operator()(way a, way b){
        return a.m > b.m;
    }
};
priority_queue<way, vector<way>, cmp> pq;
```

- 시간만 신경쓰다가 돈도 범위 보다 작아야하는 경우를 처리해주지 않아서 틀렸다..
- 배열을 두개 쓸 생각을 못하고 한 정점에서 시간최소와 돈최소를 두개 넣어야하나 고민했는데, 다윤이가 푼 방법을 보고 참고했다~~~ 땡스투다윤
