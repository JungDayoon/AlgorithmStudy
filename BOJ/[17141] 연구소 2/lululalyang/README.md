# [BOJ]/[17141] 연구소 2

## *- BFS, Bruteforce -*

* `static ArrayList<VirusPos> AllVirusPos` : 바이러스를 놓을 수 있는 좌표를 저장하는 리스트

1. `void SelectVirusPos(int n, int r, int start, ArrayList<Integer> tmp)`

   : 바이러스를 놓을 수 있는 모든 자리 중 조합을 이용해 `M`개의 자리만  고른다.

   * `tmp` : 고른 자리의 index가 담겨있는 리스트

2. `void ComputeTime(ArrayList<Integer> tmp)`

   : 전달받은 바이러스의 위치에 바이러스를 놓고, 모두 전파되는데에 걸리는 시간을 구한다.

   * `int[][] visited` : 방문 여부 검사 & 해당 위치까지 감염되는데 걸리는 시간 저장 

     * `visited[i][j] == 0` : 방문하지 않은 것
     * `visited[i][j] == 1 이상의 정수` : (`i`, `j`)까지 전파되는 데에 해당 정수만큼 걸린다.
     * `visited[i][j] == -1` : 벽 

   * BFS 이용

     * bfs를 이용해 `visited`맵에 상태를 저장하고, 그 값의 최대값을 `max`에 저장한다.

       => `max`값이 전파되는데에 걸리는 최소시간이 된다.

       ​	(BFS는 최단 거리를 구함)

   * 현재 상태에서의 전파 최소시간 `max` 와 모든 경우의 전파 최소 시간 `res`중 작은 값으로 `res` 값을 갱신한다.

3. `res`가 최종 전파 최소 시간