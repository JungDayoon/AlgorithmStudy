# [BOJ]/[16947] 서울 지하철 2호선

## *- dfs, bfs -*

1. 주어지는 역 구간 정보를 인접리스트 `ArrayList<Integer>[] adj`에 저장한다.

   * 양방향이므로 양 쪽 역 모두에 추가해준다.

2. dfs를 이용해 순환 구간을 찾는다. - `int dfs(int now, int[] nextNode, boolean[] visited)`

   * `nextNode[i]` : `i`번 역 다음에 간 역 번호
   * `visited` : 방문 여부

   1. 현재 역 `now`의 방문여부 `visited`를 `true`처리한다.

   2. `now`와 연결된 역 `next`를 확인

      * `next`가 바로 이전 역이 아닐 때, (=> `nextNode[next] != now`)

        * 방문하지 않은 역이라면, 

          `nextNode[now]`를 `next`로 설정하고, `next`로 `dfs()`를 호출한다.

          => 리턴값이 `-1`이 아니라면, 즉 사이클을 찾았다면 해당 리턴값을 리턴.

        * 방문한 역이라면,

          사이클을 찾은 것으로 `nextNode[now]`를 `next`로 설정해주고 `next`를 리턴해준다.

   3. 연결된 역을 모두 확인한 후에도 사이클을 찾지 못했다면, `visited[now]`를 `false`처리하고, `-1`을 리턴한다.

3. 2번 과정에서 얻는 `last`값, 즉 사이클의 시작 역으로 `cycle`에 속하는 역을 구한다. - `void ChkCycle(int now, boolean[] cycle, int[] nextNode)`

   * `cycle[i]` : `i`번 역의 사이클 여부 (`true`면 순환선에 속함)

   * dfs를 이용한다.

4. bfs를 이용해 각 역과 순환선 사이의 거리를 구한다. -`int bfs(int v, int N, boolean[] cycle)`

</br>

## :speaking_head:

순환선을 어떻게 구할지 생각하는데 너무 오래 걸렸다 ㅠ 요새 문제풀때 집중이 잘안된답 ,, 집중하좌 !!!!



