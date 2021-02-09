# [11000] 강의실 배정
## 분류💁

우선순위 큐

</br>

## 접근법

>  최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다. 최소 강의실의 개수를 구해야 한다.
- O(nlogn)의 시간복잡도를 가지는 우선순위큐를 사용해야한다.


## 로직
1. 입력받은 강의시간을 시작 시간을 기준으로 정렬한다.
2. 차례대로 강의시간을 Priority_queue의 끝나는 시간을 비교하며 push한다.
    - priority_queue의 한 원소는 강의실을 나타내는 것이다.
    - pq의 top() 즉, 끝나는 시간이 가장 짧은 강의실과 새로운 강의의 시작시간을 비교한다.
    - 끝나는 시간보다 시작하는 시간이 빠른 경우, cnt++을 하면서 pq에 새롭게 push한다.

### priority_queue 사용
특정 키값으로 정렬되는 우선순위큐 사용하기
```cpp
struct cmp{
    bool operator()(pair<int,int> a, pair<int,int> b){
        return a.second> b.second;
    }
};
priority_queue<pair<int,int>, vector<pair<int,int>> ,cmp> pq;
```

</br>

## 후기💡
- 우선순위큐를 사용하는게 익숙하지 않아서 떠올리기 힘들었다.ㅜㅜ