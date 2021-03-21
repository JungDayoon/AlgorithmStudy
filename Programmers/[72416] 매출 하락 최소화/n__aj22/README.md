# Programmers 72416번 : 매출 하락 최소화

## Algorithm

Tree, DP (트리에서의 DP)

## Description

### **변수**
`graph` : link 정보를 이용해 연결된 노드를 저장하고 있는 map 타입 변수

`visited` : 트리 모양으로 탐색하기 위해 DFS를 하는데 방문여부를 확인하기 위해 필요한 Boolean 리스트

`dp` : 각 노드 별로 [0,0]을 갖는다. dp 값을 저장하기 위해 필요한 변수
+ dp[i][0] : 현재 위치 i node 직원이 워크샵을 참여하게 될 경우 최소 매출 손실값

+ dp[i][1] : 현재 위치 i node 직원이 워크샵을 참여하지 않게 될 경우 최소 매출 손실값
### **함수**

**`solution(sales, links)`** : 결과 출력 함수

1. init 함수를 호출해, 직원 수만큼 graph, visited, dp를 초기화한다.

2. links의 edge정보를 이용해 graph에 저장한다.

+ 입력된 links가 다음과 같다
    ```python
    links = [[10, 8], [1, 9], [9, 7], [5, 4], [1, 5], [5, 10], [10, 6], [1, 3], [10, 2]]
    ```
+ graph에는 아래와 같이 저장된다.
    ```python
    ###안에 저장된 정보
    graph = 
    {
        1: [9, 5, 3], 
        2: [10], 
        3: [1], 
        4: [5], 
        5: [4, 1, 10], 
        6: [10], 
        7: [9], 
        8: [10], 
        9: [1, 7], 
        10: [8, 5, 6, 2]
    }
    ```
3. root는 무조건 1이기 때문에 1부터 시작해서 DFS로 트리를 탐색한다.
+ 탐색 하며, 각 위치의 dp 값이 저장된다.

4. 호출 후 정답은 dp[1]에 저장된 [a, b] 두 값의 최소값이다.

**`find(node, sales)`** : 현재 노드번호 node와 sales 정보를 이용해 node의 최소 매출 손실값을 구한다. DFS, DP

1. dp[node][0], 즉 **현재 node 직원이 워크샵을 참여하게 될 경우 최소 매출 손실값**

+ 현재 node가 팀장이 될 수 없다면(기저사례), leaf node이므로 dp 값은 다음과 같다.

    + dp[node][0] = sales[node] #현재 직원의 매출과 같다.

+ 현재 node가 팀장이 될 수 있다면 해당 팀의 팀원들의 dp 정보를 이용해 dp[node]를 찾는다.

    + 동일한 팀에 1명 이상의 직원이 참여할 수 있으므로, 현재 node가 팀장으로 있는 팀의 일반 직원들은 참여해도 되고, 안해도 된다.

    + 따라서 child 노드들의 min(dp[child][0], dp[child][1]) 값의 합을 구한다.(각 팀원들의 0, 1 값 중 작은 값을 선택해서 더하면 됨)

    + <span style="color:red">**주의**</span>  : child의 dp 값을 이용해야 하기 때문에 사용 전에 `find(child, sales)`를 호출해야한다.


2. dp[node][1], 즉 **현재 node 직원이 워크샵을 참여하지 않게 될 경우 최소 매출 손실값**

+ 현재 node가 팀장이 될 수 없다면(기저사례), leaf node이므로 dp 값은 다음과 같다.

    + dp[node][1] = 0 #현재 직원이 참여하지 않으므로 손해 없음

+ 현재 node가 팀장이 될 수 있다면 해당 팀의 팀원들의 dp 정보를 이용해 dp[node]를 찾는다.

    + 팀장이 참여하지 않게 되면 팀장 아래의 직원들 중 한명 이상이 무조건 참여해야 한다.

    + 따라서 child 노드들의 조합 중, 1명이 무조건 참여하게 되는 경우의 합의 최소값을 구한다.

        ```python
        #child_key에는 child의 번호가 있음
        for i in range(1, len(child_key)):
            combs = list(combinations(child_key, i))
            for comb in combs:
                check_child = 0
                for key in child_key:
                    if key in comb:
                        check_child+=dp[key][1]
                    else:
                        check_child+=dp[key][0]
                min_child = min(min_child, check_child)
        
        ```

    + <span style="color:red">**주의**</span>  : child의 dp 값을 이용해야 하기 때문에 사용 전에 `find(child, sales)`를 호출해야한다.


## Review

어려웡~