# [SWEA]/[1953] 탈주범 검거

## *-BFS-*

* `int[][] tunnel`: 터널 구조물 타입 1 ~ 7 의 정보를 담은 배열. 방향별로 연결가능 하면 `1`, 불가능하면 `0`을 저장한다.

  ​							예) 터널 구조물 타입 1 : `{1, 1, 1, 1}` (순서는 시계방향으로 상/우/하/좌)

  ​														        타입 2: `{1, 0, 1, 0}`

  </br>

* `ArrayList<Integer>[] dir = new ArrayList[4]	`: 방향별로 연결가능하다면 그 자리에 올 수 있는 터널 구조물의 번호를 저장하는 배열. 방향은 idx 0: 상/ 1: 우/ 2: 하/ 3: 좌

  ```java
  static void initialdir() //dir배열 초기화
  {
  	for(int i=0; i<4; i++) {
  		dir[i] = new ArrayList<Integer>();
  	}
  	dir[0].add(1); dir[0].add(2); dir[0].add(5); dir[0].add(6);
  	dir[1].add(1); dir[1].add(3); dir[1].add(6); dir[1].add(7);
  	dir[2].add(1); dir[2].add(2); dir[2].add(4); dir[2].add(7);
  	dir[3].add(1); dir[3].add(3); dir[3].add(4); dir[3].add(5);		
  }
  ```

  </br>

* `int locCnt`: 탈주범이 위치할 수 있는 장소의 개수

* `void bfs(int[][] map, boolean[][] visited, int R, int C, int L)`

  : `bfs`로 `locCnt`를 계산한다.

  * `LinkedList<int []> q`: 현재 위치의 좌표(`r`, `c`)와 이때까지 걸린 시간 `currTime`을 저장한다.

  * `q`에서 하나씩 `poll`하면서 상하좌우 방향을 확인하는데, 현재 터널 구조물이 연결할 수 있는 방향에 있는 구조물과 연결이 가능한지 확인한 후, 가능하고 이전에 방문하지 않았던 곳이면 `q`에 `add`해준다.

    `currTime`을 하나씩 늘려가면서 `q`에 넣고, `currTime`이 `L`이면 `q`의 다음 요소확인, `q`의 크기가 `0`이면 마친다.

    ```java
    while(q.size() != 0) {
    	int[] tmp = q.poll();
    	r = tmp[0];
    	c = tmp[1];
    	currTime = tmp[2];
    	if(currTime == L)	continue;
    			
    	for(int m=0; m<4; m++) {
    		int rx = r + dx[m];
    		int ry = c + dy[m];
    		if(rx>=0 && rx<map.length && ry>=0 && ry<map[0].length) {
    			if(tunnel[map[r][c]][m]==1 && dir[m].contains(map[rx][ry])) {
    				if(!visited[rx][ry]) {
    					visited[rx][ry] = true;
    					locCnt++;
    					q.add(new int[] {rx, ry, (currTime+1)});
    				}
    			}
    		}
    	}
    }
    ```

    </br>

## :speaking_head:

먼저 터널 구조물 방향별로 규칙을 찾아놓고 푸니까 나름 수월했다

근데 처음엔 dfs로 풀려고 했는데 이렇게 되면 visited로 방문했던 곳은 다시 확인할 수가 없으니까 원래 결과값보다 작게 나올 수 밖에 없었다..!

조건은 그대로 하고 bfs로 푸니까 바로 풀 수 있었당

> 어느 정점에 도달하는 시간이 중요할때는 bfs로 !
>
> BFS 는 최단 시간을 찾는 문제에 더 많이 사용된다 !
