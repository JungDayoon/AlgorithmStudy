# [SWEA]/[1949] 등산로 조성

## - dfs & Backtracking - 

* `ArrayList<int []> maxLoc` 

  : 지형높이 정보를 입력받아 `map`에 저장하면서 최대값을 가지는 x,y좌표를 `maxLoc`에 저장한다.

* ***in `Main`***

  * `maxLoc`를 돌면서 각 좌표를 시작점으로 `dfs()`호출.

  * 함수 호출 전 `map`과 `visited`를 초기화해준다.

    ```java
    for(int[] tmp : maxLoc) {
    	Initialmap(tmpMap, map);
    	visited[tmp[0]][tmp[1]] = true;
    	dfs(tmpMap, visited, tmp[0], tmp[1], 1, 0);
    	visited = new boolean[N][N];
    }
    ```

    </br>

* `void dfs(int[][] map, boolean[][] visited, int x, int y, int len, int YorN)`

  * `int len`: 이때까지 조성한 등산로의 길이

  * `int YorN`: 이전에 지형을 깎았으면 `1`, 깎지 않았으면 `0`

  * 현재 위치에서 상/하/좌/우의 좌표를 확인한다.

    * 범위내에 있고 방문하지 않았을 때,

      * 이전보다 낮은 지형이라면 `len+1`해주고 `dfs`호출

      * 이전과 높이가 같고, 아직 깎지 않았다면 `1`만큼 깎아주고 `len+1`로 `dfs`호출

      * 이전보다 높은 지형이고, 아직 깎지 않았는데 높이차이(`diff`)가 `K`보다 작으면 `diff+1`만큼 깎고 `dfs`호출. 

        `diff`가 `K`보다 크면 등산로 조성을 끝내고 그때의 `len`과 `maxLen`중 최대값을 `maxLen`을 설정.

      * 높이가 낮지 않은데 이전에 깎았다면 => 더이상 깎을 수 없음(깎을 기회 1번) => 등산로 조성을 끝내고 그때의 `len`으로 `maxLen`을 구한다.

    * 범위 밖이거나 방문했던 곳일때,

      등산로 조성을 끝내고 그때의 `len`을 `maxLen`구함.

  * 모든 과정에서 `map`과 `visited`값을 바꾸고 `dfs()`를 호출했다면 그 뒤에 다시 원래대로 바꿔줘야 함! 

    (다음 등산로 조성에 이번 등산로 조성이 영향을 미치면 안되기 때문)

    </br>

## :speaking_head:

또 ,, 처음에 bfs로 풀다가 `map`값과 `visited`값이 각 위치마다 달라져야되는 걸 알고나서 dfs로 바꿔서 풀었다 ㅜ 

왜 매번 처음에 풀려고 한 방법이 아니라 딴 방법인지  ... 이제 문제 잘 읽고 코딩하기 전에 이렇게 하면 풀릴지! 막히는 부분은 없을지! 잘 확인하고 시작해야겠다 ...ㅜ

