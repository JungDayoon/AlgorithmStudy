## 분류💁

위상정렬

## 접근법

1. N개의 문제를 모두 풀어야 함

2. 먼저 푸는 것이 좋은 문제가 있는 문제는 반드시 먼저 풀어야 한다

3. 가능하면 쉬운 문제부터 풀어야 한다.


## 우선순위큐를 이용한 위상정렬
```cpp
void topologicalSort(){
    priority_queue<int> q;

    for(int i=1;i<=N;i++)
        if(inDegree[i]==0)
            q.push(-i);
    
    while(!q.empty()){
        int here=-q.top();
        q.pop();
        cout<<here<<" ";

        for(int j=0;j<adj[here].size();j++){
            int there=adj[here][j];
            inDegree[there]--;

            if(inDegree[there]==0){
                q.push(-there);
            }
        }
    }
}
```


- 우선순위 큐를 사용하는 이유는 큐에 들어있는 문제 중 쉬운 문제부터 먼저 풀어야하기 때문이다

## 후기💡

- 위상정렬을 DFS로만 풀다가 새롭게 큐를 이용해서 풀었다. 처음엔 우선순위큐를 떠올리지 못해 일반 큐를 사용하고 계속 sort하는 방법을 했었다.

- 우선순위큐에 조금 더 익숙해지도록 하자




