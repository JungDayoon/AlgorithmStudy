## 분류💁

위상정렬


## 접근법

- 수행하는 순서가 있기 때문에 위상 정렬 문제이다.


```cpp
while(!q.empty()){
        int here=q.front();
        q.pop();

        for(int j=0;j<adj[here].size();j++){
            int there=adj[here][j];
            inDegree[there]--;

            answer[there]=max(answer[there],answer[here]+times[there]);

            if(inDegree[there]==0)
                q.push(there);
        }
    }
```
- `answer[there]=max(answer[there],answer[here]+times[there]);`

- 실제로 중요한 역할을 하는 부분의 코드이다. 

- 현재 노드에서 다음 노드로 갈려고 할때, 그 다음 노드의 값과 현재 노드에 건설시간을 더한것의 크기를 비교하여 큰 값을 넣는다.


## 후기💡

- [2056] 작업 문제와 비슷한 것 같다.

- 처음에 topological sort로 순서를 정하고 DP로 배열에 저장하려했는데, 그렇게 하면 쓸모없는 메모리가 추가가 된다.

- 그렇기 때문에, 큐에 넣으면서 값을 저장해주면 된다.

