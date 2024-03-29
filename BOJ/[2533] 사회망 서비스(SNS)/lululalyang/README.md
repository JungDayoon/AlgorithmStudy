# [BOJ]/[2533] 사회망 서비스(SNS)

## *- TreeDP -*

* `ArrayList<Integer>[] adj` : 인접리스트

* `ArrayList<Integer>[] tree` : `adj`를 이용해 루트노드가 `1`인 트리를 만든다

* `int[][] dp` 

  * `dp[i][0]` : `i`를 루트로 하는 서브트리에서 **`i`가 얼리어답터가 아닐 때**, 필요한 최소 얼리어답터 수

  * `dp[i][1]` : `i`를 루트로 하는 서브트리에서 **`i`가 얼리어답터일 때**, 필요한 최소 얼리어답터 수

#### solution

* `dp[i][0]` : `i`가 얼리어답터가 아닐 때
  * 얼리어답터가 아닌 사람은 <u>자신의 모든 친구들이 얼리어답터일 때</u>만 아이디어를 받아들일 수 있으므로, 자식노드 모두가 얼리어답터여야한다.
  * `dp[i][0] = SUM(dp[k][1])` (`k`는 `i`의 자식노드)

* `dp[i][1]` : `i`가 얼리어답터 일 때
  * 자식노드들은 얼리어답터여도되고, 아니여도 된다.
  * 필요한 얼리어답터 수의 최솟값을 구하고 있으므로 둘 중 작은 값을 더해준다.
  * `dp[i][1] += SUM(MIN(dp[k][0], dp[k][1])) + 1` (`k`는 `i`의 자식노드, `1`은 자기자신이 얼리어답터이므로 더해준다)
* **DFS** 사용

