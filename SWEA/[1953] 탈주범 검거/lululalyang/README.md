# [SWEA]/[1953] 탈주범 검거

## *- BFS -*

* `int[][] tunnel`: 7종류의 터널 구조물의 정보를 담은 배열.

  ​							해당 터널 구조물이 상하좌우 중 연결가능한 방향은 1, 불가능한 방향은 0으로 저장한다.

  ​							예) 터널 구조물 타입 1 : `{1, 1, 1, ,1}`  (순서는 시계방향으로. 상 -> 우 -> 하 -> 좌 )

  ​														타입 2 : `{1, 0, 1, 0}` 

  </br>

* `ArrayList<Integer>[] dir`: 하나의 터널 구조물이 상/우/하/좌 방향별로 연결이 가능하다면 그 위치에 올 수 있는 터널 구조물의 타입 번호를 저장해주는 배열이다.

  ```java
  static void initialdir() //dir 배열 초기화
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

* `int locCnt` : 탈주범이 위치할 수 있는 장소의 개수

* `boolean[][] visited`: 해당 위치 `locCnt`에 포함 여부를 담은 배열.

* ` void bfs(int[][] map, boolean[][] visited, int R, int C, int L)`

  : `BFS`로 `locCnt`계산.

  * `LinkedList<int []> q `: 큐에는 현재의 위치 좌표와 이때까지 걸린 시간을 저장한다.

  * 현재 위치에서 상하좌우를 확인하는데, 터널구조물이 연결할 수 있는 방향에 있는 구조물이 현재의 구조물과 연결될 수 있는지 확인하고, 연결가능하고 그위치가 방문하지 않은 곳이면 `q`에 `add`해준다.

    `currTime`을 하나씩 늘려가면서 `q`에 넣고, `currTime`이 `L`이면 다음 큐를 확인하고, `q`의 크기가 `0`이면 종료한다.

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

방향별로 규칙을 먼저 찾아놓고 하니까 나름 풀기 수월했다.

근데 처음에는 무작정 visited배열을 사용해서 DFS로 풀려고 했는데 이렇게 되면 방문했던 곳은 다시 못가게 되니까 원래 결과값보다 작게 나올 수 밖에 없었다.

이전에 방문했던 곳만 q에 넣어주지 않으면 되니까 BFS로 풀어야했다..!

>어느 정점에 도달하는 시간이 중요할때는 BFS로 !
>
>BFS는 최단 시간을 찾는 문제에 많이 쓰인다 !



