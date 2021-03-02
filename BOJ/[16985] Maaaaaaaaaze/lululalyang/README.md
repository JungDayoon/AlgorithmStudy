# [BOJ]/[16985] Maaaaaaaaaze

## *- BFS, BruteForce -*

* `ArrayList<int[][]> AllPlate` : 입력받은 각 판을 저장

1. `void AdjustPlate(int nowIdx, ArrayList<int[][]> tmp)`

   : 각 판을 회전시켜서 나올 수 있는 모든 조합을 만들어낸다.

   * `int[][] RotatePlate(int[][] nowPlate, int d)`

     : 회전 방향 `d`에 따라 `nowPlate`를 회전시킨 결과값을 return

   * return받은 배열을 `tmp`의 현재 Index에 맞게 `add()`

   * 5개의 판 모두 다 `tmp`에 담았다면 다음 단계를 진행한다.

2. `void SetPlateOrder(ArrayList<int[][]> tmp, int[] order, boolean[] visited, int depth)`

   : 앞에서 정해진 회전된 판 다섯 개의 순서를 정한다.

   * 순열 이용 (`nPr = 5P5`)

   * `order`배열에 순서를 저장한다.

     >* 순열
     >
     >```java
     >private static void Perm(int[] output, boolean[] visited, int depth, int n, int r) { 
     >	if(depth == r) {
     >		for (int i = 0; i < r; i++)
     >            System.out.print(output[i] + " ");
     >        System.out.println();
     >        return;
     >	}
     >		
     >	for(int i=0; i<n; i++) {
     >		if(visited[i] != true) {
     >			visited[i] = true;
     >			output[depth] = i;
     >			Perm(output, visited, depth+1, n, r);
     >			visited[i] = false; // 다시 돌려줌
     >		}
     >	}
     >}
     >```

   * 순열로 만들어진 순서에 맞게 다섯개의 판을 `int[][][] map`으로 옮긴다. (`void tmpTomapByorder(int[][][] map, ArrayList<int[][]> tmp, int[] order)`)
   * 만들어진 `map`배열로 다음 단계를 진행한다.

3. `void ComputeMoveCnt(int[][][] map)`

   : 위 꼭짓점 네 개의 입구에서 아래 꼭짓점 네 개의 출구로 이동하는 횟수를 확인한다.

    * `int[][][] EE = {{{0, 0, 0}, {0, 0, 4}, {0, 4, 0}, {0, 4, 4}},
      			   {{4, 4, 4}, {4, 4, 0}, {4, 0, 4}, {4, 0, 0}}};`

      : 입구와 출구 정보 (`x` 인덱스에 따라 입출구 한 쌍)

   * BFS 사용

     * `int Maze(int[][][] map, Queue<Loc> q, boolean[][][] visited, int destX, int destY, int destZ)`

       : BFS를 이용해 출구까지의 이동 횟수를 구해 return한다.

## :speaking_head:

* 수행해야되는 작업이 많아서 시간초과가 날 것 같았는데 그래도 통과됐다! 해줘야하는 처리가 많아서 그렇지 하나하나 나눠서 처리해주니까 괜찮았다. 
* 이런 문제는 빨리 이해해서 빨리 코드로 옮기려고 해보자 !! 